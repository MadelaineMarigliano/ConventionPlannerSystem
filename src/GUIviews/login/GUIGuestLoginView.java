package GUIviews.login;

import GUIcontrollers.login.GUIGuestLoginController;
import GUIviews.AbstractView;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

/**
 * The type Gui guest login view.
 */
public class GUIGuestLoginView extends AbstractView {
    /**
     * The Controller.
     */
    GUIGuestLoginController controller;
    /**
     * The Error.
     */
    boolean error = false;
    boolean valid = false;

    /**
     * Instantiates a new Gui guest login view.
     *
     * @param controller the controller
     */
    public GUIGuestLoginView(GUIGuestLoginController controller) {
        this.controller = controller;
    }

    @Override
    public Scene createScene() {
        //Title
        Label title = new Label();
        title.setText("Guest Login");
        title.setLayoutX(246.0);
        title.setLayoutY(23.0);
        title.setStyle("-fx-font-size: 44.0");

        Label usernameText = new Label();
        usernameText.setText("VIP Username");
        usernameText.setLayoutX(226.0);
        usernameText.setLayoutY(142.0);

        TextField username = new TextField();
        username.setLayoutX(226.0);
        username.setLayoutY(159.0);

        Label guestCode = new Label("Guest Code");
        guestCode.setLayoutX(226.0);
        guestCode.setLayoutY(209.0);

        TextField password = new TextField();
        password.setLayoutX(226.0);
        password.setLayoutY(226.0);

        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(226.0);
        submitButton.setLayoutY(285.0);
        submitButton.setStyle("-fx-font-size: 15.0");



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

        TextArea x = new TextArea();
        x.setPrefSize(1, 1);
        x.setLayoutY(10);
        x.setLayoutY(10);

        if (valid){
            x.setPrefSize(590, 350);
            x.setText(controller.viewEventsClicked());
        }

        layout.getChildren().addAll(title, usernameText, username, guestCode, password, submitButton, backButton, x);


        backButton.setOnAction(e -> backButtonClicked());
        submitButton.setOnAction(e -> submitButtonClicked(username.getText(), password.getText()));

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
            System.out.println(username);
            controller.goodLogin();
            valid = true;
            application.setNextScene(controller, controller.run());
        }
        else{
            this.error = true;
            application.setNextScene(controller, null);
        }
    }






}
