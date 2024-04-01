package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.startup.Note;
import seedu.address.model.startup.Startup;
import seedu.address.testutil.StartupBuilder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STARTUP;
import static seedu.address.testutil.TypicalStartups.getTypicalAddressBook;


public class DeleteNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_deleteNoteUnfilteredList_success() {
        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(INDEX_FIRST_STARTUP, 1);
        Startup startupToEdit = model.getFilteredStartupList().get(INDEX_FIRST_STARTUP.getZeroBased());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Startup expectedStartup = new StartupBuilder(startupToEdit).withNotes("Looking into Series A funding").build();
        String expectedMessage = String.format(DeleteNoteCommand.MESSAGE_SUCCESS, expectedStartup);
        expectedModel.setStartup(startupToEdit, expectedStartup);
        assertCommandSuccess(deleteNoteCommand, model, expectedMessage, expectedModel);
    }

    // Additional tests for edge cases
}
