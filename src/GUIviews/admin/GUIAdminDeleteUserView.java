package GUIviews.admin;

import GUIcontrollers.admin.GUIAdminDeleteUserController;
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
 * The type Gui admin delete user view.
 */
public class GUIAdminDeleteUserView extends AbstractView {
    /**
     * The Controller.
     */
    GUIAdminDeleteUserController controller;
    /**
     * The Table.
     */
    TableView<UserInfo> table;

    /**
     * Instantiates a new Gui admin delete user view.
     *
     * @param controller the controller
     */
    public GUIAdminDeleteUserView(GUIAdminDeleteUserController controller){ this.controller = controller; }
    @Override
    public Scene createScene() {
        //Username column
        TableColumn<UserInfo, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameColumn.setPrefWidth(130);

        //First name column
        TableColumn<UserInfo, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameColumn.setPrefWidth(119);

        //Last name column
        TableColumn<UserInfo, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameColumn.setPrefWidth(110);

        //User type column
        TableColumn<UserInfo, String> userTypeColumn = new TableColumn<>("User Type");
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        userTypeColumn.setPrefWidth(125);

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
        table.getColumns().addAll(usernameColumn, firstNameColumn, lastNameColumn, userTypeColumn);
        table.setItems(getUserInfo());
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

    private void deleteButtonClicked(){
        ObservableList<UserInfo> usersSelected, allUsers;
        allUsers = table.getItems();
        usersSelected = table.getSelectionModel().getSelectedItems();


        for (UserInfo user: usersSelected){

            System.out.println(user.getUsername());
            controller.deleteUser(user.getUsername());
        }
        usersSelected.forEach(allUsers::remove);
    }

    private void backButtonClicked(){
        application.setNextScene(controller, controller.run());
    }


    /**
     * Get user info observable list.
     *
     * @return the observable list
     */
    public ObservableList<UserInfo> getUserInfo(){
        ObservableList<UserInfo> users = FXCollections.observableArrayList();
        ArrayList<ArrayList<String>> userFields = controller.getAllUserInfo();
        for (ArrayList<String> info : userFields){
            if (!info.isEmpty() && !info.get(0).equals("admin_account")) {
                users.add(new UserInfo(info.get(0), info.get(1), info.get(2), info.get(3)));
            }
        }
        return users;
    }


    /**
     * The type User info.
     */
    public class UserInfo{
        private String username;
        private String firstName;
        private String lastName;
        private String userType;

        /**
         * Instantiates a new User info.
         *
         * @param username  the username
         * @param firstName the first name
         * @param lastName  the last name
         * @param userType  the user type
         */
        public UserInfo(String username, String firstName, String lastName, String userType){
            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.userType = userType;
        }

        /**
         * Gets username.
         *
         * @return the username
         */
        public String getUsername() {
            return username;
        }

        /**
         * Sets username.
         *
         * @param username the username
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * Gets first name.
         *
         * @return the first name
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * Sets first name.
         *
         * @param firstName the first name
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         * Gets last name.
         *
         * @return the last name
         */
        public String getLastName() {
            return lastName;
        }

        /**
         * Sets last name.
         *
         * @param lastName the last name
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /**
         * Gets user type.
         *
         * @return the user type
         */
        public String getUserType() {
            return userType;
        }

        /**
         * Sets user type.
         *
         * @param userType the user type
         */
        public void setUserType(String userType) {
            this.userType = userType;
        }

    }
}
