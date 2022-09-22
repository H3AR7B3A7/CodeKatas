package be.dog.d.steven;

import be.dog.d.steven.PrimeFactors;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrimeFactorsTest {

    @Test
    void of_1isNone() {
        assertEquals(List.of(), PrimeFactors.of(1));
    }

    @Test
    void of_2is2() {
        assertEquals(List.of(2), PrimeFactors.of(2));
    }

    @Test
    void of_3is3() {
        assertEquals(List.of(3), PrimeFactors.of(3));
    }

    @Test
    void of_4is2_2() {
        assertEquals(List.of(2, 2), PrimeFactors.of(4));
    }

    @Test
    void of_6is2_3() {
        assertEquals(List.of(2, 3), PrimeFactors.of(6));
    }

    @Test
    void of_8is2_2_2() {
        assertEquals(List.of(2, 2, 2), PrimeFactors.of(8));
    }

    @Test
    void of_9is3_3() {
        assertEquals(List.of(3, 3), PrimeFactors.of(9));
    }

    @Test
    void of_acceptanceTest() {
        assertEquals(List.of(2, 2, 2, 3, 3, 5, 7, 11), PrimeFactors.of(2 * 2 * 2 * 3 * 3 * 5 * 7 * 11));
    }
}