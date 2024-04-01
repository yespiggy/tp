package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STARTUP;
import static seedu.address.testutil.TypicalStartups.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.startup.Note;
import seedu.address.model.startup.Startup;
import seedu.address.testutil.StartupBuilder;

public class EditNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_editNoteUnfilteredList_success() {
        Startup startupToEdit = model.getFilteredStartupList().get(INDEX_FIRST_STARTUP.getZeroBased());
        Note editedNote = new Note("Edited note content");
        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_FIRST_STARTUP, 1, editedNote);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Startup expectedStartup = new StartupBuilder(startupToEdit).withNotes(editedNote.toString(),
                "Looking into Series A funding").build();
        expectedModel.setStartup(startupToEdit, expectedStartup);
        String expectedMessage = String.format(EditNoteCommand.MESSAGE_SUCCESS, expectedStartup);

        assertCommandSuccess(editNoteCommand, model, expectedMessage, expectedModel);
    }

    // Additional tests for edge cases
}

