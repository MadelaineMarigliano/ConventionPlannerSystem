package GUIviews.room;

import GUIcontrollers.room.GUIRoomController;
import GUIviews.AbstractView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * The type Gui room view.
 */
public class GUIRoomView extends AbstractView {
    /**
     * The Controller.
     */
    GUIRoomController controller;
    /**
     * The Error.
     */
    boolean error = false;

    /**
     * Instantiates a new Gui room view.
     *
     * @param controller the controller
     */
    public GUIRoomView(GUIRoomController controller) {
        this.controller = controller;
    }

    @Override
    public Scene createScene() {
        AnchorPane layout = new AnchorPane();

        Label title = new Label("Room Manager");
        title.setStyle("-fx-font-size: 29.0");
        AnchorPane.setLeftAnchor(title, 0.0);
        AnchorPane.setRightAnchor(title, 0.0);
        title.setAlignment(Pos.CENTER);

        TextArea display = new TextArea();
        display.setLayoutX(10);
        display.setLayoutY(250);
        display.setPrefSize(580, 169);

        Button addButton = new Button("Add Room");
        addButton.setPrefSize(100, 100);
        addButton.setLayoutX(10);
        addButton.setLayoutY(40);

        Button delButton = new Button("Remove Room");
        delButton.setPrefSize(100, 100);
        delButton.setLayoutX(10);
        delButton.setLayoutY(145);

        Button viewButton = new Button("View Rooms");
        viewButton.setPrefSize(290, 145);
        viewButton.setLayoutX(300);
        viewButton.setLayoutY(40);

        Button backButton = new Button("Go Back");
        backButton.setPrefSize(290, 55);
        backButton.setLayoutX(300);
        backButton.setLayoutY(190);

        TextField idTextAdd = new TextField();
        idTextAdd.setPromptText("Enter Room ID");
        idTextAdd.setPrefSize(170, 50);
        idTextAdd.setLayoutX(120);
        idTextAdd.setLayoutY(40);

        TextField idTextDel = new TextField();
        idTextDel.setPromptText("Enter Room ID");
        idTextDel.setPrefSize(170, 100);
        idTextDel.setLayoutX(120);
        idTextDel.setLayoutY(145);

        TextField capacityText = new TextField();
        capacityText.setPromptText("Enter Room Capacity");
        capacityText.setPrefSize(170, 50);
        capacityText.setLayoutX(120);
        capacityText.setLayoutY(90);

        if (error){
            display.setText("Invalid Input");
        }

        layout.getChildren().addAll(title, display, addButton, delButton, viewButton, idTextAdd, capacityText, idTextDel, backButton);

        addButton.setOnAction(e -> addButtonClicked(idTextAdd.getText(), capacityText.getText()));

        viewButton.setOnAction(e -> display.setText(viewRoomClicked()));

        backButton.setOnAction(e -> backBtnClicked());

        delButton.setOnAction(e -> delButtonClicked(idTextDel.getText()));

        Scene scene = new Scene(layout, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private String viewRoomClicked() {
        return controller.viewRoom();
    }

    private void backBtnClicked(){
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private void delButtonClicked(String id) {
        try{
            controller.delClicked(Integer.parseInt(id));
            application.setNextScene(controller, controller.run());
        }
        catch(Exception e){
            this.error = true;
            application.setNextScene(controller, null);
        }
    }

    private void addButtonClicked(String id, String capacity) {
        try {
            controller.addClicked(Integer.parseInt(id), Integer.parseInt(capacity));
            application.setNextScene(controller, controller.run());
        }

        catch (Exception e){
            this.error = true;
            application.setNextScene(controller, null);
        }
    }



}