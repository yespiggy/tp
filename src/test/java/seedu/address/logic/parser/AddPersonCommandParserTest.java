package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STARTUP;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonEmail;
import seedu.address.model.person.PersonName;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;


class AddPersonCommandParserTest {

    private AddPersonCommandParser parser = new AddPersonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder().withName("Bob Choo").withEmail("bob@example.com").build();

        // whitespace only preamble
        assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE
                + "1 " + CommandTestUtil.PERSONNAME_DESC_BOB
                + CommandTestUtil.PERSONEMAIL_DESC_BOB,
                new AddPersonCommand(INDEX_FIRST_STARTUP, expectedPerson));


        // multiple descriptions - all accepted
        Person expectedPersonMultipleDescriptions = TypicalPersons.BOB;
        assertParseSuccess(parser, "1 " + CommandTestUtil.PERSONNAME_DESC_BOB
                        + CommandTestUtil.PERSONEMAIL_DESC_BOB
                        + CommandTestUtil.DESCRIPTION_DESC_BOB_FOUNDER + CommandTestUtil.DESCRIPTION_DESC_BOB_CTO,
                new AddPersonCommand(INDEX_FIRST_STARTUP, expectedPersonMultipleDescriptions));

    }

    @Test
    public void parse_repeatedNonDescriptionValue_failure() {
        String validExpectedPersonString = CommandTestUtil.PERSONNAME_DESC_BOB
                + CommandTestUtil.PERSONEMAIL_DESC_BOB
                + CommandTestUtil.DESCRIPTION_DESC_BOB_CTO;

        //multiple names
        assertParseFailure(parser, "1 " + CommandTestUtil.PERSONNAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PERSON_NAME));

        //multiple emails
        assertParseFailure(parser, "1 " + CommandTestUtil.PERSONEMAIL_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_PERSON_EMAIL));
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
        assertParseFailure(parser, "1 " + CommandTestUtil.VALID_NAME_B
                        + CommandTestUtil.PERSONEMAIL_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, "1 " + CommandTestUtil.PERSONNAME_DESC_BOB
                        + CommandTestUtil.VALID_EMAIL_B,
                expectedMessage);

        // missing description prefix is not handled because there are cases
        // that users do not input the description, so the description without prefix
        // might be considered within email

        // all prefixes missing
        assertParseFailure(parser, "1 " + CommandTestUtil.VALID_NAME_B
                        + CommandTestUtil.VALID_EMAIL_B,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1 " + CommandTestUtil.INVALID_PERSONNAME_DESC
                + CommandTestUtil.PERSONEMAIL_DESC_BOB, PersonName.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, "1 " + CommandTestUtil.PERSONNAME_DESC_BOB
                + CommandTestUtil.INVALID_PERSONEMAIL_DESC, PersonEmail.MESSAGE_CONSTRAINTS);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, "1 " + CommandTestUtil.INVALID_PERSONNAME_DESC
                        + CommandTestUtil.INVALID_PERSONEMAIL_DESC,
                PersonName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, "1 " + CommandTestUtil.PREAMBLE_NON_EMPTY
                        + CommandTestUtil.PERSONNAME_DESC_BOB
                        + CommandTestUtil.PERSONEMAIL_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
    }

}
