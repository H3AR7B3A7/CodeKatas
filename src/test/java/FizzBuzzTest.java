import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FizzBuzzTest {

    @Test
    void of_0is0() {
        assertEquals(FizzBuzz.of(0), "0");
    }

    @Test
    void of_1is1() {
        assertEquals(FizzBuzz.of(1), "1");
    }

    @Test
    void of_3isFizz() {
        assertEquals(FizzBuzz.of(3), "Fizz");
    }

    @Test
    void of_5isBuzz() {
        assertEquals(FizzBuzz.of(5), "Buzz");
    }

    @Test
    void of_6isFizz() {
        assertEquals(FizzBuzz.of(6), "Fizz");
    }

    @Test
    void of_10isBuzz() {
        assertEquals(FizzBuzz.of(10), "Buzz");
    }

    @Test
    void of_15isFizzBuzz() {
        assertEquals(FizzBuzz.of(15), "FizzBuzz");
    }
}