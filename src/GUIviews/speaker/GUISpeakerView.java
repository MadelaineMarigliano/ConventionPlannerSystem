package GUIviews.speaker;

import GUIcontrollers.speaker.GUISpeakerController;
import GUIviews.AbstractView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type Gui speaker view.
 */
public class GUISpeakerView extends AbstractView {
    /**
     * The Controller.
     */
    GUISpeakerController controller;

    private transient Image icon;

    /**
     * Instantiates a new Gui speaker view.
     *
     * @param controller the controller
     */
    public GUISpeakerView(GUISpeakerController controller){
        this.controller = controller;
        try {
            this.icon = new Image(new FileInputStream("phase2/src/resources/" + controller.getColor() + ".png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Scene createScene() {
        // Profile Button
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

        // Title
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

        Button eventButton = new Button ("My Events");
        eventButton.setPrefSize(185, 177);
        eventButton.setStyle("-fx-font-size: 16.0");

        Button contactsButton = new Button("My Contacts");
        contactsButton.setPrefSize(185, 177);
        contactsButton.setStyle("-fx-font-size: 16.0");

        Button messageButton = new Button("My Messages");
        messageButton.setPrefSize(185,177);
        messageButton.setStyle("-fx-font-size: 16.0");

        HBox allButtons = new HBox();
        allButtons.getChildren().addAll(eventButton, contactsButton, messageButton);
        allButtons.setAlignment(Pos.CENTER);
        allButtons.setPadding(new Insets(19.0, 15.0, 25.0, 15.0));
        allButtons.setSpacing(7.5);

        Button logoutButton = new Button("Log out");
        logoutButton.setPrefSize(570, 43);
        logoutButton.setStyle("-fx-font-size: 16.0");
        HBox logoutButtonBox = new HBox();
        logoutButtonBox.getChildren().addAll(logoutButton);
        logoutButtonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(profileButtonBox, titleBox, welcomeBackBox, allButtons, logoutButtonBox);
        layout.setPadding(new Insets(15.0, 0.0, 15.0, 0.0));

        profileButton.setOnAction(e -> profileButtonClicked());
        eventButton.setOnAction(e -> eventButtonClicked());
        contactsButton.setOnAction(e -> contactsButtonClicked());
        messageButton.setOnAction(e -> messageButtonClicked());
        logoutButton.setOnAction(e -> logoutButtonClicked());

        Scene scene = new Scene(layout, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void profileButtonClicked() {
        controller.profileClicked();
        application.setNextScene(controller, controller.run());
    }

    private void eventButtonClicked(){
        controller.eventClicked();
        application.setNextScene(controller, controller.run());
    }

    private void contactsButtonClicked(){
        controller.contactClicked();
        application.setNextScene(controller, controller.run());
    }

    private void messageButtonClicked(){
        controller.messageClicked();
        application.setNextScene(controller, controller.run());
    }

    private void logoutButtonClicked(){
        controller.logoutClicked();
        application.setNextScene(controller, controller.run());
    }
}
