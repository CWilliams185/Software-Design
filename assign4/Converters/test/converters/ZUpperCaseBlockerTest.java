package converters;

import java.util.Map;

public class ZUpperCaseBlockerTest implements ConverterTest {

    @Override
    public Converter createInstance() { return new LetterBlocker("Z"); }

    @Override
    public Map<String, String> createTestSample() {
        return Map.of("Z", "", "zZ", "z", "aZBz1Z", "aBz1");
    }
}
