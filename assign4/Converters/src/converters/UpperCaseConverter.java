package converters;

public class UpperCaseConverter implements Converter {

    @Override
    public String convert(String text) {
        return text.toUpperCase();
    }
}
