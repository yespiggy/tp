package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.startup.Note;
import seedu.address.model.startup.Startup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STARTUPS;

/**
 * Edits a Note of a startup in the address book!
 */
public class AddNoteCommand extends Command {
    public static final String COMMAND_WORD = "addnote";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to the startup identified "
            + "by the index number used in the displayed startup list. "
            + "Parameters: INDEX (must be a positive integer) n/NOTE\n"
            + "Example: " + COMMAND_WORD + " 1 n/New note for startup";

    public static final String MESSAGE_SUCCESS = "New note added to Startup: %1$s";

    private final Index index;
    private final Note note;

    public AddNoteCommand(Index index, Note note) {
        requireNonNull(index);
        requireNonNull(note);
        this.index = index;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Startup> lastShownList = model.getFilteredStartupList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STARTUP_DISPLAYED_INDEX);
        }

        Startup startupToEdit = lastShownList.get(index.getZeroBased());

        // Creates a new ArrayList from the existing notes and adds the new note
        ArrayList<Note> updatedNotes = new ArrayList<>(startupToEdit.getNotes());
        updatedNotes.add(note);

        // Now directly use the updated constructor
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
