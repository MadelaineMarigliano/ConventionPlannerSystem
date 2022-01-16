package GUIviews.organizer;

import GUIviews.AbstractView;
import javafx.scene.image.Image;

import java.io.*;

import GUIcontrollers.organizer.GUIOrganizerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * The type Gui organizer view.
 */
public class GUIOrganizerView extends AbstractView {
    /**
     * The Controller.
     */
    GUIOrganizerController controller;

    private transient Image icon;

    /**
     * Instantiates a new Gui organizer view.
     *
     * @param controller the controller
     */
    public GUIOrganizerView(GUIOrganizerController controller) {
        this.controller = controller;
        try {
            this.icon = new Image(new FileInputStream("phase2/src/resources/" + controller.getColor() + ".png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Scene createScene() {
        //Profile Button
        Button profileButton = new Button();
        profileButton.setStyle("-fx-background-radius: 28.0");
        profileButton.setPadding(new Insets(0.0, 0.0, 0.0, 0.0));
        profileButton.setMinWidth(60);
        profileButton.setMaxWidth(60);
        profileButton.setMinHeight(60);
        profileButton.setMaxHeight(60);
        ImageView view = new ImageView(icon);
        view.setFitHeight(66.22);
        view.setFitWidth(68.53);
        view.setPreserveRatio(true);
        view.setSmooth(true);
        profileButton.setGraphic(view);
        HBox profileButtonBox = new HBox();
        profileButtonBox.getChildren().addAll(profileButton);
        profileButtonBox.setAlignment(Pos.CENTER);

        //Title
        Label title = new Label("Main Menu");
        title.setStyle("-fx-font-size: 30.0");
        HBox titleBox = new HBox();
        titleBox.getChildren().addAll(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(6.0, 0.0, 0.0, 0.0));

        Label welcomeBack = new Label("Welcome back, " + controller.getUsername());
        welcomeBack.setStyle("-fx-font-size: 16.0");
        HBox welcomeBackBox = new HBox();
        welcomeBackBox.getChildren().addAll(welcomeBack);
        welcomeBackBox.setAlignment(Pos.CENTER);

        Button eventButton = new Button("My Events");
        eventButton.setPrefSize(185, 85);
        eventButton.setStyle("-fx-font-size: 16.0");

        Button contactsButton = new Button("My Contacts");
        contactsButton.setPrefSize(185, 85);
        contactsButton.setStyle("-fx-font-size: 16.0");

        Button messageButton = new Button("My Messages");
        messageButton.setPrefSize(185, 85);
        messageButton.setStyle("-fx-font-size: 16.0");

        HBox topButtons = new HBox();
        topButtons.getChildren().addAll(eventButton, contactsButton, messageButton);
        topButtons.setAlignment(Pos.CENTER);
        topButtons.setPadding(new Insets(19.0, 15.0, 3.5, 15.0));
        topButtons.setSpacing(7.5);

        Button manageRoomsButton = new Button("Manage Rooms");
        manageRoomsButton.setPrefSize(136.8, 85);
        manageRoomsButton.setStyle("-fx-font-size: 16.0");

        Button manageUsersButton = new Button("Manage Users");
        manageUsersButton.setPrefSize(136.7, 85);
        manageUsersButton.setStyle("-fx-font-size: 16.0");

        Button manageEventsButton = new Button("Manage Events");
        manageEventsButton.setPrefSize(136.8, 85);
        manageEventsButton.setStyle("-fx-font-size: 16.0");

        Button statsButton = new Button("Stats");
        statsButton.setPrefSize(136.7, 85);
        statsButton.setStyle("-fx-font-size: 16.0");

        HBox bottomButtons = new HBox();
        bottomButtons.getChildren().addAll(manageRoomsButton, manageUsersButton, manageEventsButton, statsButton);
        bottomButtons.setAlignment(Pos.CENTER);
        bottomButtons.setPadding(new Insets(3.5, 15.0, 25.0, 15.0));
        bottomButtons.setSpacing(7.5);

        Button logoutButton = new Button("Log out");
        logoutButton.setPrefSize(570, 43);
        logoutButton.setStyle("-fx-font-size: 16.0");
        HBox logoutButtonBox = new HBox();
        logoutButtonBox.getChildren().addAll(logoutButton);
        logoutButtonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(profileButtonBox, titleBox, welcomeBackBox, topButtons, bottomButtons, logoutButtonBox);
        layout.setPadding(new Insets(15.0, 0.0, 15.0, 0.0));

        profileButton.setOnAction(e -> profileButtonClicked());
        eventButton.setOnAction(e -> eventButtonClicked());
        contactsButton.setOnAction(e -> contactsButtonClicked());
        messageButton.setOnAction(e -> messageButtonClicked());
        manageRoomsButton.setOnAction(e -> manageRoomButtonClicked());
        manageUsersButton.setOnAction(e -> manageUsersButtonClicked());
        manageEventsButton.setOnAction(e -> manageEventsButtonClicked());
        statsButton.setOnAction(e -> statsButtonClicked());
        logoutButton.setOnAction(e -> logoutButtonClicked());

        Scene scene = new Scene(layout, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void statsButtonClicked() {
        controller.statsButtonClicked();
        application.setNextScene(controller, controller.run());
    }

    private void profileButtonClicked() {
        controller.profileClicked();
        application.setNextScene(controller, controller.run());
    }

    private void eventButtonClicked(){
        controller.eventsClicked();
        application.setNextScene(controller, controller.run());
    }
    private void contactsButtonClicked(){
        controller.contactsClicked();
        application.setNextScene(controller, controller.run());
    }
    private void messageButtonClicked(){
        controller.messagesClicked();
        application.setNextScene(controller, controller.run());
    }
    private void manageRoomButtonClicked(){
        controller.manageRoomsClicked();
        application.setNextScene(controller, controller.run());
    }
    private void manageUsersButtonClicked(){
        controller.manageUsersClicked();
        application.setNextScene(controller, controller.run());
    }
    private void manageEventsButtonClicked(){
        controller.manageEventsClicked();
        application.setNextScene(controller, controller.run());
    }
    private void logoutButtonClicked(){
        controller.logoutClicked();
        application.setNextScene(controller, controller.run());
    }
}
















