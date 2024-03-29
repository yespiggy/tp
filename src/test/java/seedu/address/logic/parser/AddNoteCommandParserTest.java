package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.model.startup.Note;

public class AddNoteCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE);

    private AddNoteCommandParser parser = new AddNoteCommandParser();

    @Test
    public void parse_validArgs_returnsAddNoteCommand() {
        Index targetIndex = Index.fromOneBased(1);
        String userInput = targetIndex.getOneBased() + " Some note";
        AddNoteCommand expectedCommand = new AddNoteCommand(targetIndex, new Note("Some note"));

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        String userInput = "a Some note";
        CommandParserTestUtil.assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }
}

