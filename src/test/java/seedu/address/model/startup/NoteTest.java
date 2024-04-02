package seedu.address.model.startup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void constructor_invalidNote_throwsIllegalArgumentException() {
        String invalidNote = "";
        assertThrows(IllegalArgumentException.class, () -> new Note(invalidNote));
    }

    @Test
    public void isValidNote() {
        // null note
        assertThrows(NullPointerException.class, () -> Note.isValidNote(null));

        // invalid notes
        assertFalse(Note.isValidNote("")); // empty string
        assertFalse(Note.isValidNote(" ")); // spaces only

        // valid notes
        assertTrue(Note.isValidNote("Note content here")); // regular note
        assertTrue(Note.isValidNote("N")); // one character
        assertTrue(Note.isValidNote("This is a very long note content just to test if the "
                + "validation works for long strings as well.")); // long note
    }

    @Test
    public void equals() {
        Note note = new Note("A valid note");

        // same values -> returns true
        assertTrue(note.equals(new Note("A valid note")));

        // same object -> returns true
        assertTrue(note.equals(note));

        // null -> returns false
        assertFalse(note.equals(null));

        // different types -> returns false
        assertFalse(note.equals(5));

        // different values -> returns false
        assertFalse(note.equals(new Note("Another valid note")));
    }
}

