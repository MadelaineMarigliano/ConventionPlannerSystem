package GUIcontrollers.attendee;

import GUIcontrollers.contact.GUIContactHomeController;
import GUIcontrollers.settings.GUISettingsController;
import GUIcontrollers.message.GUIMessageHomeController;
import controllers.*;
import use_cases.UserManager;

/**
 * The type Gui attendee controller.
 */
public class GUIAttendeeController extends UserController {
    private int option; // 1=event, 2=Contacts, 3=Messaging, 4=Logout

    private String username;
    private UserManager userManager;
    private String color;

    /**
     * Instantiates a new Gui attendee controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIAttendeeController(UseCaseBundle bundle, String username){
        super(bundle, username.trim());
        setControllerType(ControllerType.ATTENDEE);
        this.username = username;
        this.userManager = bundle.getUserManager();
        this.color = userManager.getUser(username).getColor();
    }

    @Override
    public AbstractController run() {

        switch (option){
            case 1: // Profile/Settings
                setPopNum(1);
                return new GUISettingsController(getBundle(), getUsername());
            case 2: // My Events
                return new GUIAttendeeEventController(getBundle(), getUsername());
            case 3: // My Contacts
                return new GUIContactHomeController(getBundle(), getUsername());
            case 4: // My Messages
                return new GUIMessageHomeController(getBundle(), getUsername());
            case 5: // Log Out
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
     * Event clicked.
     */
    public void eventClicked(){ option = 2; }

    /**
     * Contact clicked.
     */
    public void contactClicked(){ option = 3; }

    /**
     * Message clicked.
     */
    public void messageClicked(){ option = 4; }

    /**
     * Logout clicked.
     */
    public void logoutClicked(){ option = 5; }

    /**
     * Is vip boolean.
     *
     * @return the boolean
     */
    public boolean isVIP(){
        return userManager.isVIP(username);
    }

    /**
     * Get guest code string.
     *
     * @return the string
     */
    public String getGuestCode(){
        return userManager.getGuestCode(username);
    }

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
