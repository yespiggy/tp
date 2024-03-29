package seedu.address.model.startup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Startup's valuation in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidValuation(String)}
 */
public class Valuation {

    public static final String MESSAGE_CONSTRAINTS =
        "Valuation of a company must be a INTEGER "
        + "(No decimal places, i.e. 1 is a integer, 1.1 is not)"
        + " above -1 and "
        + " below 5 trillion (the highest valued company in the world is only worth ~3T).";
    public static final String VALIDATION_REGEX = "\\d+";
    private static char[] endOfNumber = new char[]{'k', 'm', 'b', 't'};

    public final String value;

    /**
     * Constructs a {@code Valuation}.
     *
     * @param valuation A valid valuation.
     */
    public Valuation(String valuation) {
        requireNonNull(valuation);
        checkArgument(isValidValuation(valuation), MESSAGE_CONSTRAINTS);
        value = valuation;
    }

    /**
     * Reformats valuation for clean display on the UI, truncates to 4 characters.
     * @param valuation The valuation to reformat
     * @return The truncated, short-form version of the valuation.
     */
    public static String reformatValuation(String valuation) {
        Double doubleValuation = Double.valueOf(valuation);
        // If it's below 1000, we don't need to format it!
        if (doubleValuation.compareTo((double) 1000) < 0) {
            // We can safely use integer here because we know decimals will not be an output.
            // And since it's value < 1000, it will not overflow on integer.
            return Integer.valueOf(valuation).toString();
        }
        return reformatString(doubleValuation, 0);
    }

    /**
     * This function helps us reformat the user's input into
     * a short string representation (within 4 characters).
     * Credit to Ilya Saunkin, SWE from AWS, shared in the following stackoverflow discussion:
     * https://stackoverflow.com/questions/4753251/how-to-go-about-formatting-1200-to-1-2k-in-java
     *
     * Recursive implementation,
     * invokes itself for each factor of a thousand,
     * increasing the class on each invocation.
     *
     * @param n the number to format
     * @param iteration in fact this is the class from the array c
     * @return a String representing the number n formatted in a cool looking way.
     */
    private static String reformatString(double n, int iteration) {
        double d = ((long) n / 100) / 10.0;
        //true if the decimal part is equal to 0 (then it's trimmed anyway)
        boolean isRound = (d * 10) % 10 == 0;

        //this determines the class, i.e. 'k', 'm' etc
        return (d < 1000 ? ((
                d > 99.9 || isRound || (
                    !isRound && d > 9.99)
            ? //this decides whether to trim the decimals
            (int) d * 10 / 10 : d + "") + "" // (int) d * 10 / 10 drops the decimal
             + Valuation.endOfNumber[iteration])
            : reformatString(d, iteration + 1));
    }


    /**
     * Returns true if a given string is a valid valuation amount.
     */
    public static boolean isValidValuation(String test) {
        // First test that the string only contains numbers.
        boolean isOnlyNumbers = test.matches(VALIDATION_REGEX);
        if (!isOnlyNumbers) { // No need to further check if it is not a number.
            return false;
        }
        // Check that the string is within bounds.
        Double doubleRepresentation = Double.valueOf(test);
        boolean isNonNegative = doubleRepresentation.compareTo((double) -1) > 0;
        boolean isBelowFiveTrillion = doubleRepresentation.compareTo((double) 5000000000000L) < 0;
        return isNonNegative && isBelowFiveTrillion;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Valuation)) {
            return false;
        }

        Valuation otherValuation = (Valuation) other;
        return value.equals(otherValuation.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
