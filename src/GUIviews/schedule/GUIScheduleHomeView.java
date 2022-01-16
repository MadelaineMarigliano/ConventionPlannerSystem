package GUIviews.schedule;

import GUIcontrollers.schedule.GUIScheduleHomeController;
import GUIviews.AbstractView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * The type Gui schedule home view.
 */
public class GUIScheduleHomeView extends AbstractView {
    /**
     * The Controller.
     */
    GUIScheduleHomeController controller;

    /**
     * Instantiates a new Gui schedule home view.
     *
     * @param controller the controller
     */
    public GUIScheduleHomeView(GUIScheduleHomeController controller){ this.controller = controller; }

    @Override
    public Scene createScene() {
        // Title
        Label title = new Label("Event Management");
        title.setLayoutX(170);
        title.setLayoutY(33);
        title.setStyle("-fx-font-size: 31.0");

        Button createEventButton = new Button("Create event");
        createEventButton.setLayoutX(224);
        createEventButton.setLayoutY(127);
        createEventButton.setStyle("-fx-font-size: 21.0");

        Button deleteEventButton = new Button("Delete event");
        deleteEventButton.setLayoutX(224);
        deleteEventButton.setLayoutY(191);
        deleteEventButton.setStyle("-fx-font-size: 21.0");


        Button backButton = new Button("Back");
        backButton.setLayoutX(506);
        backButton.setLayoutY(379);



        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(title, createEventButton, deleteEventButton, backButton);

        createEventButton.setOnAction(e -> createEventButtonClicked());
        deleteEventButton.setOnAction(e -> deleteEventButtonClicked());
        backButton.setOnAction(e -> backButtonClicked());

        Scene scene = new Scene(layout, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private void deleteEventButtonClicked() {
        controller.deleteEventClicked();
        application.setNextScene(controller, controller.run());
    }

    private void createEventButtonClicked() {
        controller.createEventClicked();
        application.setNextScene(controller, controller.run());
    }
}
