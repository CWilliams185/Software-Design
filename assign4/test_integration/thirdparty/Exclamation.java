package thirdparty;
import converters.Converter;

public class Exclamation implements Converter {
  private String letterToReplace;
  
  public Exclamation(String letter) {
    letterToReplace = letter;
  }                                   
  
  public String convert(String text) {
    return text.replace(letterToReplace, "!");
  }
}
