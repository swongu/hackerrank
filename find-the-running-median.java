import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution
{

    /*
     * Complete the runningMedian function below.
     */
    static double[] runningMedian(int[] a)
    {
        /*
         * Write your code here.
         */

        List<Double> medians = new ArrayList<>();
        Queue<Integer> low = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> high = new PriorityQueue<>();
        for (int i: a)
        {
            // Start case.
            if (low.isEmpty())
            {
                low.add(i);
                medians.add((double)i);
                continue;
            }
            
            // Heaps both equal in size, then add to appropriate heap.
            if (low.size() == high.size())
            {
                if (low.peek() >= i)
                {
                    low.add(i);
                }
                else
                {
                    high.add(i);
                    low.add(high.remove());
                }

                medians.add((double)low.peek());
            }
            else // Low is one bigger in size.
            {
                if (low.peek() >= i)
                {
                    high.add(low.remove());
                    low.add(i);
                }
                else
                {
                    high.add(i);
                }

                medians.add((high.peek() + low.peek()) / 2.0);
            }
        }

        return medians.stream().mapToDouble(d -> d).toArray();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int aCount = Integer.parseInt(scanner.nextLine().trim());

        int[] a = new int[aCount];

        for (int aItr = 0; aItr < aCount; aItr++) {
            int aItem = Integer.parseInt(scanner.nextLine().trim());
            a[aItr] = aItem;
        }

        double[] result = runningMedian(a);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
