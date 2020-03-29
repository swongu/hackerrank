import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.IntStream;

public class Solution {

   // Complete the breakingRecords function below.
   static int[] breakingRecords(int[] scores) 
   {
      int[] m = {scores[0], scores[0]};
      int[] b = {0, 0};

      IntStream.range(1, scores.length)
         .forEachOrdered(i -> 
         {
            if (scores[i] > m[0])
            {
               m[1] = scores[i];
               ++b[0];
            }
            
            if (scores[i] < m[1])
            {
               m[1] = scores[i];
               ++b[1];
            }
         });
      
      return b;
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] scores = new int[n];

      String[] scoresItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < n; i++) {
         int scoresItem = Integer.parseInt(scoresItems[i]);
         scores[i] = scoresItem;
      }

      int[] result = breakingRecords(scores);

      for (int i = 0; i < result.length; i++) {
         bufferedWriter.write(String.valueOf(result[i]));

         if (i != result.length - 1) {
            bufferedWriter.write(" ");
         }
      }

      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
