package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.startup.Note;

/**
 * Panel containing the list of notes.
 */
public class NoteListPanel extends UiPart<Region> {
    private static final String FXML = "NoteListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(NoteListPanel.class);

    @FXML
    private ScrollPane noteScrollPane; // Direct reference to the ScrollPane, if needed

    @FXML
    private VBox noteContainer; // Direct reference to the VBox containing the notes

    /**
     * Displays the note list panel.
     * @param notes The note list to update.
     */
    public NoteListPanel(ObservableList<Note> notes) {
        super(FXML);
        String style = "-fx-background-color: #333;";
        noteScrollPane.setStyle(style);
        noteContainer.setStyle(style);
        displayNotes(notes);
    }

    /**
     * Displays a list of notes.
     * @param notes The list of notes to be displayed.
     */
    public void displayNotes(ObservableList<Note> notes) {
        noteContainer.getChildren().clear(); // Clear existing notes
        Label title = new Label("Notes");
        title.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        noteContainer.getChildren().add(title);

        if (notes.isEmpty()) {
            // Add a placeholder to fill the background color
            Label placeholderLabel = new Label("No notes to display");
            placeholderLabel.setStyle("-fx-background-color: #333; -fx-text-fill: white;");
            placeholderLabel.setMaxWidth(Double.MAX_VALUE); // Make the label fill the width
            placeholderLabel.setAlignment(Pos.CENTER); // Center the text if desired
            noteContainer.getChildren().add(placeholderLabel);
        } else {
            // Display notes with alternating background colors
            for (int i = 0; i < notes.size(); i++) {
                Note note = notes.get(i);
                Label noteLabel = new Label(note.toString()); // Adjust based on how you want to display the note
                noteLabel.setWrapText(true);
                noteLabel.setMinHeight(Region.USE_PREF_SIZE);
                noteLabel.setPrefHeight(Region.USE_COMPUTED_SIZE);
                // Set alternating background colors
                String style = i % 2 == 0 ? "-fx-background-color: #444; -fx-text-fill: white; -fx-padding: 5;"
                        : "-fx-background-color: #555; -fx-text-fill: white; -fx-padding: 5;";
                noteLabel.setStyle(style);
                noteContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
                noteContainer.getChildren().add(noteLabel);
            }
        }
    }
}
