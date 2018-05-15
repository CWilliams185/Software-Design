package ui;

import converters.Converter;
import converters.Processor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


import static converters.ConverterFactory.createConverters;
import static java.util.stream.Collectors.toList;

public class ConverterUI {

    private static List<String> readFile(String fileName) throws IOException {

      return Files.lines(Paths.get(fileName))
         .collect(toList());
    }

   public static void useConverters(List<Converter> converters, List<String> text) {

        System.out.println(new Processor(converters).process(text.get(0)));
    }

    public static void main(String arg[]) {

        try {
            List<String> text = readFile("inputs/text.txt");
            List<Converter> converters = createConverters(readFile("inputs/blocks.txt"));

            useConverters(converters, text);

        } catch (IOException ex) {
            System.out.println("IO error in main");
        }
    }
}
