package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditNoteCommand;
import seedu.address.model.startup.Note;

public class EditNoteCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE);

    private EditNoteCommandParser parser = new EditNoteCommandParser();

    @Test
    public void parse_validArgs_returnsEditNoteCommand() {
        Index startupIndex = Index.fromOneBased(1);
        Index targetIndex = Index.fromOneBased(1);
        String noteContent = "Updated note content";
        String userInput = startupIndex.getOneBased() + " " + targetIndex.getOneBased() + " " + noteContent;
        EditNoteCommand expectedCommand = new EditNoteCommand(startupIndex, targetIndex.getOneBased(), new Note(noteContent));

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidStartupIndex_throwsParseException() {
        String userInput = "a 1 Updated note content"; // Invalid startup index
        CommandParserTestUtil.assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidNoteIndex_throwsParseException() {
        String userInput = "1 a Updated note content"; // Invalid note index
        CommandParserTestUtil.assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingNoteContent_throwsParseException() {
        String userInput = "1 1"; // Missing note content
        CommandParserTestUtil.assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    // Add any other relevant tests
}


