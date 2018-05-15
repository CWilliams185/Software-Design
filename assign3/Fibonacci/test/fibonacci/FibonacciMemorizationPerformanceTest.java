package fibonacci;

import static junit.framework.TestCase.assertTrue;

import org.junit.jupiter.api.Test;

public class FibonacciMemorizationPerformanceTest {
    
    long time(FibonacciComputation fibonacci) {
        
        final long startTime = System.nanoTime();
        
        fibonacci.compute(25);
        
        return System.nanoTime() - startTime;
    }
    
    @Test
    void performanceBetweenMemorizeAndRecursive () {
        
        long recursiveTime = time(new FibonacciRecursiveSolution());
        long memomizedTime = time(new FibonacciMemorizationSolution());
        
        assertTrue(memomizedTime < recursiveTime / 10);
    }
}
