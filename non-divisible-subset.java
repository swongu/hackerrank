import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'nonDivisibleSubset' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY s
     */

   public static int nonDivisibleSubset(int k, List<Integer> s) {
      // Group integers into their mod equivalents
      int[] mod = new int[k];
      s.forEach(i -> mod[i % k]++);

      // For each mod group, include in subset the largest of its complement
      // Exclude 0 and k/2 if k is even
      // E.g., k = 4 -> exclude 0, 2 / include 1
      // E.g., k = 5 -> exclude 0    / include 1 2
      int size = 0;
      for (int i = 1; i < (k + 1) / 2; ++i)
      {
         size += Math.max(mod[i], mod[k - i]);
      }

      // Include at most 1 from mod 0, and if even, mod k/2
      size += Math.min(1, mod[0]);
      if (k % 2 == 0) size += Math.min(1, mod[k / 2]);

      return size;
   }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.nonDivisibleSubset(k, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
