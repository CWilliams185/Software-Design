package game;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class SpellCheckerService implements SpellChecker{
    
    @Override
    public boolean isSpellingCorrect(String word) {
      try {
        return getReponseFromURL(word).equals("true");
      } catch(Exception ex) {
        throw new RuntimeException(ex.getMessage());
      }
    }
    
    protected String getReponseFromURL(String word) throws IOException {
      URL url = new URL("http://agile.cs.uh.edu/spell?check=" + word);
      Scanner scanner = new Scanner(url.openStream());
      return scanner.next();
    }
}