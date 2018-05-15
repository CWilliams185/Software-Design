package converters;

public class LetterBlocker implements Converter{

    private final String letterToBlock;

    public LetterBlocker(String theLetterToBlock) {
        letterToBlock = theLetterToBlock;
    }

    @Override
    public String convert(String text) { return text.replaceAll(letterToBlock, ""); }
}

