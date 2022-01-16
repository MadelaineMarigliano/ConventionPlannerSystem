package GUIviews.contact;

import GUIcontrollers.contact.GUIContactHomeController;
import GUIviews.AbstractView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

/**
 * The type Gui contact home view.
 */
public class GUIContactHomeView extends AbstractView {

    /**
     * The Controller.
     */
    GUIContactHomeController controller;

    /**
     * Instantiates a new Gui contact home view.
     *
     * @param controller the controller
     */
    public GUIContactHomeView(GUIContactHomeController controller){
        this.controller = controller;
    }
    @Override
    public Scene createScene() {
        BorderPane borderPane = new BorderPane();

        VBox vbox = createCenter();
        HBox hbox = createBottom();

        borderPane.setCenter(vbox);
        borderPane.setBottom(hbox);

        Scene scene = new Scene(borderPane, 600.0, 429.0);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private VBox createCenter(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(115, 175, 0, 175));
        vbox.setSpacing(15);

        Button viewButton = new Button("View Contacts");
        Button addButton = new Button("Add Contact");
        Button removeButton = new Button("Remove Contact");

        viewButton.setPrefWidth(250);
        addButton.setPrefWidth(250);
        removeButton.setPrefWidth(250);

        viewButton.setStyle("-fx-font-size: 18");
        addButton.setStyle("-fx-font-size: 18");
        removeButton.setStyle("-fx-font-size: 18");

        viewButton.setOnAction(e -> viewButtonClicked());
        addButton.setOnAction(e -> addButtonClicked());
        removeButton.setOnAction(e -> removeButtonClicked());

        vbox.getChildren().addAll(viewButton, addButton, removeButton);

        return vbox;
    }

    private HBox createBottom(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(5);
        hbox.setStyle("-fx-background-color: #cfd4d3;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 14");

        backButton.setOnAction(e -> backButtonClicked());

        hbox.getChildren().addAll(backButton);

        return hbox;
    }

    private void viewButtonClicked(){
        controller.viewClicked();
        application.setNextScene(controller, controller.run());
    }

    private void addButtonClicked(){
        controller.addClicked();
        application.setNextScene(controller, controller.run());
    }

    private void removeButtonClicked(){
        controller.removeClicked();
        application.setNextScene(controller, controller.run());
    }

    private void backButtonClicked(){
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

}
