import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.*;

public class Solution {

    // Complete the queensAttack function below.
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        Map<Obstacle.Type, Optional<Obstacle>> map = Stream.of(obstacles)
            .flatMap(pos -> toObstacle(n, r_q, c_q, pos[0], pos[1]))
            .collect(Collectors.groupingBy(
                o -> o.type,
                Collectors.minBy(Comparator.comparingInt(o -> o.squares))
            ));

        int total =
            map.getOrDefault(Obstacle.Type.N,  Optional.empty()).map(o -> o.squares).orElse(n - c_q) +
            map.getOrDefault(Obstacle.Type.NE, Optional.empty()).map(o -> o.squares).orElse(Math.min(n - c_q, n - r_q)) + 
            map.getOrDefault(Obstacle.Type.E,  Optional.empty()).map(o -> o.squares).orElse(n - r_q) +
            map.getOrDefault(Obstacle.Type.SE, Optional.empty()).map(o -> o.squares).orElse(Math.min(n - r_q, c_q - 1)) +
            map.getOrDefault(Obstacle.Type.S,  Optional.empty()).map(o -> o.squares).orElse(c_q - 1) +
            map.getOrDefault(Obstacle.Type.SW, Optional.empty()).map(o -> o.squares).orElse(Math.min(c_q - 1, r_q - 1)) + 
            map.getOrDefault(Obstacle.Type.W,  Optional.empty()).map(o -> o.squares).orElse(r_q - 1) +
            map.getOrDefault(Obstacle.Type.NW, Optional.empty()).map(o -> o.squares).orElse(Math.min(r_q - 1, n - c_q));

        return total;
    }

    private static Stream<Obstacle> toObstacle(int n, int r_q, int c_q, int r, int c)
    {
        if (r == r_q) // Same row
        {
            return Stream.of(new Obstacle(
                (c_q > c) ? Obstacle.Type.S : Obstacle.Type.N,
                Math.abs(c_q - c) - 1
            ));
        }
        else if (c == c_q) // Same column
        {
            return Stream.of(new Obstacle(
                (r_q > r) ? Obstacle.Type.W : Obstacle.Type.E,
                Math.abs(r_q - r) - 1
            ));
        }
        else if (r_q - c_q == r - c) // Same NE/SW diagonal
        {
            return Stream.of(new Obstacle(
                (r_q > r) ? Obstacle.Type.SW : Obstacle.Type.NE,
                Math.abs(r_q - r) - 1
            ));
        }
        else if (r_q + c_q == r + c) // Same NW/SE diagonal
        {
            return Stream.of(new Obstacle(
                (r_q > r) ? Obstacle.Type.NW : Obstacle.Type.SE,
                Math.abs(r_q - r) - 1
            ));
        }
        else // Not an obstacle
        {
            return Stream.empty();
        }
    }

    static final class Obstacle
    {
        enum Type { N, NE, E, SE, S, SW, W, NW }
        final Type type;
        final int squares; // Amount of squares between Q and this obstable

        Obstacle(Type type, int squares)
        {
            this.type = type;
            this.squares = squares;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
