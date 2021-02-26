import java.util.*;
import java.io.*;

class Node {
    Node left;
    Node right;
    int data;
    
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {

	/* 
    
    class Node 
    	int data;
    	Node left;
    	Node right;
	*/
	public static void levelOrder(Node root) 
    {        
        List<String> parts = new ArrayList<>();
        Deque<Node> queue = new ArrayDeque();
        queue.add(root);
        while (!queue.isEmpty())
        {
            Node node = queue.removeFirst();
            parts.add(Integer.toString(node.data));
            
            if (node.left != null)
            {
                queue.addLast(node.left);
            }
            
            if (node.right != null)
            {
                queue.addLast(node.right);
            }
        }  
      
        System.out.println(String.join(" ", parts));
    }

	public static Node insert(Node root, int data) {
