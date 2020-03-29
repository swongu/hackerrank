import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution
{

   // Complete the stones function below.
   static int[] stones(int n, int a, int b)
   {
      return IntStream.rangeClosed(0, n - 1)
         .map(i -> i*a + (n - 1 - i)*b)
         .sorted()
         .distinct()
         .toArray();
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int T = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int TItr = 0; TItr < T; TItr++) {
         int n = scanner.nextInt();
         scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

         int a = scanner.nextInt();
         scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

         int b = scanner.nextInt();
         scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

         int[] result = stones(n, a, b);

         for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
               bufferedWriter.write(" ");
            }
         }

         bufferedWriter.newLine();
      }

      bufferedWriter.close();

      scanner.close();
   }
}
