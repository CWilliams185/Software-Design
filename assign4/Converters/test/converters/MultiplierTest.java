package converters;

import java.util.Map;

public class MultiplierTest implements ConverterTest {
    
    @Override
    public Converter createInstance() {
        return new Multiplier();
    }
    
    @Override
    public Map<String, String> createTestSample() {
        return Map.of("a", "aa", "AA", "AAAA", "abC1dEf", "aabbCC11ddEEff");

    }
}
