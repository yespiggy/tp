package seedu.address.model.startup;

import org.junit.jupiter.api.Test;

import javax.xml.validation.Validator;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

public class ValuationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Valuation(null));
    }

    @Test
    public void constructor_invalidValuation_throwsIllegalArgumentException() {
        String invalidValuation = "";
        assertThrows(IllegalArgumentException.class, () -> new Valuation(invalidValuation));
    }

    @Test
    public void isValidValuation() {
        // null valuation
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid valuation
        assertFalse(Valuation.isValidValuation("")); // empty string
        assertFalse(Valuation.isValidValuation(" ")); // spaces only

        assertFalse(Valuation.isValidValuation("-1")); // negative numbers
        assertFalse(Valuation.isValidValuation("-1000"));

        // numbers above & equal to 5 trillion
        assertFalse(Valuation.isValidValuation("8200000000000"));
        assertFalse(Valuation.isValidValuation("5000000000000"));

        // valid valuations
        assertTrue(Valuation.isValidValuation("911"));
        assertTrue(Valuation.isValidValuation("93121534"));

        // Above a trillion, less than 5 trillion
        assertTrue(Valuation.isValidValuation("3000000000000"));
        assertTrue(Valuation.isValidValuation("100000500000"));
    }

    @Test
    public void testValuationRepresentation() {

        // Below 1000
        assertEquals("0", Valuation.reformatValuation("0"));
        assertEquals("1", Valuation.reformatValuation("1"));
        assertEquals("1k", Valuation.reformatValuation("1000"));
        assertEquals("999", Valuation.reformatValuation("999"));

        // Above 1000
        assertEquals("2k", Valuation.reformatValuation("2000"));

        // Drop the decimal to maintain 4 characters, i.e. not 21.1k but 21k.
        assertEquals("21k", Valuation.reformatValuation("21100"));

        // Can be represented in 4 characters.
        assertEquals("5.8k", Valuation.reformatValuation("5800"));

        // Above 1000, breach 4 characters.
        assertEquals("101k", Valuation.reformatValuation("101800"));

        // Above 1m no breach in character.
        assertEquals("2m", Valuation.reformatValuation("2000000"));
        assertEquals("7.8m", Valuation.reformatValuation("7800000"));

        // Above 1m, breach in character.
        assertEquals("92m", Valuation.reformatValuation("92150000"));
        assertEquals("9.9m", Valuation.reformatValuation("9999999"));

        // Above 100m, no breach in character.
        assertEquals("123m", Valuation.reformatValuation("123200000"));

        // Above 1 trillion, no breach in characters.
        assertEquals("1t", Valuation.reformatValuation("1000000000000"));
        assertEquals("2.2t", Valuation.reformatValuation("2200000000000"));

        // Above 1 trillion, breach in characters.
        assertEquals("3.2t", Valuation.reformatValuation("3200011100000"));
    }

    @Test
    public void equals() {
        Valuation valuation = new Valuation("999");

        // same values -> returns true
        assertTrue(valuation.equals(new Valuation("999")));

        // same object -> returns true
        assertTrue(valuation.equals(valuation));

        // null -> returns false
        assertFalse(valuation.equals(null));

        // different types -> returns false
        assertFalse(valuation.equals(5.0f));

        // different values -> returns false
        assertFalse(valuation.equals(new Valuation("995")));
    }
}
