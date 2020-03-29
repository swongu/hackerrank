import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution
{

   // Complete the minimumDistances function below.
   static int minimumDistances(int[] a)
   {
      // Groups indices [0, a.length) by element type
      Map<Integer, List<Integer>> group = IntStream.range(0, a.length)
         .boxed()
         .collect(Collectors.groupingBy(i -> a[i]));

      return group.values().stream()
         .filter(list -> list.size() >= 2)                  // Filter lists of size 2 or greater
         .map(list -> IntStream.range(1, list.size())       // Find minimum of distances of indexes...
            .map(i -> list.get(i) - list.get(i - 1))
            .min().orElse(Integer.MAX_VALUE))
         .min(Integer::compare).orElse(-1);                 // ... over all groups
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] a = new int[n];

      String[] aItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < n; i++) {
         int aItem = Integer.parseInt(aItems[i]);
         a[i] = aItem;
      }

      int result = minimumDistances(a);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
