import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanConverterTest {

    RomanConverter romanConverter;

    @BeforeEach
    void setup() {
        romanConverter = new RomanConverter();
    }

    @Test
    void shouldConvert_1_to_I() {
        assertEquals("I", romanConverter.toRoman(1));
    }

    @Test
    void shouldConvert_2_to_II() {
        assertEquals("II", romanConverter.toRoman(2));
    }

    @Test
    void shouldConvert_3_to_III() {
        assertEquals("III", romanConverter.toRoman(3));
    }

    @Test
    void shouldConvert_5_to_V() {
        assertEquals("V", romanConverter.toRoman(5));
    }

    @Test
    void shouldConvert_10_to_X() {
        assertEquals("X", romanConverter.toRoman(10));
    }

    @Test
    void shouldConvert_20_to_XX() {
        assertEquals("XX", romanConverter.toRoman(20));
    }

    @Test
    void shouldConvert_21_to_XXI() {
        assertEquals("XXI", romanConverter.toRoman(21));
    }

    @Test
    void shouldConvert_4_to_IV() {
        assertEquals("IV", romanConverter.toRoman(4));
    }

    @Test
    void shouldConvert_9_to_IX() {
        assertEquals("IX", romanConverter.toRoman(9));
    }

    @Test
    void acceptanceTest() {
        assertEquals("CMXCIX", romanConverter.toRoman(999));
    }
}