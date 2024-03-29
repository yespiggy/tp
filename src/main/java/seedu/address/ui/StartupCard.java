package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.startup.Startup;
import seedu.address.model.startup.Note;

/**
 * An UI component that displays information of a {@code Startup}.
 */
public class StartupCard extends UiPart<Region> {

    private static final String FXML = "StartupListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Startup startup;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane industryAndFundingStage;

    @FXML
    private Label note; // Add this field for note
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code StartupCode} with the given {@code Startup} and index to display.
     */
    public StartupCard(Startup startup, int displayedIndex) {
        super(FXML);
        this.startup = startup;
        id.setText(displayedIndex + ". ");
        name.setText(startup.getName().fullName);
        phone.setText(startup.getPhone().value);
        address.setText(startup.getAddress().value);
        email.setText(startup.getEmail().value);

        String fundingLevel = startup.getFundingStage().value;
        if (fundingLevel.equals("PS")) {
            fundingLevel = "PRE-SEED";
        } else if (fundingLevel.equals("S")) {
            fundingLevel = "SEED";
        } else {
            fundingLevel = "SERIES " + fundingLevel;
        }
        industryAndFundingStage.getChildren().addAll(
                new Label(startup.getIndustry().value),
                new Label(fundingLevel));
        startup.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }


}
