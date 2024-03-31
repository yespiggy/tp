package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.PersonEmail;
import seedu.address.model.person.Description;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Person}.
 */
public class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String email;
    private final List<JsonAdaptedDescription> descriptions = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
                              @JsonProperty("email") String email,
                              @JsonProperty("descriptions") List<JsonAdaptedDescription> descriptions) {
        this.name = name;
        this.email = email;
        if (descriptions != null) {
            this.descriptions.addAll(descriptions);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        email = source.getEmail().value;
        descriptions.addAll(source.getDescriptions().stream()
                .map(JsonAdaptedDescription::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted startup.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Description> personDescriptions = new ArrayList<>();
        for (JsonAdaptedDescription description : descriptions) {
            personDescriptions.add(description.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName()));
        }
        if (!PersonName.isValidName(name)) {
            throw new IllegalValueException(PersonName.MESSAGE_CONSTRAINTS);
        }
        final PersonName modelName = new PersonName(name);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, PersonEmail.class.getSimpleName()));
        }
        if (!PersonEmail.isValidEmail(email)) {
            throw new IllegalValueException(PersonEmail.MESSAGE_CONSTRAINTS);
        }
        final PersonEmail modelEmail = new PersonEmail(email);

        final Set<Description> modelDescriptions = new HashSet<>(personDescriptions);
        return new Person(modelName, modelEmail, modelDescriptions);
    }

}
