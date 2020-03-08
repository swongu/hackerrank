import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.*;

import static java.util.stream.Collectors.*;

public class Solution {

   // Complete the cutTheSticks function below.
   static List<Integer> cutTheSticks(List<Integer> list) {
      // Key: length of stick, Value: number of sticks of this length
      Map<Integer, Long> sizes = list.stream().collect(groupingBy(i -> i, counting()));

      AtomicInteger remain = new AtomicInteger(list.size());

      return sizes.keySet().stream().sorted()   // Length of sticks 
         .map(sizes::get)                       // Number of sticks ordered by length
         .map(i -> remain.getAndAdd((int)-i))   // Subtract from remaining
         .collect(toList());
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      List<Integer> list = new ArrayList<>();

      String[] arrItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < n; i++) {
         int arrItem = Integer.parseInt(arrItems[i]);
         list.add(arrItem);
      }

      List<Integer> result = cutTheSticks(list);

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
