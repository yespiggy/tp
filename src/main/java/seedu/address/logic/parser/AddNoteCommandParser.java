package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.startup.Note;

/**
 * Parses input arguments and creates a new NoteCommand object
 */
public class AddNoteCommandParser implements Parser<AddNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddNoteCommand
     * and returns an AddNoteCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] argParts = args.trim().split("\\s+", 2); // Split into index and note description

        if (argParts.length != 2 || !argParts[0].matches("\\d+")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
        }

        Index index;
        String noteDescription;
        try {
            index = ParserUtil.parseIndex(argParts[0]); // Parse index
            noteDescription = argParts[1]; // The rest is considered the note description
            if (noteDescription.isEmpty()) {
                throw new ParseException(Note.MESSAGE_CONSTRAINTS);
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE), pe);
        }

        Note note = new Note(noteDescription); // Create a new Note object
        return new AddNoteCommand(index, note);
    }
}


