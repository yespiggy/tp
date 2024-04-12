package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_STARTUP_A;
import static seedu.address.logic.commands.CommandTestUtil.DESC_STARTUP_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NEW;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditStartupDescriptor;
import seedu.address.testutil.EditStartupDescriptorBuilder;

public class EditStartupDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditStartupDescriptor descriptorWithSameValues = new EditStartupDescriptor(DESC_STARTUP_A);
        assertTrue(DESC_STARTUP_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_STARTUP_A.equals(DESC_STARTUP_A));

        // null -> returns false
        assertFalse(DESC_STARTUP_A.equals(null));

        // different types -> returns false
        assertFalse(DESC_STARTUP_A.equals(5));

        // different values -> returns false
        assertFalse(DESC_STARTUP_A.equals(DESC_STARTUP_B));

        // different name -> returns false
        EditStartupDescriptor editedAmy = new EditStartupDescriptorBuilder(DESC_STARTUP_A)
                .withName(VALID_NAME_B).build();
        assertFalse(DESC_STARTUP_A.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditStartupDescriptorBuilder(DESC_STARTUP_A).withPhone(VALID_PHONE_B).build();
        assertFalse(DESC_STARTUP_A.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditStartupDescriptorBuilder(DESC_STARTUP_A).withEmail(VALID_EMAIL_B).build();
        assertFalse(DESC_STARTUP_A.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditStartupDescriptorBuilder(DESC_STARTUP_A).withAddress(VALID_ADDRESS_B).build();
        assertFalse(DESC_STARTUP_A.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditStartupDescriptorBuilder(DESC_STARTUP_A).withTags(VALID_TAG_NEW).build();
        assertFalse(DESC_STARTUP_A.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditStartupDescriptor editStartupDescriptor = new EditStartupDescriptor();
        String expected = EditStartupDescriptor.class.getCanonicalName() + "{name="
                + editStartupDescriptor.getName().orElse(null) + ", industry="
                + editStartupDescriptor.getIndustry().orElse(null) + ", funding stage="
                + editStartupDescriptor.getFundingStage().orElse(null) + ", phone="
                + editStartupDescriptor.getPhone().orElse(null) + ", email="
                + editStartupDescriptor.getEmail().orElse(null) + ", address="
                + editStartupDescriptor.getAddress().orElse(null) + ", valuation="
                + editStartupDescriptor.getValuation().orElse(null) + ", tags="
                + editStartupDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editStartupDescriptor.toString());
    }
}
