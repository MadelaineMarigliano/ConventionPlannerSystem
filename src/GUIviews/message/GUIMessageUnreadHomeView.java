package GUIviews.message;

import GUIcontrollers.message.GUIMessageUnreadHomeController;
import GUIviews.AbstractView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Gui message unread home view.
 */
public class GUIMessageUnreadHomeView extends AbstractView {
    /**
     * The Controller.
     */
    GUIMessageUnreadHomeController controller;
    /**
     * The Has unread messages.
     */
    boolean hasUnreadMessages;

    /**
     * Instantiates a new Gui message unread home view.
     *
     * @param controller the controller
     */
    public GUIMessageUnreadHomeView(GUIMessageUnreadHomeController controller) {
        this.controller = controller;
        this.hasUnreadMessages = controller.hasUnreadMessages();
    }

    @Override
    public Scene createScene() {
        // Title
        Label title = new Label();
        title.setText("Unread Messages");
        title.setStyle("-fx-font-size: 25.0");

        // Back
        Button backButton;
        backButton = new Button("<-");
        backButton.setPrefWidth(50);
        backButton.setPrefHeight(25);

        // New message
        Button newMessageButton = new Button("+");
        newMessageButton.setPrefWidth(50);
        newMessageButton.setPrefHeight(25);

        // Filter options
        Button messagesButton = new Button("messages");
        messagesButton.setAlignment(Pos.CENTER);
        messagesButton.setStyle("-fx-font-size: 14.0");
        messagesButton.setTextFill(Color.BLACK);
        messagesButton.setPrefWidth(225);
        messagesButton.setPrefHeight(20);

        Button unreadButton = new Button("unread");
        unreadButton.setDisable(true);
        unreadButton.setAlignment(Pos.CENTER);
        unreadButton.setStyle("-fx-font-size: 14.0");
        unreadButton.setTextFill(Color.BLACK);
        unreadButton.setPrefWidth(225);
        unreadButton.setPrefHeight(20);

        Button announcementButton = new Button("announcements");
        announcementButton.setAlignment(Pos.CENTER);
        announcementButton.setStyle("-fx-font-size: 14.0");
        announcementButton.setTextFill(Color.BLACK);
        announcementButton.setPrefWidth(225);
        announcementButton.setPrefHeight(20);

        HBox filters = new HBox();
        filters.setPadding(new Insets(10.0, 0.0, 2.0, 0.0));
        filters.setAlignment(Pos.CENTER);
        filters.getChildren().addAll(messagesButton, unreadButton, announcementButton);

        // Header
        BorderPane header = new BorderPane();
        header.setPadding(new Insets(10.0, 11.0, 10.0,10.0));
        BorderPane.setAlignment(backButton, Pos.CENTER_LEFT);
        header.setLeft(backButton);
        BorderPane.setAlignment(title, Pos.CENTER);
        header.setCenter(title);
        BorderPane.setAlignment(newMessageButton, Pos.CENTER_RIGHT);
        header.setRight(newMessageButton);
        BorderPane.setAlignment(filters, Pos.CENTER);
        header.setBottom(filters);

        VBox unreadMessageHome = new VBox();

        if (hasUnreadMessages) {
            // Message Threads
            ScrollPane threads = new ScrollPane();
            threads.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

            // Gets all messageThreads before adding to threads
            VBox vbox = new VBox();

            HashMap<String, ArrayList<Integer>> allUnreadMessages = getAllUnreadMessages();
            for (Map.Entry sendThread : allUnreadMessages.entrySet()) {
                // Generic Message Thread
                String sendThreadName = (String) sendThread.getKey();

                // Name
                Label name = new Label();
                name.setText(sendThreadName);
                name.setStyle("-fx-font-size: 18.0");

                // Message preview
                Label preview = new Label();
                ArrayList<Integer> unreadMessageHistory = allUnreadMessages.get(sendThreadName);
                Integer unreadMessageHistorySize = unreadMessageHistory.size();
                preview.setText(getMessagePreview(unreadMessageHistory.get(unreadMessageHistorySize - 1)));
                preview.setStyle("-fx-font-size: 14.0");

                // See message button
                Button seeMessageButton = new Button(">");
                seeMessageButton.setPrefWidth(50);

                VBox info = new VBox();
                info.setPrefWidth(528);
                info.setAlignment(Pos.CENTER_LEFT);
                info.getChildren().addAll(name, preview);

                BorderPane messageThread = new BorderPane();
                if (controller.getAppearanceMode().equals("dark")) {
                    messageThread.setId("BorderPane-threads");
                }
                messageThread.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
                BorderPane.setAlignment(info, Pos.CENTER_LEFT);
                messageThread.setLeft(info);
                BorderPane.setAlignment(seeMessageButton, Pos.CENTER_RIGHT);
                messageThread.setRight(seeMessageButton);

                vbox.getChildren().addAll(messageThread);

                seeMessageButton.setOnAction(e -> seeMessageButtonClicked(name.getText()));
            }
            threads.setContent(vbox);
            unreadMessageHome.getChildren().addAll(header, threads);

        } else {
            Label noUnreadMessages = new Label();
            noUnreadMessages.setText("No unread messages");
            noUnreadMessages.setStyle("-fx-font-size: 20");

            unreadMessageHome.getChildren().addAll(header, noUnreadMessages);
        }
        unreadMessageHome.setAlignment(Pos.TOP_CENTER);

        newMessageButton.setOnAction(e -> newMessageButtonClicked());
        backButton.setOnAction(e -> backButtonClicked());

        messagesButton.setOnAction(e -> messagesButtonClicked());
        announcementButton.setOnAction(e -> announcementButtonClicked());

        Scene scene = new Scene(unreadMessageHome, 600.0, 429.0);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void seeMessageButtonClicked(String sendTo) {
        controller.seeMessageClicked(sendTo);
        application.setNextScene(controller, controller.run());
    }

    private void newMessageButtonClicked() {
        controller.newMessageClicked();
        application.setNextScene(controller, controller.run());
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private void messagesButtonClicked() {
        controller.messagesClicked();
        application.setNextScene(controller, controller.run());
    }

    private void announcementButtonClicked() {
        controller.announcementClicked();
        application.setNextScene(controller, controller.run());
    }

    private HashMap<String, ArrayList<Integer>> getAllUnreadMessages() {
        return controller.getAllUnreadMessages();
    }

    private String getMessagePreview(Integer messageId) {
        return controller.getMessagePreview(messageId);
    }


}
