package game;

import java.awt.*;
import java.util.*;
import java.util.List;

import java.util.stream.IntStream;
import java.util.function.IntFunction;
import java.util.function.Function;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.counting;

import static game.Mastermind.Response.*;

public class Mastermind {

    public enum GameStatus { RUNNING, WON, LOST }

    public enum Response { NO_MATCH, MATCH, POSITIONAL_MATCH }

    public static final int SIZE = 6;

    private List<Color> selection;
    private GameStatus gameStatus = GameStatus.RUNNING;
    protected int tries = 0;
    protected static long seed;

    public Mastermind() {
        seed = System.currentTimeMillis();
        selection = generateRandomColors(seed);
    }

    protected Mastermind(List<Color> answerSelection) {
        selection = answerSelection;
    }

    public Map<Response, Long> guess(List<Color> userInput) {
        tries++;
        IntFunction<Response> computeMatchAtPosition = index -> 
          selection.get(index) == userInput.get(index) ? POSITIONAL_MATCH :
            userInput.contains(selection.get(index)) ? MATCH : NO_MATCH;
        
         Map<Response, Long> response = 
           IntStream.range(0, SIZE)
             .mapToObj(computeMatchAtPosition)
             .collect(groupingBy(Function.identity(), counting()));
        
         response.computeIfAbsent(NO_MATCH, key -> 0L);
         response.computeIfAbsent(MATCH, key -> 0L);
         response.computeIfAbsent(POSITIONAL_MATCH, key -> 0L);
         
        updateGameStatus(response);
        return response;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    private void updateGameStatus(Map<Response, Long> response) {
        if (gameStatus == GameStatus.RUNNING){
            if (SIZE == response.get(Response.POSITIONAL_MATCH))
                gameStatus = GameStatus.WON;
        
            else if (tries > 19)
                gameStatus = GameStatus.LOST;
        }
    }
    
    public List<Color> getSelection() {
        return selection;
    }
    
    protected List<Color> generateRandomColors(long seed) {
      
        int iter = 0;
        int max = 9;
        int min = 0;
        Random rand = new Random(seed);
        int randomNum;
        List<Color> possibleColorChoices = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.BLACK, Color.GRAY, Color.PINK, Color.ORANGE);
        List<Color> randomColors = new ArrayList<>();
        Collections.shuffle(possibleColorChoices, rand);
        
        while (iter < SIZE) {
            randomNum = rand.nextInt((max - min) + 1) + min;
            randomColors.add(possibleColorChoices.get(randomNum));
            iter++;
        }
        
        return randomColors;
    }
}
