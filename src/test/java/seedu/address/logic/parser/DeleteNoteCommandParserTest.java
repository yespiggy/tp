package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteNoteCommand;

public class DeleteNoteCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNoteCommand.MESSAGE_USAGE);

    private DeleteNoteCommandParser parser = new DeleteNoteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteNoteCommand() {
        Index targetIndex = Index.fromOneBased(1);
        int noteIndex = 1; // Example note index
        String userInput = targetIndex.getOneBased() + " " + noteIndex;
        DeleteNoteCommand expectedCommand = new DeleteNoteCommand(targetIndex, noteIndex);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidStartupIndex_throwsParseException() {
        String userInput = "a 1"; // Invalid startup index
        CommandParserTestUtil.assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidNoteIndex_throwsParseException() {
        String userInput = "1 a"; // Invalid note index
        CommandParserTestUtil.assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingNoteIndex_throwsParseException() {
        String userInput = "1"; // Missing note index
        CommandParserTestUtil.assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }
}



