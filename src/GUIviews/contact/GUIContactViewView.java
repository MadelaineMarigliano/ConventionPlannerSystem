package GUIviews.contact;

import GUIcontrollers.contact.GUIContactViewController;
import GUIviews.AbstractView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

/**
 * The type Gui contact view view.
 */
public class GUIContactViewView extends AbstractView {
    /**
     * The Controller.
     */
    GUIContactViewController controller;

    /**
     * Instantiates a new Gui contact view view.
     *
     * @param controller the controller
     */
    public GUIContactViewView(GUIContactViewController controller){
        this.controller = controller;
    }
    @Override
    public Scene createScene() {
        BorderPane borderPane = new BorderPane();

        TableView<ContactInfo> listView = createCenter();
        HBox hbox = createBottom();

        borderPane.setCenter(listView);
        borderPane.setBottom(hbox);

        Scene scene = new Scene(borderPane, 600, 429);
        scene.getStylesheets().add("resources/" + controller.getAppearanceMode() + ".css");
        return scene;
    }

    private TableView<ContactInfo> createCenter(){
        TableView<GUIContactViewView.ContactInfo> contactInfoTable = new TableView<>();

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(200);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<GUIContactViewView.ContactInfo, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(200);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<GUIContactViewView.ContactInfo, String>("lastName"));

        TableColumn usernameCol = new TableColumn("Username");
        usernameCol.setMinWidth(198);
        usernameCol.setCellValueFactory(
                new PropertyValueFactory<GUIContactViewView.ContactInfo, String>("username"));


        contactInfoTable.setItems(getContactInfoData());
        contactInfoTable.getColumns().addAll(firstNameCol, lastNameCol, usernameCol);


        return contactInfoTable;
    }

    private HBox createBottom(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(5);
        hbox.setStyle("-fx-background-color: #cfd4d3;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 14");

        backButton.setOnAction(e -> backButtonClicked());

        hbox.getChildren().addAll(backButton);

        return hbox;
    }


    private void backButtonClicked(){
        controller.backClicked();
        application.setNextScene(controller, controller.run());
    }

    private ObservableList<GUIContactViewView.ContactInfo> getContactInfoData(){
        ObservableList<GUIContactViewView.ContactInfo> contactInfos = FXCollections.observableArrayList();

        ArrayList<ArrayList<String>> allContactsInStrings = controller.getAllContactsInString();
        for (ArrayList<String> info : allContactsInStrings){
            contactInfos.add(new GUIContactViewView.ContactInfo(info.get(0), info.get(1), info.get(2)));
        }

        return contactInfos;
    }

    /**
     * The type Contact info.
     */
    public static class ContactInfo{
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty username;

        private ContactInfo(String firstName, String lastName, String username) {
            this.firstName = new SimpleStringProperty(firstName);
            this.lastName = new SimpleStringProperty(lastName);
            this.username = new SimpleStringProperty(username);
        }


        /**
         * Gets first name.
         *
         * @return the first name
         */
        public String getFirstName() {
            return firstName.get();
        }

        /**
         * Sets first name.
         *
         * @param firstName the first name
         */
        public void setFirstName(String firstName) {
            this.firstName.set(firstName);
        }

        /**
         * Gets last name.
         *
         * @return the last name
         */
        public String getLastName() {
            return lastName.get();
        }

        /**
         * Sets last name.
         *
         * @param lastName the last name
         */
        public void setLastName(String lastName) {
            this.lastName.set(lastName);
        }

        /**
         * Gets username.
         *
         * @return the username
         */
        public String getUsername() {
            return username.get();
        }

        /**
         * Sets username.
         *
         * @param username the username
         */
        public void setUsername(String username) {
            this.username.set(username);
        }
    }

}
