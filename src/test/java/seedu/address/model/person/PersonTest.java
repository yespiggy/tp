package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getDescriptions().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE)
                .withName(CommandTestUtil.VALID_NAME_B)
                .withDescriptions(CommandTestUtil.VALID_TAG_NEW).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different email, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(CommandTestUtil.VALID_EMAIL_B).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // email differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withEmail(CommandTestUtil.VALID_EMAIL_B.toUpperCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = CommandTestUtil.VALID_NAME_B + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(CommandTestUtil.VALID_NAME_B).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(CommandTestUtil.VALID_EMAIL_B).build();
        assertFalse(ALICE.equals(editedAlice));

        // different descriptions -> returns false
        editedAlice = new PersonBuilder(ALICE).withDescriptions(CommandTestUtil.VALID_DESCRIPTION_CTO).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName()
                + "{name=" + ALICE.getName()
                + ", email=" + ALICE.getEmail()
                + ", descriptions=" + ALICE.getDescriptions()
                + "}";
        assertEquals(expected, ALICE.toString());
    }
}
