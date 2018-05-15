package converters;

import java.util.Map;

public class KLowerCaseBlockerTest implements ConverterTest {

    @Override
    public Converter createInstance() { return new LetterBlocker("k"); }

    @Override
    public Map<String, String> createTestSample() {
        return Map.of("k", "", "ak", "a", "akB1k", "aB1");
    }
}
