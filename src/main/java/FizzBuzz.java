public class FizzBuzz {

    public static String of(int i) {
        if (i == 0)
            return "0";

        String s = "";

        if (isDivisibleBy(i, 3))
            s += "Fizz";
        if (isDivisibleBy(i, 5))
            s += "Buzz";

        return s.isEmpty() ? String.valueOf(i) : s;
    }

    private static boolean isDivisibleBy(int i, int divisor) {
        return i % divisor == 0;
    }
}