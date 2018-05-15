package fibonacci;

public class FibonacciMemorizationSolutionTest implements FibonacciComputationTest {
    
    @Override
    public FibonacciComputation createInstance() {
        return new FibonacciMemorizationSolution();
    }

}
