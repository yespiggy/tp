package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.startup.FundingStageContainsKeywordsPredicate;
import seedu.address.model.startup.IndustryContainsKeywordsPredicate;
import seedu.address.model.startup.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        FindCommand findCommand;
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME,
                        CliSyntax.PREFIX_INDUSTRY,
                        CliSyntax.PREFIX_FUNDING_STAGE
                );

        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            findCommand = parseNameKeywords(argMultimap);
        } else if (argMultimap.getValue(CliSyntax.PREFIX_INDUSTRY).isPresent()) {
            findCommand = parseIndustryKeywords(argMultimap);
        } else if (argMultimap.getValue(CliSyntax.PREFIX_FUNDING_STAGE).isPresent()) {
            findCommand = parseFundingStageKeywords(argMultimap);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        return findCommand;
    }

    /**
     * Parses predicate for find by name command.
     * If the names are empty, ParseException will be thrown.
     *
     * @param argMultimap ArgumentMultimap that contains the predicate.
     * @return A FindCommand object for execution.
     * @throws ParseException If the predicate is empty.
     */
    public FindCommand parseNameKeywords(ArgumentMultimap argMultimap) throws ParseException {
        String[] nameKeywords = argMultimap.getValue(CliSyntax.PREFIX_NAME).get().split("\\s+");
        assert nameKeywords != null;
        if (nameKeywords[0].isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }
        return new FindCommand(new NameContainsKeywordsPredicate((Arrays.asList(nameKeywords))));
    }

    /**
     * Parses predicate for find by industry command.
     * If the industries are empty, ParseException will be thrown.
     *
     * @param argMultimap ArgumentMultimap that contains the predicate.
     * @return A FindCommand object for execution.
     * @throws ParseException If the predicate is empty.
     */
    public FindCommand parseIndustryKeywords(ArgumentMultimap argMultimap) throws ParseException {
        String[] industryKeywords = argMultimap.getValue(CliSyntax.PREFIX_INDUSTRY).get().split("\\s+");
        assert industryKeywords != null;
        if (industryKeywords[0].isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }
        return new FindCommand(new IndustryContainsKeywordsPredicate((Arrays.asList(industryKeywords))));
    }

    /**
     * Parses predicate for find by funding stage command.
     * If the funding stages are empty, ParseException will be thrown.
     *
     * @param argMultimap ArgumentMultimap that contains the predicate.
     * @return A FindCommand object for execution.
     * @throws ParseException If the predicate is empty.
     */
    public FindCommand parseFundingStageKeywords(ArgumentMultimap argMultimap) throws ParseException {
        String[] fundingStageKeywords = argMultimap.getValue(CliSyntax.PREFIX_FUNDING_STAGE).get().split("\\s+");
        assert fundingStageKeywords != null;
        if (fundingStageKeywords[0].isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }
        return new FindCommand(
                new FundingStageContainsKeywordsPredicate((Arrays.asList(fundingStageKeywords))));
    }

}
