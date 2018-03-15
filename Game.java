
/**
 * Used for Quikgame - game to see how quickly you can type a bunch of random letters
 * @author Beckwith and Fineberg
 * @version 2.0
 */
public class Game
{
    /**
     * generates a random string of capital letters of the given length
     * @param length the length of the generated "word"
     * @return the randomly generated "word"
     */
    public String getWord(int length)
    {
        String word = "";
        //randomly choose capital letters
        for(int i = 0; i < length; i ++)
            word += (char)('A' + (int)(Math.random() * 26));
        return word;
    }
}
