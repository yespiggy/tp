package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.startup.Note;
import seedu.address.model.startup.Startup;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Edits a Note of a startup in the address book!
 */
public class DeleteNoteCommand extends Command {
    public static final String COMMAND_WORD = "deletenote";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a note from the startup identified "
            + "by the index number used in the displayed startup list. "
            + "Parameters: INDEX (must be a positive integer) noteIndex (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 1";

    public static final String MESSAGE_SUCCESS = "Note deleted from Startup: %1$s";

    private final Index index;
    private final int noteIndex;

    public DeleteNoteCommand(Index index, int noteIndex) {
        requireNonNull(index);
        this.index = index;
        this.noteIndex = noteIndex - 1; // User input is 1-based, internal storage is 0-based.
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Startup> lastShownList = model.getFilteredStartupList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STARTUP_DISPLAYED_INDEX);
        }

        Startup startupToEdit = lastShownList.get(index.getZeroBased());
        if (noteIndex >= startupToEdit.getNotes().size()) {
            throw new CommandException("The note index provided is invalid.");
        }

        ArrayList<Note> updatedNotes = new ArrayList<>(startupToEdit.getNotes());
        updatedNotes.remove(noteIndex);
        Startup editedStartup = new Startup(
                startupToEdit.getName(),
                startupToEdit.getFundingStage(),
                startupToEdit.getIndustry(),
                startupToEdit.getPhone(),
                startupToEdit.getEmail(),
                startupToEdit.getAddress(),
                startupToEdit.getTags(),
                updatedNotes
        );

        model.setStartup(startupToEdit, editedStartup);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedStartup));
    }
}


