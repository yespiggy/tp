package seedu.address.model.startup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Startup's valuation in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidValuation(String)}
 */
public class Valuation {

    private static char[] endOfNumber = new char[]{'k', 'm', 'b', 't'};

    /**
     * This function helps us reformat the user's input into a short string representation.
     * Credit to Ilya Saunkin, SWE from AWS, shared in the following stackoverflow discussion:
     * https://stackoverflow.com/questions/4753251/how-to-go-about-formatting-1200-to-1-2k-in-java
     *
     * Recursive implementation,
     * invokes itself for each factor of a thousand,
     * increasing the class on each invocation.
     * @param n the number to format
     * @param iteration in fact this is the class from the array c
     * @return a String representing the number n formatted in a cool looking way.
     */
    private static String reformatString(double n, int iteration) {
        double d = ((long) n / 100) / 10.0;
        //true if the decimal part is equal to 0 (then it's trimmed anyway)
        boolean isRound = (d * 10) %10 == 0;
        return (d < 1000? //this determines the class, i.e. 'k', 'm' etc
          ((d > 99.9 || isRound || (!isRound && d > 9.99)? //this decides whether to trim the decimals
            (int) d * 10 / 10 : d + "" // (int) d * 10 / 10 drops the decimal
          ) + "" + Valuation.endOfNumber[iteration])
          : reformatString(d, iteration+1));
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Valuation of a company must be a number above -1 and " +
            " below 5 trillion (the highest valued company in the world as of now is only worth ~3T).";
    public static final String VALIDATION_REGEX = "\\d";
    public final String value;

    /**
     * Constructs a {@code Valuation}.
     *
     * @param valuation A valid valuation.
     */
    public Valuation(String valuation) {
        requireNonNull(valuation);
        checkArgument(isValidValuation(valuation), MESSAGE_CONSTRAINTS);
        value = this.reformatString(Double.valueOf(valuation), 0);
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
