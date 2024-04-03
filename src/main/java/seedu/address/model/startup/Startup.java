package seedu.address.model.startup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents a Startup in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Startup {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields

    private final FundingStage fundingStage;
    private final Industry industry;
    private final Valuation valuation;
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private List<Note> notes = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Startup(Name name, FundingStage fundingStage, Industry industry,
                   Phone phone, Email email, Address address, Valuation valuation,
                   Set<Tag> tags) {
        requireAllNonNull(name, fundingStage, industry, valuation, phone, email, address, tags);
        this.name = name;
        this.fundingStage = fundingStage;
        this.valuation = valuation;
        this.industry = industry;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.notes = new ArrayList<>();
    }

    /**
     * Constructor for Startup with notes.
     */
    public Startup(Name name, FundingStage fundingStage, Industry industry,
                   Phone phone, Email email, Address address, Valuation valuation,
                   Set<Tag> tags, List<Note> notes) {
        requireAllNonNull(name, fundingStage, industry, valuation, phone, email, address, tags);
        this.name = name;
        this.fundingStage = fundingStage;
        this.industry = industry;
        this.phone = phone;
        this.valuation = valuation;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.notes = new ArrayList<>(notes); // Defensive copy
    }

    /**
     * Constructor for Startup with persons.
     */
    public Startup(Name name, FundingStage fundingStage, Industry industry,
                   Phone phone, Email email, Address address, Valuation valuation,
                   Set<Tag> tags, List<Note> notes, List<Person> persons) {
        requireAllNonNull(name, fundingStage, industry, valuation, phone, email, address, tags);
        this.name = name;
        this.fundingStage = fundingStage;
        this.industry = industry;
        this.phone = phone;
        this.valuation = valuation;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.notes = new ArrayList<>(notes); // Defensive copy
        this.persons = new ArrayList<>(persons);
    }

    public FundingStage getFundingStage() {
        return this.fundingStage;
    }

    public Valuation getValuation() {
        return this.valuation;
    }

    public Industry getIndustry() {
        return this.industry;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    // Getters and setters
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable person set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Person> getPersons() {
        return persons;
    }

    /**
     * Returns true if both startups have the same name.
     * This defines a weaker notion of equality between two startups.
     */
    public boolean isSameStartup(Startup otherStartup) {
        if (otherStartup == this) {
            return true;
        }

        return otherStartup != null
                && otherStartup.getName().equals(getName());
    }

    /**
     * Returns true if both startups have the same identity and data fields.
     * This defines a stronger notion of equality between two startups.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Startup)) {
            return false;
        }

        Startup otherStartup = (Startup) other;
        return name.equals(otherStartup.name)
                && industry.equals(otherStartup.industry)
                && fundingStage.equals(otherStartup.fundingStage)
                && phone.equals(otherStartup.phone)
                && email.equals(otherStartup.email)
                && address.equals(otherStartup.address)
                && tags.equals(otherStartup.tags)
                && notes.equals(otherStartup.notes)
                && valuation.equals(otherStartup.valuation)
                && persons.equals(otherStartup.persons);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, notes, persons);
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this)
                .add("name", name)
                .add("industry", industry)
                .add("funding stage", fundingStage)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("valuation", valuation)
                .add("tags", tags)
                .add("persons", persons);

        if (notes.isEmpty()) {
            builder.add("notes", "No notes added");
        } else {
            builder.add("notes", notes);
        }
        return builder.toString();
    }


    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }
}
