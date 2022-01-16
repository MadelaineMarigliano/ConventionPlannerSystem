package GUIviews.message;

import GUIcontrollers.message.GUIMessageController;
import GUIviews.AbstractView;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Gui message view.
 */
public class GUIMessageView extends AbstractView {
    /**
     * The Controller.
     */
    GUIMessageController controller;

    private String sendTo;
    private String username;
    private ArrayList<Integer> messageList = new ArrayList<>();
    private boolean error;
    private boolean unreadMessageAdded = false;
    private Background fromMsg = new Background(new BackgroundFill(Color.web("#CEDCD5"), new CornerRadii(10.0), Insets.EMPTY));
    private Background fromMsgDark = new Background(new BackgroundFill(Color.web("#31AD6D"), new CornerRadii(10.0), Insets.EMPTY));
    private Background toMsg = new Background(new BackgroundFill(Color.web("#CBDFBF"), new CornerRadii(10.0), Insets.EMPTY));
    private Background toMsgDark = new Background(new BackgroundFill(Color.web("#63A63A"), new CornerRadii(10.0), Insets.EMPTY));

    /**
     * Instantiates a new Gui message view.
     *
     * @param controller the controller
     */
    public GUIMessageView(GUIMessageController controller) {
        this.controller = controller;
        this.sendTo = controller.getSendTo();
        this.username = controller.getUsername();
    }

    @Override
    public Scene createScene() {
        // Username banner
        Label banner = new Label();
        banner.setText(sendTo);
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
        Integer n = messages.size() + 1;
        Integer unreadMessagesCount = getUnreadMessagesCount(sendTo);
        Integer currentUnreadCount = 0;

        for (Integer i : messages) {

            // message
            Label message = new Label();
            message.setWrapText(true);
            message.setPadding(new Insets(5.0, 7.0, 5.0, 7.0));
            message.setMaxWidth(450);
            String msg = getMessage(i);
            message.setText(msg);
            message.setStyle("-fx-font-size: 16.0");

            HBox hbox = new HBox();
            if (controller.getAppearanceMode().equals("dark")) {
                hbox.setId("hbox-group-threads");
            }
            hbox.setMinWidth(550);
            hbox.setMaxWidth(550);
            hbox.setPadding(new Insets(4.0, 7.0, 0.0, 7.0));

            // name
            String sender = getSender(i);

            if (sender.equals(username)) {
                // from messages
                message.setBackground(fromMsg);
                if (controller.getAppearanceMode().equals("dark")) {
                    message.setBackground(fromMsgDark);
                }
                hbox.setAlignment(Pos.BASELINE_RIGHT);
            } else {
                // to messages
                message.setBackground(toMsg);
                if (controller.getAppearanceMode().equals("dark")) {
                    message.setBackground(toMsgDark);
                }
                hbox.setAlignment(Pos.BASELINE_LEFT);
                currentUnreadCount++;
            }
            hbox.getChildren().add(message);
            messageOrder.add(hbox, 0, n);
            n--;

            // unread separator
            if (!unreadMessageAdded && currentUnreadCount > 0 && currentUnreadCount.equals(unreadMessagesCount)) {
                Label unreadLabel = new Label();
                unreadLabel.setText("~ unread messages ~");
                unreadLabel.setStyle("-fx-font-size: 14.0");
                HBox unreadLabelBox = new HBox();
                unreadLabelBox.setAlignment(Pos.CENTER);
                unreadLabelBox.getChildren().add(unreadLabel);
                unreadLabelBox.setMinWidth(550);
                unreadLabelBox.setMaxWidth(550);
                unreadLabelBox.setPadding(new Insets(4.0, 0.0, 0.0, 13.0));

                messageOrder.add(unreadLabelBox, 0, n);
                n--;
                unreadMessageAdded = true;
            }
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
        BorderPane border = new BorderPane();
        border.setTop(header);
        border.setBottom(messageArea);
        border.setCenter(messageHistory);

        sendButton.setOnAction(e -> sendButtonClicked(messageField));
        backButton.setOnAction(e -> backButtonClicked());

        Scene scene = new Scene(border, 600.0, 429.0);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void sendButtonClicked(TextField messageField){
        sawUnreadMessagesWith(sendTo);
        controller.sendClicked(messageField.getText());
        application.setNextScene(controller, null);
    }

    private void backButtonClicked() {
        sawUnreadMessagesWith(sendTo);
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private ArrayList<Integer> getMessagesWith() {
        return controller.getMessagesWith();
    }

    private String getMessage(Integer messageId) {
        return controller.getMessage(messageId);
    }

    private String getSender(Integer messageId) {
        return controller.getSender(messageId);
    }

    private void sawUnreadMessagesWith(String from) {
        controller.sawUnreadMessagesWith(from);
    }

    private Integer getUnreadMessagesCount(String from) {
        return controller.getUnreadMessagesCount(from);
    }
}
