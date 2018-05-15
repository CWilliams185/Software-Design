package converters;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessorTest {

    @Test
    void processorWithZBlockerAndUpper() {
        Processor processor = new Processor(List.of(new LetterBlocker("Z"), new UpperCaseConverter()));

        assertEquals("AZBC", processor.process("azBZc"));
    }

    @Test
    void processorWithZBlockerAndLower() {
        Processor processor = new Processor(List.of(new LetterBlocker("Z"), new LowerCaseConverter()));

        assertEquals("azbc", processor.process("azBZc"));
    }

    @Test
    void processorWithThreeBlocks() {
        Processor processor = new Processor(List.of(
                new UpperCaseConverter(),
                new LetterBlocker("Z"),
                new LowerCaseConverter()));

        assertEquals("abc", processor.process("azBZc"));
    }

    @Test
    void processorWithFourBlocksIncludingUpperCaseZBlocker() {
        Processor processor = new Processor(List.of(
                new UpperCaseConverter(),
                new LetterBlocker("Z"),
                new LetterBlocker("k"),
                new LowerCaseConverter()));

        assertEquals("akak", processor.process("azkAZK"));
    }

    @Test
    void processorWithFourBlocksIncludingLowerCaseZBlocker() {
        Processor processor = new Processor(List.of(
                new UpperCaseConverter(),
                new LetterBlocker("z"),
                new LetterBlocker("k"),
                new LowerCaseConverter()));

        assertEquals("azkazk", processor.process("azkAZK"));
    }
}