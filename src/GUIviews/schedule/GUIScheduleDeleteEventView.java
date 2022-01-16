package GUIviews.schedule;

import GUIcontrollers.schedule.GUIScheduleDeleteEventController;
import GUIviews.AbstractView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

/**
 * The type Gui schedule delete event view.
 */
public class GUIScheduleDeleteEventView extends AbstractView {
    /**
     * The Controller.
     */
    GUIScheduleDeleteEventController controller;
    /**
     * The Table.
     */
    TableView<EventInfo> table;

    /**
     * Instantiates a new Gui schedule delete event view.
     *
     * @param controller the controller
     */
    public GUIScheduleDeleteEventView(GUIScheduleDeleteEventController controller){
        this.controller = controller;
    }
    @Override
    public Scene createScene() {
        //Event Name Column
        TableColumn<EventInfo, String> usernameColumn = new TableColumn<>("Event");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        usernameColumn.setPrefWidth(130);

        //First name column
        TableColumn<EventInfo, String> firstNameColumn = new TableColumn<>("Start Time");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        firstNameColumn.setPrefWidth(119);

        //Last name column
        TableColumn<EventInfo, String> lastNameColumn = new TableColumn<>("Room");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        lastNameColumn.setPrefWidth(110);



        //Delete button
        Button deleteButton = new Button("Delete");
        deleteButton.setLayoutX(510);
        deleteButton.setLayoutY(47);
        deleteButton.setStyle("-fx-font-size: 16");

        //Back button
        Button backButton = new Button("Back");
        backButton.setLayoutX(537);
        backButton.setLayoutY(390);


        table = new TableView<>();
        table.getColumns().addAll(usernameColumn, firstNameColumn, lastNameColumn);
        table.setItems(getEventInfo());
        table.setPrefHeight(429);
        table.setPrefWidth(485);

        AnchorPane layout = new AnchorPane();
        layout.getChildren().addAll(table, deleteButton, backButton);

        deleteButton.setOnAction(e -> deleteButtonClicked());
        backButton.setOnAction(e -> backButtonClicked());

        Scene scene = new Scene(layout, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private ObservableList<EventInfo> getEventInfo() {
        ObservableList<EventInfo> events = FXCollections.observableArrayList();
        ArrayList<ArrayList<String>> eventFields = controller.getAllEventInfo();
        for (ArrayList<String> info: eventFields){
            if (!info.isEmpty()){

                System.out.println(info.get(0));
                System.out.println(info.get(1));
                System.out.println(info.get(2));
                events.add(new EventInfo(info.get(0), info.get(1), info.get(2)));
            }
        }
        return events;
    }

    private void backButtonClicked() { application.setNextScene(controller, controller.run()); }

    private void deleteButtonClicked() {
        ObservableList<EventInfo> eventSelected, allEvents;
        allEvents = table.getItems();
        eventSelected = table.getSelectionModel().getSelectedItems();

        for (EventInfo info: eventSelected){
            System.out.println(info.getEventName());
            controller.deleteEvent(info.getEventName());
        }
        eventSelected.forEach(allEvents::remove);
    }

    /**
     * The type Event info.
     */
    public class EventInfo{
        private String eventName;
        private String startTime;
        private String roomID;

        /**
         * Instantiates a new Event info.
         *
         * @param eventName the event name
         * @param startTime the start time
         * @param roomID    the room id
         */
        public EventInfo(String eventName, String startTime, String roomID){
            this.eventName = eventName;
            this.startTime = startTime;
            this.roomID = roomID;
        }

        /**
         * Gets event name.
         *
         * @return the event name
         */
        public String getEventName() {
            return eventName;
        }

        /**
         * Sets event name.
         *
         * @param eventName the event name
         */
        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        /**
         * Gets start time.
         *
         * @return the start time
         */
        public String getStartTime() {
            return startTime;
        }

        /**
         * Sets start time.
         *
         * @param startTime the start time
         */
        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        /**
         * Gets room id.
         *
         * @return the room id
         */
        public String getRoomID() {
            return roomID;
        }

        /**
         * Sets room id.
         *
         * @param roomID the room id
         */
        public void setRoomID(String roomID) {
            this.roomID = roomID;
        }
    }
}
