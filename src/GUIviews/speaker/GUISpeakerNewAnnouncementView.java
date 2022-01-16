package GUIviews.speaker;

import GUIcontrollers.speaker.GUISpeakerNewAnnouncementController;
import GUIviews.AbstractView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * The type Gui speaker new announcement view.
 */
public class GUISpeakerNewAnnouncementView extends AbstractView {
    /**
     * The Controller.
     */
    GUISpeakerNewAnnouncementController controller;

    private boolean sendError = false;

    /**
     * Instantiates a new Gui speaker new announcement view.
     *
     * @param controller the controller
     */
    public GUISpeakerNewAnnouncementView(GUISpeakerNewAnnouncementController controller) {
        this.controller = controller;
    }

    @Override
    public Scene createScene() {

        HBox top = createTop();
        HBox center = createCenter();

        BorderPane newAnnouncementHome = new BorderPane();
        newAnnouncementHome.setPadding(new Insets(15, 25, 25, 25));
        newAnnouncementHome.setTop(top);
        newAnnouncementHome.setCenter(center);

        Scene scene = new Scene(newAnnouncementHome, 600.0, 429.0);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private HBox createTop(){
        // Title
        Label title = new Label();
        title.setText("New Announcement");
        title.setStyle("-fx-font-size: 20.0");

        // Back
        Button backButton = new Button("<-");
        backButton.setPrefWidth(50);
        backButton.setPrefHeight(25);
        backButton.setOnAction(e -> backButtonClicked());

        // Header
        HBox header = new HBox();
        header.setPadding(new Insets(0, 0, 20, 0));
        header.setSpacing(140);
        header.getChildren().addAll(backButton, title);

        return header;
    }

    private HBox createCenter(){
        HBox hbox = new HBox();
        hbox.setSpacing(30);

        // first child of hbox
        TableView<EventInfo> eventInfoTable = new TableView<>();

        TableColumn eventNameCol = new TableColumn("Event Name");
        eventNameCol.setMinWidth(120);
        eventNameCol.setCellValueFactory(
                new PropertyValueFactory<EventInfo, String>("name"));

        TableColumn startTimeCol = new TableColumn("Start Time");
        startTimeCol.setMinWidth(150);
        startTimeCol.setCellValueFactory(
                new PropertyValueFactory<EventInfo, String>("startTime"));

        TableColumn roomCol = new TableColumn("Room #");
        roomCol.setMinWidth(60);
        roomCol.setCellValueFactory(
                new PropertyValueFactory<EventInfo, String>("room"));


        eventInfoTable.setItems(getEventInfoData());
        eventInfoTable.getColumns().addAll(eventNameCol, startTimeCol, roomCol);

        eventInfoTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Second child of hbox
        VBox announcementBox = new VBox();
        announcementBox.setSpacing(20);

        Label instruction = new Label("Select events from left, then send an announcement:");
        instruction.setStyle("-fx-font-size: 14.0");
        instruction.setAlignment(Pos.TOP_LEFT);
        instruction.setPrefWidth(200);
        instruction.setPrefHeight(50);
        instruction.setWrapText(true);

        TextArea announcement = new TextArea();
        announcement.setPrefWidth(200);
        announcement.setPrefHeight(180);
        announcement.setWrapText(true);

        Label feedbackLabel = new Label();
        feedbackLabel.setAlignment(Pos.TOP_LEFT);
        feedbackLabel.setPrefWidth(200);
        feedbackLabel.setPrefHeight(50);
        feedbackLabel.setWrapText(true);

        Button sendButton = new Button("send");
        sendButton.setStyle("-fx-font-size: 14.0");
        sendButton.setOnAction(e -> {
            String feedback = sendButtonClicked(eventInfoTable.getSelectionModel().getSelectedItems(), announcement.getText());
            if(sendError){
                feedbackLabel.setStyle("-fx-text-fill: red");
            }else{
                feedbackLabel.setStyle("-fx-text-fill: green");
            }
            feedbackLabel.setText(feedback);

        });

        announcementBox.getChildren().addAll(instruction, announcement, sendButton, feedbackLabel);

        // Add the children to hbox
        hbox.getChildren().addAll(eventInfoTable, announcementBox);

        return hbox;
    }


    private String sendButtonClicked(ObservableList<EventInfo> selectedEventInfo, String announcement){
            if(selectedEventInfo.isEmpty()){
                sendError = true;
                return "Please select at least one event.";
            }

            try{
                ArrayList<String> eventNames = new ArrayList<>();
                for(EventInfo info: selectedEventInfo){
                    eventNames.add(info.getName());
                }
                controller.sendClicked(eventNames, announcement);
            }catch(Exception e){
                sendError = true;
                return e.getMessage();
            }

            sendError = false;
            return "Announcement has been sent ^-^";
    }

    private void backButtonClicked() {
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }


    private ObservableList<EventInfo> getEventInfoData(){
        ObservableList<GUISpeakerNewAnnouncementView.EventInfo> eventInfos = FXCollections.observableArrayList();
        ArrayList<ArrayList<String>> allEventsInStrings = controller.getAllEventsInString();

        for (ArrayList<String> info : allEventsInStrings){
            eventInfos.add(new EventInfo(info.get(0), info.get(1), info.get(2)));
        }

        return eventInfos;
    }

    /**
     * The type Event info.
     */
    public static class EventInfo{
        private final SimpleStringProperty name;
        private final SimpleStringProperty startTime;
        private final SimpleStringProperty room;

        private EventInfo(String name, String startTime, String room) {
            this.name = new SimpleStringProperty(name);
            this.startTime = new SimpleStringProperty(startTime);
            this.room = new SimpleStringProperty(room);
        }

        /**
         * Gets name.
         *
         * @return the name
         */
        public String getName() {
            return name.get();
        }

        /**
         * Sets name.
         *
         * @param name the name
         */
        public void setName(String name) {
            this.name.set(name);
        }

        /**
         * Gets start time.
         *
         * @return the start time
         */
        public String getStartTime() {
            return startTime.get();
        }

        /**
         * Sets start time.
         *
         * @param startTime the start time
         */
        public void setStartTime(String startTime) {
            this.startTime.set(startTime);
        }

        /**
         * Gets room.
         *
         * @return the room
         */
        public String getRoom() {
            return room.get();
        }

        /**
         * Sets room.
         *
         * @param room the room
         */
        public void setRoom(String room) {
            this.room.set(room);
        }
    }

}
