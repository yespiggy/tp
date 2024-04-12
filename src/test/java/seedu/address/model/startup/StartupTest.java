package seedu.address.model.startup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStartups.STARTUP1;
import static seedu.address.testutil.TypicalStartups.STARTUP_B;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.testutil.StartupBuilder;

public class StartupTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Startup startup = new StartupBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> startup.getTags().remove(0));
    }

    @Test
    public void isSameStartup() {
        // same object -> returns true
        assertTrue(STARTUP1.isSameStartup(STARTUP1));

        // null -> returns false
        assertFalse(STARTUP1.isSameStartup(null));

        // same name, all other attributes different -> returns true
        Startup editedAlice = new StartupBuilder(STARTUP1).withPhone(CommandTestUtil.VALID_PHONE_B)
                .withEmail(CommandTestUtil.VALID_EMAIL_B)
                .withAddress(CommandTestUtil.VALID_ADDRESS_B).withValuation("0")
                .withTags(CommandTestUtil.VALID_TAG_NEW).build();
        assertTrue(STARTUP1.isSameStartup(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new StartupBuilder(STARTUP1).withName(CommandTestUtil.VALID_NAME_B).build();
        assertFalse(STARTUP1.isSameStartup(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Startup editedBob = new StartupBuilder(STARTUP_B).withName(CommandTestUtil.VALID_NAME_B.toLowerCase()).build();
        assertFalse(STARTUP_B.isSameStartup(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = CommandTestUtil.VALID_NAME_B + " ";
        editedBob = new StartupBuilder(STARTUP_B).withName(nameWithTrailingSpaces).build();
        assertFalse(STARTUP_B.isSameStartup(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Startup aliceCopy = new StartupBuilder(STARTUP1).build();
        assertTrue(STARTUP1.equals(aliceCopy));

        // same object -> returns true
        assertTrue(STARTUP1.equals(STARTUP1));

        // null -> returns false
        assertFalse(STARTUP1.equals(null));

        // different type -> returns false
        assertFalse(STARTUP1.equals(5));

        // different startup -> returns false
        assertFalse(STARTUP1.equals(STARTUP_B));

        // different name -> returns false
        Startup editedAlice = new StartupBuilder(STARTUP1).withName(CommandTestUtil.VALID_NAME_B).build();
        assertFalse(STARTUP1.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StartupBuilder(STARTUP1).withPhone(CommandTestUtil.VALID_PHONE_B).build();
        assertFalse(STARTUP1.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StartupBuilder(STARTUP1).withEmail(CommandTestUtil.VALID_EMAIL_B).build();
        assertFalse(STARTUP1.equals(editedAlice));

        // different address -> returns false
        editedAlice = new StartupBuilder(STARTUP1).withAddress(CommandTestUtil.VALID_ADDRESS_B).build();
        assertFalse(STARTUP1.equals(editedAlice));

        // different funding stage -> returns false
        editedAlice = new StartupBuilder(STARTUP1).withFundingStage("PS").build();
        assertFalse(STARTUP1.equals(editedAlice));

        // different industry -> returns false
        editedAlice = new StartupBuilder(STARTUP1).withIndustry("web3").build();
        assertFalse(STARTUP1.equals(editedAlice));

        // different valuation -> returns false
        editedAlice = new StartupBuilder(STARTUP1).withValuation(CommandTestUtil.VALID_VALUATION_A).build();
        assertFalse(STARTUP1.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StartupBuilder(STARTUP1).withTags(CommandTestUtil.VALID_TAG_NEW).build();
        assertFalse(STARTUP1.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Startup.class.getCanonicalName()
                + "{name=" + STARTUP1.getName()
                + ", industry=" + STARTUP1.getIndustry()
                + ", funding stage=" + STARTUP1.getFundingStage()
                + ", phone=" + STARTUP1.getPhone()
                + ", email=" + STARTUP1.getEmail()
                + ", address=" + STARTUP1.getAddress()
                + ", valuation=" + STARTUP1.getValuation()
                + ", tags=" + STARTUP1.getTags()
                + ", persons=" + STARTUP1.getPersons()
                + ", notes=" + STARTUP1.getNotes() + "}";
        assertEquals(expected, STARTUP1.toString());
    }
}
