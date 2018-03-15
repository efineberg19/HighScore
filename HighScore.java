import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Creates structure for HighScore objects
 *
 * @author Beth Fineberg
 * @version 1.0
 */
public class HighScore
{
    String name;
    int score;
    String date;
    DateTest dT = new DateTest();
    
    /**
     * Constructor for HighScore
     * 
     * @param n name of user
     * @param s score from gave
     * @param d date of score
     */
    public HighScore(String n, int s, String d)
    {
        name = n;
        score = s;
        date = d;
    }
    
    /**
     * getName returns the name of the user who made the HighScore
     * 
     * @return name the name of the user
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * getScore returns the score of the user who made the HighScore
     * 
     * @return score the points the user got
     */
    public int getScore()
    {
        return score;
    }
    
    /**
     * getDate returns the date that the user got the HighScore
     * 
     * @return date the date the user got score
     */
    public String getDate()
    {
        return date;
    }
}
