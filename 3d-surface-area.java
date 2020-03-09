import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

   // Complete the surfaceArea function below.
   static int surfaceArea(int[][] A)
   {
      class Model
      {
         final List<Integer> heights = Stream.of(A)
            .flatMap(row -> IntStream.of(row).boxed())
            .collect(Collectors.toList());
         final int lh = heights.stream()
            .max(Integer::compareTo)
            .orElseThrow(IllegalStateException::new);
         final int lx = A[0].length;
         final int ly = A.length;

         int i(int x, int y)
         {
            return x + y * lx;
         }

         // Returns true if voxel is filled
         boolean filled(int x, int y, int h)
         {
            // Return false if outside boundary
            if (x < 0 || y < 0 || x >= lx || y >= ly) return false;

            return heights.get(i(x, y)) >= h;
         }
      }

      Model m = new Model();

      // Surfaces parallel to x-axis
      int ax = IntStream.range(0, m.lx)
         .map(x -> IntStream.rangeClosed(0, m.ly)
            .map(y -> IntStream.rangeClosed(1, m.lh)
               .map(h -> m.filled(x, y, h) != m.filled(x, y - 1, h) ? 1 : 0)
               .sum())
            .sum())
         .sum();

      // Surfaces parallel to y-axis
      int ay = IntStream.rangeClosed(0, m.lx)
         .map(x -> IntStream.range(0, m.ly)
            .map(y -> IntStream.rangeClosed(1, m.lh)
               .map(h -> m.filled(x, y, h) != m.filled(x - 1, y, h) ? 1 : 0)
               .sum())
            .sum())
         .sum();

      // Surfaces parallel to h-axis
      int ah = m.lx * m.ly * 2;

      return ax + ay + ah;
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      String[] HW = scanner.nextLine().split(" ");

      int H = Integer.parseInt(HW[0]);

      int W = Integer.parseInt(HW[1]);

      int[][] A = new int[H][W];

      for (int i = 0; i < H; i++) {
         String[] ARowItems = scanner.nextLine().split(" ");
         scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

         for (int j = 0; j < W; j++) {
            int AItem = Integer.parseInt(ARowItems[j]);
            A[i][j] = AItem;
         }
      }

      int result = surfaceArea(A);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
