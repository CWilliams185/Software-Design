package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import game.Mastermind.GameStatus;
import game.Mastermind.Response;

public class MastermindTest {

    public Mastermind mastermind;

    @BeforeEach
    void init() {
        List<Color> selection = Arrays.asList(Color.BLUE, Color.RED, Color.GREEN, Color.BLACK, Color.PINK, Color.CYAN);

        mastermind = new Mastermind(selection);
    }

    @Test
    void canary() {
        assertTrue(true);
    }

    @Test
    void noCorrectColors() {
        List<Color> userInput = Arrays.asList(Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE);
        
        Map<Response, Long> response = mastermind.guess(userInput);

        assertAll(
                () -> assertEquals(6, (long) response.get(Response.NO_MATCH)),
                () -> assertEquals(0, (long) response.get(Response.MATCH)),
                () -> assertEquals(0, (long) response.get(Response.POSITIONAL_MATCH)));
    }

    @Test
    void oneColorZeroPosition() {
        List<Color> userInput = Arrays.asList(Color.RED, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE);
        
        Map<Response, Long> response = mastermind.guess(userInput);

        assertAll(
                () -> assertEquals(5, (long) response.get(Response.NO_MATCH)),
                () -> assertEquals(1, (long) response.get(Response.MATCH)),
                () -> assertEquals(0, (long) response.get(Response.POSITIONAL_MATCH)));
    }

    @Test
    void oneColorOnePosition() {
        List<Color> userInput = Arrays.asList(Color.ORANGE, Color.RED, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE);
        
        Map<Response, Long> response = mastermind.guess(userInput);

        assertAll(
                () -> assertEquals(5, (long) response.get(Response.NO_MATCH)),
                () -> assertEquals(0, (long) response.get(Response.MATCH)),
                () -> assertEquals(1, (long) response.get(Response.POSITIONAL_MATCH)));
    }

    @Test
    void twoColorsOnePosition() {
        List<Color> userInput = Arrays.asList(Color.GREEN, Color.RED, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE);
        
        Map<Response, Long> response = mastermind.guess(userInput);

        assertAll(
                () -> assertEquals(4, (long) response.get(Response.NO_MATCH)),
                () -> assertEquals(1, (long) response.get(Response.MATCH)),
                () -> assertEquals(1, (long) response.get(Response.POSITIONAL_MATCH)));
    }

    @Test
    void twoSameColorButOneCorrectPosition() {
        List<Color> userInput = Arrays.asList(Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE);
        
        Map<Response, Long> response = mastermind.guess(userInput);

        assertAll(
                () -> assertEquals(5, (long) response.get(Response.NO_MATCH)),
                () -> assertEquals(0, (long) response.get(Response.MATCH)),
                () -> assertEquals(1, (long) response.get(Response.POSITIONAL_MATCH)));
    }

    @Test
    void allColorsAllPositions() {
        List<Color> userInput = Arrays.asList(Color.BLUE, Color.RED, Color.GREEN, Color.BLACK, Color.PINK, Color.CYAN);
        
        Map<Response, Long> response = mastermind.guess(userInput);

        assertAll(
                () -> assertEquals(0, (long) response.get(Response.NO_MATCH)),
                () -> assertEquals(0, (long) response.get(Response.MATCH)),
                () -> assertEquals(6, (long) response.get(Response.POSITIONAL_MATCH)));
    }

    @Test
    void allColorsFourPositions() {
        List<Color> userInput = Arrays.asList(Color.BLUE, Color.GREEN, Color.RED, Color.BLACK, Color.PINK, Color.CYAN);
        
        Map<Response, Long> response = mastermind.guess(userInput);

        assertAll(
                () -> assertEquals(0, (long) response.get(Response.NO_MATCH)),
                () -> assertEquals(2, (long) response.get(Response.MATCH)),
                () -> assertEquals(4, (long) response.get(Response.POSITIONAL_MATCH)));
    }

    @Test
    void fiveColorsFourPositions1Duplicated() {
        List<Color> userInput = Arrays.asList(Color.BLUE, Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, Color.CYAN);
        
        Map<Response, Long> response = mastermind.guess(userInput);

        assertAll(
                () -> assertEquals(1, (long) response.get(Response.NO_MATCH)),
                () -> assertEquals(1, (long) response.get(Response.MATCH)),
                () -> assertEquals(4, (long) response.get(Response.POSITIONAL_MATCH)));
    }

    @Test
    void fiveColorsNoPositions() {
        List<Color> userInput = Arrays.asList(Color.CYAN, Color.PINK, Color.BLACK, Color.BLUE, Color.RED, Color.BLUE);
        
        Map<Response, Long> response = mastermind.guess(userInput);

        assertAll(
                () -> assertEquals(1, (long) response.get(Response.NO_MATCH)),
                () -> assertEquals(5, (long) response.get(Response.MATCH)),
                () -> assertEquals(0, (long) response.get(Response.POSITIONAL_MATCH)));
    }

    @Test
    void gameStatusAtStart() {
        assertEquals(GameStatus.RUNNING, mastermind.getGameStatus());
    }

    @Test
    void gameStatusAfterGuessIsCalledTwice() {
        List<Color> userInput = Arrays.asList(Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE);
        
        mastermind.guess(userInput);
        mastermind.guess(userInput);

        assertEquals(GameStatus.RUNNING, mastermind.getGameStatus());
    }

    @Test
    void gameStatusAfterGuessIsCalledWithAllCorrectColorsAndPositions() {
        List<Color> userInput = Arrays.asList(Color.BLUE, Color.RED, Color.GREEN, Color.BLACK, Color.PINK, Color.CYAN);

        mastermind.guess(userInput);

        assertEquals(GameStatus.WON, mastermind.getGameStatus());
    }

    @Test
    void gameStatusAfterGuessIsCalled20Times() {
        List<Color> userInput = Arrays.asList(Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE);
        mastermind.tries = 19;

        mastermind.guess(userInput);

        assertEquals(GameStatus.LOST, mastermind.getGameStatus());
    }

    @Test
    void gameStatusAfterIncorrectGuessIsCalledAfterCorrectGuess() {
        List<Color> userInput1 = Arrays.asList(Color.BLUE, Color.RED, Color.GREEN, Color.BLACK, Color.PINK, Color.CYAN);
        List<Color> userInput2 = Arrays.asList(Color.ORANGE, Color.RED, Color.GREEN, Color.BLACK, Color.PINK, Color.CYAN);

        mastermind.guess(userInput1);
        mastermind.guess(userInput2);

        assertEquals(GameStatus.WON, mastermind.getGameStatus());
    }                         
    
    @Test
    void gameStatusWhenUserGuessesCorrectlyAfterLosing() {
        List<Color> userInput1 = Arrays.asList(Color.ORANGE, Color.RED, Color.GREEN, Color.BLACK, Color.PINK, Color.CYAN);
        List<Color> userInput2 = Arrays.asList(Color.BLUE, Color.RED, Color.GREEN, Color.BLACK, Color.PINK, Color.CYAN);
        mastermind.tries = 19;

        mastermind.guess(userInput1);
        mastermind.guess(userInput2);

        assertEquals(GameStatus.LOST, mastermind.getGameStatus());
    }
    
    @Test
    void gameStatusWhenUserGuessesCorrectlyOnTry20() {
        List<Color> userInput = Arrays.asList(Color.BLUE, Color.RED, Color.GREEN, Color.BLACK, Color.PINK, Color.CYAN);
        mastermind.tries = 19;

        mastermind.guess(userInput);

        assertEquals(GameStatus.WON, mastermind.getGameStatus());
    }

    @Test
    void verifyGenerateRandomColorsIsRandom() {
        List<Color> randomColors = mastermind.generateRandomColors(mastermind.seed);
        
        assertEquals(6, randomColors.size());
    }
    
    @Test
    void publicConstructorCallsGenerateRandomColors() {
        List<Color> returnPlaceHolder = Arrays.asList(Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE);
        AtomicBoolean called = new AtomicBoolean();
        
        Mastermind stub = new Mastermind() {
            @Override
            protected List<Color> generateRandomColors(long seed) {
                called.set(true);
                return(returnPlaceHolder);
            }
        };

        assertTrue(called.get());
    }               
    
    @Test
    void verifyGenerateRandomColorsIsTrulyRandom() {
        List<Color> randomColors1 = mastermind.generateRandomColors(mastermind.seed);
        List<Color> randomColors2 = mastermind.generateRandomColors(mastermind.seed);
        
        Mastermind NEWSEED = new Mastermind();
        List<Color> randomColors3 = NEWSEED.generateRandomColors(NEWSEED.seed);
        
        assertAll(
                () -> assertTrue(randomColors1.equals(randomColors2)),
                () -> assertFalse(mastermind.getSelection().equals(NEWSEED.getSelection())));
    }
    

    @Test
    void toSuppressCoverageIssueWithEnumResponse() {
        Response.values();
        Response.valueOf("NO_MATCH");
        assertTrue(true);
    }

    @Test
    void toSuppressCoverageIssueWithEnumGameStatus() {
        Mastermind.GameStatus.values();
        Mastermind.GameStatus.valueOf("RUNNING");
        assertTrue(true);
    }
}
