package converters;

import java.util.List;
import static java.util.stream.Collectors.toList;

 public class ConverterFactory {

    public static Converter createConverter(String converterInfo) {


            try {
                if (converterInfo.contains(" ")) {
                    List<String> converterInfoSplit = List.of(converterInfo.split(" "));
                    Class<?> currentClass = Class.forName(converterInfoSplit.get(0));

                    Class[] paramTypes = new Class[converterInfoSplit.size()-1];

                    for (int i = 0; i < converterInfoSplit.size()-1; i++)
                        paramTypes[i] = String.class;

                    Converter currentConverter = (Converter) currentClass.getConstructor(paramTypes)
                            .newInstance(converterInfoSplit.stream().skip(1).toArray());

                    return currentConverter;

                } else {
                    Class<?> currentClass = Class.forName(converterInfo);
                    Converter currentConverter = (Converter) currentClass.getDeclaredConstructor().newInstance();

                    return currentConverter;
                }

            } catch (Exception e) {
                throw new RuntimeException("Error processing...");
            }
    }
    public static List<Converter> createConverters(List<String> convertersInfo) {

        return convertersInfo.stream()
                .map(ConverterFactory::createConverter)
                .collect(toList());
    }
}
