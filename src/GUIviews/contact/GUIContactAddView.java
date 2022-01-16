package GUIviews.contact;

import GUIcontrollers.contact.GUIContactAddController;
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
 * The type Gui contact add view.
 */
public class GUIContactAddView extends AbstractView {
    /**
     * The Controller.
     */
    GUIContactAddController controller;
    private boolean addError;

    /**
     * Instantiates a new Gui contact add view.
     *
     * @param controller the controller
     */
    public GUIContactAddView(GUIContactAddController controller){
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
        Button addButton = new Button("Add to Contacts");
        Label feedbackLabel = new Label();

        addText.setPrefWidth(250);
        addTextField.setPrefWidth(250);
        addButton.setPrefWidth(250);
        feedbackLabel.setPrefSize(250, Double.MAX_VALUE);

        addText.setStyle("-fx-font-size: 18");
        addTextField.setStyle("-fx-font-size: 18");
        addButton.setStyle("-fx-font-size: 18");
        feedbackLabel.setStyle("-fx-font-size: 14");

        vbox.getChildren().addAll(addText, addTextField, addButton, feedbackLabel);

        addButton.setOnAction(e -> {
            String feedback = addButtonClicked(addTextField.getText());
            feedbackLabel.setText(feedback);
            if(addError){
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

    private String addButtonClicked(String usernameAdd){
        try{
            controller.addClicked(usernameAdd);
        }catch (Exception e){
            addError = true;
            return e.getMessage();
        }
        addError = false;
        return "Contact added ^-^";

    }

    private void backButtonClicked(){
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }
}
