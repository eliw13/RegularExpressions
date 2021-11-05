
/*
This program writes a file with 100 random binary numbers. It then uses this file to filter out even Binary numbers, Binary numbers that have '10' within them, and then Binary numbers that are divisible by 3 in their own separate files. 
*/
import java.util.*;
import java.io.*;
import static java.lang.Math.*;
import java.util.regex.*;

//Method to convert a number to a binary string
public class Engine {
    public static String convert(int n) {
        String result = ((n % 2 == 0) ? "0" : "1");
        if (abs(n) > 1) {
            result = convert(n / 2) + result;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {

        File file = new File("numbers.txt"); // input file
        PrintWriter out = new PrintWriter(file); // create a file to write to
        Random rand = new Random(); // Random number generator

        for (int i = 0; i < 100; i++) {
            int n = rand.nextInt(100); // generates numbers (n) ranging anywhere 0 <= n <= 99
            String b = convert(n); // converts the number to a binary string
            out.println(b); // write to file
        }
        out.close(); // close main file

        try {
            File evenFile = new File("evenFile.txt"); // Creates the file containing even binary numbers
            File tenFile = new File("tenFile.txt"); // Creates the file containing binary numbers with '10'
            File threeFile = new File("threeFile.txt"); // Creates the file containing binary numbers divisible by 3
            FileReader fr = new FileReader(file); // Creates a file reader to read the files
            PrintWriter pw2 = new PrintWriter(new FileWriter(evenFile)); // Creates a file writer to write to the even
                                                                         // file
            PrintWriter pw3 = new PrintWriter(new FileWriter(tenFile));
            PrintWriter pw4 = new PrintWriter(new FileWriter(threeFile));
            String str = ""; // String to store the file contents
            int x;

            while ((x = fr.read()) != -1) { // Reads the file
                str += (char) x; // Adds the file contents to the string
            }

            // Patterns
            String pattern1 = "(0|1)*0"; // Regular Expression pattern for even numbers
            String pattern2 = "(0*1*100*1*)"; // RE pattern for
                                              // numbers containing '10'
            String pattern3 = "(1(01*0)*1|0)*"; // RE pattern for numbers
                                                // divisible by 3

            // Compiling Patterns
            Pattern p = Pattern.compile(pattern1); // compile pattern
            Pattern p2 = Pattern.compile(pattern2); // compile pattern2
            Pattern p3 = Pattern.compile(pattern3); // compile pattern3

            // Three Separate Matchers for Above Patterns
            Matcher m = p.matcher(str); // match pattern
            Matcher m2 = p2.matcher(str); // match pattern2
            Matcher m3 = p3.matcher(str); // match pattern3

            // While loops to write matched binary numbers to new file
            while (m.find()) {
                pw2.write(m.group() + "\n"); // write to even file
            }
            while (m2.find()) {
                pw3.write(m2.group() + "\n"); // write to ten file
            }
            while (m3.find()) {
                pw4.write(m3.group() + "\n"); // write to file
            }

            // Closing all of the file readers and writers
            fr.close();
            pw2.close();
            pw3.close();
            pw4.close();
            System.out.println("File reading and writing both done.");

        } catch (IOException e) {
            System.out.println("Error"); // handle exception
        }
    }
}
