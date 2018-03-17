import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*; 
import javax.swing.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.io.*;

/**
 *  Quikgame - GUIT for game to see how quickly you can type a bunch of random letters
 *  
 * @author Beckwith and Fineberg
 * @version 3.0
 */
public class GUI extends JFrame implements ActionListener
{
    int LENGTH = 12;            //length of word to type
    JPanel masterPanel;         //panel for all components of the display
    JPanel interactionsPanel;
    JPanel gamePanel;           //panel for JLabel with game to user

    JButton quitButton, playButton;
    JLabel game;                //displays game word and score
    JTextField userEntry;  
    boolean playing;
    Game g;                     //to run the game
    String playWord;            //the word that needs to be typed
    long startTime;             //to generate the score
    String name;
    ScoreProcessor sp = new ScoreProcessor();
    ArrayList<HighScore> highScores = new ArrayList<HighScore>();
    DateTest now = new DateTest(); 

    /**
     * GUI constructor
     */
    public GUI(String title)
    {
        sp.read("highscores.txt");
        name = JOptionPane.showInputDialog("What is your name?");

        g = new Game();         //to access method that generates the random letters
        Font gameFont = new Font("Verdana", Font.BOLD, 28);
        Font scoresFont = new Font("Verdana", Font.BOLD, 23);
        playing = false;
        this.setLayout(new BorderLayout());
        /*
         * TIME TO MAKE SOME BUTTONS:
         */

        playButton = new JButton("PLAY");
        playButton.addActionListener(this);
        playButton.setActionCommand("playButton"); 

        quitButton = new JButton("QUIT");
        quitButton.addActionListener(this);
        quitButton.setActionCommand("quitButton");  

        /*
         * WHEN YOU PLAY THE GAME, THE WORD IS DISPLAYED HERE:
         */
        gamePanel = new JPanel();
        game = new JLabel();
        game.setForeground(Color.BLACK);
        game.setFont(scoresFont);
        //makes background blue
        gamePanel.setBackground(new Color(45, 182, 255));
        gamePanel.add(game);   //put the JLabel into the panel

        DocumentListener documentListener = new DocumentListener() {
                public void changedUpdate(DocumentEvent documentEvent) {
                }

                public void insertUpdate(DocumentEvent documentEvent) {
                    getLength(documentEvent);
                }

                public void removeUpdate(DocumentEvent documentEvent) {

                }
            };
        //textField FOR USER INPUT:
        userEntry = new JTextField("", 15);  //15 is the number of characters width
        userEntry.getDocument().addDocumentListener(documentListener);
        userEntry.setFont(gameFont);
        /*
         * THE INTERACTIONS PANEL HAS BUTTONS AND TEXTFIELD:
         */
        interactionsPanel = new JPanel(); 
        interactionsPanel.add(playButton);  
        interactionsPanel.add(userEntry);
        interactionsPanel.add(quitButton);
        //makes upper bar green
        interactionsPanel.setBackground(new Color(18, 188, 0));

        /*
         * Put the two panels into the master panel, i.e. the whole window:
         */
        this.add(interactionsPanel, BorderLayout.NORTH);
        this.add(gamePanel, BorderLayout.CENTER);

        /* 
         * Wrap-up: set title and size of window and set to be visible in the JFrame
         */
        this.setTitle(title);    
        this.setSize(950, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void getLength(DocumentEvent documentEvent) {
        DocumentEvent.EventType type = documentEvent.getType();
        Document source = documentEvent.getDocument();
        int length = source.getLength();

        if(length == 1)
        {
            startTime = System.currentTimeMillis();
        }
        if(length == LENGTH) 

        {  
            /*
             * CORRECT ANSWER: calculate and show score
             */
            String userWord = userEntry.getText();
            if(userWord.toUpperCase().equals(playWord))
            {
                long score = System.currentTimeMillis() - startTime;   //get how long it took them
                score = (int)(score / 10);                             //convert to int and make smaller (5 secs = 5000 msecs = 500 points)
                score = 2000 - score;                                  //make it so that small time = large score (5 secs = 1500, 1 sec = 1900) 
                String msg = "";
                String scoreMsg = "";

                if(score > 1600) 
                {
                    if(score >= sp.getScores().get(0).getScore())
                    {
                        msg = "Wow! You've got the highest score: " + score;
                    }
                    else
                    {
                        msg = "You're fast! Current high score: ";
                        scoreMsg = sp.getScores().get(0).getScore() + " from " +
                        sp.getScores().get(0).getName() + " on " + sp.getScores().get(0).getDate();
                    }
                }
                else if(score > 1000) 
                {
                    msg = "Good job. Current high score: ";
                    scoreMsg = sp.getScores().get(0).getScore() + " from " +
                    sp.getScores().get(0).getName() + " on " + sp.getScores().get(0).getDate();
                }
                else 
                {
                    if(score <= sp.getScores().get(sp.getScores().size() - 1).getScore())
                    {
                        msg = "You're very slow and got the lowest score of all time. ";
                    }
                    else
                    {
                        msg = "Really? Current high score: ";
                        scoreMsg = sp.getScores().get(0).getScore() + " from " +
                        sp.getScores().get(0).getName() + " on " + sp.getScores().get(0).getDate();
                    }
                }

               game.setText("Score: " + score + "... " + msg + "\t" + scoreMsg);

                DateTest now = new DateTest();
                sp.add((int)score, name);

                ArrayList<HighScore> scores = sp.getScores();

                sp.save(sp.getScores());
            }
            //else userEntry.setText("WRONG - TRY AGAIN!");
        }
    }

    public void actionPerformed (ActionEvent e)
    {
        //ACTIONS TO TAKE WHEN BUTTONS ARE CLICKED
        if(e.getActionCommand().equals("playButton")) 
        {
            playing = true;
            playWord =  g.getWord(LENGTH);
            userEntry.setText("");
            userEntry.requestFocus();
            game.setText("Type this in the box as quickly as you can:  " + playWord);  
        }

        else if(e.getActionCommand().equals("quitButton")) 
        {
            System.exit(0);
        }
    }
}
