package GUIviews.message;

import GUIcontrollers.message.GUIMessageUnreadController;
import GUIviews.AbstractView;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Gui message unread view.
 */
public class GUIMessageUnreadView extends AbstractView {
    /**
     * The Controller.
     */
    GUIMessageUnreadController controller;

    private String username;
    private String sendTo;
    private Background toMsg = new Background(new BackgroundFill(Color.web("#CBDFBF"), new CornerRadii(10.0), Insets.EMPTY));

    /**
     * Instantiates a new Gui message unread view.
     *
     * @param controller the controller
     */
    public GUIMessageUnreadView(GUIMessageUnreadController controller) {
        this.controller = controller;
        this.username = controller.getUsername();
        this.sendTo = controller.getSendTo();
    }

    @Override
    public Scene createScene() {
        // Username banner
        Label banner = new Label();
        banner.setText("Unread Messages From " + sendTo);
        banner.setStyle("-fx-font-size: 25.0");
        banner.setAlignment(Pos.CENTER);

        // Back
        Button backButton = new Button("<-");
        backButton.setPrefWidth(50);
        backButton.setPrefHeight(25);
        HBox backButtonBox = new HBox();
        backButtonBox.getChildren().addAll(backButton);
        backButtonBox.setAlignment(Pos.CENTER_LEFT);

        // Header
        StackPane header = new StackPane();
        header.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        header.getChildren().addAll(banner, backButtonBox);

        // Message History
        ScrollPane messageHistory = new ScrollPane();
        messageHistory.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        messageHistory.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        messageHistory.setPadding(new Insets(10.0, 10.0, 10.0, 17.0));

        GridPane messageOrder = new GridPane();
        if (controller.getAppearanceMode().equals("dark")) {
            messageOrder.getStyleClass().add("grid");
        }
        messageOrder.setVgap(3.0);

        // Message
        ArrayList<Integer> messages = (ArrayList<Integer>) getMessagesWith().clone();
        // to start from newest to oldest
        Collections.reverse(messages);
        Integer unreadMessagesCount = getUnreadMessagesCount();
        Integer currentUnreadCount = 0;
        Integer currentMessage = 0;

        while (currentUnreadCount < unreadMessagesCount && currentMessage < messages.size()) {
            // name
            String sender = getSender(messages.get(currentMessage));

            if (!sender.equals(username)) {

                // message
                Label message = new Label();
                message.setWrapText(true);
                message.setPadding(new Insets(5.0, 7.0, 5.0, 7.0));
                message.setMaxWidth(550);
                String msg = getMessage(messages.get(currentMessage));
                message.setText(msg);
                message.setStyle("-fx-font-size: 16.0");

                HBox hbox = new HBox();
                if (controller.getAppearanceMode().equals("dark")) {
                    hbox.setId("hbox-group-threads");
                }
                hbox.setMinWidth(550);
                hbox.setMaxWidth(550);
                hbox.setPadding(new Insets(4.0, 7.0, 0.0, 7.0));

                message.setBackground(toMsg);
                hbox.setAlignment(Pos.BASELINE_LEFT);
                hbox.getChildren().add(message);

                messageOrder.add(hbox, 0, currentUnreadCount);

                currentUnreadCount++;
            }

            currentMessage++;
        }

        messageHistory.setContent(messageOrder);
        messageHistory.setVvalue(messageHistory.getVmax());

        // Message field
        TextField messageField = new TextField();
        messageField.setPrefWidth(525);
        messageField.setPrefHeight(30);

        // Send
        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(75);
        sendButton.setPrefHeight(30);
        sendButton.setDisable(true);

        sendButton.disableProperty().bind(
                Bindings.createBooleanBinding( () ->
                        messageField.getText().trim().isEmpty(), messageField.textProperty()));

        // Send message area
        GridPane messageArea = new GridPane();
        messageArea.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        GridPane.setHalignment(messageField, HPos.LEFT);
        messageArea.add(messageField, 0, 0);
        GridPane.setHalignment(sendButton, HPos.RIGHT);
        messageArea.add(sendButton, 1, 0);

        // Layout
        BorderPane layout = new BorderPane();
        layout.setTop(header);
        layout.setBottom(messageArea);
        layout.setCenter(messageHistory);

        sendButton.setOnAction(e -> sendButtonClicked(sendTo, messageField.getText()));
        backButton.setOnAction(e -> backButtonClicked());

        sawUnreadMessagesWith();

        Scene scene = new Scene(layout, 600.0, 429.0);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void sendButtonClicked(String sendTo, String message) {
        controller.sendClicked(sendTo, message);
        application.setNextScene(controller, controller.run());
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private ArrayList<Integer> getMessagesWith() {
        return controller.getMessagesWith();
    }

    private Integer getUnreadMessagesCount() {
        return controller.getUnreadMessageCount();
    }

    private String getSender(Integer messageId) {
        return controller.getSender(messageId);
    }

    private String getMessage(Integer messageId) {
        return controller.getMessage(messageId);
    }

    private void sawUnreadMessagesWith() {
        controller.sawUnreadMessagesWith();
    }

}
