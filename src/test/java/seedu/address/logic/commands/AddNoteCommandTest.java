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


public class AddNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addNoteUnfilteredList_success() {
        Startup startupToEdit = model.getFilteredStartupList().get(INDEX_FIRST_STARTUP.getZeroBased());
        Note newNote = new Note("New note content");
        AddNoteCommand addNoteCommand = new AddNoteCommand(INDEX_FIRST_STARTUP, newNote);

        String expectedMessage = String.format(AddNoteCommand.MESSAGE_SUCCESS, newNote.toString());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Startup expectedStartup = new StartupBuilder(startupToEdit).withNotes(newNote.toString()).build();
        expectedModel.setStartup(startupToEdit, expectedStartup);

        assertCommandSuccess(addNoteCommand, model, expectedMessage, expectedModel);
    }

    // Additional tests for edge cases
}
