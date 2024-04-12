package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeletePersonCommand;


public class DeletePersonCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE);

    private DeletePersonCommandParser parser = new DeletePersonCommandParser();

    @Test
    public void parse_validArgs_returnsDeletePersonCommand() {
        Index targetIndex = Index.fromOneBased(1);
        int personIndex = 1; // Example person index
        String userInput = targetIndex.getOneBased() + " " + personIndex;
        DeletePersonCommand expectedCommand = new DeletePersonCommand(targetIndex, personIndex);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidStartupIndex_throwsParseException() {
        String userInput = "a 1"; // Invalid startup index
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPersonIndex_throwsParseException() {
        String userInput = "1 a"; // Invalid person index
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingPersonIndex_throwsParseException() {
        String userInput = "1"; // Missing person index
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }
}



