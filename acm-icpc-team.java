import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

   // Complete the acmTeam function below.
   static int[] acmTeam(String[] topic) {
      List<Integer> teamTopics = new ArrayList<>();
      for (int i = 0; i < topic.length; ++i)
      {
         for (int j = i + 1; j < topic.length; ++j)
         {
            teamTopics.add(countTeamTopics(topic[i], topic[j]));
         }
      }

      int max = Collections.max(teamTopics);
      int count = (int)teamTopics.stream().filter(i -> i == max).count();
      return new int[]{ max, count };
   }

   private static int countTeamTopics(String a, String b)
   {
      return (int)IntStream.range(0, a.length())
         .filter(i -> a.charAt(i) == '1' || b.charAt(i) == '1')
         .count();
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) throws IOException {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

      String[] nm = scanner.nextLine().split(" ");

      int n = Integer.parseInt(nm[0]);

      int m = Integer.parseInt(nm[1]);

      String[] topic = new String[n];

      for (int i = 0; i < n; i++) {
         String topicItem = scanner.nextLine();
         topic[i] = topicItem;
      }

      int[] result = acmTeam(topic);

      for (int i = 0; i < result.length; i++) {
         bufferedWriter.write(String.valueOf(result[i]));

         if (i != result.length - 1) {
            bufferedWriter.write("\n");
         }
      }

      bufferedWriter.newLine();

      bufferedWriter.close();

      scanner.close();
   }
}
