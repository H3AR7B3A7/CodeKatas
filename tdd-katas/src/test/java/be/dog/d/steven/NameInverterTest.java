package be.dog.d.steven;

import be.dog.d.steven.NameInverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameInverterTest {

    @Test
    void invert_null_shouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> NameInverter.invert(null));
    }

    @Test
    void invert_emptyString_shouldReturnEmptyString() {
        assertEquals("", NameInverter.invert(""));
        assertEquals("", NameInverter.invert("   "));
    }

    @Test
    void invert_firstName_shouldReturnFirstName() {
        assertEquals("John", NameInverter.invert("John"));
        assertEquals("John", NameInverter.invert("   John   "));
    }

    @Test
    void invertFirstLast_shouldReturnLastCommaFirst() {
        assertEquals("Doe, John", NameInverter.invert("John Doe"));
        assertEquals("Doe, John", NameInverter.invert("John    Doe"));
    }

    @Test
    void invert_honorificsFirstLast_shouldReturnLastCommaFirst() {
        assertEquals("Doe, John", NameInverter.invert("Mr. John Doe"));
        assertEquals("Doe, Jane", NameInverter.invert("Mrs. Jane Doe"));
    }

    @Test
    void invert_firstLastPostnominals_shouldReturnLastCommaFirstPostnominals() {
        assertEquals("Doe, John Sr.", NameInverter.invert("John Doe Sr."));
        assertEquals("Doe, John Sr. PhD.", NameInverter.invert("John Doe Sr. PhD."));
    }

    @Test
    void invert_acceptanceTests() {
        assertEquals("Doe, John Sr. PhD.", NameInverter.invert("   Mr.   John   Doe   Sr.   PhD.   "));
    }
}