package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Adds a tag to a contact.
 */
public class TagAddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = TagCommand.COMMAND_WORD + " "
            + COMMAND_WORD + ": Adds a tag to the contact "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + "TAG...\n"
            + "Example: " + TagCommand.COMMAND_WORD + " "
            + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Tag added: %1$s";
    public static final String MESSAGE_NO_SUCH_TAG = "This tag does not exist";
    public static final String MESSAGE_TAG_ALREADY_ADDED = "The contact already has the tag.";

    private final Index index;
    private final Tag tag;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public TagAddCommand(Index index, Tag tag) {
        requireAllNonNull(index, tag);

        this.index = index;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (!model.hasTag(tag)) {
            throw new CommandException(MESSAGE_NO_SUCH_TAG);
        } else if (personToEdit.getTags().contains(tag)) {
            throw new CommandException(MESSAGE_TAG_ALREADY_ADDED);
        } else {
            Person editedPerson = createEditedPerson(personToEdit, tag);
            model.setPerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, tag));
        }
    }

    /**
     * Recreates the same person with updated tags.
     */
    private static Person createEditedPerson(Person personToEdit,
                                             Tag tag) {
        requireNonNull(personToEdit);

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();

        Set<Tag> oldTags = personToEdit.getTags();
        List<Tag> tagList = new ArrayList<>(oldTags);
        tagList.add(tag);
        Set<Tag> updatedTags = new HashSet<>(tagList);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

}
