package utils;

public class RegularExpressions {

    public static String anyAmountOfWhitespace() {
        return "\\s+";
    }

    public static String commaOrNewLineDelimited() {
        return "\\s*[,\\n]\\s*";
    }
}
