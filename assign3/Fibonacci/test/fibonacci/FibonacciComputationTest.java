package fibonacci;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public interface FibonacciComputationTest {

    FibonacciComputation createInstance();
    
    @Test
    default void baseFibonacciForVariousPositions() { 
        FibonacciComputation fibonacci = createInstance();
        
        assertAll(
            () -> assertEquals(1, fibonacci.compute(0).intValue()),
            () -> assertEquals(1, fibonacci.compute(1).intValue()),
            () -> assertEquals(2, fibonacci.compute(2).intValue()),
            () -> assertEquals(3, fibonacci.compute(3).intValue()),
            () -> assertEquals(5, fibonacci.compute(4).intValue()),
            () -> assertEquals(8, fibonacci.compute(5).intValue()),
            () -> assertEquals(55, fibonacci.compute(9).intValue()));
    }
}