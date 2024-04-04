package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.startup.NameContainsKeywordsPredicate;
import seedu.address.model.startup.Startup;
import seedu.address.testutil.EditStartupDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_A = "Startup A";
    public static final String VALID_NAME_B = "Startup B";
    public static final String VALID_PHONE_A = "11111111";
    public static final String VALID_PHONE_B = "22222222";
    public static final String VALID_EMAIL_A = "amy@example.com";

    public static final String VALID_VALUATION_A = "0";

    public static final String VALID_VALUATION_B = "1000";

    public static final String VALID_EMAIL_B = "bob@example.com";
    public static final String VALID_ADDRESS_A = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_B = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_NEW = "new";
    public static final String VALID_TAG_POTENTIAL = "potential";
    public static final String VALID_DESCRIPTION_FOUNDER = "founder";
    public static final String VALID_DESCRIPTION_CTO = "Chief Technology Officer";

    public static final String VALID_INDUSTRY_B = "Finance";
    public static final String VALID_INDUSTRY_A = "Finance";

    public static final String VALID_FUNDING_B = "A";
    public static final String VALID_FUNDING_A = "A";

    public static final String NAME_DESC_A = " " + CliSyntax.PREFIX_NAME + VALID_NAME_A;
    public static final String NAME_DESC_B = " " + CliSyntax.PREFIX_NAME + VALID_NAME_B;

    public static final String PERSONNAME_DESC_AMY = " " + CliSyntax.PREFIX_PERSON_NAME + VALID_NAME_A;
    public static final String PERSONNAME_DESC_BOB = " " + CliSyntax.PREFIX_PERSON_NAME + VALID_NAME_B;

    public static final String PERSONEMAIL_DESC_AMY = " " + CliSyntax.PREFIX_PERSON_EMAIL + VALID_EMAIL_A;
    public static final String PERSONEMAIL_DESC_BOB = " " + CliSyntax.PREFIX_PERSON_EMAIL + VALID_EMAIL_B;

    public static final String PERSONDESC_DESC_CTO = " " + CliSyntax.PREFIX_PERSON_DESCRIPTION + VALID_DESCRIPTION_CTO;
    public static final String PERSONDESC_DESC_FOUNDER = " "
            + CliSyntax.PREFIX_PERSON_DESCRIPTION + VALID_DESCRIPTION_FOUNDER;


    public static final String INDUSTRY_DESC_A = " " + CliSyntax.PREFIX_INDUSTRY + VALID_INDUSTRY_A;

    public static final String INDUSTRY_DESC_B = " " + CliSyntax.PREFIX_INDUSTRY + VALID_INDUSTRY_B;

    public static final String FUNDING_DESC_A = " " + CliSyntax.PREFIX_FUNDING_STAGE + VALID_FUNDING_A;
    public static final String FUNDING_DESC_B = " " + CliSyntax.PREFIX_FUNDING_STAGE + VALID_FUNDING_B;
    public static final String PHONE_DESC_A = " " + CliSyntax.PREFIX_PHONE + VALID_PHONE_A;
    public static final String PHONE_DESC_B = " " + CliSyntax.PREFIX_PHONE + VALID_PHONE_B;
    public static final String EMAIL_DESC_A = " " + CliSyntax.PREFIX_EMAIL + VALID_EMAIL_A;
    public static final String EMAIL_DESC_B = " " + CliSyntax.PREFIX_EMAIL + VALID_EMAIL_B;
    public static final String ADDRESS_DESC_A = " " + CliSyntax.PREFIX_ADDRESS + VALID_ADDRESS_A;
    public static final String ADDRESS_DESC_B = " " + CliSyntax.PREFIX_ADDRESS + VALID_ADDRESS_B;
    public static final String VALUATION_DESC_A = " " + CliSyntax.PREFIX_VALUATION + VALID_VALUATION_A;
    public static final String VALUATION_DESC_B = " " + CliSyntax.PREFIX_VALUATION + VALID_VALUATION_B;
    public static final String TAG_DESC_POTENTIAL = " " + CliSyntax.PREFIX_TAG + VALID_TAG_POTENTIAL;
    public static final String TAG_DESC_NEW = " " + CliSyntax.PREFIX_TAG + VALID_TAG_NEW;
    public static final String DESCRIPTION_DESC_BOB_CTO = " " + CliSyntax.PREFIX_PERSON_DESCRIPTION
            + VALID_DESCRIPTION_CTO;
    public static final String DESCRIPTION_DESC_BOB_FOUNDER = " " + CliSyntax.PREFIX_PERSON_DESCRIPTION
            + VALID_DESCRIPTION_FOUNDER;

    public static final String INVALID_NAME_DESC = " "
        + CliSyntax.PREFIX_NAME + "startups&"; // '&' not allowed in names
    public static final String INVALID_PERSONNAME_DESC = " "
            + CliSyntax.PREFIX_PERSON_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " "
        + CliSyntax.PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " "
        + CliSyntax.PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_PERSONEMAIL_DESC = " "
            + CliSyntax.PREFIX_PERSON_EMAIL + "bob!yahoo"; // missing '@' symbol

    public static final String INVALID_ADDRESS_DESC = " "
        + CliSyntax.PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " "
        + CliSyntax.PREFIX_TAG + "passionate*"; // '*' not allowed in tags
    public static final String INVALID_PERSONDESC_DESC = " "
            + CliSyntax.PREFIX_PERSON_DESCRIPTION + "passionate*"; // '*' not allowed in descriptions

    public static final String INVALID_VALUATION_DESC = " " + CliSyntax.PREFIX_VALUATION + "-1";

    public static final String INVALID_INDUSTRY_DESC = " " + CliSyntax.PREFIX_INDUSTRY + "";

    public static final String INVALID_FUNDING_DESC = " " + CliSyntax.PREFIX_FUNDING_STAGE + "D";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStartupDescriptor DESC_STARTUP_A;
    public static final EditCommand.EditStartupDescriptor DESC_STARTUP_B;

    static {
        DESC_STARTUP_A = new EditStartupDescriptorBuilder().withName(VALID_NAME_A)
                .withPhone(VALID_PHONE_A).withEmail(VALID_EMAIL_A).withAddress(VALID_ADDRESS_A)
                .withTags(VALID_TAG_POTENTIAL).withIndustry(VALID_INDUSTRY_A).withValuation(VALID_VALUATION_A)
                .withFundingStage(VALID_FUNDING_A).build();
        DESC_STARTUP_B = new EditStartupDescriptorBuilder().withName(VALID_NAME_B)
                .withPhone(VALID_PHONE_B).withEmail(VALID_EMAIL_B).withAddress(VALID_ADDRESS_B)
                .withFundingStage(VALID_FUNDING_B).withIndustry(VALID_INDUSTRY_B)
                .withValuation(VALID_VALUATION_B)
                .withTags(VALID_TAG_NEW, VALID_TAG_POTENTIAL).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered startup list and selected startup in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Startup> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStartupList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStartupList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the startup at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStartupAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStartupList().size());

        Startup startup = model.getFilteredStartupList().get(targetIndex.getZeroBased());
        final String[] splitName = startup.getName().fullName.split("\\s+");
        model.updateFilteredStartupList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStartupList().size());
    }

}
