package GUIviews.login;

import GUIcontrollers.login.GUILoginController;
import GUIviews.AbstractView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * The type Gui login view.
 */
public class GUILoginView extends AbstractView {
    /**
     * The Controller.
     */
    GUILoginController controller;
    /**
     * The Error.
     */
    boolean error = false;

    /**
     * Instantiates a new Gui login view.
     *
     * @param controller the controller
     */
    public GUILoginView(GUILoginController controller) {
        this.controller = controller;
    }

    @Override
    public Scene createScene() {
        //Title
        Label title = new Label();
        title.setText("Login");
        title.setLayoutX(246.0);
        title.setLayoutY(23.0);
        title.setStyle("-fx-font-size: 44.0");

        Label usernameText = new Label();
        usernameText.setText("Username");
        usernameText.setLayoutX(226.0);
        usernameText.setLayoutY(142.0);

        TextField username = new TextField();
        username.setLayoutX(226.0);
        username.setLayoutY(159.0);

        Label passwordText = new Label("Password");
        passwordText.setLayoutX(226.0);
        passwordText.setLayoutY(209.0);

        PasswordField password = new PasswordField();
        password.setLayoutX(226.0);
        password.setLayoutY(226.0);

        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(226.0);
        submitButton.setLayoutY(285.0);
        submitButton.setStyle("-fx-font-size: 15.0");

        Button signUpButton = new Button("Sign Up");
        signUpButton.setLayoutX(301.0);
        signUpButton.setLayoutY(285.0);
        signUpButton.setStyle("-fx-font-size: 15.0");

        Button backButton = new Button("Back");
        backButton.setLayoutX(523.0);
        backButton.setLayoutY(390.0);

        AnchorPane layout = new AnchorPane();
        if (error){
            Label errorMessage = new Label("Incorrect username or password. Try again or sign up!");
            errorMessage.setLayoutX(226);
            errorMessage.setLayoutY(255);
            errorMessage.setStyle("-fx-text-fill: red");
            layout.getChildren().add(errorMessage);

        }
        layout.getChildren().addAll(title, usernameText, username, passwordText, password, submitButton, signUpButton,
                backButton);


        backButton.setOnAction(e -> backButtonClicked());
        submitButton.setOnAction(e -> submitButtonClicked(username.getText(), password.getText()));
        signUpButton.setOnAction(e -> signUpButtonClicked());

        Scene scene = new Scene(layout, 600.0, 429.0);
        scene.getStylesheets().add("resources/light.css");
        return scene;
    }

    private void backButtonClicked(){
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private void submitButtonClicked(String username, String password){
        if (controller.validateCredentials(username, password)){
            controller.goodLogin();
            application.setNextScene(controller, controller.run());
        }
        else{
            this.error = true;
            application.setNextScene(controller, null);
        }
    }

    private void signUpButtonClicked(){
        controller.signUpClicked();
        application.setNextScene(controller, controller.run());
    }


}
