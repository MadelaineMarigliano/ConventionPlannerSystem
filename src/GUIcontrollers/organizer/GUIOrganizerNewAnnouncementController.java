package GUIcontrollers.organizer;

import GUIcontrollers.announcement.GUIAnnouncementController;
import GUIcontrollers.announcement.GUIAnnouncementHomeController;
import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import use_cases.MessagingSystem;
import use_cases.UserManager;

/**
 * The type Gui organizer new announcement controller.
 */
public class GUIOrganizerNewAnnouncementController extends AbstractController {
    private UserManager userManager;

    private int option;
    private String username;
    private String group;
    private String message;
    private MessagingSystem messagingSystem;

    /**
     * Instantiates a new Gui organizer new announcement controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIOrganizerNewAnnouncementController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.userManager = bundle.getUserManager();
        this.username = username;
        this.messagingSystem = bundle.getMessagingSystem();
        setControllerType(ControllerType.ORGANIZERNEWANNOUNCEMENT);
    }

    @Override
    public AbstractController run() {
        if (option == 1) { //send announcement
            setPopNum(1);
            return new GUIAnnouncementController(getBundle(), username, group);
        } else if (option == 2) { //back
            setPopNum(1);
            return new GUIAnnouncementHomeController(getBundle(), username);
        } else {
            return null;
        }
    }

    /**
     * Send clicked boolean.
     *
     * @param group   the group
     * @param message the message
     * @return the boolean
     */
    public boolean sendClicked(String group, String message) {
        this.message = message;
        option = 1;
        if (group.equals("all attendees")) {
            this.group = "ATTENDEES";
            try {
                messagingSystem.sendUserTypeAnnouncement(userManager.getUser(username), userManager.getAllAttendeesAndOrganizers(), message, this.group);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else if (group.equals("all speakers")) {
            this.group = "SPEAKERS";
            try {
                messagingSystem.sendUserTypeAnnouncement(userManager.getUser(username), userManager.getAllSpeakers(), message, this.group);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Back clicked.
     */
    public void backClicked() {
        option = 2;
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
     * Gets appearance mode.
     *
     * @return the appearance mode
     */
    public String getAppearanceMode() {
        return userManager.getUser(username).getAppearanceMode();
    }
}