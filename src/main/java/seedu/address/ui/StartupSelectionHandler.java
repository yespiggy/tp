package seedu.address.ui;

import seedu.address.model.startup.Startup;

/**
 * Handles startup selection.
 */
@FunctionalInterface
public interface StartupSelectionHandler {
    void handle(Startup selectedStartup);
}
