package GUIviews.contact;

import GUIcontrollers.contact.GUIContactRemoveController;
import GUIviews.AbstractView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The type Gui contact remove view.
 */
public class GUIContactRemoveView extends AbstractView {
    /**
     * The Controller.
     */
    GUIContactRemoveController controller;
    /**
     * The Remove error.
     */
    boolean removeError;

    /**
     * Instantiates a new Gui contact remove view.
     *
     * @param controller the controller
     */
    public GUIContactRemoveView(GUIContactRemoveController controller){
        this.controller = controller;
    }
    @Override
    public Scene createScene() {
        BorderPane borderPane = new BorderPane();

        VBox vbox = createCenter();
        HBox hbox = createBottom();

        borderPane.setCenter(vbox);
        borderPane.setBottom(hbox);

        Scene scene = new Scene(borderPane, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private VBox createCenter(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(115, 175, 0, 175));
        vbox.setSpacing(15);

        Label addText = new Label("Enter a Username:");
        TextField addTextField = new TextField();
        Button removeButton = new Button("Remove Contact");
        Label feedbackLabel = new Label();

        addText.setPrefWidth(250);
        addTextField.setPrefWidth(250);
        removeButton.setPrefWidth(250);
        feedbackLabel.setPrefSize(250, Double.MAX_VALUE);

        addText.setStyle("-fx-font-size: 18");
        addTextField.setStyle("-fx-font-size: 18");
        removeButton.setStyle("-fx-font-size: 18");
        feedbackLabel.setStyle("-fx-font-size: 14");

        vbox.getChildren().addAll(addText, addTextField, removeButton, feedbackLabel);

        removeButton.setOnAction(e -> {
            String feedback = removeButtonClicked(addTextField.getText());
            feedbackLabel.setText(feedback);
            if(removeError){
                feedbackLabel.setStyle("-fx-text-fill: red");
            }else{
                feedbackLabel.setStyle("-fx-text-fill: green");
            }
        });

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

    private String removeButtonClicked(String usernameAdd){
        try{
            controller.removeClicked(usernameAdd);
        }catch (Exception e){
            removeError = true;
            return e.getMessage();
        }
        removeError = false;
        return "Contact removed ^-^";

    }

    private void backButtonClicked(){
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }
}
