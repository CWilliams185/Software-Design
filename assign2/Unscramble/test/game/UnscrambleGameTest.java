package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class UnscrambleGameTest {

    UnscrambleGame unscrambleGame;

    @BeforeEach
    void init() {
        SpellChecker spellCheckerMock = Mockito.mock(SpellChecker.class);
        
        when(spellCheckerMock.isSpellingCorrect(anyString())).thenReturn(true);
        
        unscrambleGame = new UnscrambleGame(spellCheckerMock);
    }

    @Test
    void canary() {
        assertTrue(true);
    }

    @Test
    void userGuessesOneCorrectConsonant() {
        assertEquals(2, unscrambleGame.score("oekmny", "m"));
    }

    @Test
    void userGuessesOneCorrectVowel() {

        assertEquals(1, unscrambleGame.score("oekmny", "e"));
    }

    @Test
    void userGuessesOneVowelAndTwoConsonantsCorrectly() {
        assertEquals(5, unscrambleGame.score("oekmny", "key"));
    }

    @Test
    void userGuessesAnIncorrectLetter() {
        assertEquals(0, unscrambleGame.score("oekmny", "mop"));
    }

    @Test
    void userGuessUsesUppercaseLetters() {
        assertEquals(7, unscrambleGame.score("oekmny", "MoNk"));
    }

    @Test
    void userGuessIncludesSpace() {
        assertEquals(0, unscrambleGame.score("oekmny", "mon key"));
    }

    @Test
    void correctSpellingWithScore() {
        assertEquals(7, unscrambleGame.score("oekmny", "monk"));
    }
    
    @Test
    void incorrectSpellingWithScore() {
        SpellChecker spellCheckerIncorrect = Mockito.mock(SpellChecker.class);
        when(spellCheckerIncorrect.isSpellingCorrect("ney")).thenReturn(false);
        UnscrambleGame unscrambleGameIncorrect = new UnscrambleGame(spellCheckerIncorrect);
        
        assertEquals(0, unscrambleGameIncorrect.score("oekmny", "ney"));
    }
    
    @Test
    void isSpellingCorrectCausesError() {
        SpellChecker spellCheckerError = Mockito.mock(SpellChecker.class);
        when(spellCheckerError.isSpellingCorrect(anyString()))
                .thenThrow(new RuntimeException ("Error Processing"));
        UnscrambleGame unscrambleGameError = new UnscrambleGame(spellCheckerError);

        assertThrows(RuntimeException.class,
                () -> unscrambleGameError.score("oekmny", "munky"), "Error Processing");
    }
    
    @Test
    void scrambledWordIsNotOriginalWord() {
        assertNotEquals("monkey", unscrambleGame.wordScrambler("monkey", 0));
    }
    
    @Test
    void scrambledWordIsTheSameWhenSeedIsTheSame() {
        assertEquals(unscrambleGame.wordScrambler("monkey", 0), unscrambleGame.wordScrambler("monkey", 0));
    }
    
    @Test
    void scrambledWordIsDifferentWhenSeedIsDifferent() {
        assertNotEquals(unscrambleGame.wordScrambler("monkey", 7), unscrambleGame.wordScrambler("monkey", 13));
    }
    
    @Test
    void wordScramblerResultHasSameLengthAsGivenWord() {
        assertEquals(6, unscrambleGame.wordScrambler("monkey", 7).length());
    }
    
    @Test
    void wordScramblerResultHasSameLettersAsGivenWord() {
        List<String> givenWord = Arrays.asList("monkey".split(""));
        
        List<String> returnedWord = Arrays.asList(unscrambleGame.wordScrambler("monkey", 7).split(""));
        
        assertTrue(givenWord.containsAll(returnedWord));
    }                     
    
    @Test
    void obtainAWordFromTheList() {
        List<String> wordList  = Arrays.asList("monkey");

        
        assertEquals("monkey", unscrambleGame.selectWord(wordList, 7));
    }

    @Test
    void obtainedWordIsTheSameWhenSeedIsTheSame() {
        List<String> wordList = Arrays.asList("monkey", "fruit", "banana", "fruit", "cosmopolitan");

        assertEquals(unscrambleGame.selectWord(wordList, 0),
                unscrambleGame.selectWord(wordList, 0));
    }
    
    @Test
    void obtainedWordIsDifferentWhenTheSeedIsDifferent() {
        List<String> wordList = Arrays.asList("monkey", "fruit", "banana", "fruit", "cosmopolitan");

        
        assertNotEquals(unscrambleGame.selectWord(wordList, 7),
                unscrambleGame.selectWord(wordList, 13));
    }
    
    @Test
    void obtainedWordIsAMemberOfTheList() {
        List<String> wordList = Arrays.asList("fruit", "banana");

        assertTrue(wordList.contains(unscrambleGame.selectWord(wordList, 7)));
    }
    
    @Test
    void emptyWordListCausesError() {
        List<String> wordList = new ArrayList<>();
        
        Exception ex = assertThrows(IllegalStateException.class,
                () -> unscrambleGame.selectWord(wordList, 7), "Error: Word List was empty");
        
        assertEquals("Error: Word List was empty", ex.getMessage());
    }
}