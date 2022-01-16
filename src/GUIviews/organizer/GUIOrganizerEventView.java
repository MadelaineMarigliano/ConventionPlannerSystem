package GUIviews.organizer;

import GUIcontrollers.organizer.GUIOrganizerEventController;
import GUIviews.AbstractView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

/**
 * The type Gui organizer event view.
 */
public class GUIOrganizerEventView extends AbstractView {
    /**
     * The Controller.
     */
    GUIOrganizerEventController controller;

    /**
     * Instantiates a new Gui organizer event view.
     *
     * @param controller the controller
     */
    public GUIOrganizerEventView(GUIOrganizerEventController controller){

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

        Button enrolButton = new Button("Enrol");
        enrolButton.setPrefSize(135,100);
        enrolButton.setLayoutX(10);
        enrolButton.setLayoutY(40);

        Button unenrolButton = new Button("Unenrol");
        unenrolButton.setPrefSize(135,100);
        unenrolButton.setLayoutX(160);
        unenrolButton.setLayoutY(40);

        Button viewEvent = new Button("View Schedules");
        viewEvent.setPrefSize(290,145);
        viewEvent.setLayoutX(300);
        viewEvent.setLayoutY(40);

        TextArea display = new TextArea();
        display.setLayoutX(10);
        display.setLayoutY(250);
        display.setPrefSize(580, 169);

        Button backButton = new Button("Go Back");
        backButton.setPrefSize(290, 55);
        backButton.setLayoutX(300);
        backButton.setLayoutY(190);

        Button downloadSchedule = new Button("Download Conference Itinerary");
        downloadSchedule.setPrefSize(285, 40);
        downloadSchedule.setLayoutX(10);
        downloadSchedule.setLayoutY(205);

        TextField idEventName = new TextField();
        idEventName.setPromptText("Enter Event Name");
        idEventName.setPrefSize(285, 50);
        idEventName.setLayoutX(10);
        idEventName.setLayoutY(150);

        layout.getChildren().addAll(title, enrolButton, unenrolButton, idEventName, viewEvent, display, backButton, downloadSchedule);

        backButton.setOnAction(e -> backBtnClicked());

        enrolButton.setOnAction(e -> enrolBtnClicked(idEventName.getText()));

        unenrolButton.setOnAction(e -> unenrolBtnClicked(idEventName.getText()));

        downloadSchedule.setOnAction(e -> downloadScheduleBtnClicked());

        viewEvent.setOnAction(e -> display.setText(viewEventsClicked()));

        Scene scene = new Scene(layout, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void downloadScheduleBtnClicked() {
        controller.downloadScheduleBtnClicked();
        application.setNextScene(controller, controller.run());
    }

    private void unenrolBtnClicked(String event) {
        try {
            controller.unenrolClicked(event);
            application.setNextScene(controller, controller.run());
        }

        catch (Exception e){
            application.setNextScene(controller, null);
        }
    }

    private String viewEventsClicked() {
        return controller.viewEventsClicked();
    }

    private void enrolBtnClicked(String event){
        try {
            controller.enrolClicked(event);
            application.setNextScene(controller, controller.run());
        }

        catch (Exception e){
            application.setNextScene(controller, null);
        }
    }

    private void backBtnClicked(){
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }
}
