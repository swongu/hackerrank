import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution
{
   enum CellType
   {
      Good, Occupied, Bad;

      static CellType toCellType(int x)
      {
         switch ((char)x)
         {
            case 'G': return CellType.Good;
            case 'B': return CellType.Bad;
            default:  throw new IllegalArgumentException();
         }
      }
   }

   static int MAX_ARM = 7;

   // Complete the twoPluses function below.
   static int twoPluses(String[] grid)
   {
      final class Grid
      {
         final int n = grid.length;
         final int m = grid[0].length();
         final List<CellType> cells = Arrays.stream(grid)
            .flatMap(row -> row.chars().boxed())
            .map(CellType::toCellType)
            .collect(Collectors.toList());

         int i(int x, int y)
         {
            return x + y * m;
         }

         int x(int i)
         {
            return i % m;
         }

         int y(int i)
         {
            return i / m;
         }

         int area(int arm)
         {
            return 1 + 4 * arm;
         }

         // Returns stream of indices for a closed box
         IntStream grid(int x0, int y0, int x1, int y1)
         {
            return IntStream.range(x0, x1)
               .flatMap(x -> IntStream.range(y0, y1)
                  .map(y -> i(x, y)));
         }

         // Return stream of indices for a plus
         IntStream plus(int l, int x0, int y0)
         {
            return IntStream.concat(
               IntStream.rangeClosed(x0 - l, x0 + l)
                  .map(x -> i(x, y0)),
               IntStream.rangeClosed(y0 - l, y0 + l)
                  .map(y -> i(x0, y))
            );
         }

         // Returns true if two crosses can be fit into grid
         boolean fitPlus(int l1, int l2)
         {
            return grid(l1, l1, m - l1, n - l1)
               .filter(i1 -> fitPlus(l1, x(i1), y(i1), cells))
               .anyMatch(i1 -> {
                  List<CellType> fitted = applyPlus(l1, x(i1), y(i1));
                  return grid(l2, l2, m - l2, n - l2)
                     .anyMatch(i2 -> fitPlus(l2, x(i2), y(i2), fitted));
               });
         }

         boolean fitPlus(int l, int x0, int y0, List<CellType> cells)
         {
            return plus(l, x0, y0).allMatch(i -> cells.get(i).equals(CellType.Good));
         }

         // Add a plus of arm length l to grid centered at position (x, y)
         List<CellType> applyPlus(int l, int x0, int y0)
         {
            List<CellType> output = new ArrayList<>(cells);
            plus(l, x0, y0).forEach(i -> output.set(i, CellType.Occupied));
            return output;
         }
      }

      Grid g = new Grid();

      int max = 0;
      for (int i = MAX_ARM; i >= 0; --i)
      {
         for (int j = MAX_ARM; j >= 0; --j)
         {
            if (max > g.area(i) * g.area(j)) continue;

            boolean fit = g.fitPlus(i, j);
            if (fit) max = g.area(i) * g.area(j);
         }
      }

      return max;
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      String[] nm = scanner.nextLine().split(" ");

      int n = Integer.parseInt(nm[0]);

      int m = Integer.parseInt(nm[1]);

      String[] grid = new String[n];

      for (int i = 0; i < n; i++) {
         String gridItem = scanner.nextLine();
         grid[i] = gridItem;
      }

      int result = twoPluses(grid);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
