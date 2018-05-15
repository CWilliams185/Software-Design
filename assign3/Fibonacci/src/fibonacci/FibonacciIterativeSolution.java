package fibonacci;

import java.math.BigInteger;

public class FibonacciIterativeSolution implements FibonacciComputation {
    
    @Override
    public BigInteger compute(int position) {  
        
        if (position < 2)
            return BigInteger.ONE; 
        
        BigInteger current = new BigInteger("1");
        BigInteger next = new BigInteger("1");
        
        for(int i = 1; i < position; i++) {
            BigInteger temp = current.add(next);
            current = next;
            next = temp;
        }

        return next;
    }
}
