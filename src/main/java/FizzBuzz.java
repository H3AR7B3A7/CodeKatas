public class FizzBuzz {

    public static String of(int n) {
        if (n == 0)
            return "0";

        String s = "";

        if (isDivisibleBy(n, 3))
            s += "Fizz";
        if (isDivisibleBy(n, 5))
            s += "Buzz";

        return s.isEmpty() ? String.valueOf(n) : s;
    }

    private static boolean isDivisibleBy(int n, int divisor) {
        return n % divisor == 0;
    }
}