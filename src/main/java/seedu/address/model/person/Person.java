package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Email email;

    // Data fields
    private final Set<Description> descriptions = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Email email, Set<Description> descriptions) {
        requireAllNonNull(name, email, descriptions);
        this.name = name;
        this.email = email;
        this.descriptions.addAll(descriptions);
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }
    /**
     * Returns an immutable description set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Description> getDescriptions() {
        return Collections.unmodifiableSet(descriptions);
    }

    /**
     * Returns true if both persons have the same email.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && email.equals(otherPerson.email)
                && descriptions.equals(otherPerson.descriptions);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, email, descriptions);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("email", email)
                .add("descriptions", descriptions)
                .toString();
    }

}
