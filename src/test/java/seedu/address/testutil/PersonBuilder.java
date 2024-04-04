package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Description;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonEmail;
import seedu.address.model.person.PersonName;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";

    private PersonName name;

    private PersonEmail email;

    private Set<Description> descriptions;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new PersonName(DEFAULT_NAME);
        email = new PersonEmail(DEFAULT_EMAIL);
        descriptions = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        email = personToCopy.getEmail();
        descriptions = new HashSet<>(personToCopy.getDescriptions());
    }

    /**
     * Sets the {@code PersonName} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new PersonName(name);
        return this;
    }

    /**
     * Parses the {@code descriptions} into a {@code Set<Description>} and set it to the {@code Person}
     * that we are building.
     */
    public PersonBuilder withDescriptions(String ... descriptions) {
        this.descriptions = SampleDataUtil.getDescriptionSet(descriptions);
        return this;
    }

    /**
     * Sets the {@code PersonEmail} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new PersonEmail(email);
        return this;
    }

    public Person build() {
        return new Person(name, email, descriptions);
    }

}
