import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    static class TreeNode
    {
        TreeNode left, right;
        int value;
        TreeNode(int value)
        {
            this.value = value;
        }
    }

    /*
     * Complete the swapNodes function below.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries)
    {
        // Build tree nodes.
        TreeNode[] nodes = new TreeNode[indexes.length];
        for (int i = 0; i < indexes.length; ++i)
        {
            nodes[i] = new TreeNode(i+1);
        }
        for (int i = 0; i < indexes.length; ++i)
        {
            if (indexes[i][0] != -1)
            {
                nodes[i].left = nodes[indexes[i][0] - 1];
            }
            
            if (indexes[i][1] != -1)
            {
                nodes[i].right = nodes[indexes[i][1] - 1];
            }
        }
        
        int[][] results = new int[queries.length][];
        for (int i = 0; i < queries.length; ++i)
        {
            // For each query do level-order traversal and swap on appropriate levels.
            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.addLast(nodes[0]);
            int level = 1;
            int levelSize = 1;
            while (!queue.isEmpty())
            {
                TreeNode node = queue.removeFirst();
                
                // Swap when level is multiple of k (the query)
                if (level % queries[i] == 0)
                {
                    TreeNode tmp = node.left;
                    node.left = node.right;
                    node.right = tmp;
                }

                if (node.left != null)
                {
                    queue.addLast(node.left);
                }
                
                if (node.right != null)
                {
                    queue.addLast(node.right);
                }

                if (--levelSize == 0)
                {
                    ++level;
                    levelSize = queue.size();
                }
            }
            
            // Print in-order traversal
            List<Integer> list = new ArrayList<>();
            dfs(nodes[0], list);
            results[i] = list.stream().mapToInt(j -> j).toArray();
        }
        
        return results;
    }
    
    private static void dfs(TreeNode node, List<Integer> list)
    {
        if (node == null) return;
        dfs(node.left, list);
        list.add(node.value);
        dfs(node.right, list);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                bufferedWriter.write(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
