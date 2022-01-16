package GUIviews.speaker;

import GUIcontrollers.speaker.GUISpeakerEventController;
import GUIviews.AbstractView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * The type Gui speaker event view.
 */
public class GUISpeakerEventView extends AbstractView {

    /**
     * The Controller.
     */
    GUISpeakerEventController controller;

    /**
     * Instantiates a new Gui speaker event view.
     *
     * @param controller the controller
     */
    public GUISpeakerEventView(GUISpeakerEventController controller) {
        this.controller = controller;
    }

    @Override
    public Scene createScene() {
        AnchorPane layout = new AnchorPane();

        Label title = new Label("Manage Events");
        title.setStyle("-fx-font-size: 29.0");
        AnchorPane.setLeftAnchor(title, 0.0);
        AnchorPane.setRightAnchor(title, 0.0);
        title.setAlignment(Pos.CENTER);

        Button viewEvent = new Button("View Schedules");
        viewEvent.setPrefSize(580,145);
        viewEvent.setLayoutX(10);
        viewEvent.setLayoutY(40);

        TextArea display = new TextArea();
        display.setLayoutX(10);
        display.setLayoutY(250);
        display.setPrefSize(580, 169);

        Button backButton = new Button("Go Back");
        backButton.setPrefSize(580, 55);
        backButton.setLayoutX(10);
        backButton.setLayoutY(190);

        backButton.setOnAction(e -> backBtnClicked());

        viewEvent.setOnAction(e -> display.setText(viewEventsClicked()));

        layout.getChildren().addAll(title, viewEvent, display, backButton);

        Scene scene = new Scene(layout, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private String viewEventsClicked() {
        return controller.viewEventsClicked();
    }

    private void backBtnClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }
}
