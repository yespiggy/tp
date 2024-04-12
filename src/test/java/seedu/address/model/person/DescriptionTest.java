package seedu.address.model.person;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescriptionName_throwsIllegalArgumentException() {
        String invalidDescriptionName = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescriptionName));
    }

    @Test
    public void isValidDescriptionName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Description.isValidDescriptionName(null));
    }

}
