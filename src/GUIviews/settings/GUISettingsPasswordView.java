package GUIviews.settings;

import GUIcontrollers.settings.GUISettingsPasswordController;
import GUIviews.AbstractView;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class GUISettingsPasswordView extends AbstractView {
    GUISettingsPasswordController controller;

    public GUISettingsPasswordView(GUISettingsPasswordController controller) {
        this.controller = controller;
    }

    @Override
    public Scene createScene() {
        // Title
        Label title = new Label("Change Password");
        title.setStyle("-fx-font-size: 25.0");
        title.setAlignment(Pos.CENTER);

        // Back
        Button backButton = new Button("<-");
        backButton.setPrefWidth(50);
        HBox backButtonBox = new HBox();
        backButtonBox.getChildren().addAll(backButton);
        backButtonBox.setAlignment(Pos.CENTER_LEFT);

        // Header
        StackPane header = new StackPane();
        header.getChildren().addAll(title, backButtonBox);

        // NewPassword Label
        Label newPassword = new Label("Type a new password: ");
        newPassword.setStyle("-fx-font-size: 17.0");
        newPassword.setPrefWidth(225);

        // NewPassword
        TextField newPasswordField = new TextField();
        newPasswordField.setPrefSize(225, 30);

        // NewPassword box
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(newPassword, newPasswordField);

        // Save
        Button saveButton = new Button("Save");
        saveButton.setPrefSize(75, 30);
        saveButton.setDisable(true);

        saveButton.disableProperty().bind(
                Bindings.createBooleanBinding( () ->
                        newPasswordField.getText().trim().isEmpty(), newPasswordField.textProperty()));

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(15.0, 15.0, 15.0, 15.0));
        BorderPane.setAlignment(header, Pos.CENTER);
        border.setTop(header);
        BorderPane.setAlignment(hbox, Pos.CENTER);
        border.setCenter(hbox);
        BorderPane.setAlignment(saveButton, Pos.CENTER);
        border.setBottom(saveButton);

        backButton.setOnAction(e -> backButtonClicked());
        saveButton.setOnAction(e -> saveButtonClicked(newPasswordField.getText()));

        Scene scene = new Scene(border, 600.0, 429.0);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private void saveButtonClicked(String newPassword) {
        controller.saveClicked(newPassword);
        application.setNextScene(controller, controller.run());
    }

}
