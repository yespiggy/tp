package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.person.Person;
import seedu.address.model.startup.*;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.StartupBuilder;
import seedu.address.testutil.TypicalPersons;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STARTUP;
import static seedu.address.testutil.TypicalStartups.AMY;
import static seedu.address.testutil.TypicalStartups.BOB;

class AddPersonCommandParserTest {

    private AddPersonCommandParser parser = new AddPersonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder().withName("Bob Choo").withEmail("bob@example.com").build();

        // whitespace only preamble
        assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE
                + "1 " + CommandTestUtil.PERSONNAME_DESC_BOB
                + CommandTestUtil.PERSONEMAIL_DESC_BOB
                , new AddPersonCommand(INDEX_FIRST_STARTUP, expectedPerson));


        // multiple descriptions - all accepted
        Person expectedPersonMultipleDescriptions = TypicalPersons.BOB;
        assertParseSuccess(parser, "1 " + CommandTestUtil.PERSONNAME_DESC_BOB
                        + CommandTestUtil.PERSONEMAIL_DESC_BOB
                        + CommandTestUtil.DESCRIPTION_DESC_BOB_FOUNDER + CommandTestUtil.DESCRIPTION_DESC_BOB_CTO,
                new AddPersonCommand(INDEX_FIRST_STARTUP, expectedPersonMultipleDescriptions));

    }

    @Test
    public void parse_repeatedNonDescriptionValue_failure() {
        String validExpectedPersonString = "1 " + CommandTestUtil.PERSONNAME_DESC_BOB
                + CommandTestUtil.PERSONEMAIL_DESC_BOB
                + CommandTestUtil.DESCRIPTION_DESC_BOB_CTO;

        //multiple names
        //assertParseFailure(parser, );

        //multiple emails

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero descriptions
        Person expectedPerson = new PersonBuilder().withName("Bob Choo").withEmail("bob@example.com").build();
        assertParseSuccess(parser, "1 " + CommandTestUtil.PERSONNAME_DESC_BOB
                        + CommandTestUtil.PERSONEMAIL_DESC_BOB,
                new AddPersonCommand(INDEX_FIRST_STARTUP, expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, "1 " + CommandTestUtil.VALID_NAME_BOB
                        + CommandTestUtil.PERSONEMAIL_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, "1 " + CommandTestUtil.PERSONNAME_DESC_BOB
                        + CommandTestUtil.VALID_EMAIL_BOB,
                expectedMessage);

        // missing description prefix is not handled because there are cases
        // that users do not input the description, so the description without prefix
        // might be considered within email

        // all prefixes missing
        assertParseFailure(parser, "1 " + CommandTestUtil.VALID_NAME_BOB
                        + CommandTestUtil.VALID_EMAIL_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.VALUATION_DESC_BOB
                + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.INVALID_EMAIL_DESC
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.VALUATION_DESC_BOB
                + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);



        // two invalid values, only first invalid value reported
        assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC
                        + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.VALUATION_DESC_BOB
                        + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                        + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, CommandTestUtil.PREAMBLE_NON_EMPTY
                        + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.VALUATION_DESC_BOB
                        + CommandTestUtil.FUNDING_DESC_BOB + CommandTestUtil.INDUSTRY_DESC_BOB
                        + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_HUSBAND
                        + CommandTestUtil.TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

}