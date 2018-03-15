import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Processes HighScore objects and allows them to be usable.
 *
 * @author Beth Fineberg
 * @version 1.0
 */
public class ScoreProcessor
{
    ArrayList<HighScore> highScores = new ArrayList<HighScore>(1);
    String name, date;
    int score;
    DateTest now = new DateTest(); 

    /**
     * Read collects data from external file and takes that data
     * to create a new HighScore object. It then adds that HighScore
     * object to an ArrayList.
     * 
     * @param fileName name for file to create
     * @return highScores ArrayList of HighScores from document
     */
    public ArrayList<HighScore> read(String fileName)
    {
        try
        {
            Scanner scan = new Scanner(new FileReader(fileName));

            while(scan.hasNext())
            {
                name = scan.next();
                score = scan.nextInt();
                date = scan.nextLine();
                
                HighScore m = new HighScore(name, score, date);
                
                highScores.add(m);
            }
            
            scan.close();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }

        return highScores;
    }

    /**
     * Add puts new HighScores into the highScore ArrayList.
     * 
     * @param score the points the user got
     * @param name the inputted name of user
     * @return highScores updated ArrayList of HighScores
     */
    public ArrayList<HighScore> add(int score, String name)
    {
        DateTest now = new DateTest(); 
        String current = now.getDate();

        if (highScores.size() == 0)
        {
            highScores.add(new HighScore(name, score, current));
        }
        else
        {
            for (int i = highScores.size() - 1; i >= 0; i--)
            {
                if (highScores.get(i).getScore() > score)
                {
                    highScores.add(i + 1, new HighScore(name, score, current));
                    break;
                }
                else if (highScores.get(0).getScore() < score)
                {
                    highScores.add(0, new HighScore(name, score, current));
                }
            }
        }
        
        return highScores;
    }

    /**
     * Save collects data from the ArrayList of HighScores and saves them onto
     * the external document.
     * 
     * @param hS ArrayList of HighScores to put on document
     */
    public void save(ArrayList<HighScore> hS)
    {
        try
        {
            PrintWriter w = new PrintWriter("highscores.txt");

            for(int i = 0; i < hS.size(); i++)
            {
                w.println(highScores.get(i).getName());
                w.println(highScores.get(i).getScore());
                w.println(highScores.get(i).getDate());
            }

            w.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Display prints the highest couple of scores onto the console. I don't
     * believe I ended up using this.
     * 
     * @param hS ArrayList of HighScores to print
     */
    public void display(ArrayList<HighScore> hS)
    {
        for (int i = 0; i < 3; i++)
        {
            System.out.println("Highest Scores: ");
            System.out.println("Name: " + hS.get(i).getName());
            System.out.println("Score: " + hS.get(i).getScore());
            System.out.println("Date: " + hS.get(i).getDate());
            System.out.println();
        }
    }

    /**
     * getScores gives the user access to the saved ArrayList of HighScores.
     * 
     * @return highScores ArrayList of HighScores
     */
    public ArrayList<HighScore> getScores()
    {
        return highScores;
    }
}
