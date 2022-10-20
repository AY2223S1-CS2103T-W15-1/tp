package soconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;

import soconnect.commons.core.Messages;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.model.Model;
import soconnect.model.person.Person;

/**
 * Searches and lists all people in SoConnect whose information contains the argument keyword.
 * Keyword matching is case-insensitive.
 */
public class SearchCommand extends Command {

    public static final String COMMAND_WORD = "search";
    public static final String AND_CONDITION = "and";
    public static final String OR_CONDITION = "or";
    public static final String EMPTY_CONDITION = "";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Searches all people whose information contains "
            + "the specified keyword (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [CONDITION] [KEYWORD]...\n"
            + "Example: " + COMMAND_WORD + " t/friend, "
            + COMMAND_WORD + " " + AND_CONDITION + " n/John a/NUS"
            + COMMAND_WORD + " " + OR_CONDITION + " p/12345678 e/betsy@nus.edu";

    private final Predicate<Person> predicate;
    private final Predicate<Person> alternativePredicate;

    /**
     * Constructs a {@code SearchCommand} to search contacts in SoConnect.
     */
    public SearchCommand(Predicate<Person> predicate, Predicate<Person> alternativePredicate) {
        requireAllNonNull(predicate, alternativePredicate);
        this.predicate = predicate;
        this.alternativePredicate = alternativePredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        if (model.isFilteredPersonListEmpty()) {
            model.updateFilteredPersonList(alternativePredicate);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchCommand // instanceof handles nulls
                && predicate.equals(((SearchCommand) other).predicate)
                && alternativePredicate.equals(((SearchCommand) other).alternativePredicate)); // state check
    }
}
