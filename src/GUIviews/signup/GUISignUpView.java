package GUIviews.signup;

import GUIcontrollers.signup.GUISignUpController;
import GUIviews.AbstractView;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * The type Gui sign up view.
 */
public class GUISignUpView extends AbstractView {

    /**
     * The Controller.
     */
    GUISignUpController controller;
    /**
     * The Error.
     */
    boolean error = false;

    /**
     * Instantiates a new Gui sign up view.
     *
     * @param controller the controller
     */
    public GUISignUpView(GUISignUpController controller){

        this.controller = controller;
    }
    @Override
    public Scene createScene() {
        //Title
        Label title = new Label("Sign Up");
        title.setLayoutX(250);
        title.setLayoutY(25);
        title.setStyle("-fx-font-size: 29.0");

        Label firstNameText = new Label("First Name");
        firstNameText.setLayoutX(76);
        firstNameText.setLayoutY(83);
        firstNameText.setStyle("-fx-font-size: 14.0");

        Label lastNameText = new Label("Last Name");
        lastNameText.setLayoutX(76.0);
        lastNameText.setLayoutY(145);
        lastNameText.setStyle("-fx-font-size: 14.0");

        Label usernameText = new Label("Username");
        usernameText.setLayoutX(76);
        usernameText.setLayoutY(208);
        usernameText.setStyle("-fx-font-size: 14.0");

        Label passwordText = new Label("Password");
        passwordText.setLayoutX(76.0);
        passwordText.setLayoutY(273);
        passwordText.setStyle("-fx-font-size: 14.0");

        TextField firstName = new TextField();
        firstName.setLayoutX(76);
        firstName.setLayoutY(103);

        TextField lastName = new TextField();
        lastName.setLayoutX(76);
        lastName.setLayoutY(165);

        TextField username = new TextField();
        username.setLayoutX(76);
        username.setLayoutY(228);

        PasswordField password = new PasswordField();
        password.setLayoutX(76);
        password.setLayoutY(293);

        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(76);
        submitButton.setLayoutY(346);
        submitButton.setStyle("-fx-font-size: 15");
        submitButton.setDisable(true);

        submitButton.disableProperty().bind(
                Bindings.createBooleanBinding( () ->
                        firstName.getText().trim().isEmpty(), firstName.textProperty()
                )
                .or(  Bindings.createBooleanBinding( () ->
                         lastName.getText().trim().isEmpty(), lastName.textProperty()
                     )
                )
                .or(  Bindings.createBooleanBinding( () ->
                        username.getText().trim().isEmpty(), username.textProperty()
                    )
                )
                .or(  Bindings.createBooleanBinding( () ->
                        password.getText().trim().isEmpty(), password.textProperty()
                    )
                )

        );

        Button backButton = new Button("Back");
        backButton.setLayoutX(528);
        backButton.setLayoutY(377);



        AnchorPane layout = new AnchorPane();

        if (error){
            Label errorMessage = new Label("Invalid entries. Please try again");
            errorMessage.setLayoutX(300);
            errorMessage.setLayoutY(100);
            errorMessage.setStyle("-fx-text-fill: red");
            layout.getChildren().add(errorMessage);

        }
        layout.getChildren().addAll(title, firstNameText, lastNameText, usernameText, passwordText, firstName,
                lastName, username, password, submitButton, backButton);

        submitButton.setOnAction(e -> submitButtonClicked(firstName.getText(), lastName.getText(),
                username.getText(), password.getText()));
        backButton.setOnAction(e -> backButtonClicked());

        Scene scene = new Scene(layout, 600, 429);
        scene.getStylesheets().add("resources/light.css");
        return scene;
    }

    private void submitButtonClicked(String firstName, String lastName, String username, String password){
        if (controller.validEntries(firstName, lastName, username, password)){
            controller.submitClicked();
            application.setNextScene(controller, controller.run());
        }
        else{
            this.error = true;
            application.setNextScene(controller, null);
        }
    }

    private void backButtonClicked(){
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

}


















