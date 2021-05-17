import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    @Test
    void emptyStringReturnsZero() {
        assertEquals(0, StringCalculator.calculate(""));
    }

    @Test
    void singleNumberReturnsItsValue() {
        assertEquals(3, StringCalculator.calculate("3"));
    }

    @Test
    void twoCommaDelimitedNumbersReturnSum() {
        assertEquals(9, StringCalculator.calculate("4,5"));
    }

    @Test
    void twoNewLineDelimitedNumbersReturnSum() {
        assertEquals(9, StringCalculator.calculate("4\n5"));
    }

    @Test
    void threeNumbersDelimitedEitherWayReturnSum() {
        assertEquals(9, StringCalculator.calculate("2,3\n4"));
        assertEquals(9, StringCalculator.calculate("2\n3,4"));
        assertEquals(9, StringCalculator.calculate("2,3,4"));
        assertEquals(9, StringCalculator.calculate("2\n3\n4"));
    }

    @Test
    void negativeNumbersThrowException() {
        assertThrows(IllegalArgumentException.class, () -> StringCalculator.calculate("-5"));
    }

    @Test
    void ignoringNumbersGreaterThan1000() {
        assertEquals(9, StringCalculator.calculate("4,5\n1001"));
    }

    @Test
    void acceptanceTest() {
        assertEquals(9, StringCalculator.calculate(",,\n,2,1001,\n3\n4\n"));
        assertThrows(IllegalArgumentException.class, () -> StringCalculator.calculate("2,1001,\n3\n4\n-1"));
    }
}