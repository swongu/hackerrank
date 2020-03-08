import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.IntStream;

public class Solution {

   // Complete the permutationEquation function below.
   static int[] permutationEquation(int[] p) {
      int[] r = reverseMapping(p);
      return IntStream.range(0, p.length)
         .map(i -> r[r[i]] + 1) // Back to 1-based index
         .toArray();
   }
   
   // Compute reverse mapping with 0-based index
   private static int[] reverseMapping(int[] p)
   {
      int[] r = new int[p.length];

      for (int i = 0; i < p.length; ++i)
      {
         r[p[i] - 1] = i;
      }

      return r;
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] p = new int[n];

      String[] pItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < n; i++) {
         int pItem = Integer.parseInt(pItems[i]);
         p[i] = pItem;
      }

      int[] result = permutationEquation(p);

      for (int i = 0; i < result.length; i++) {
         bufferedWriter.write(String.valueOf(result[i]));

         if (i != result.length - 1) {
            bufferedWriter.write("\n");
         }
      }

      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
