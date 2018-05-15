package game;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SpellCheckerTest {
    
    SpellCheckerService service;
    
    @BeforeEach
    void init() {
        service = new SpellCheckerService();
    }
    
    @Test
    void spellingIsCorrect() {
        assertTrue(service.isSpellingCorrect("monk"));
    }
    
    @Test
    void spellingIsIncorrect() {
        assertFalse(service.isSpellingCorrect("ney"));
    }
    
    @Test
    void isSpellingCorrectHasExceptionWhenConnectingToURL() throws IOException {
        String message = "network failure";
        SpellCheckerService spy = spy(SpellCheckerService.class);
        when(spy.getReponseFromURL(anyString())).thenThrow(new IOException(message));
        
        assertThrows(RuntimeException.class, () -> spy.isSpellingCorrect("whatever"), message);
    }
}
