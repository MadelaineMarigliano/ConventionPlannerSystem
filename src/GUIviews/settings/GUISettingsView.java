package GUIviews.settings;

import GUIcontrollers.settings.GUISettingsController;
import GUIviews.AbstractView;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * The type Gui settings view.
 */
public class GUISettingsView extends AbstractView {
    /**
     * The Controller.
     */
    GUISettingsController controller;

    /**
     * Instantiates a new Gui settings view.
     *
     * @param controller the controller
     */
    public GUISettingsView(GUISettingsController controller) {
        this.controller = controller;
    }

    @Override
    public Scene createScene() {
        // Title
        Label title = new Label("Settings");
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
        header.setPadding(new Insets(0.0, 0.0, 5.0, 0.0));
        header.getChildren().addAll(title, backButtonBox);

        // Light/Dark Mode
        Label appearance = new Label("appearance");
        appearance.setStyle("-fx-font-size: 17.0");
        appearance.setPrefSize(281, 57.7);

        ChoiceBox<String> appearanceChoices = new ChoiceBox<>();
        appearanceChoices.setPrefSize(281, 57.7);
        appearanceChoices.getItems().addAll("light", "dark");
        appearanceChoices.setStyle("-fx-font-size: 17.0");

        HBox appearanceBox = new HBox();
        appearanceBox.getChildren().addAll(appearance, appearanceChoices);
        appearanceBox.setAlignment(Pos.CENTER);

        // Change Profile Icon
        Label profileIcon = new Label("profile icon color");
        profileIcon.setStyle("-fx-font-size: 17.0");
        profileIcon.setPrefSize(281, 57.7);

        ChoiceBox<String> colorChoices = new ChoiceBox<>();
        colorChoices.setPrefSize(281, 57.7);
        colorChoices.getItems().addAll("orange", "yellow", "green", "blue", "purple");
        colorChoices.setStyle("-fx-font-size: 17.0");

        HBox iconBox = new HBox();
        iconBox.getChildren().addAll(profileIcon, colorChoices);
        iconBox.setAlignment(Pos.CENTER);

        // Change Password
        Button changePasswordButton = new Button("change password");
        changePasswordButton.setPrefSize(562, 57.7);
        changePasswordButton.setAlignment(Pos.CENTER);
        changePasswordButton.setStyle("-fx-font-size: 17.0");

        // Delete Account
        Button deleteAccountButton = new Button("delete account");
        deleteAccountButton.setPrefSize(562, 57.7);
        deleteAccountButton.setAlignment(Pos.CENTER);
        deleteAccountButton.setStyle("-fx-font-size: 17.0");

        // Save
        Button saveButton = new Button("save");
        saveButton.setPadding(new Insets(5.0, 0.0, 0.0, 0.0));
        saveButton.setPrefSize(562, 57.7);
        saveButton.setAlignment(Pos.CENTER);
        saveButton.setStyle("-fx-font-size: 17.0");
        saveButton.setDisable(true);

        saveButton.disableProperty().bind(
                Bindings.createBooleanBinding( () ->
                        appearanceChoices.getSelectionModel().isEmpty(), appearanceChoices.valueProperty()
                )
                .and(  Bindings.createBooleanBinding( () ->
                        colorChoices.getSelectionModel().isEmpty(), colorChoices.valueProperty())
                )
        );

        // Layout
        VBox layout = new VBox();
        layout.getChildren().addAll(header, appearanceBox, iconBox, changePasswordButton, deleteAccountButton, saveButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(31.0, 31.0, 31.0, 31.0));
        layout.setSpacing(7.5);

        backButton.setOnAction(e -> backButtonClicked());
        changePasswordButton.setOnAction(e -> changePasswordButtonClicked());
        deleteAccountButton.setOnAction(e -> deleteAccountButtonClicked());
        saveButton.setOnAction(e -> {
            String appearanceChoice;
            String colorChoice;
            if (!appearanceChoices.getSelectionModel().isEmpty()) {
                appearanceChoice = appearanceChoices.getValue();
            } else {
                appearanceChoice = "";
            }
            if (!colorChoices.getSelectionModel().isEmpty()) {
                colorChoice = colorChoices.getValue();
            } else {
                colorChoice = "";
            }
            saveButtonClicked(appearanceChoice, colorChoice);
        });

        Scene scene = new Scene(layout, 600.0, 429.0);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private void changePasswordButtonClicked() {
        controller.changePasswordClicked();
        application.setNextScene(controller, controller.run());
    }

    private void deleteAccountButtonClicked() {
        controller.deleteAccountClicked();
        application.setNextScene(controller, controller.run());
    }

    private void saveButtonClicked(String appearance, String color) {
        if (!appearance.isEmpty()) {
            controller.changeAppearance(appearance);
        }
        if (!color.isEmpty()) {
            controller.changeColor(color);
        }
        application.setNextScene(controller, null);
    }
}
