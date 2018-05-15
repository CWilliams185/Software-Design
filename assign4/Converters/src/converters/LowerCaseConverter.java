package converters;

public class LowerCaseConverter implements Converter {

    @Override
    public String convert(String text) {
        return text.toLowerCase();
    }
}
