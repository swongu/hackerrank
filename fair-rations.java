import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution {

   // Complete the fairRations function below.
   static String fairRations(int[] B) {
      int[] odds = IntStream.range(0, B.length)
         .filter(i -> B[i] % 2 == 1)
         .toArray();

      if (odds.length % 2 == 1) return "NO";
      
      int bread = 0;
      for (int i = 0; i + 1 < odds.length; i += 2)
      {
         bread += 2 * (odds[i+1] - odds[i]);
      }
      
      return String.valueOf(bread);
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int N = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] B = new int[N];

      String[] BItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < N; i++) {
         int BItem = Integer.parseInt(BItems[i]);
         B[i] = BItem;
      }

      bufferedWriter.write(fairRations(B));
      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
