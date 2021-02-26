import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution 
{
    static class Checker
    {
        enum BracketType { Curly, Square, Round };
        Deque<BracketType> stack = new ArrayDeque<>();
    
        String isBalanced(String s)
        {
            try
            {
                for (int i = 0; i < s.length(); ++i)
                {
                    switch (s.charAt(i))
                    {
                        case '{': add(BracketType.Curly); break;
                        case '[': add(BracketType.Square); break;
                        case '(': add(BracketType.Round); break;
                        case '}': remove(BracketType.Curly); break;
                        case ']': remove(BracketType.Square); break;
                        case ')': remove(BracketType.Round); break;
                    }
                }
                
                return stack.isEmpty() ? "YES" : "NO";
            }
            catch (Exception e)
            {
                return "NO";
            }
        }
        
        void add(BracketType type)
        {
            stack.addLast(type);
        }
        
        void remove(BracketType type) throws Exception
        {
            if (stack.isEmpty())
            {
                throw new Exception();
            }
            
            if (stack.removeLast() != type)
            {
                throw new Exception();
            }
        }
    }

    // Complete the isBalanced function below.
    static String isBalanced(String s) 
    {
        Checker checker = new Checker();
        return checker.isBalanced(s);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
