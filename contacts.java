import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution 
{
    static class Trie
    {
        Node root = new Node();
        
        public void add(String word)
        {
            Node current = root;
            for (int i = 0; i < word.length(); ++i)
            {
                ++current.count;
                current = current.next.computeIfAbsent(word.charAt(i), k -> new Node());
            }
            
            ++current.count;
            current.end = true;
        }
        
        public int find(String partial)
        {
            // Find the partial word match.
            Node current = root;
            for (int i = 0; i < partial.length(); ++i)
            {
                current = current.next.get(partial.charAt(i));
                // Ended at a terminal node of trie, so no results.
                if (current == null)
                {
                    return 0;
                }
            }

            // Return number of words that continue from here.
            return current.count;
            
            // Count number of trues from here on down.
            // int count = 0;
            // Queue<Node> queue = new ArrayDeque();
            // queue.add(current);
            // while (!queue.isEmpty())
            // {
            //     Node node = queue.remove();
            //     if (node.end) ++count;
                
            //     node.next.values().forEach(queue::add);
            // }
            
            // return count;
        }
    }
    
    static class Node
    {
        Map<Character, Node> next = new HashMap<>();
        // Node[] next = new Node[26];
        boolean end = false;
        int count = 0;
    }

    /*
     * Complete the contacts function below.
     */
    static int[] contacts(String[][] queries) 
    {
        /*
         * Write your code here.
         */
        Trie trie = new Trie();
        List<Integer> finds = new ArrayList<>();
         
        for (String[] query: queries)
        {
            switch (query[0])
            {
            case "add":
                trie.add(query[1]);
                break;
            case "find":
                finds.add(trie.find(query[1]));
                break;
            }
        }
        
        return finds.stream().mapToInt(i -> i).toArray();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int queriesRows = Integer.parseInt(scanner.nextLine().trim());

        String[][] queries = new String[queriesRows][2];

        for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                String queriesItem = queriesRowItems[queriesColumnItr];
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        int[] result = contacts(queries);

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
