import java.math.BigInteger;
import java.util.Scanner;

public class Solution
{

   // Complete the extraLongFactorials function below.
   static void extraLongFactorials(int n)
   {
      BigInteger f = new BigInteger("1");
      for (int i = 1; i <= n; ++i)
      {
         f = f.multiply(new BigInteger(Integer.toString(i)));
      }

      System.out.println(f.toString(10));
   }

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) {
      int n = scanner.nextInt();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      extraLongFactorials(n);

      scanner.close();
   }
}
