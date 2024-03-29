package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.startup.Note;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new NoteCommand object
 */
public class EditNoteCommandParser implements Parser<EditNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditNoteCommand
     * and returns an EditNoteCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public EditNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] argParts = args.trim().split("\\s+", 3); // Split into startup index, note index, and note description

        if (argParts.length != 3) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE));
        }

        if (!argParts[0].matches("\\d+") || !argParts[1].matches("\\d+")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE));
        }

        Index startupIndex;
        Index noteIndex;
        String noteDescription;
        try {
            startupIndex = ParserUtil.parseIndex(argParts[0]); // Parse startup index
            noteIndex = ParserUtil.parseIndex(argParts[1]); // Parse note index as another Index for consistency
            noteDescription = argParts[2]; // The rest is considered the note description
            if (noteDescription.isEmpty()) {
                throw new ParseException(Note.MESSAGE_CONSTRAINTS);
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE), pe);
        }

        Note note = new Note(noteDescription); // Create a new Note object
        return new EditNoteCommand(startupIndex, noteIndex.getOneBased(), note);
    }
}



