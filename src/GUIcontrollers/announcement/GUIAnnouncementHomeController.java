package GUIcontrollers.announcement;

import GUIcontrollers.organizer.GUIOrganizerNewAnnouncementController;
import GUIcontrollers.speaker.GUISpeakerNewAnnouncementController;
import GUIcontrollers.message.GUIMessageHomeController;
import GUIcontrollers.message.GUIMessageUnreadHomeController;
import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import entities.UserTypes;
import use_cases.MessagingSystem;
import use_cases.UserManager;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Gui announcement home controller.
 */
public class GUIAnnouncementHomeController extends AbstractController {
    private UserManager userManager;

    private String username;
    private int option;
    private String seeAnnouncementsFrom;
    private MessagingSystem messagingSystem;
    private UserTypes userType;

    /**
     * Instantiates a new Gui announcement home controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIAnnouncementHomeController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.userManager = bundle.getUserManager();
        this.username = username;
        this.userType = userManager.getUser(username).getUserType();
        this.messagingSystem = bundle.getMessagingSystem();
        setControllerType(ControllerType.ANNOUNCEMENTHOME);
    }

    @Override
    public AbstractController run() {
        if (option == 1) { //see event announcement
            return new GUIAnnouncementController(getBundle(), username, seeAnnouncementsFrom);
        } else if (option == 2) { //see user type announcement
            return new GUIAnnouncementController(getBundle(), username, seeAnnouncementsFrom);
        } else if (option == 3) { //messages home
            setPopNum(1);
            return new GUIMessageHomeController(getBundle(), username);
        } else if (option == 4) { //unread home
            setPopNum(1);
            return new GUIMessageUnreadHomeController(getBundle(), username);
        } else if (option == 5) { //new announcement
            if (userType == UserTypes.ORGANIZER) {
                setPopNum(1);
                return new GUIOrganizerNewAnnouncementController(getBundle(), username);
            } else if (userType == UserTypes.SPEAKER) {
                setPopNum(1);
                return new GUISpeakerNewAnnouncementController(getBundle(), username);
            } else {
                return null;
            }
        } else if (option == 6) { //back
            setPopNum(1);
            return null;
        }
        return null;
    }

    /**
     * See event announcement clicked.
     *
     * @param eventName the event name
     */
    public void seeEventAnnouncementClicked(String eventName) {
        this.option = 1;
        this.seeAnnouncementsFrom = eventName;
    }

    /**
     * See user announcement clicked.
     *
     * @param sender the sender
     */
    public void seeUserAnnouncementClicked(String sender) {
        this.option = 2;
        this.seeAnnouncementsFrom = sender;
    }

    /**
     * Messages clicked.
     */
    public void messagesClicked() {
        option = 3;
    }

    /**
     * Unread clicked.
     */
    public void unreadClicked() {
        option = 4;
    }

    /**
     * New announcement clicked.
     */
    public void newAnnouncementClicked() {
        this.option = 5;
    }

    /**
     * Back clicked.
     */
    public void backClicked() {
        this.option = 6;
    }

    /**
     * Has announcements boolean.
     *
     * @return the boolean
     */
    public boolean hasAnnouncements() {
        return hasEventAnnouncements() || hasUserAnnouncements();
    }

    private boolean hasEventAnnouncements() {
        return userManager.getUser(username).hasEventAnnouncements();
    }

    private boolean hasUserAnnouncements() {
        return userManager.getUser(username).hasUserAnnouncements();
    }

    /**
     * Gets all event announcements.
     *
     * @return the all event announcements
     */
    public HashMap<String, HashMap<String, ArrayList<Integer>>> getAllEventAnnouncements() {
        return userManager.getUser(username).getEventAnnouncements();
    }

    /**
     * Gets event announcements.
     *
     * @param eventName the event name
     * @return the event announcements
     */
    public ArrayList<Integer> getEventAnnouncements(String eventName) {
        return userManager.getUser(username).getEventAnnouncementsByEvent(eventName);
    }

    /**
     * Gets all user announcements.
     *
     * @return the all user announcements
     */
    public HashMap<String, HashMap<String, ArrayList<Integer>>> getAllUserAnnouncements() {
        return userManager.getUser(username).getUserTypeAnnouncements();
    }

    /**
     * Gets user announcements.
     *
     * @param userType the user type
     * @return the user announcements
     */
    public ArrayList<Integer> getUserAnnouncements(String userType) {
        return userManager.getUser(username).getUserAnnouncementsByType(userType);
    }

    /**
     * Gets announcement preview.
     *
     * @param messageId the message id
     * @return the announcement preview
     */
    public String getAnnouncementPreview(Integer messageId) {
        String preview = messagingSystem.getMessage(messageId).getMessage();
        if (preview.length() > 25) {
            return preview.substring(0, 24);
        } else {
            return preview;
        }
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
     * Gets user type.
     *
     * @return the user type
     */
    public UserTypes getUserType() {
        return userType;
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
