package GUIviews.announcement;

import GUIcontrollers.announcement.GUIAnnouncementHomeController;
import GUIviews.AbstractView;
import entities.UserTypes;
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
 * The type Gui announcement home view.
 */
public class GUIAnnouncementHomeView extends AbstractView {
    /**
     * The Controller.
     */
    GUIAnnouncementHomeController controller;

    private String username;
    private UserTypes type;
    private boolean hasAnnouncements;

    /**
     * Instantiates a new Gui announcement home view.
     *
     * @param controller the controller
     */
    public GUIAnnouncementHomeView(GUIAnnouncementHomeController controller) {
        this.controller = controller;
        this.username = controller.getUsername();
        this.type = controller.getUserType();
        this.hasAnnouncements = controller.hasAnnouncements();
    }

    @Override
    public Scene createScene() {
        // Title
        Label title = new Label();
        title.setText("Announcements");
        title.setStyle("-fx-font-size: 25.0");

        // Back
        Button backButton = new Button("<-");
        backButton.setPrefWidth(50);
        backButton.setPrefHeight(25);

        // Filter options
        Button messagesButton = new Button("messages");
        messagesButton.setAlignment(Pos.CENTER);
        messagesButton.setStyle("-fx-font-size: 14.0");
        messagesButton.setTextFill(Color.BLACK);
        messagesButton.setPrefWidth(225);
        messagesButton.setPrefHeight(20);

        Button unreadButton = new Button("unread");
        unreadButton.setAlignment(Pos.CENTER);
        unreadButton.setStyle("-fx-font-size: 14.0");
        unreadButton.setTextFill(Color.BLACK);
        unreadButton.setPrefWidth(225);
        unreadButton.setPrefHeight(20);

        Button announcementButton = new Button("announcements");
        announcementButton.setDisable(true);
        announcementButton.setAlignment(Pos.CENTER);
        announcementButton.setStyle("-fx-font-size: 14.0");
        announcementButton.setTextFill(Color.BLACK);
        announcementButton.setPrefWidth(225);
        announcementButton.setPrefHeight(20);

        Button newAnnouncementButton = new Button("+");
        newAnnouncementButton.setPrefWidth(50);
        newAnnouncementButton.setPrefHeight(25);
        newAnnouncementButton.setDisable(true);


        HBox filters = new HBox();
        filters.setPadding(new Insets(10.0, 0.0, 2.0, 0.0));
        filters.setAlignment(Pos.CENTER);
        filters.getChildren().addAll(messagesButton, unreadButton, announcementButton);

        // Header
        BorderPane header = new BorderPane();
        header.setPadding(new Insets(10.0, 11.0, 10.0, 10.0));
        BorderPane.setAlignment(backButton, Pos.CENTER_LEFT);
        header.setLeft(backButton);
        BorderPane.setAlignment(title, Pos.CENTER);
        header.setCenter(title);
        BorderPane.setAlignment(newAnnouncementButton, Pos.CENTER_RIGHT);
        header.setRight(newAnnouncementButton);
        BorderPane.setAlignment(filters, Pos.CENTER);
        header.setBottom(filters);

        if (type.equals(UserTypes.ORGANIZER) || type.equals(UserTypes.SPEAKER)) {
            newAnnouncementButton.setDisable(false);
            newAnnouncementButton.setOnAction(e -> newAnnouncementButtonClicked());
        }

        VBox announcementHome = new VBox();

        if (hasAnnouncements) {
            // Mass Message / Announcement Threads
            ScrollPane threads = new ScrollPane();
            threads.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

            // Gets all massMessage / announcement threads before adding to threads
            VBox vbox = new VBox();

            // userAnnouncements
            HashMap<String, HashMap<String,ArrayList<Integer>>> allUserAnnouncements = getAllUserAnnouncements();

            for (Map.Entry type : allUserAnnouncements.entrySet()) {
                // Generic userTypeThreadName
                String typeThreadName = (String) type.getKey();

                ArrayList<Integer> typeAnnouncementHistory = getUserAnnouncements(typeThreadName);
                if (typeAnnouncementHistory.size() > 0) {
                    // typeName
                    Label typeName = new Label();
                    typeName.setText(typeThreadName);
                    typeName.setStyle("-fx-font-size: 18.0");

                    // Announcement preview
                    Label typePreview = new Label();
                    Integer typeAnnouncementHistorySize = typeAnnouncementHistory.size();
                    typePreview.setText(getAnnouncementPreview(typeAnnouncementHistory.get(typeAnnouncementHistorySize - 1)));
                    typePreview.setStyle("-fx-font-size: 14.0");

                    // See message button
                    Button seeAnnouncementButton = new Button(">");
                    seeAnnouncementButton.setPrefWidth(50);
                    seeAnnouncementButton.setPrefHeight(30);

                    VBox userInfo = new VBox();
                    userInfo.setPrefWidth(528);
                    userInfo.setAlignment(Pos.CENTER_LEFT);
                    userInfo.getChildren().addAll(typeName, typePreview);

                    BorderPane userAnnouncementThread = new BorderPane();
                    if (controller.getAppearanceMode().equals("dark")) {
                        userAnnouncementThread.setId("BorderPane-threads");
                    }
                    userAnnouncementThread.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
                    BorderPane.setAlignment(userInfo, Pos.CENTER_LEFT);
                    userAnnouncementThread.setLeft(userInfo);
                    BorderPane.setAlignment(seeAnnouncementButton, Pos.CENTER_RIGHT);
                    userAnnouncementThread.setRight(seeAnnouncementButton);

                    vbox.getChildren().addAll(userAnnouncementThread);

                    seeAnnouncementButton.setOnAction(e -> seeUserAnnouncementButtonClicked(typeName.getText()));
                }
            }

            // EventAnnouncements
            HashMap<String, HashMap<String, ArrayList<Integer>>> allEventAnnouncements = getAllEventAnnouncements();

            for (Map.Entry event : allEventAnnouncements.entrySet()) {
                // Generic eventThreadName
                String eventThreadName = (String) event.getKey();

                ArrayList<Integer> eventAnnouncementHistory = getEventAnnouncements(eventThreadName);
                if (eventAnnouncementHistory.size() > 0) {
                    // EventName
                    Label eventName = new Label();
                    eventName.setText(eventThreadName);
                    eventName.setStyle("-fx-font-size: 18.0");

                    // Announcement preview
                    Label eventPreview = new Label();
                    Integer eventAnnouncementHistorySize = eventAnnouncementHistory.size();
                    eventPreview.setText(getAnnouncementPreview(eventAnnouncementHistory.get(eventAnnouncementHistorySize - 1)));
                    eventPreview.setStyle("-fx-font-size: 14.0");

                    // See message button
                    Button seeEventAnnouncementButton = new Button(">");
                    seeEventAnnouncementButton.setPrefWidth(50);
                    seeEventAnnouncementButton.setPrefHeight(30);

                    VBox eventInfo = new VBox();
                    eventInfo.setPrefWidth(528);
                    eventInfo.setAlignment(Pos.CENTER_LEFT);
                    eventInfo.getChildren().addAll(eventName, eventPreview);

                    BorderPane eventAnnouncementThread = new BorderPane();
                    if (controller.getAppearanceMode().equals("dark")) {
                        eventAnnouncementThread.setId("BorderPane-threads");
                    }
                    eventAnnouncementThread.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
                    BorderPane.setAlignment(eventInfo, Pos.CENTER_LEFT);
                    eventAnnouncementThread.setLeft(eventInfo);
                    BorderPane.setAlignment(seeEventAnnouncementButton, Pos.CENTER_RIGHT);
                    eventAnnouncementThread.setRight(seeEventAnnouncementButton);

                    vbox.getChildren().addAll(eventAnnouncementThread);

                    seeEventAnnouncementButton.setOnAction(e -> seeEventAnnouncementButtonClicked(eventName.getText()));
                }
            }
            threads.setContent(vbox);
            announcementHome.getChildren().addAll(header, threads);

        } else {
            Label noAnnouncements = new Label();
            noAnnouncements.setText("No announcements");
            noAnnouncements.setStyle("-fx-font-size: 20");

            announcementHome.getChildren().addAll(header, noAnnouncements);
        }
        announcementHome.setAlignment(Pos.TOP_CENTER);

        backButton.setOnAction(e -> backButtonClicked());

        messagesButton.setOnAction(e -> messagesButtonClicked());
        unreadButton.setOnAction(e -> unreadButtonClicked());

        Scene scene = new Scene(announcementHome, 600.0, 429.0);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void newAnnouncementButtonClicked() {
        controller.newAnnouncementClicked();
        application.setNextScene(controller, controller.run());
    }

    private void seeEventAnnouncementButtonClicked(String eventName) {
        controller.seeEventAnnouncementClicked(eventName);
        application.setNextScene(controller, controller.run());
    }

    private void seeUserAnnouncementButtonClicked(String sender) {
        controller.seeUserAnnouncementClicked(sender);
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

    private void unreadButtonClicked() {
        controller.unreadClicked();
        application.setNextScene(controller, controller.run());
    }

    private HashMap<String, HashMap<String, ArrayList<Integer>>> getAllEventAnnouncements() {
        return controller.getAllEventAnnouncements();
    }

    private ArrayList<Integer> getEventAnnouncements(String eventName) {
        return controller.getEventAnnouncements(eventName);
    }

    private HashMap<String, HashMap<String, ArrayList<Integer>>> getAllUserAnnouncements() {
        return controller.getAllUserAnnouncements();
    }

    private String getAnnouncementPreview(Integer messageId) {
        return controller.getAnnouncementPreview(messageId);
    }

    private ArrayList<Integer> getUserAnnouncements(String typeName) {
        return controller.getUserAnnouncements(typeName);
    }

}
