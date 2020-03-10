import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

   /*
    * Complete the 'getTotalX' function below.
    *
    * The function is expected to return an INTEGER.
    * The function accepts following parameters:
    *  1. INTEGER_ARRAY a
    *  2. INTEGER_ARRAY b
    */

   public static int getTotalX(List<Integer> a, List<Integer> b) 
   {
      return (int)IntStream.rangeClosed(1, 100)
         .filter(i -> a.stream().allMatch(it -> i % it == 0))
         .filter(i -> b.stream().allMatch(it -> it % i == 0))
         .count();
   }

}

public class Solution {
   public static void main(String[] args) throws IOException {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

      int n = Integer.parseInt(firstMultipleInput[0]);

      int m = Integer.parseInt(firstMultipleInput[1]);

      List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
         .map(Integer::parseInt)
         .collect(toList());

      List<Integer> brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
         .map(Integer::parseInt)
         .collect(toList());

      int total = Result.getTotalX(arr, brr);

      bufferedWriter.write(String.valueOf(total));
      bufferedWriter.newLine();

      bufferedReader.close();
      bufferedWriter.close();
   }
}
