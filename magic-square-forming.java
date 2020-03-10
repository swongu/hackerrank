import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

   // Complete the formingMagicSquare function below.
   static int formingMagicSquare(int[][] s)
   {
      // There are only 4 * 2 = 8 degrees of freedom:
      // * Place 1 in any of the middle spaces; four spaces
      // * Place 3 in opposing row or column; two spaces

      class Square
      {
         int[] s;

         Square(int t1, int t3)
         {
            s = new int[9];
            int p5 = 4; s[p5] = 5;
            int p1 = 2 * t1 + 1; s[p1] = 1;
            int p3 = getP3(p1, p5, t3); s[p3] = 3;
            int p7 = sameLine(p3, p5); s[p7] = 7;
            int p9 = sameLine(p1, p5); s[p9] = 9;
            int p8 = sameBox(p5, p1, p3); s[p8] = 8;
            int p2 = sameBox(p5, p7, p9); s[p2] = 2;
            int p4 = sameBox(p5, p3, p9); s[p4] = 4;
            int p6 = sameBox(p5, p1, p7); s[p6] = 6;
         }

         int i(int x, int y)
         {
            return x + y * 3;
         }

         int x(int i)
         {
            return i % 3;
         }

         int y(int i)
         {
            return i / 3;
         }

         int getP3(int p1, int p5, int t3)
         {
            return (x(p1) == x(p5))
               ? i((x(p5) + t3 + 1) % 3, y(p5))  // Same y-column
               : i(x(p5), (y(p5) + t3 + 1) % 3); // Same x-column
         }

         int sameLine(int a, int b)
         {
            return (y(a) == y(b))
               ? i(3 - x(a) - x(b), y(a))  // a & b in same row
               : i(x(a), 3 - y(a) - y(b)); // a & b in same column
         }

         int sameBox(int a, int b, int c)
         {
            int d = i(x(b), y(c));
            return (a == d) ? i(x(c), y(b)) : d;
         }

         int diff(int[][] t)
         {
            System.err.println(IntStream.of(s).boxed().map(Object::toString).collect(Collectors.joining()));
            return IntStream.range(0, 9).map(i -> Math.abs(s[i] - t[y(i)][x(i)])).sum();
         }
      }

      return IntStream.range(0, 4)
         .map(i -> IntStream.range(0, 2)
            .map(j -> new Square(i, j).diff(s))
            .min().orElse(0))
         .min().orElse(0);
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      int[][] s = new int[3][3];

      for (int i = 0; i < 3; i++) {
         String[] sRowItems = scanner.nextLine().split(" ");
         scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

         for (int j = 0; j < 3; j++) {
            int sItem = Integer.parseInt(sRowItems[j]);
            s[i][j] = sItem;
         }
      }

      int result = formingMagicSquare(s);

      bufferedWriter.write(String.valueOf(result));
      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
