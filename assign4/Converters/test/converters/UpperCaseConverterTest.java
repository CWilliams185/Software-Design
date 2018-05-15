package converters;

import java.util.Map;

public class UpperCaseConverterTest implements ConverterTest {

    @Override
    public Converter createInstance() {
        return new UpperCaseConverter();
    }
    
    @Override
    public Map<String, String> createTestSample() {
        return Map.of("a", "A", "aa", "AA", "aBc1D2E", "ABC1D2E");

    }
}
