package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.startup.Startup;
import seedu.address.model.startup.Name;
import seedu.address.model.startup.FundingStage;
import seedu.address.model.startup.Industry;
import seedu.address.model.startup.Phone;
import seedu.address.model.startup.Email;
import seedu.address.model.startup.Address;
import seedu.address.model.startup.Note;
import seedu.address.model.tag.Tag;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SampleDataUtil {
    public static Startup[] getSampleStartups() {
        return new Startup[] {
                new Startup(new Name("Alex Yeoh"), new FundingStage("A"), new Industry("Finance"),
                        new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("friends"), getNoteList("Innovative project ideas")),
                new Startup(new Name("Bernice Yu"), new FundingStage("B"), new Industry("GreenTech"),
                        new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("colleagues", "friends"), getNoteList("Looking for Series B funding")),
                // Add additional startups with notes here...
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

