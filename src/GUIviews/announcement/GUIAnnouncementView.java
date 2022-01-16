package GUIviews.announcement;

import GUIcontrollers.announcement.GUIAnnouncementController;
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
 * The type Gui announcement view.
 */
public class GUIAnnouncementView extends AbstractView {
    private GUIAnnouncementController controller;

    private String threadWith;
    private String username;
    private Background fromMsg = new Background(new BackgroundFill(Color.web("#CEDCD5"), new CornerRadii(10.0), Insets.EMPTY));
    private Background fromMsgDark = new Background(new BackgroundFill(Color.web("#31AD6D"), new CornerRadii(10.0), Insets.EMPTY));
    private Background toMsg = new Background(new BackgroundFill(Color.web("#CBDFBF"), new CornerRadii(10.0), Insets.EMPTY));
    private Background toMsgDark = new Background(new BackgroundFill(Color.web("#63A63A"), new CornerRadii(10.0), Insets.EMPTY));

    /**
     * Instantiates a new Gui announcement view.
     *
     * @param controller the controller
     */
    public GUIAnnouncementView(GUIAnnouncementController controller) {
        this.controller = controller;
        this.threadWith = controller.getThreadWith();
        this.username = controller.getUsername();
    }

    @Override
    public Scene createScene() {
        // ThreadWith banner
        Label banner = new Label();
        banner.setText(threadWith);
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

        // Announcement history
        ScrollPane announcementHistory = new ScrollPane();
        announcementHistory.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        announcementHistory.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        announcementHistory.setPadding(new Insets(10.0, 10.0, 10.0, 17.0));

        GridPane announcementOrder = new GridPane();
        if (controller.getAppearanceMode().equals("dark")) {
            announcementOrder.getStyleClass().add("grid");
        }
        announcementOrder.setVgap(3.0);

        // Announcement
        ArrayList<Integer> announcements = (ArrayList<Integer>) getAnnouncementsFor().clone();
        // to start from newest to oldest
        Collections.reverse(announcements);
        Integer n = announcements.size();

        for (Integer i : announcements) {

            // announcements
            Label announcement = new Label();
            announcement.setWrapText(true);
            announcement.setPadding(new Insets(5.0, 7.0, 5.0, 7.0));
            announcement.setMaxWidth(550);
            String a = getAnnouncement(i);
            announcement.setText(a);
            announcement.setStyle("-fx-font-size: 16.0");

            HBox hbox = new HBox();
            if (controller.getAppearanceMode().equals("dark")) {
                hbox.setId("hbox-group-threads");
            }
            hbox.setMinWidth(550);
            hbox.setMaxWidth(550);
            hbox.setPadding(new Insets(4.0, 7.0, 0.0, 7.0));

            // sender
            Label from = new Label();
            from.setPadding(new Insets(5.0, 7.0, 5.0, 7.0));
            from.setMaxWidth(450);
            String sender = getSender(i);
            from.setText(sender);
            from.setStyle("-fx-font-size: 12.0");

            if (sender.equals(username)) {
                // from announcements
                announcement.setBackground(fromMsg);
                if (controller.getAppearanceMode().equals("dark")) {
                    announcement.setBackground(fromMsgDark);
                }
                hbox.setAlignment(Pos.BASELINE_RIGHT);
            } else {
                // to announcements
                announcement.setBackground(toMsg);
                if (controller.getAppearanceMode().equals("dark")) {
                    announcement.setBackground(toMsgDark);
                }
                hbox.setAlignment(Pos.BASELINE_LEFT);
            }
            hbox.getChildren().add(announcement);
            announcementOrder.add(hbox, 0, n);
            n--;
        }

        announcementHistory.setContent(announcementOrder);
        announcementHistory.setVvalue(announcementHistory.getVmax());

        // Layout
        BorderPane border = new BorderPane();
        border.setTop(header);
        border.setCenter(announcementHistory);

        if (isOrganizerMessagingSpeakers() || isOrganizerMessagingAttendees() || isSpeakerMessagingAttendees()) {
            // Announcement field
            TextField announcementField = new TextField();
            announcementField.setPrefWidth(525);
            announcementField.setPrefHeight(30);

            // Send
            Button sendButton = new Button("Send");
            sendButton.setPrefWidth(75);
            sendButton.setPrefHeight(30);
            sendButton.setDisable(true);

            sendButton.disableProperty().bind(
                    Bindings.createBooleanBinding( () ->
                            announcementField.getText().trim().isEmpty(), announcementField.textProperty()));

            // Send announcement area
            GridPane announcementArea = new GridPane();
            announcementArea.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
            GridPane.setHalignment(announcementField, HPos.LEFT);
            announcementArea.add(announcementField, 0, 0);
            GridPane.setHalignment(sendButton, HPos.RIGHT);
            announcementArea.add(sendButton, 1, 0);

            border.setBottom(announcementArea);

            sendButton.setOnAction(e -> sendButtonClicked(announcementField));
        }

        backButton.setOnAction(e -> backButtonClicked());

        Scene scene = new Scene(border, 600.0, 429.0);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void sendButtonClicked(TextField announcementField) {
        controller.sendClicked(announcementField.getText());
        application.setNextScene(controller, null);
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private ArrayList<Integer> getAnnouncementsFor() {
        return controller.getAnnouncementsFor();
    }

    private String getAnnouncement(Integer messageId) {
        return controller.getAnnouncement(messageId);
    }

    private String getSender(Integer messageId) {
        return controller.getSender(messageId);
    }

    private boolean isOrganizerMessagingSpeakers() {
        return controller.isOrganizerMessagingSpeakers();
    }

    private boolean isOrganizerMessagingAttendees() {
        return controller.isOrganizerMessagingAttendees();
    }

    private boolean isSpeakerMessagingAttendees() {
        return controller.isSpeakerMessagingAttendees();
    }
}
