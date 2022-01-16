package controllers;

import controllers.FileSystemController;
import use_cases.*;

import java.util.ArrayList;
import javafx.stage.Stage;

/**
 * An object that stores an instance of each use case
 * so that they can be used by the controllers via dependency injection.
 */
public class UseCaseBundle {
    private EventSystem eventSystem;
    private MessagingSystem messagingSystem;
    private RoomManager roomManager;
    private ScheduleSystem scheduleSystem;
    private UserManager userManager;
    private ContactManager contactManager;
    private UserInfoManager userInfoManager;
    private StatsManager statsManager;

    private Stage window;

    /**
     * Instantiates a new Use case bundle.
     *
     * @throws ClassNotFoundException the class not found exception
     */
    public UseCaseBundle() throws ClassNotFoundException {
        FileSystemController fileSystemController = new FileSystemController();
        ArrayList<Object> useCaseList = fileSystemController.load();
        eventSystem = (EventSystem) useCaseList.get(0);
        messagingSystem = (MessagingSystem) useCaseList.get(1);
        roomManager = (RoomManager) useCaseList.get(2);
        scheduleSystem = (ScheduleSystem) useCaseList.get(3);
        userManager = (UserManager) useCaseList.get(4);
        contactManager = (ContactManager) useCaseList.get(5);
        userInfoManager = (UserInfoManager) useCaseList.get(6);
        statsManager = (StatsManager) useCaseList.get(7);
    }

    /**
     * Gets event system.
     *
     * @return the event system
     */
    public EventSystem getEventSystem() {
        return eventSystem;
    }

    /**
     * Gets messaging system.
     *
     * @return the messaging system
     */
    public MessagingSystem getMessagingSystem() {
        return messagingSystem;
    }

    /**
     * Gets room manager.
     *
     * @return the room manager
     */
    public RoomManager getRoomManager() {
        return roomManager;
    }

    /**
     * Gets schedule system.
     *
     * @return the schedule system
     */
    public ScheduleSystem getScheduleSystem() {
        return scheduleSystem;
    }

    /**
     * Gets user manager.
     *
     * @return the user manager
     */
    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * Gets contact manager.
     *
     * @return the contact manager
     */
    public ContactManager getContactManager() {
        return contactManager;
    }

    /**
     * Gets user info manager.
     *
     * @return the user info manager
     */
    public UserInfoManager getUserInfoManager() {
        return userInfoManager;
    }

    /**
     * Gets stats manager.
     *
     * @return the user info manager
     */
    public StatsManager getStatsManager() {
        return statsManager;
    }

    /**
     * Gets window.
     *
     * @return the window
     */
    public Stage getWindow() {
        return window;
    }

    /**
     * Sets window.
     *
     * @param window the window
     */
    public void setWindow(Stage window) {
        this.window = window;
    }
}
