package converters;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConverterFactoryTest {

    @Test
    void convertUpperCaseConverterFromStringToConverter() {
        List<String> blockInput=List.of("converters.UpperCaseConverter");

        List<Converter> converters = new ConverterFactory().createConverters(blockInput);
        Processor processor = new Processor(converters);

        assertEquals("AZBZC", processor.process("azBZc"));
    }

    @Test
    void convertThreeStringsToConverters() {
        List<String> blockInput = List.of("converters.UpperCaseConverter", "converters.LetterBlocker Z", "converters.LowerCaseConverter");

        List<Converter> converters =new ConverterFactory().createConverters(blockInput);

        Processor processor = new Processor(converters);

        assertEquals("abc", processor.process("azBZc"));
    }

    @Test
    void convertSeveralBlockersWithDifferentSpellings() {
        List<String> blockInput = List.of(
                "converters.LetterBlocker s",
                "converters.LetterBlocker T",
                "converters.LetterBlocker u",
                "converters.LetterBlocker V",
                "converters.LetterBlocker w",
                "converters.LetterBlocker X");

        List<Converter> converters = new ConverterFactory().createConverters(blockInput);
        Processor processor = new Processor(converters);

        assertEquals("RyZ", processor.process("RsTuVwXyZ"));
    }

    @Test
    void createConverterFailsWithException() {
        Exception ex = assertThrows(RuntimeException.class,
                () -> new ConverterFactory().createConverter("NotAConverter"),
                "Error processing...");

        assertEquals("Error processing...", ex.getMessage());
    }
}