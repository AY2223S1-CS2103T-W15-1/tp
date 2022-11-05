package soconnect.model.person;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the SoConnect.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}.
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private static final int CHARACTER_LIMIT = 45;

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullName;
    }

    /**
     * Compares this Name to another Name.
     *
     * @param other The other Name object
     * @return      -1 if this object is lesser, 0 if they are equal, 1 otherwise
     */
    public int compareTo(Name other) {
        return fullName.compareToIgnoreCase(other.fullName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
