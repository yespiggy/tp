package seedu.address.testutil;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.address.model.person.Description;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonEmail;
import seedu.address.model.person.PersonName;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setEmail(person.getEmail());
        descriptor.setDescriptions(person.getDescriptions());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new PersonName(name));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new PersonEmail(email));
        return this;
    }

    /**
     * Parses the {@code notes} into a {@code List<Note>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withDescriptions(String... descriptions) {
        Set<Description> descriptionSet = Arrays.stream(descriptions).map(Description::new).collect(Collectors.toSet());
        descriptor.setDescriptions(descriptionSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }

}
