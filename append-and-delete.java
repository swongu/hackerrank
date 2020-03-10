import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.IntStream;

public class Solution {

   // Complete the appendAndDelete function below.
   static String appendAndDelete(String s, String t, int k)
   {
      // Short-circuit if number of characters is less than k
      if (s.length() + t.length() < k) return "Yes";

      // Find first difference
      int index = IntStream.range(0, Math.max(s.length(), t.length()))
         .filter(i -> s.length() <= i || t.length() <= i || s.charAt(i) != t.charAt(i))
         .findFirst().orElse(s.length());
      
      // Delete all characters after this index
      int ops = (s.length() - index) + (t.length() - index);

      // Exactly k operations means ops + 2 * (redundant add/remove)
      return (ops <= k && (k - ops) % 2 == 0) ? "Yes" : "No";
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      String s = scanner.nextLine();

      String t = scanner.nextLine();

      int k = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      String result = appendAndDelete(s, t, k);

      bufferedWriter.write(result);
      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
