package GUIviews.schedule;

import GUIcontrollers.schedule.GUIScheduleCreateEventController;
import GUIviews.AbstractView;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

/**
 * The type Gui schedule create event view.
 */
public class GUIScheduleCreateEventView extends AbstractView {
    /**
     * The Error.
     */
    boolean error = false;
    /**
     * The Error message.
     */
    String errorMessage = "";
    /**
     * The Controller.
     */
    GUIScheduleCreateEventController controller;
    private TextField eventName;
    private TextField speakerUsernames;
    private TextField roomID;
    private TextField roomCapacity;
    private Spinner<Integer> startHour;
    private Spinner<Integer> startMinute;
    private Spinner<Integer> endHour;
    private Spinner<Integer> endMinute;

    /**
     * Instantiates a new Gui schedule create event view.
     *
     * @param controller the controller
     */
    public GUIScheduleCreateEventView(GUIScheduleCreateEventController controller){ this.controller = controller; }
    @Override
    public Scene createScene() {
        //Title
        Label title = new Label("Create Event");
        title.setLayoutX(220);
        title.setLayoutY(22);
        title.setStyle("-fx-font-size: 29.0");

        Label eventNameLabel = new Label("Event Name");
        eventNameLabel.setLayoutX(76);
        eventNameLabel.setLayoutY(83);
        eventNameLabel.setStyle("-fx-font-size: 14.0");

        Label speakerUsernamesLabel = new Label("Speaker Usernames");
        speakerUsernamesLabel.setLayoutX(76.0);
        speakerUsernamesLabel.setLayoutY(145);
        speakerUsernamesLabel.setStyle("-fx-font-size: 14.0");

        Label roomIDLabel = new Label("Room ID");
        roomIDLabel.setLayoutX(76);
        roomIDLabel.setLayoutY(208);
        roomIDLabel.setStyle("-fx-font-size: 14.0");

        Label roomCapacityLabel = new Label("Room Capacity");
        roomCapacityLabel.setLayoutX(76.0);
        roomCapacityLabel.setLayoutY(273);
        roomCapacityLabel.setStyle("-fx-font-size: 14.0");

        Label startHourLabel = new Label("Start Hour");
        startHourLabel.setLayoutX(340);
        startHourLabel.setLayoutY(83);
        startHourLabel.setStyle("-fx-font-size: 14.0");

        Label startMinuteLabel = new Label("Start Minute");
        startMinuteLabel.setLayoutX(340);
        startMinuteLabel.setLayoutY(145);
        startMinuteLabel.setStyle("-fx-font-size: 14.0");

        Label endHourLabel = new Label("End Hour");
        endHourLabel.setLayoutX(340);
        endHourLabel.setLayoutY(210);
        endHourLabel.setStyle("-fx-font-size: 14.0");

        Label endMinuteLabel = new Label("End Minute");
        endMinuteLabel.setLayoutX(340);
        endMinuteLabel.setLayoutY(272);
        endMinuteLabel.setStyle("-fx-font-size: 14.0");

        eventName = new TextField();
        eventName.setLayoutX(76);
        eventName.setLayoutY(103);

        speakerUsernames = new TextField();
        speakerUsernames.setLayoutX(76);
        speakerUsernames.setLayoutY(165);
        speakerUsernames.setPromptText("Comma-separated/None");

        roomID = new TextField();
        roomID.setLayoutX(76);
        roomID.setLayoutY(228);

        roomCapacity = new TextField();
        roomCapacity.setLayoutX(76);
        roomCapacity.setLayoutY(293);

        SpinnerValueFactory<Integer> startHourValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);

        SpinnerValueFactory<Integer> endHourValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);

        SpinnerValueFactory<Integer> startMinuteValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);

        SpinnerValueFactory<Integer> endMinuteValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);

        startHour = new Spinner<>();
        startHour.setLayoutX(340);
        startHour.setLayoutY(103);
        startHour.setValueFactory(startHourValueFactory);

        startMinute = new Spinner<>();
        startMinute.setLayoutX(340);
        startMinute.setLayoutY(165);
        startMinute.setValueFactory(startMinuteValueFactory);

        endHour = new Spinner<>();
        endHour.setLayoutX(340);
        endHour.setLayoutY(230);
        endHour.setValueFactory(endHourValueFactory);

        endMinute = new Spinner<>();
        endMinute.setLayoutX(340);
        endMinute.setLayoutY(292);
        endMinute.setValueFactory(endMinuteValueFactory);

        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(76);
        submitButton.setLayoutY(346);
        submitButton.setStyle("-fx-font-size: 15");
        submitButton.setDisable(true);

        submitButton.disableProperty().bind(
                Bindings.createBooleanBinding( () ->
                        eventName.getText().trim().isEmpty(), eventName.textProperty()
                )
                        .or(  Bindings.createBooleanBinding( () ->
                                        !roomID.getText().trim().matches("^\\d+$"), roomID.textProperty()
                                )
                        )
                        .or(  Bindings.createBooleanBinding( () ->
                                        !roomCapacity.getText().trim().matches("^\\d+$"), roomCapacity.textProperty()
                                )
                        )


        );

        Button backButton = new Button("Back");
        backButton.setLayoutX(528);
        backButton.setLayoutY(377);



        AnchorPane layout = new AnchorPane();

        if (error){
            Label errorMessageLabel = new Label(errorMessage);
            errorMessageLabel.setLayoutX(300);
            errorMessageLabel.setLayoutY(350);
            errorMessageLabel.setStyle("-fx-text-fill: red");
            layout.getChildren().add(errorMessageLabel);

        }
        layout.getChildren().addAll(title, eventNameLabel, speakerUsernamesLabel, roomIDLabel, roomCapacityLabel,
                startHourLabel, startMinute, endHourLabel, endMinuteLabel, eventName, speakerUsernames, roomID,
                roomCapacity, submitButton, backButton, startHour, endHour, endMinute, startMinuteLabel);

        submitButton.setOnAction(e -> submitButtonClicked());
        backButton.setOnAction(e -> backButtonClicked());

        Scene scene = new Scene(layout, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private void backButtonClicked() {
        controller.backButtonClicked();
        application.setNextScene(controller, controller.run());
    }

    private void submitButtonClicked() {
        int[] timeArray = {startHour.getValue(), startMinute.getValue(), endHour.getValue(), endMinute.getValue()};
        System.out.println(startHour.getValue());
        try {
            controller.createEvent(eventName.getText(), speakerUsernames.getText(), Integer.parseInt(roomID.getText()),
                    Integer.parseInt(roomCapacity.getText()), timeArray);
            System.out.println("event being created");
            controller.backButtonClicked();
            application.setNextScene(controller, controller.run());
        }
        catch(Exception e){
            //throw e;/*
            System.out.println(eventName.getText());
            System.out.println();
            System.out.println("error");
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());

            this.error = true;
            this.errorMessage = e.getLocalizedMessage();
            application.setNextScene(controller, null);
        }
    }
}

