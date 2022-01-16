package GUIcontrollers.message;

import GUIcontrollers.announcement.GUIAnnouncementHomeController;
import controllers.*;
import use_cases.MessagingSystem;
import use_cases.UserManager;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Gui message home controller.
 */
public class GUIMessageHomeController extends AbstractController {
    private UserManager userManager;

    private String username;
    private int option;
    private String sendTo;
    private MessagingSystem messagingSystem;

    /**
     * Instantiates a new Gui message home controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIMessageHomeController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.userManager = bundle.getUserManager();
        this.username = username;
        this.messagingSystem = bundle.getMessagingSystem();
        setControllerType(ControllerType.MESSAGEHOME);
    }

    @Override
    public AbstractController run() {
        if (option == 1) { //see message
            return new GUIMessageController(getBundle(), username, sendTo);
        } else if (option == 2) { //new message
            setPopNum(1);
            return new GUINewMessageController(getBundle(), username);
        } else if (option == 3) { //back
            setPopNum(1);
            return null;
        } else if (option == 4) { //unread messages
            setPopNum(1);
            return new GUIMessageUnreadHomeController(getBundle(), username);
        } else if (option == 5) { //announcements
            setPopNum(1);
            return new GUIAnnouncementHomeController(getBundle(), username);
        }
        return null;
    }

    /**
     * See message clicked.
     *
     * @param sendTo the send to
     */
    public void seeMessageClicked(String sendTo) {
        this.sendTo = sendTo;
        option = 1;
    }

    /**
     * New message clicked.
     */
    public void newMessageClicked() {
        option = 2;
    }

    /**
     * Back clicked.
     */
    public void backClicked() {
        option = 3;
    }

    /**
     * Unread clicked.
     */
    public void unreadClicked() {
        option = 4;
    }

    /**
     * Announcement clicked.
     */
    public void announcementClicked() {
        option = 5;
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
     * Has messages boolean.
     *
     * @return the boolean
     */
    public boolean hasMessages() {
        return !getAllMessages().isEmpty();
    }

    /**
     * Gets all messages.
     *
     * @return the all messages
     */
    public HashMap<String, ArrayList<Integer>> getAllMessages() {
        return userManager.getUser(username).getMessageMap();
    }

    /**
     * Gets message preview.
     *
     * @param messageId the message id
     * @return the message preview
     */
    public String getMessagePreview(Integer messageId) {
        String message = messagingSystem.getMessage(messageId).getMessage();
        if (message.length() > 25) {
            return message.substring(0, 24) + "...";
        } else {
            return message;
        }
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
