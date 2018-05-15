package converters;

import java.util.List;

public class Processor {
    private Converter converter;

    public Processor(List<Converter> theConverter) {
      converter = theConverter.stream()
         .reduce(input -> input, Converter::andThen);
    }

    public String process(String text) {
      return converter.convert(text);
    }
}
