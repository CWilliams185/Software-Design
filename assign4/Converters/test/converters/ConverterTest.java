package converters;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DynamicTest;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import org.junit.jupiter.api.TestFactory;

public interface ConverterTest {
    Converter createInstance();
    
    Map<String, String> createTestSample();
    
    @TestFactory
    default Stream<DynamicTest> convert() {
        Converter block = createInstance();
        Map<String, String> testSample = createTestSample();

        Function<String, DynamicTest> createDynamicTest = key -> 
            dynamicTest("convert from " + key, () -> assertEquals(testSample.get(key), block.convert(key)));

      return testSample.keySet()
                       .stream()
                       .map(createDynamicTest);
    }
}
