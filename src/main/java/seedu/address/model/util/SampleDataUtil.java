package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
<<<<<<< HEAD
<<<<<<< HEAD
import seedu.address.model.startup.Startup;
import seedu.address.model.startup.Name;
import seedu.address.model.startup.FundingStage;
import seedu.address.model.startup.Industry;
import seedu.address.model.startup.Phone;
import seedu.address.model.startup.Email;
import seedu.address.model.startup.Address;
import seedu.address.model.startup.Note;
=======
import seedu.address.model.startup.*;
>>>>>>> 2ab63d37 (fix: Fix validity check for valuation)
import seedu.address.model.tag.Tag;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

=======
import seedu.address.model.startup.Address;
import seedu.address.model.startup.Email;
import seedu.address.model.startup.FundingStage;
import seedu.address.model.startup.Industry;
import seedu.address.model.startup.Name;
import seedu.address.model.startup.Phone;
import seedu.address.model.startup.Startup;
import seedu.address.model.startup.Valuation;
import seedu.address.model.tag.Tag;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
>>>>>>> dcb07730 (style: Fix checkstyle for main)
public class SampleDataUtil {
    public static Startup[] getSampleStartups() {
        return new Startup[] {
<<<<<<< HEAD
                new Startup(new Name("Alex Yeoh"), new FundingStage("A"), new Industry("Finance"),
                        new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("friends"), getNoteList("Innovative project ideas")),
                new Startup(new Name("Bernice Yu"), new FundingStage("B"), new Industry("GreenTech"),
                        new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("colleagues", "friends"), getNoteList("Looking for Series B funding")),
                // Add additional startups with notes here...
=======
            new Startup(new Name("Alex Yeoh"), new FundingStage("A"), new Industry("Finance"),
                new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Valuation("999"),
                getTagSet("friends")),
            new Startup(new Name("Bernice Yu"), new FundingStage("B"), new Industry("GreenTech"),
                new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Valuation("999"),
                getTagSet("colleagues", "friends")),
            new Startup(new Name("Charlotte Oliveiro"), new FundingStage("C"), new Industry("Health"),
                new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Valuation("999"),
                getTagSet("neighbours")),
            new Startup(new Name("David Li"), new FundingStage("A"), new Industry("Manufacturing"),
                new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Valuation("999"),
                getTagSet("family")),
            new Startup(new Name("Irfan Ibrahim"), new FundingStage("C"), new Industry("Tech"),
                new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Valuation("999"),
                getTagSet("classmates")),
            new Startup(new Name("Roy Balakrishnan"), new FundingStage("A"), new Industry("Food"),
                new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Valuation("999"),
                getTagSet("colleagues"))
>>>>>>> 2ab63d37 (fix: Fix validity check for valuation)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Startup sampleStartup : getSampleStartups()) {
            sampleAb.addStartup(sampleStartup);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a note list containing the notes given as strings.
     */
    public static List<Note> getNoteList(String... strings) {
        return Arrays.stream(strings)
                .map(Note::new)
                .collect(Collectors.toList());
    }
}

