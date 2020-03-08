import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.*;

public class Solution {

   // Complete the serviceLane function below.
   static List<Integer> serviceLane(int[] width, int[][] cases) {
      return Stream.of(cases)
         .map(c -> serviceLane(width, c[0], c[1]))
         .collect(Collectors.toList());
   }

   private static int serviceLane(int[] width, int i, int j)
   {
      return Arrays.stream(width, i, j + 1)
         .min()
         .getAsInt();
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      String[] nt = scanner.nextLine().split(" ");

      int n = Integer.parseInt(nt[0]);

      int t = Integer.parseInt(nt[1]);

      int[] width = new int[n];

      String[] widthItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < n; i++) {
         int widthItem = Integer.parseInt(widthItems[i]);
         width[i] = widthItem;
      }

      int[][] cases = new int[t][2];

      for (int i = 0; i < t; i++) {
         String[] casesRowItems = scanner.nextLine().split(" ");
         scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

         for (int j = 0; j < 2; j++) {
            int casesItem = Integer.parseInt(casesRowItems[j]);
            cases[i][j] = casesItem;
         }
      }

      List<Integer> result = serviceLane(width, cases);

      for (int i = 0; i < result.size(); i++) {
         bufferedWriter.write(String.valueOf(result.get(i)));

         if (i != result.size() - 1) {
            bufferedWriter.write("\n");
         }
      }

      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
