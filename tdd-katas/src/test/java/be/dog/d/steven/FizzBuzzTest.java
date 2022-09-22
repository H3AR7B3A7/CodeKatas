package be.dog.d.steven;

import be.dog.d.steven.FizzBuzz;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FizzBuzzTest {

    @Test
    void of_0is0() {
        assertEquals("0", FizzBuzz.of(0));
    }

    @Test
    void of_1is1() {
        assertEquals("1", FizzBuzz.of(1));
    }

    @Test
    void of_3isFizz() {
        assertEquals("Fizz", FizzBuzz.of(3));
    }

    @Test
    void of_5isBuzz() {
        assertEquals("Buzz", FizzBuzz.of(5));
    }

    @Test
    void of_6isFizz() {
        assertEquals("Fizz", FizzBuzz.of(6));
    }

    @Test
    void of_10isBuzz() {
        assertEquals("Buzz", FizzBuzz.of(10));
    }

    @Test
    void of_15isFizzBuzz() {
        assertEquals("FizzBuzz", FizzBuzz.of(15));
    }
}