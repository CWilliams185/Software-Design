package thirdparty;
import converters.Converter;

public class Replace implements Converter {
  private String letterToReplace;
  private String letterToReplaceWith;
  
  public Replace(String letter, String replaceWith) {
    letterToReplace = letter;
    letterToReplaceWith = replaceWith;
  }                                   
  
  public String convert(String text) {
    return text.replace(letterToReplace, letterToReplaceWith);
  }
}