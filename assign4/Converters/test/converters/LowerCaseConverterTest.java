package converters;

import java.util.Map;

public class LowerCaseConverterTest implements ConverterTest {
    
    @Override
    public Converter createInstance() {
        return new LowerCaseConverter();
    }
    
    @Override
    public Map<String, String> createTestSample() {
        return Map.of("A", "a", "AA", "aa", "Ab1C2D", "ab1c2d");

    }
}
