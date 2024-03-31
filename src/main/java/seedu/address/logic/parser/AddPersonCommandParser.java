package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Description;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonEmail;
import seedu.address.model.person.PersonName;


import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_NAME;

/**
 * Parses input arguments and creates a new AddPersonCommand object
 */
public class AddPersonCommandParser implements Parser<AddPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddNoteCommand
     * and returns an AddNoteCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddPersonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_PERSON_NAME,
                        CliSyntax.PREFIX_PERSON_EMAIL, CliSyntax.PREFIX_PERSON_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_PERSON_NAME, PREFIX_PERSON_EMAIL)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
        }


        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE), pe);
        }


        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PERSON_NAME,
                PREFIX_PERSON_EMAIL, PREFIX_PERSON_DESCRIPTION);
        PersonName personName = ParserUtil.parsePersonName(argMultimap.getValue(PREFIX_PERSON_NAME).get());
        PersonEmail personEmail = ParserUtil.parsePersonEmail(argMultimap.getValue(PREFIX_PERSON_EMAIL).get());
        Set<Description> descriptions =
                parseDescriptionsForAddPerson(argMultimap.getAllValues(PREFIX_PERSON_DESCRIPTION));

        //None of the following should be null
        assert personName.fullName != null;
        assert personEmail.value != null;

        Person person = new Person(personName, personEmail, descriptions); //Create a new person object
        return new AddPersonCommand(index, person);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses {@code Collection<String> description} into a {@code Set<Description>} if {@code descriptions} is non-empty.
     * If {@code descriptions} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Description>} containing zero descriptions.
     */
    private Set<Description> parseDescriptionsForAddPerson(Collection<String> descriptions) throws ParseException {
        assert descriptions != null;

        Collection<String> descriptionSet = descriptions.size() == 1 && descriptions.contains("") ?
                Collections.emptySet() : descriptions;
        return ParserUtil.parseDescriptions(descriptionSet);
    }
}
