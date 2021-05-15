import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NameInverter {

    public static String invert(String name) {
        List<String> nameParts = breakIntoPartsIgnoringWhitespace(name);

        if (nameParts.size() < 2)
            return nameParts.get(0);

        return invert(withoutHonorifics(nameParts));
    }

    private static List<String> withoutHonorifics(List<String> nameParts) {
        if (Honorifics.isHonorific(nameParts.get(0)))
            nameParts.remove(0);
        return nameParts;
    }

    private static List<String> breakIntoPartsIgnoringWhitespace(String name) {
        return new ArrayList<>(Arrays.asList(name.trim().split(RegularExpressions.anyAmountOfWhitespace())));
    }

    private static String invert(List<String> nameParts) {
        String first = nameParts.get(0);
        String last = nameParts.get(1);
        String postNominal = findAndMergePostNominal(nameParts);
        return String.format("%s, %s %s", last, first, postNominal).trim();
    }

    private static String findAndMergePostNominal(List<String> nameParts) {
        String postNominal = "";
        for (String np : nameParts.subList(2, nameParts.size()))
            postNominal += np + " ";
        return postNominal;
    }

}

class RegularExpressions {

    public static String anyAmountOfWhitespace() {
        return "\\s+";
    }
}

class Honorifics {
    private static final List<String> KNOWN_HONORIFICS = List.of("Mr.", "Mrs.");

    public static boolean isHonorific(String s) {
        return KNOWN_HONORIFICS.contains(s);
    }
}