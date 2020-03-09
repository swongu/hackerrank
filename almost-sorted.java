import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution {

   // Complete the almostSorted function below.
   static String almostSorted(int[] arr)
   {
      int[] sorted = IntStream.of(arr).sorted().toArray();

      // Extract difference between input and sorted arrays
      int[] diff = IntStream.range(0, arr.length)
         .filter(i -> arr[i] != sorted[i])
         .toArray();

      if (diff.length == 0)
      {
         return "yes";
      }
      else if (diff.length == 2)
      {
         return "yes\nswap " + (diff[0] + 1) + " " + (diff[1] + 1);
      }
      else if (isReverse(arr, sorted, diff[0], diff[diff.length - 1]))
      {
         return "yes\nreverse " + (diff[0] + 1) + " " + (diff[diff.length - 1] + 1);
      }
      else
      {
         return "no";
      }
   }

   private static boolean isReverse(int[] arr, int[] sorted, int l, int r)
   {
      return IntStream.rangeClosed(0, r - l).allMatch(i -> arr[l + i] == sorted[r - i]);
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) {
      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      int[] arr = new int[n];

      String[] arrItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int i = 0; i < n; i++) {
         int arrItem = Integer.parseInt(arrItems[i]);
         arr[i] = arrItem;
      }

      System.out.println(almostSorted(arr));

      scanner.close();
   }
}
