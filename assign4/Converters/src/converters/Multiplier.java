package converters;

import java.util.stream.Stream;
import static java.util.stream.Collectors.joining;

public class Multiplier implements Converter {
    
    @Override
    public String convert(String text) {
        return Stream.of(text.split(""))
          .map(letter -> letter + letter)
          .collect(joining(""));
    }
}
