package seedu.address.ui;

import seedu.address.model.startup.Startup;

@FunctionalInterface
public interface StartupSelectionHandler {
    void handle(Startup selectedStartup);
}
