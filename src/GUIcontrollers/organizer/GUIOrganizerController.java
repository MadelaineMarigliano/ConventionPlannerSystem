package GUIcontrollers.organizer;

import GUIcontrollers.factory.GUIEventControllerFactory;
import GUIcontrollers.room.GUIRoomController;
import GUIcontrollers.schedule.GUIScheduleHomeController;
import GUIcontrollers.statistics.GUIStatsController;
import GUIcontrollers.admin.GUIAdminHomeController;
import GUIcontrollers.contact.GUIContactHomeController;
import GUIcontrollers.message.GUIMessageEntryController;
import GUIcontrollers.settings.GUISettingsController;
import controllers.*;
import use_cases.UserManager;

/**
 * The type Gui organizer controller.
 */
public class GUIOrganizerController extends UserController {
    private int option;

    private String username;
    private UserManager userManager;
    private String color;

    /**
     * Instantiates a new Gui organizer controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIOrganizerController(UseCaseBundle bundle, String username){
        super(bundle, username.trim());
        setControllerType(ControllerType.ORGANIZER);
        this.userManager = bundle.getUserManager();
        this.username = username;
        this.color = userManager.getUser(username).getColor();
    }

    @Override
    public AbstractController run() {

        switch (option) {
            case 1: // Profile/Settings
                setPopNum(1);
                return new GUISettingsController(getBundle(), getUsername());
            case 2: // My Events
                GUIEventControllerFactory factory = new GUIEventControllerFactory();
                return factory.generate(getBundle(), getUsername());
            case 3: // My Contacts
                return new GUIContactHomeController(getBundle(), getUsername());
            case 4: // My Messages
                return new GUIMessageEntryController(getBundle(), getUsername());
            case 5: // Manage Users
                return new GUIAdminHomeController(getBundle(), getUsername());
            case 6: // Manage Rooms
                return new GUIRoomController(getBundle(), getUsername());
            case 7: // Manage Events
                return new GUIScheduleHomeController(getBundle(), getUsername());
            case 8:
                return new GUIStatsController(getBundle(), getUsername());
            case 9: // Log Out
                setPopNum(1);
                return null;
        }
        return null;
    }

    /**
     * Profile clicked.
     */
    public void profileClicked(){ option = 1; }

    /**
     * Events clicked.
     */
    public void eventsClicked(){ option = 2; }

    /**
     * Contacts clicked.
     */
    public void contactsClicked(){ option = 3; }

    /**
     * Messages clicked.
     */
    public void messagesClicked(){ option = 4; }

    /**
     * Manage users clicked.
     */
    public void manageUsersClicked(){ option = 5; }

    /**
     * Manage rooms clicked.
     */
    public void manageRoomsClicked(){ option = 6; }

    /**
     * Manage events clicked.
     */
    public void manageEventsClicked(){ option = 7; }

    /**
     * Stats button clicked.
     */
    public void statsButtonClicked(){ option = 8; }

    /**
     * Logout clicked.
     */
    public void logoutClicked(){ option = 9; }

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets appearance mode.
     *
     * @return the appearance mode
     */
    public String getAppearanceMode() {
        return userManager.getUser(username).getAppearanceMode();
    }
}
