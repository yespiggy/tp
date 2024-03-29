package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.startup.FundingStageContainsKeywordsPredicate;
import seedu.address.model.startup.IndustryContainsKeywordsPredicate;
import seedu.address.model.startup.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "find n/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "find i/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "find f/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "find d/dd", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }



    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindbyNameCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "find n/Alice Bob", expectedFindbyNameCommand);

        FindCommand expectedFindbyIndustryCommand =
                new FindCommand(new IndustryContainsKeywordsPredicate(Arrays.asList("Web3", "AI")));
        assertParseSuccess(parser, "find i/Web3 AI", expectedFindbyIndustryCommand);

        FindCommand expectedFindbyFundingStageCommand =
                new FindCommand(new FundingStageContainsKeywordsPredicate(Arrays.asList("A", "C")));
        assertParseSuccess(parser, "find f/A C", expectedFindbyFundingStageCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "find n/ \n Alice \n \t Bob  \t", expectedFindbyNameCommand);
        assertParseSuccess(parser, "find i/ \n Web3 \n \t AI  \t", expectedFindbyIndustryCommand);
        assertParseSuccess(parser, "find f/ \n A \n \t C  \t", expectedFindbyFundingStageCommand);
    }

}
