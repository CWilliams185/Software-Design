package fibonacci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciIterativeSolutionTest implements FibonacciComputationTest {

    @Override
    public FibonacciComputation createInstance() {
        return new FibonacciIterativeSolution();
    }
    
    @Test
    void simpleIterationForPosition50() {
        FibonacciComputation fibonacci = createInstance();
        
        assertEquals(12586269025l, fibonacci.compute(49).longValue());
    }

    @Test
    void simpleIterationForPosition300() {
        FibonacciComputation fibonacci = createInstance();
        
        assertEquals("359579325206583560961765665172189099052367214309267232255589801", 
            String.valueOf(fibonacci.compute(300)));
    }
}