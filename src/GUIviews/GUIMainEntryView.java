package GUIviews;

import GUIcontrollers.GUIMainEntryController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * The type Gui main entry view.
 */
public class GUIMainEntryView extends AbstractView{

    /**
     * The Controller.
     */
    GUIMainEntryController controller;

    /**
     * Instantiates a new Gui main entry view.
     *
     * @param controller the controller
     */
    public GUIMainEntryView(GUIMainEntryController controller){

        this.controller = controller;
    }
    @Override
    public Scene createScene(){

        Label welcome = new Label("Convention Planner System");
        welcome.setLayoutX(125);
        welcome.setLayoutY(68);
        welcome.setStyle("-fx-font-size: 29.0");

        Button loginButton = new Button("Log In");
        loginButton.setLayoutX(214);
        loginButton.setLayoutY(195);
        loginButton.setStyle("-fx-font-size: 18.0");

        Button signUpButton = new Button("Sign Up");
        signUpButton.setLayoutX(311);
        signUpButton.setLayoutY(195);
        signUpButton.setStyle("-fx-font-size: 18.0");

        Button guestLoginButton = new Button("Guest Login");
        guestLoginButton.setLayoutX(247);
        guestLoginButton.setLayoutY(260);
        guestLoginButton.setStyle("-fx-font-size: 18.0");

        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(welcome, loginButton, signUpButton, guestLoginButton);

        loginButton.setOnAction(e -> loginButtonClicked());
        signUpButton.setOnAction(e -> signUpButtonClicked());
        guestLoginButton.setOnAction(e -> guestLoginButtonClicked());

        Scene scene = new Scene(layout, 600, 429);
        scene.getStylesheets().add("resources/light.css");
        return scene;
    }

    private void guestLoginButtonClicked() {
        controller.guestLoginClicked();
        application.setNextScene(controller, controller.run());
    }

    private void loginButtonClicked(){
        controller.loginClicked();
        application.setNextScene(controller, controller.run());

    }

    private void signUpButtonClicked(){
        controller.signUpClicked();
        application.setNextScene(controller, controller.run());
    }

}
