package GUIviews.statistics;

import GUIcontrollers.statistics.GUIStatsController;
import GUIviews.AbstractView;
import entities.DblStrTuple;
import entities.IntStrTuple;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * The type Gui stats view.
 */
public class GUIStatsView extends AbstractView {
    private GUIStatsController controller;
    private Integer numUsers, numLogins, totalMessagesSent, totalAttendance;
    private Double avgEventsAttendees, avgAttendeesEvents;

    /**
     * Instantiates a new Gui stats view.
     *
     * @param controller the controller
     */
    public GUIStatsView(GUIStatsController controller){
        this.controller = controller;
        numUsers = controller.getTotalUsers();
        numLogins = controller.getNumLogins();
        totalMessagesSent = controller.totalMessagesSent();
        totalAttendance = controller.getTotalAttendance();
        avgEventsAttendees = controller.attendeesPerUser();//Todo: double check if this is a typo
        avgAttendeesEvents = controller.attendeesPerEvent();
    }
    @Override
    public Scene createScene() {
        Label title = new Label("Statistics");
        title.setLayoutX(391);
        title.setLayoutY(14);
        title.setStyle("-fx-font-size: 26");

        //Most Attended Events
        TableColumn<IntStrTuple, String> mostAttendedEventsColumn1 = new TableColumn("Event");
        mostAttendedEventsColumn1.setCellValueFactory(new PropertyValueFactory<>("string"));
        mostAttendedEventsColumn1.setPrefWidth(75);

        TableColumn<IntStrTuple, Integer> mostAttendedEventsColumn2 = new TableColumn("Number");
        mostAttendedEventsColumn2.setCellValueFactory(new PropertyValueFactory<>("integer"));
        mostAttendedEventsColumn2.setPrefWidth(75);
        
        TableView<IntStrTuple> mostAttendedEvents = new TableView<>();
        mostAttendedEvents.getColumns().addAll(mostAttendedEventsColumn1, mostAttendedEventsColumn2);
        mostAttendedEvents.setItems(FXCollections.observableArrayList(controller.eventsAttendanceOrder()));
        mostAttendedEvents.setLayoutX(69);
        mostAttendedEvents.setLayoutY(143);
        mostAttendedEvents.setPrefHeight(200);
        mostAttendedEvents.setPrefWidth(151);

        //Percentage of Event Attendees
        TableColumn<DblStrTuple, String> percentageOfEventColumn1 = new TableColumn("Event");
        percentageOfEventColumn1.setCellValueFactory(new PropertyValueFactory<>("string"));
        percentageOfEventColumn1.setPrefWidth(75);

        TableColumn<DblStrTuple, Double> percentageOfEventColumn2 = new TableColumn("Percentage");
        percentageOfEventColumn2.setCellValueFactory(new PropertyValueFactory<>("doub"));
        percentageOfEventColumn2.setPrefWidth(75);

        TableView<DblStrTuple> percentageOfEvent = new TableView<>();
        percentageOfEvent.getColumns().addAll(percentageOfEventColumn1, percentageOfEventColumn2);
        percentageOfEvent.setItems(FXCollections.observableArrayList(controller.eventsPercentage()));
        percentageOfEvent.setLayoutX(281);
        percentageOfEvent.setLayoutY(143);
        percentageOfEvent.setPrefHeight(200);
        percentageOfEvent.setPrefWidth(151);

        //Number of Events Attending
        TableColumn<IntStrTuple, String> numEventsAttendingColumn1 = new TableColumn("Username");
        numEventsAttendingColumn1.setCellValueFactory(new PropertyValueFactory<>("string"));
        numEventsAttendingColumn1.setPrefWidth(75);

        TableColumn<IntStrTuple, Integer> numEventsAttendingColumn2 = new TableColumn("Number");
        numEventsAttendingColumn2.setCellValueFactory(new PropertyValueFactory<>("integer"));
        numEventsAttendingColumn2.setPrefWidth(75);

        TableView<IntStrTuple> numEventsAttending = new TableView<>();
        numEventsAttending.getColumns().addAll(numEventsAttendingColumn1, numEventsAttendingColumn2);
        numEventsAttending.setItems(FXCollections.observableArrayList(controller.mostAttendingUsers()));
        numEventsAttending.setLayoutX(493);
        numEventsAttending.setLayoutY(143);
        numEventsAttending.setPrefHeight(200);
        numEventsAttending.setPrefWidth(151);

        //Most Active Users
        TableColumn<IntStrTuple, String> mostActiveUsersColumn1 = new TableColumn("Username");
        mostActiveUsersColumn1.setCellValueFactory(new PropertyValueFactory<>("string"));
        mostActiveUsersColumn1.setPrefWidth(75);

        TableColumn<IntStrTuple, Integer> mostActiveUsersColumn2 = new TableColumn("Number");
        mostActiveUsersColumn2.setCellValueFactory(new PropertyValueFactory<>("integer"));
        mostActiveUsersColumn2.setPrefWidth(75);

        TableView<IntStrTuple> mostActiveUsers = new TableView<>();
        mostActiveUsers.getColumns().addAll(mostActiveUsersColumn1, mostActiveUsersColumn2);
        mostActiveUsers.setItems(FXCollections.observableArrayList(controller.userLogins()));
        mostActiveUsers.setLayoutX(695);
        mostActiveUsers.setLayoutY(143);
        mostActiveUsers.setPrefHeight(200);
        mostActiveUsers.setPrefWidth(151);

        Label numUsersLabel = new Label("Number of Users:");
        numUsersLabel.setLayoutX(90);
        numUsersLabel.setLayoutY(434);
        numUsersLabel.setStyle("-fx-font-size: 18");

        Label numLoginsLabel = new Label("Number of Logins:");
        numLoginsLabel.setLayoutX(90);
        numLoginsLabel.setLayoutY(470);
        numLoginsLabel.setStyle("-fx-font-size: 18");

        Label totalMessagesLabel = new Label("Total Messages Sent:");
        totalMessagesLabel.setLayoutX(90);
        totalMessagesLabel.setLayoutY(507);
        totalMessagesLabel.setStyle("-fx-font-size: 18");

        Label totalAttendanceLabel = new Label("Total Attendance:");
        totalAttendanceLabel.setLayoutX(484);
        totalAttendanceLabel.setLayoutY(434);
        totalAttendanceLabel.setStyle("-fx-font-size: 18");

        Label avgAttendeesEventLabel = new Label("Average Attendees per Event:");
        avgAttendeesEventLabel.setLayoutX(484);
        avgAttendeesEventLabel.setLayoutY(507);
        avgAttendeesEventLabel.setStyle("-fx-font-size: 18");

        Label avgEventAttendeesLabel = new Label("Average Events per Attendees:");
        avgEventAttendeesLabel.setLayoutX(484);
        avgEventAttendeesLabel.setLayoutY(470);
        avgEventAttendeesLabel.setStyle("-fx-font-size: 18");

        Line bottomDivider = new Line();
        bottomDivider.setLayoutX(107);
        bottomDivider.setLayoutY(406);
        bottomDivider.setEndX(776);
        bottomDivider.setStartX(-107);

        Label mostAttendedEventLabel = new Label("Most Attended Events");
        mostAttendedEventLabel.setLayoutX(69);
        mostAttendedEventLabel.setLayoutY(96);
        mostAttendedEventLabel.setStyle("-fx-font-size: 13");

        Label percentageEventLabel = new Label("Percentage of Event Attendants");
        percentageEventLabel.setLayoutX(281);
        percentageEventLabel.setLayoutY(96);
        percentageEventLabel.setStyle("-fx-font-size: 13");

        Label numEventsAttendingLabel = new Label("Number of Events Attending");
        numEventsAttendingLabel.setLayoutX(493);
        numEventsAttendingLabel.setLayoutY(96);
        numEventsAttendingLabel.setStyle("-fx-font-size: 13");

        Label mostActiveUsersLabel = new Label("Most Active Users");
        mostActiveUsersLabel.setLayoutX(695);
        mostActiveUsersLabel.setLayoutY(96);
        mostActiveUsersLabel.setStyle("-fx-font-size: 13");

        Label numUsersStat = new Label(numUsers.toString());
        numUsersStat.setLayoutX(296);
        numUsersStat.setLayoutY(434);
        numUsersStat.setStyle("-fx-font-size: 18");

        Label numLoginsStats = new Label(numLogins.toString());
        numLoginsStats.setLayoutX(296);
        numLoginsStats.setLayoutY(470);
        numLoginsStats.setStyle("-fx-font-size: 18");

        Label totalMessagesSentStats = new Label(totalMessagesSent.toString());
        totalMessagesSentStats.setLayoutX(296);
        totalMessagesSentStats.setLayoutY(507);
        totalMessagesSentStats.setStyle("-fx-font-size: 18");

        Label totalAttendanceStats = new Label(totalAttendance.toString());
        totalAttendanceStats.setLayoutX(768);
        totalAttendanceStats.setLayoutY(434);
        totalAttendanceStats.setStyle("-fx-font-size: 18");

        Label avgEventsAttendeesStats = new Label(avgEventsAttendees.toString());
        avgEventsAttendeesStats.setLayoutX(768);
        avgEventsAttendeesStats.setLayoutY(470);
        avgEventsAttendeesStats.setStyle("-fx-font-size: 18");

        Label avgAttendeesEventsStats = new Label(avgAttendeesEvents.toString());
        avgAttendeesEventsStats.setLayoutX(768);
        avgAttendeesEventsStats.setLayoutY(507);
        avgAttendeesEventsStats.setStyle("-fx-font-size: 18");

        Button backButton = new Button("Back");
        backButton.setLayoutX(818);
        backButton.setLayoutY(21);
        backButton.setOnAction(e -> backButtonClicked());

        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(title, mostAttendedEvents, percentageOfEvent, numEventsAttending, mostActiveUsers,
                numUsersLabel, numLoginsLabel, totalMessagesLabel, totalAttendanceLabel, avgAttendeesEventLabel,
                avgEventAttendeesLabel, bottomDivider, mostAttendedEventLabel, percentageEventLabel,
                numEventsAttendingLabel, mostActiveUsersLabel, numUsersStat, numLoginsStats, totalMessagesSentStats,
                totalAttendanceStats, avgEventsAttendeesStats, avgAttendeesEventsStats, backButton);

        Scene scene = new Scene(layout, 883, 561);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }



    private void backButtonClicked() {
        application.setNextScene(controller, controller.run());
    }
}





















