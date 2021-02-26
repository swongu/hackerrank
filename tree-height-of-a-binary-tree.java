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
	public static int height(Node root) 
    {
      	// Write your code here.
        return Math.max(
            root.left == null ? 0 : height(root.left) + 1,
            root.right == null ? 0 : height(root.right) + 1
        );
    }

	public static Node insert(Node root, int data) {
