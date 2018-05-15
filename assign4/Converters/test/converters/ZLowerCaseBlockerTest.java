package converters;

import java.util.Map;

public class ZLowerCaseBlockerTest implements ConverterTest {

    @Override
    public Converter createInstance() {
            return new LetterBlocker("z");
        }

    @Override
    public Map<String, String> createTestSample() {
        return Map.of("z", "", "az", "a", "azB1z", "aB1");
    }
}
