package ui;

import game.UnscrambleGame;
import game.SpellCheckerService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class UnscrambleGUI {

    private static List<String> openFileForReading( String filename ) throws IOException
    {
        if ( !Files.exists(Paths.get(filename)) )
            throw new IOException("Invalid Filename");
        return Files.lines(Paths.get(filename)).collect(toList());
    }
    
    public static String userInput() throws IOException {
        System.out.println("please enter guess word ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public static void userTries(String originalWord, String scrambleWord,UnscrambleGame unscrambleGame) throws IOException {

        String guess_word=userInput();
        while(!originalWord.equals(guess_word)){
            System.out.println("your score is: " + unscrambleGame.score(scrambleWord, guess_word));
            System.out.println("Wrong answer try again");
            guess_word=userInput();
        }
        System.out.println("The original word is: "+(originalWord));
        System.out.println ("The score is "+unscrambleGame.score(scrambleWord, guess_word));
    }

    public static void main(String arg[]) {
        UnscrambleGame unscrambleGame =new UnscrambleGame(new SpellCheckerService());
        int seed = (int)System.currentTimeMillis();

        List<String> wordList = new ArrayList<>();
        try {
            wordList = openFileForReading("Unscramble/src/ui/File1.txt");
            
            String originalWord = unscrambleGame.selectWord(wordList, seed);
            String scrambleWord = unscrambleGame.wordScrambler(originalWord,seed);
            System.out.println("This is scrambled word: "+scrambleWord);
            userTries(originalWord, scrambleWord,unscrambleGame);
            
        } catch (IOException ex) {
            System.out.println("IO error in main");
        }
    }
}
