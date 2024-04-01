package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStartups.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STARTUP;

import org.junit.jupiter.api.Test;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.startup.Note;
import seedu.address.model.startup.Startup;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.StartupBuilder;


public class AddNoteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_addNoteUnfilteredList_success() {
        Startup startupToEdit = model.getFilteredStartupList().get(INDEX_FIRST_STARTUP.getZeroBased());
        Note newNote = new Note("New note content");
        AddNoteCommand addNoteCommand = new AddNoteCommand(INDEX_FIRST_STARTUP, newNote);

        Startup expectedStartup = new StartupBuilder(startupToEdit).withNotes("Innovative project ideas", "Looking into Series A funding", newNote.toString()).build();
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        expectedModel.setStartup(startupToEdit, expectedStartup);
        String expectedMessage = String.format(AddNoteCommand.MESSAGE_SUCCESS, expectedStartup);

        assertCommandSuccess(addNoteCommand, model, expectedMessage, expectedModel);
    }

}
