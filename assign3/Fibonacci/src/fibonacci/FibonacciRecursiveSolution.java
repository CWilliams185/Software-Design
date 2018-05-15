package fibonacci;

import java.math.BigInteger;

public class FibonacciRecursiveSolution implements FibonacciComputation {
    
    @Override
    public BigInteger compute(int position) {
        
        if (position < 2)
            return BigInteger.ONE;
        
        return compute(position - 1).add(compute(position - 2));
    }
}
