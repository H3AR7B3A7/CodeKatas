package be.dog.d.steven;

import be.dog.d.steven.utils.RegularExpressions;

import java.util.List;

public class StringCalculator {

    public static int calculate(String input) {
        List<String> numbers = getDelimitedStrings(input);
        return getSum(numbers);
    }

    private static int getSum(List<String> numbers) {
        int sum = 0;
        for (String s : numbers) {
            if (!s.isEmpty()) {
                int value = Integer.parseInt(s);
                if (value < 0)
                    throw new IllegalArgumentException("No negative numbers allowed.");
                if (value <= 1000)
                    sum += value;
            }
        }
        return sum;
    }

    private static List<String> getDelimitedStrings(String input) {
        return List.of(input.split(RegularExpressions.commaOrNewLineDelimited()));
    }
}