package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteNoteCommand object
 */
public class DeleteNoteCommandParser implements Parser<DeleteNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteNoteCommand
     * and returns a DeleteNoteCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeleteNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] argParts = args.trim().split("\\s+"); // Split into startup index and note index

        if (argParts.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNoteCommand.MESSAGE_USAGE));
        }

        if (!argParts[0].matches("\\d+") || !argParts[1].matches("\\d+")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNoteCommand.MESSAGE_USAGE));
        }

        Index startupIndex;
        Index noteIndex;
        try {
            startupIndex = ParserUtil.parseIndex(argParts[0]); // Parse startup index
            noteIndex = ParserUtil.parseIndex(argParts[1]); // Parse note index
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteNoteCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteNoteCommand(startupIndex, noteIndex.getOneBased());
    }
}




