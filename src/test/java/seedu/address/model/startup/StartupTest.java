package seedu.address.model.startup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStartups.ALICE;
import static seedu.address.testutil.TypicalStartups.BOB;

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
        assertTrue(ALICE.isSameStartup(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameStartup(null));

        // same name, all other attributes different -> returns true
        Startup editedAlice = new StartupBuilder(ALICE).withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withEmail(CommandTestUtil.VALID_EMAIL_BOB)
                .withAddress(CommandTestUtil.VALID_ADDRESS_BOB).withValuation("0")
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameStartup(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new StartupBuilder(ALICE).withName(CommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameStartup(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Startup editedBob = new StartupBuilder(BOB).withName(CommandTestUtil.VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameStartup(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = CommandTestUtil.VALID_NAME_BOB + " ";
        editedBob = new StartupBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameStartup(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Startup aliceCopy = new StartupBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different startup -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Startup editedAlice = new StartupBuilder(ALICE).withName(CommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StartupBuilder(ALICE).withPhone(CommandTestUtil.VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StartupBuilder(ALICE).withEmail(CommandTestUtil.VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new StartupBuilder(ALICE).withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different funding stage -> returns false
        editedAlice = new StartupBuilder(ALICE).withFundingStage("PS").build();
        assertFalse(ALICE.equals(editedAlice));

        // different industry -> returns false
        editedAlice = new StartupBuilder(ALICE).withIndustry("web3").build();
        assertFalse(ALICE.equals(editedAlice));

        // different valuation -> returns false
        editedAlice = new StartupBuilder(ALICE).withValuation(CommandTestUtil.VALID_VALUATION_AMY).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StartupBuilder(ALICE).withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Startup.class.getCanonicalName()
                + "{name=" + ALICE.getName()
                + ", industry=" + ALICE.getIndustry()
                + ", funding stage=" + ALICE.getFundingStage()
                + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail()
                + ", address=" + ALICE.getAddress()
                + ", valuation=" + ALICE.getValuation()
                + ", tags=" + ALICE.getTags()
                + ", persons=" + ALICE.getPersons()
                + ", notes=" + ALICE.getNotes() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
