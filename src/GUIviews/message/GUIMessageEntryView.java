package GUIviews.message;

import GUIcontrollers.message.GUIMessageEntryController;
import GUIviews.AbstractView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * The type Gui message entry view.
 */
public class GUIMessageEntryView extends AbstractView {
    /**
     * The Controller.
     */
    GUIMessageEntryController controller;

    /**
     * Instantiates a new Gui message entry view.
     *
     * @param controller the controller
     */
    public GUIMessageEntryView(GUIMessageEntryController controller) {
        this.controller = controller;
    }

    @Override
    public Scene createScene() {
        // Title
        Label title = new Label("Messages");
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
        header.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));

        // Message one person
        Button messageIndividualButton = new Button("Message an Individual");
        messageIndividualButton.setStyle("-fx-font-size: 17.0");
        messageIndividualButton.setPrefWidth(250);

        // Mass message
        Button massMessageButton = new Button("Mass Message");
        massMessageButton.setStyle("-fx-font-size: 17.0");
        massMessageButton.setPrefWidth(250);

        // For buttons
        VBox vbox = new VBox();
        vbox.getChildren().addAll(messageIndividualButton, massMessageButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20.0);

        BorderPane border = new BorderPane();
        BorderPane.setAlignment(header, Pos.CENTER);
        border.setTop(header);
        BorderPane.setAlignment(vbox, Pos.CENTER);
        border.setCenter(vbox);

        messageIndividualButton.setOnAction(e -> messageIndividualButtonClicked());
        massMessageButton.setOnAction(e -> massMessageButtonClicked());
        backButton.setOnAction(e -> backButtonClicked());

        Scene scene = new Scene(border, 600.0, 429.0);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void messageIndividualButtonClicked() {
        controller.messageIndividualClicked();
        application.setNextScene(controller, controller.run());
    }

    private void massMessageButtonClicked() {
        controller.massMessageClicked();
        application.setNextScene(controller, controller.run());
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }
}
