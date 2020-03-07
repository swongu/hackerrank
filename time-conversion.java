import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import java.time.*;
import java.time.format.*;

public class Solution {

    /*
     * Complete the timeConversion function below.
     */
    static String timeConversion(String s) {
        LocalTime time = LocalTime.parse(s, DateTimeFormatter.ofPattern("hh:mm:ssa"));
        return time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    static int twentyFourHour(int hour, boolean morning)
    {
        if (hour == 12 && morning) return 0;
        else return morning ? hour : hour + 12;
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scan.nextLine();

        String result = timeConversion(s);

        bw.write(result);
        bw.newLine();

        bw.close();
    }
}
