package game;

import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UnscrambleGame{
    private SpellChecker theSpellChecker;
    
    public UnscrambleGame(SpellChecker spellchecker) {
        theSpellChecker = spellchecker;
    }
    
    public int score(String scrambledWord, String userGuess) { 
        if(!theSpellChecker.isSpellingCorrect(userGuess)){
          return 0;
        }
        
        String guessLowerCase = userGuess.toLowerCase();
        
        Map<String, Long> frequencyOfLettersInGuess = Stream.of(guessLowerCase.split(""))
          .collect(groupingBy(letter -> letter, counting()));
        
        Map<String, Long> frequencyOfLettersWord = Stream.of(scrambledWord.split(""))
          .collect(groupingBy(letter -> letter, counting()));
        
        if(frequencyOfLettersInGuess.keySet().stream()
          .filter(letter -> frequencyOfLettersInGuess.get(letter) > frequencyOfLettersWord.computeIfAbsent(letter, key -> 0L))
          .count() > 0) return 0;
        
        List<String> VOWELS = Arrays.asList("a", "e", "i", "o", "u");
        
        return Stream.of(guessLowerCase.split(""))
          .mapToInt(letter -> VOWELS.contains(letter) ? 1 : 2)
          .sum();        
    }
    
    public String wordScrambler(String originalWord, long seed){
        List<String> scrambledWord = Arrays.asList(originalWord.split(""));
        
        Collections.shuffle(scrambledWord, new Random(seed));
        
        return String.join("", scrambledWord);
    }
    
    public String selectWord(List<String> wordList, long seed){
        List<String> words = wordList;
        
        if(words.isEmpty())
            throw new IllegalStateException("Error: Word List was empty");
        
        Random rand = new Random(seed);
        int randomNum = rand.nextInt(wordList.size());
        
        return words.get(randomNum);
    }
}