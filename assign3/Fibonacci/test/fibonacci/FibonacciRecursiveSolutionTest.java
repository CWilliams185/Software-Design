package fibonacci;

public class FibonacciRecursiveSolutionTest implements FibonacciComputationTest {
    
    @Override
    public FibonacciComputation createInstance() {
        return new FibonacciRecursiveSolution();
    }
}