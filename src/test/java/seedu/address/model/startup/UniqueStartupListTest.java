package seedu.address.model.startup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NEW;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStartups.STARTUP1;
import static seedu.address.testutil.TypicalStartups.STARTUP_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.startup.exceptions.DuplicateStartupException;
import seedu.address.model.startup.exceptions.StartupNotFoundException;
import seedu.address.testutil.StartupBuilder;

public class UniqueStartupListTest {

    private final UniqueStartupList uniqueStartupList = new UniqueStartupList();

    @Test
    public void contains_nullStartup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStartupList.contains(null));
    }

    @Test
    public void contains_startupNotInList_returnsFalse() {
        assertFalse(uniqueStartupList.contains(STARTUP1));
    }

    @Test
    public void contains_startupInList_returnsTrue() {
        uniqueStartupList.add(STARTUP1);
        assertTrue(uniqueStartupList.contains(STARTUP1));
    }

    @Test
    public void contains_startupWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStartupList.add(STARTUP1);
        Startup editedAlice = new StartupBuilder(STARTUP1).withAddress(VALID_ADDRESS_B).withTags(VALID_TAG_NEW)
                .build();
        assertTrue(uniqueStartupList.contains(editedAlice));
    }

    @Test
    public void add_nullStartup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStartupList.add(null));
    }

    @Test
    public void add_duplicateStartup_throwsDuplicateStartupException() {
        uniqueStartupList.add(STARTUP1);
        assertThrows(DuplicateStartupException.class, () -> uniqueStartupList.add(STARTUP1));
    }

    @Test
    public void setStartup_nullTargetStartup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStartupList.setStartup(null, STARTUP1));
    }

    @Test
    public void setStartup_nullEditedStartup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStartupList.setStartup(STARTUP1, null));
    }

    @Test
    public void setStartup_targetStartupNotInList_throwsStartupNotFoundException() {
        assertThrows(StartupNotFoundException.class, () -> uniqueStartupList.setStartup(STARTUP1, STARTUP1));
    }

    @Test
    public void setStartup_editedStartupIsSameStartup_success() {
        uniqueStartupList.add(STARTUP1);
        uniqueStartupList.setStartup(STARTUP1, STARTUP1);
        UniqueStartupList expectedUniqueStartupList = new UniqueStartupList();
        expectedUniqueStartupList.add(STARTUP1);
        assertEquals(expectedUniqueStartupList, uniqueStartupList);
    }

    @Test
    public void setStartup_editedStartupHasSameIdentity_success() {
        uniqueStartupList.add(STARTUP1);
        Startup editedAlice = new StartupBuilder(STARTUP1).withAddress(VALID_ADDRESS_B).withTags(VALID_TAG_NEW)
                .build();
        uniqueStartupList.setStartup(STARTUP1, editedAlice);
        UniqueStartupList expectedUniqueStartupList = new UniqueStartupList();
        expectedUniqueStartupList.add(editedAlice);
        assertEquals(expectedUniqueStartupList, uniqueStartupList);
    }

    @Test
    public void setStartup_editedStartupHasDifferentIdentity_success() {
        uniqueStartupList.add(STARTUP1);
        uniqueStartupList.setStartup(STARTUP1, STARTUP_B);
        UniqueStartupList expectedUniqueStartupList = new UniqueStartupList();
        expectedUniqueStartupList.add(STARTUP_B);
        assertEquals(expectedUniqueStartupList, uniqueStartupList);
    }

    @Test
    public void setStartup_editedStartupHasNonUniqueIdentity_throwsDuplicateStartupException() {
        uniqueStartupList.add(STARTUP1);
        uniqueStartupList.add(STARTUP_B);
        assertThrows(DuplicateStartupException.class, () -> uniqueStartupList.setStartup(STARTUP1, STARTUP_B));
    }

    @Test
    public void remove_nullStartup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStartupList.remove(null));
    }

    @Test
    public void remove_startupDoesNotExist_throwsStartupNotFoundException() {
        assertThrows(StartupNotFoundException.class, () -> uniqueStartupList.remove(STARTUP1));
    }

    @Test
    public void remove_existingStartup_removesStartup() {
        uniqueStartupList.add(STARTUP1);
        uniqueStartupList.remove(STARTUP1);
        UniqueStartupList expectedUniqueStartupList = new UniqueStartupList();
        assertEquals(expectedUniqueStartupList, uniqueStartupList);
    }

    @Test
    public void setStartups_nullUniqueStartupList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStartupList.setStartups((UniqueStartupList) null));
    }

    @Test
    public void setStartups_uniqueStartupList_replacesOwnListWithProvidedUniqueStartupList() {
        uniqueStartupList.add(STARTUP1);
        UniqueStartupList expectedUniqueStartupList = new UniqueStartupList();
        expectedUniqueStartupList.add(STARTUP_B);
        uniqueStartupList.setStartups(expectedUniqueStartupList);
        assertEquals(expectedUniqueStartupList, uniqueStartupList);
    }

    @Test
    public void setStartups_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStartupList.setStartups((List<Startup>) null));
    }

    @Test
    public void setStartups_list_replacesOwnListWithProvidedList() {
        uniqueStartupList.add(STARTUP1);
        List<Startup> startupList = Collections.singletonList(STARTUP_B);
        uniqueStartupList.setStartups(startupList);
        UniqueStartupList expectedUniqueStartupList = new UniqueStartupList();
        expectedUniqueStartupList.add(STARTUP_B);
        assertEquals(expectedUniqueStartupList, uniqueStartupList);
    }

    @Test
    public void setStartups_listWithDuplicateStartups_throwsDuplicateStartupException() {
        List<Startup> listWithDuplicateStartups = Arrays.asList(STARTUP1, STARTUP1);
        assertThrows(DuplicateStartupException.class, () -> uniqueStartupList.setStartups(listWithDuplicateStartups));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueStartupList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueStartupList.asUnmodifiableObservableList().toString(), uniqueStartupList.toString());
    }
}
