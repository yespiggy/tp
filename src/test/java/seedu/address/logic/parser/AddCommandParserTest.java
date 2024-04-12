package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStartups.STARTUP_A;
import static seedu.address.testutil.TypicalStartups.STARTUP_B;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.startup.Address;
import seedu.address.model.startup.Email;
import seedu.address.model.startup.FundingStage;
import seedu.address.model.startup.Industry;
import seedu.address.model.startup.Name;
import seedu.address.model.startup.Phone;
import seedu.address.model.startup.Startup;
import seedu.address.model.startup.Valuation;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.StartupBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Startup expectedStartup = new StartupBuilder(STARTUP_B).withTags(CommandTestUtil.VALID_TAG_POTENTIAL).build();

        // whitespace only preamble
        assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE
                + CommandTestUtil.NAME_DESC_B + CommandTestUtil.PHONE_DESC_B
                + CommandTestUtil.EMAIL_DESC_B
                + CommandTestUtil.INDUSTRY_DESC_B + CommandTestUtil.FUNDING_DESC_B
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.VALUATION_DESC_B
                + CommandTestUtil.TAG_DESC_POTENTIAL, new AddCommand(expectedStartup));


        // multiple tags - all accepted
        Startup expectedStartupMultipleTags = new StartupBuilder(STARTUP_B).withTags(
            CommandTestUtil.VALID_TAG_POTENTIAL, CommandTestUtil.VALID_TAG_NEW)
                .build();
        assertParseSuccess(parser,
                CommandTestUtil.NAME_DESC_B + CommandTestUtil.PHONE_DESC_B
                + CommandTestUtil.EMAIL_DESC_B + CommandTestUtil.ADDRESS_DESC_B
                + CommandTestUtil.VALUATION_DESC_B
                + CommandTestUtil.INDUSTRY_DESC_B + CommandTestUtil.FUNDING_DESC_B
                + CommandTestUtil.TAG_DESC_NEW + CommandTestUtil.TAG_DESC_POTENTIAL,
                new AddCommand(expectedStartupMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedStartupString = CommandTestUtil.NAME_DESC_B
                + CommandTestUtil.INDUSTRY_DESC_B + CommandTestUtil.FUNDING_DESC_B
                + CommandTestUtil.VALUATION_DESC_B
                + CommandTestUtil.PHONE_DESC_B + CommandTestUtil.EMAIL_DESC_B
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.TAG_DESC_POTENTIAL;

        // multiple names
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_A + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_NAME));

        // multiple phones
        assertParseFailure(parser, CommandTestUtil.PHONE_DESC_A + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PHONE));

        // multiple emails
        assertParseFailure(parser, CommandTestUtil.EMAIL_DESC_A + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_EMAIL));

        // multiple addresses
        assertParseFailure(parser, CommandTestUtil.ADDRESS_DESC_A + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_ADDRESS));

        // multiple funding stage
        assertParseFailure(parser, CommandTestUtil.FUNDING_DESC_A + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_FUNDING_STAGE));

        // multiple industry
        assertParseFailure(parser, CommandTestUtil.INDUSTRY_DESC_A + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_INDUSTRY));

        // multiple valuations
        assertParseFailure(parser, CommandTestUtil.VALUATION_DESC_A + validExpectedStartupString,
            Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_VALUATION));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedStartupString + CommandTestUtil.PHONE_DESC_A
                        + CommandTestUtil.EMAIL_DESC_A + CommandTestUtil.NAME_DESC_A
                        + CommandTestUtil.ADDRESS_DESC_A,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_NAME,
                    CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_PHONE));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, CommandTestUtil.INVALID_EMAIL_DESC + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, CommandTestUtil.INVALID_PHONE_DESC + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, CommandTestUtil.INVALID_ADDRESS_DESC + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_ADDRESS));

        // invalid industry
        assertParseFailure(parser, CommandTestUtil.INVALID_INDUSTRY_DESC + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_INDUSTRY));

        // invalid funding stage
        assertParseFailure(parser, CommandTestUtil.INVALID_FUNDING_DESC + validExpectedStartupString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_FUNDING_STAGE));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedStartupString + CommandTestUtil.INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_NAME));

        // invalid email
        assertParseFailure(parser, validExpectedStartupString + CommandTestUtil.INVALID_EMAIL_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_EMAIL));

        // invalid phone
        assertParseFailure(parser, validExpectedStartupString + CommandTestUtil.INVALID_PHONE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PHONE));

        // invalid address
        assertParseFailure(parser, validExpectedStartupString + CommandTestUtil.INVALID_ADDRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_ADDRESS));

        // invalid industry
        assertParseFailure(parser, validExpectedStartupString + CommandTestUtil.INVALID_INDUSTRY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_INDUSTRY));

        // invalid funding stage
        assertParseFailure(parser, validExpectedStartupString + CommandTestUtil.INVALID_FUNDING_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_FUNDING_STAGE));

        // invalid valuation
        assertParseFailure(parser, validExpectedStartupString + CommandTestUtil.INVALID_VALUATION_DESC,
            Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_VALUATION));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Startup expectedStartup = new StartupBuilder(STARTUP_A).withTags().build();
        assertParseSuccess(parser, CommandTestUtil.NAME_DESC_A
                + CommandTestUtil.INDUSTRY_DESC_A + CommandTestUtil.FUNDING_DESC_A
                + CommandTestUtil.PHONE_DESC_A + CommandTestUtil.VALUATION_DESC_A
                + CommandTestUtil.EMAIL_DESC_A + CommandTestUtil.ADDRESS_DESC_A,
                new AddCommand(expectedStartup));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_B
                + CommandTestUtil.FUNDING_DESC_B + CommandTestUtil.INDUSTRY_DESC_B
                + CommandTestUtil.PHONE_DESC_B + CommandTestUtil.EMAIL_DESC_B
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.VALUATION_DESC_B,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B
                + CommandTestUtil.FUNDING_DESC_B + CommandTestUtil.INDUSTRY_DESC_B
                + CommandTestUtil.VALID_PHONE_B + CommandTestUtil.VALUATION_DESC_B
                + CommandTestUtil.EMAIL_DESC_B + CommandTestUtil.ADDRESS_DESC_B,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B
                + CommandTestUtil.FUNDING_DESC_B + CommandTestUtil.INDUSTRY_DESC_B
                + CommandTestUtil.PHONE_DESC_B + CommandTestUtil.VALID_EMAIL_B
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.VALUATION_DESC_B,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B
                + CommandTestUtil.FUNDING_DESC_B + CommandTestUtil.INDUSTRY_DESC_B
                + CommandTestUtil.PHONE_DESC_B + CommandTestUtil.EMAIL_DESC_B
                + CommandTestUtil.VALID_ADDRESS_B + CommandTestUtil.VALUATION_DESC_B,
                expectedMessage);

        // missing industry prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B
            + CommandTestUtil.FUNDING_DESC_B + CommandTestUtil.ADDRESS_DESC_B
            + CommandTestUtil.PHONE_DESC_B + CommandTestUtil.EMAIL_DESC_B
            + CommandTestUtil.VALID_ADDRESS_B + CommandTestUtil.VALUATION_DESC_B,
            expectedMessage);

        // missing funding stage prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B
            + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.INDUSTRY_DESC_B
            + CommandTestUtil.PHONE_DESC_B + CommandTestUtil.EMAIL_DESC_B
            + CommandTestUtil.VALID_ADDRESS_B + CommandTestUtil.VALUATION_DESC_B,
            expectedMessage);

        // missing valuation prefix
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B
            + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.INDUSTRY_DESC_B
            + CommandTestUtil.PHONE_DESC_B + CommandTestUtil.EMAIL_DESC_B
            + CommandTestUtil.VALID_ADDRESS_B + CommandTestUtil.FUNDING_DESC_B,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_B
                + CommandTestUtil.FUNDING_DESC_B + CommandTestUtil.INDUSTRY_DESC_B
                + CommandTestUtil.VALID_PHONE_B + CommandTestUtil.VALID_EMAIL_B
                + CommandTestUtil.VALID_ADDRESS_B,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC
                + CommandTestUtil.PHONE_DESC_B + CommandTestUtil.EMAIL_DESC_B
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.VALUATION_DESC_B
                + CommandTestUtil.FUNDING_DESC_B + CommandTestUtil.INDUSTRY_DESC_B
                + CommandTestUtil.TAG_DESC_NEW
                + CommandTestUtil.TAG_DESC_POTENTIAL, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B
                + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.EMAIL_DESC_B
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.VALUATION_DESC_B
                + CommandTestUtil.FUNDING_DESC_B + CommandTestUtil.INDUSTRY_DESC_B
                + CommandTestUtil.TAG_DESC_NEW
                + CommandTestUtil.TAG_DESC_POTENTIAL, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B
                + CommandTestUtil.PHONE_DESC_B + CommandTestUtil.INVALID_EMAIL_DESC
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.VALUATION_DESC_B
                + CommandTestUtil.FUNDING_DESC_B + CommandTestUtil.INDUSTRY_DESC_B
                + CommandTestUtil.TAG_DESC_NEW
                + CommandTestUtil.TAG_DESC_POTENTIAL, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B
                + CommandTestUtil.PHONE_DESC_B + CommandTestUtil.EMAIL_DESC_B
                + CommandTestUtil.INVALID_ADDRESS_DESC + CommandTestUtil.VALUATION_DESC_B
                + CommandTestUtil.FUNDING_DESC_B + CommandTestUtil.INDUSTRY_DESC_B
                + CommandTestUtil.TAG_DESC_NEW
                + CommandTestUtil.TAG_DESC_POTENTIAL, Address.MESSAGE_CONSTRAINTS);

        // invalid funding stage
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B
              + CommandTestUtil.PHONE_DESC_B + CommandTestUtil.EMAIL_DESC_B
              + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.INVALID_FUNDING_DESC
                + CommandTestUtil.INDUSTRY_DESC_B + CommandTestUtil.VALUATION_DESC_B
              + CommandTestUtil.TAG_DESC_NEW
              + CommandTestUtil.TAG_DESC_POTENTIAL, FundingStage.MESSAGE_CONSTRAINTS);

        // invalid industry
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B
              + CommandTestUtil.PHONE_DESC_B + CommandTestUtil.EMAIL_DESC_B
              + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.VALUATION_DESC_B
              + CommandTestUtil.FUNDING_DESC_B + CommandTestUtil.INVALID_INDUSTRY_DESC
              + CommandTestUtil.TAG_DESC_NEW
              + CommandTestUtil.TAG_DESC_POTENTIAL, Industry.MESSAGE_CONSTRAINTS);

        // invalid valuation
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B
              + CommandTestUtil.PHONE_DESC_B + CommandTestUtil.EMAIL_DESC_B
              + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.INVALID_VALUATION_DESC
              + CommandTestUtil.FUNDING_DESC_B + CommandTestUtil.INDUSTRY_DESC_B
              + CommandTestUtil.TAG_DESC_NEW
              + CommandTestUtil.TAG_DESC_POTENTIAL, Valuation.MESSAGE_CONSTRAINTS);


        // invalid tag
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_B
                + CommandTestUtil.PHONE_DESC_B + CommandTestUtil.EMAIL_DESC_B
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.VALUATION_DESC_B
                + CommandTestUtil.FUNDING_DESC_B + CommandTestUtil.INDUSTRY_DESC_B
                + CommandTestUtil.INVALID_TAG_DESC
                + CommandTestUtil.VALID_TAG_POTENTIAL, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC
                + CommandTestUtil.PHONE_DESC_B + CommandTestUtil.VALUATION_DESC_B
                + CommandTestUtil.FUNDING_DESC_B + CommandTestUtil.INDUSTRY_DESC_B
                + CommandTestUtil.EMAIL_DESC_B + CommandTestUtil.INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, CommandTestUtil.PREAMBLE_NON_EMPTY
                + CommandTestUtil.NAME_DESC_B + CommandTestUtil.PHONE_DESC_B
                + CommandTestUtil.EMAIL_DESC_B + CommandTestUtil.VALUATION_DESC_B
                + CommandTestUtil.FUNDING_DESC_B + CommandTestUtil.INDUSTRY_DESC_B
                + CommandTestUtil.ADDRESS_DESC_B + CommandTestUtil.TAG_DESC_NEW
                + CommandTestUtil.TAG_DESC_POTENTIAL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
