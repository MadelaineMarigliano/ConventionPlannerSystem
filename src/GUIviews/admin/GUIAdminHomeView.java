package GUIviews.admin;

import GUIcontrollers.admin.GUIAdminHomeController;
import GUIviews.AbstractView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * The type Gui admin home view.
 */
public class GUIAdminHomeView extends AbstractView {
    private GUIAdminHomeController controller;

    /**
     * Instantiates a new Gui admin home view.
     *
     * @param controller the controller
     */
    public GUIAdminHomeView(GUIAdminHomeController controller){ this.controller = controller; }

    @Override
    public Scene createScene() {
        // Title
        Label title = new Label("Admin Menu");
        title.setLayoutX(215);
        title.setLayoutY(25);
        title.setStyle("-fx-font-size: 31.0");

        Button createUserButton = new Button("Create User");
        createUserButton.setPrefSize(290, 225);
        createUserButton.setLayoutX(10);
        createUserButton.setLayoutY(100);
        createUserButton.setStyle("-fx-font-size: 21.0");

        Button deleteUserButton = new Button("Delete User");
        deleteUserButton.setPrefSize(290, 225);
        deleteUserButton.setLayoutX(305);
        deleteUserButton.setLayoutY(100);
        deleteUserButton.setStyle("-fx-font-size: 21.0");

        Button backButton = new Button("Back");
        backButton.setPrefSize(580, 70);
        backButton.setLayoutX(10);
        backButton.setLayoutY(340);



        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(title, createUserButton, deleteUserButton, backButton);

        createUserButton.setOnAction(e -> createUserButtonClicked());
        deleteUserButton.setOnAction(e -> deleteUserButtonClicked());
        backButton.setOnAction(e -> backButtonClicked());

        Scene scene = new Scene(layout, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private void deleteUserButtonClicked() {
        controller.deleteUserClicked();
        application.setNextScene(controller, controller.run());
    }


    private void createUserButtonClicked() {
        controller.createUserClicked();
        application.setNextScene(controller, controller.run());
    }
}
