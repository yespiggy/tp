package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POTENTIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VALUATION_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VALUATION_B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.startup.Startup;

/**
 * A utility class containing a list of {@code Startup} objects to be used in tests.
 */
public class TypicalStartups {

    public static final Startup STARTUP1 = new StartupBuilder().withName("StartupC")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withIndustry("finance").withFundingStage("A")
            .withNotes("Innovative project ideas", "Looking into Series A funding").withTags("friends")
            .withPersons(TypicalPersons.AMY).build();
    public static final Startup STARTUP2 = new StartupBuilder().withName("StartupD")
            .withAddress("311, Clementi Ave 2, #02-25").withIndustry("finance").withFundingStage("A")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withNotes("Strong technical team", "Consider for next investment round")
            .withTags("owesMoney", "friends").build();
    public static final Startup STARTUP3 = new StartupBuilder().withName("StartupE").withPhone("95352563")
            .withEmail("heinz@example.com").withIndustry("web3").withFundingStage("C")
            .withAddress("wall street").build();
    public static final Startup STARTUP4 = new StartupBuilder().withName("StartupF").withPhone("87652533")
            .withEmail("cornelia@example.com").withIndustry("finance").withFundingStage("A")
            .withNotes("Solid business model", "Expansion into new markets")
            .withAddress("10th street").withTags("friends").build();
    public static final Startup STARTUP5 = new StartupBuilder().withName("StartupG").withPhone("9482224")
            .withEmail("werner@example.com").withIndustry("web3").withFundingStage("C")
            .withNotes("Revolutionary technology", "Needs additional funding").withAddress("michegan ave").build();
    public static final Startup STARTUP6 = new StartupBuilder().withName("StartupG G").withPhone("9482427")
            .withEmail("lydia@example.com").withIndustry("web3").withFundingStage("C")
            .withNotes("High user engagement", "Exploring partnership opportunities")
            .withAddress("little tokyo").build();
    public static final Startup STARTUP7 = new StartupBuilder().withName("StartupI").withPhone("9482442")
            .withEmail("anna@example.com").withIndustry("finance").withValuation("1000")
            .withFundingStage("A")
            .withNotes("Experienced management team", "Profitable quarter reported")
            .withAddress("4th street").withPersons(TypicalPersons.AMY, TypicalPersons.GEORGE).build();

    // Manually added
    public static final Startup STARTUP8 = new StartupBuilder().withName("StartupJ").withPhone("8482424")
            .withEmail("stefan@example.com").withIndustry("finance").withFundingStage("A")
            .withAddress("little india").withValuation("1000").build();
    public static final Startup STARTUP9 = new StartupBuilder().withName("StartupK").withPhone("8482131")
            .withEmail("hans@example.com").withIndustry("finance").withFundingStage("A")
            .withAddress("chicago ave").withValuation("1000").build();

    // Manually added - Startup's details found in {@code CommandTestUtil}
    public static final Startup STARTUP_A = new StartupBuilder().withName(VALID_NAME_A).withPhone(VALID_PHONE_A)
            .withEmail(VALID_EMAIL_A).withValuation(VALID_VALUATION_A)
            .withAddress(VALID_ADDRESS_A).withTags(VALID_TAG_POTENTIAL).build();
    public static final Startup STARTUP_B = new StartupBuilder().withName(VALID_NAME_B).withPhone(VALID_PHONE_B)
            .withEmail(VALID_EMAIL_B).withValuation(VALID_VALUATION_B)
            .withAddress(VALID_ADDRESS_B).withTags(VALID_TAG_NEW, VALID_TAG_POTENTIAL)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStartups() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical startups.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Startup startup : getTypicalStartups()) {
            ab.addStartup(startup);
        }
        return ab;
    }

    public static List<Startup> getTypicalStartups() {
        return new ArrayList<>(Arrays.asList(STARTUP1, STARTUP2, STARTUP3, STARTUP4, STARTUP5, STARTUP6, STARTUP7));
    }
}
