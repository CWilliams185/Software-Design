package fibonacci;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FibonacciMemorizationSolution extends FibonacciRecursiveSolution  {

    private Map<Integer, BigInteger> memorizedSeries = new HashMap<>();

    @Override
    public BigInteger compute(int position) {
        
        if(!memorizedSeries.containsKey(position))
            memorizedSeries.put(position, super.compute(position));
        
        return memorizedSeries.get(position);
    }
}
