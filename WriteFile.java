import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * THIS IS NOT RELATED TO THE REST OF THE PROJECT. I JUST WANTED IT FOR
 * REFERENCE WHILE WRITING MY METHODS IN OTHER CLASSES.
 *
 * @author Beth Fineberg
 * @version 1.0
 */
public class WriteFile
{
    public static void main(String[] args) throws Exception
    {
        int max = 0;
        String maxInit = "";
        int min = 5000;
        String minInit = "";
        
        PrintWriter w = new PrintWriter("fileName");
        
        Scanner scan = new Scanner(new FileReader("fileName"));
        
        w.print("ALL WORLDWIDE SCORES");
        w.println();

        for(int i = 0; i < 5000; i++)
        {
            int n = (int)(Math.random() * 5000);
            char l = (char)('A' + (int)(Math.random() * 26));
            char k = (char)('A' + (int)(Math.random() * 26));
            
            w.println();
            w.print(n);
            w.print(" " + l);
            w.print(k);
            w.println();
            
            if (n > max)
            {
                max = n;
                maxInit = "" + l + k;
            }
            
            if (n < min)
            {
                min = n;
                minInit = "" + l + k;
            }
        }
        
        w.println("min score of: " + min + " belongs to: " + minInit);
        w.println("max score of: " + max + " belongs to: " + maxInit);
        
        ArrayList<Object> information = new ArrayList<Object>();
        
        while(scan.hasNext())
        {
            String m = scan.nextLine();
            
            information.add(m);
        }
        
        scan.close();
    }
}
