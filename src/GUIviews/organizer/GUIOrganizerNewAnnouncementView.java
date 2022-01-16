package GUIviews.organizer;

import GUIcontrollers.organizer.GUIOrganizerNewAnnouncementController;
import GUIviews.AbstractView;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

/**
 * The type Gui organizer new announcement view.
 */
public class GUIOrganizerNewAnnouncementView extends AbstractView {
    /**
     * The Controller.
     */
    GUIOrganizerNewAnnouncementController controller;

    private String username;
    private boolean error = false;

    /**
     * Instantiates a new Gui organizer new announcement view.
     *
     * @param controller the controller
     */
    public GUIOrganizerNewAnnouncementView(GUIOrganizerNewAnnouncementController controller) {
        this.controller = controller;
        this.username = controller.getUsername();
    }

    @Override
    public Scene createScene() {
        // Title
        Label title = new Label();
        title.setText("New Announcement");
        title.setStyle("-fx-font-size: 25.0");

        // Back
        Button backButton = new Button("<-");
        backButton.setPrefWidth(50);
        backButton.setPrefHeight(25);
        HBox backButtonBox = new HBox();
        backButtonBox.getChildren().addAll(backButton);
        backButtonBox.setAlignment(Pos.CENTER_LEFT);

        // Header
        StackPane header = new StackPane();
        header.getChildren().addAll(title, backButtonBox);
        header.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));

        // Send to username
        Label to = new Label("To:");
        to.setPrefWidth(50);
        to.setPrefHeight(30);
        to.setAlignment(Pos.CENTER);

        ComboBox choices = new ComboBox();
        choices.setPrefWidth(530);
        choices.setPrefHeight(30);
        choices.setPromptText("message these users...");
        choices.getItems().addAll("all attendees", "all speakers");

        HBox sendToFields = new HBox();
        sendToFields.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        sendToFields.setSpacing(2);
        sendToFields.getChildren().addAll(to, choices);

        VBox sendToArea = new VBox();
        sendToArea.getChildren().addAll(sendToFields);

        // Announcement
        TextField announcementField = new TextField();
        announcementField.setPrefWidth(525);
        announcementField.setPrefHeight(30);

        // Send
        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(75);
        sendButton.setPrefHeight(30);
        sendButton.setDisable(true);

        sendButton.disableProperty().bind(
                Bindings.createBooleanBinding(  () ->
                        announcementField.getText().trim().isEmpty(), announcementField.textProperty()
                )
                        .or(  choices.valueProperty().isNull()
                        )
        );

        // Send announcement area
        GridPane sendAnnouncementArea = new GridPane();
        sendAnnouncementArea.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        GridPane.setHalignment(announcementField, HPos.LEFT);
        sendAnnouncementArea.add(announcementField, 0, 0);
        GridPane.setHalignment(sendButton, HPos.RIGHT);
        sendAnnouncementArea.add(sendButton, 1, 0);

        if (error) {
            Label errorMessage = new Label("No users of this type");
            HBox errorMessageBox = new HBox();
            errorMessageBox.getChildren().add(errorMessage);
            errorMessageBox.setAlignment(Pos.CENTER);
            sendToArea.getChildren().addAll(errorMessageBox);
        }

        sendButton.setOnAction(e -> sendButtonClicked(choices.getValue().toString(), announcementField.getText()));
        backButton.setOnAction(e -> backButtonClicked());

        BorderPane newAnnouncementHome = new BorderPane();
        newAnnouncementHome.setTop(header);
        newAnnouncementHome.setCenter(sendToArea);
        BorderPane.setAlignment(sendToArea, Pos.TOP_CENTER);
        newAnnouncementHome.setBottom(sendAnnouncementArea);

        Scene scene = new Scene(newAnnouncementHome, 600.0, 429.0);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void sendButtonClicked(String group, String message) {
        if (controller.sendClicked(group, message)) {
            application.setNextScene(controller, controller.run());
        } else {
            this.error = true;
            application.setNextScene(controller, null);
        }
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }
}
