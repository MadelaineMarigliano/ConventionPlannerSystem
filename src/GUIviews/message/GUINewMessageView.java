package GUIviews.message;

import GUIcontrollers.message.GUINewMessageController;
import GUIviews.AbstractView;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

/**
 * The type Gui new message view.
 */
public class GUINewMessageView extends AbstractView {
    /**
     * The Controller.
     */
    GUINewMessageController controller;
    /**
     * The Error.
     */
    boolean error = false;

    /**
     * Instantiates a new Gui new message view.
     *
     * @param controller the controller
     */
    public GUINewMessageView(GUINewMessageController controller) {
        this.controller = controller;
    }

    @Override
    public Scene createScene() {
        // Title
        Label title = new Label();
        title.setText("New Message");
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
        to.setPrefHeight(30);
        to.setPrefWidth(50);
        to.setAlignment(Pos.CENTER);
        TextField sendToField = new TextField();
        sendToField.setPrefWidth(530);
        sendToField.setPrefHeight(30);

        VBox sendToArea = new VBox();
        HBox sendToFields = new HBox();
        sendToFields.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        sendToFields.setSpacing(2);
        sendToFields.getChildren().addAll(to, sendToField);
        sendToArea.getChildren().addAll(sendToFields);

        // Message
        TextField messageField = new TextField();
        messageField.setPrefWidth(525);
        messageField.setPrefHeight(30);

        // Send
        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(75);
        sendButton.setPrefHeight(30);
        sendButton.setDisable(true);

        sendButton.disableProperty().bind(
                Bindings.createBooleanBinding(  () ->
                        messageField.getText().trim().isEmpty(), messageField.textProperty()
                )
                        .or(  Bindings.createBooleanBinding(  () ->
                                sendToField.getText().trim().isEmpty(), sendToField.textProperty()
                        )
                        )
        );

        // Send message area
        GridPane sendMessageArea = new GridPane();
        sendMessageArea.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        GridPane.setHalignment(messageField, HPos.LEFT);
        sendMessageArea.add(messageField, 0, 0);
        GridPane.setHalignment(sendButton, HPos.RIGHT);
        sendMessageArea.add(sendButton, 1, 0);

        if (error) {
            Label errorMessage = new Label("Invalid username");
            HBox errorMessageBox = new HBox();
            errorMessageBox.getChildren().addAll(errorMessage);
            errorMessageBox.setAlignment(Pos.CENTER);
            sendToArea.getChildren().addAll(errorMessageBox);
        }

        sendButton.setOnAction(e -> sendButtonClicked(sendToField.getText(), messageField.getText()));
        backButton.setOnAction(e -> backButtonClicked());

        BorderPane newMessageHome = new BorderPane();
        newMessageHome.setTop(header);
        newMessageHome.setCenter(sendToArea);
        BorderPane.setAlignment(sendToArea, Pos.TOP_CENTER);
        newMessageHome.setBottom(sendMessageArea);

        Scene scene = new Scene(newMessageHome, 600.0, 429.0);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void sendButtonClicked(String sendTo, String message) {
        if (controller.validSendTo(sendTo)) {
            controller.sendClicked(sendTo, message);
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
