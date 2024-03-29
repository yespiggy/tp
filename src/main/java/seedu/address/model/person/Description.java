package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Description in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDescriptionName(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Description should only contain alphanumeric characters and spaces";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String descriptionName;

    /**
     * Constructs a {@code Description}.
     *
     * @param descriptionName A valid description name.
     */
    public Description(String descriptionName) {
        requireNonNull(descriptionName);
        checkArgument(isValidDescriptionName(descriptionName), MESSAGE_CONSTRAINTS);
        this.descriptionName = descriptionName;
    }

    /**
     * Returns true if a given string is a valid description name.
     */
    public static boolean isValidDescriptionName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Description)) {
            return false;
        }

        Description otherDescription = (Description) other;
        return descriptionName.equals(otherDescription.descriptionName);
    }

    @Override
    public int hashCode() {
        return descriptionName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return descriptionName;
    }

}
