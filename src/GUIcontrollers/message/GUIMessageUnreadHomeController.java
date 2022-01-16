package GUIcontrollers.message;

import GUIcontrollers.announcement.GUIAnnouncementHomeController;
import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import use_cases.MessagingSystem;
import use_cases.UserManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Gui message unread home controller.
 */
public class GUIMessageUnreadHomeController extends AbstractController {
    private UserManager userManager;
    private String username;
    private int option;
    private String sendTo;
    private MessagingSystem messagingSystem;

    /**
     * Instantiates a new Gui message unread home controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIMessageUnreadHomeController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.userManager = bundle.getUserManager();
        this.username = username;
        this.messagingSystem = bundle.getMessagingSystem();
        setControllerType(ControllerType.MESSAGEUNREADHOME);
    }

    @Override
    public AbstractController run() {
        if (option == 1) { //see message
            return new GUIMessageUnreadController(getBundle(), username, sendTo);
        } else if (option == 2) { //messages
            setPopNum(1);
            return new GUIMessageHomeController(getBundle(), username);
        } else if (option == 3) { //announcements
            setPopNum(1);
            return new GUIAnnouncementHomeController(getBundle(), username);
        } else if (option == 4) { //new message
            setPopNum(1);
            return new GUINewMessageController(getBundle(), username);
        } else if (option == 5) { //back
            setPopNum(1);
            return null;
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
     * Messages clicked.
     */
    public void messagesClicked() {
        option = 2;
    }

    /**
     * Announcement clicked.
     */
    public void announcementClicked() {
        option = 3;
    }

    /**
     * New message clicked.
     */
    public void newMessageClicked() {
        option = 4;
    }

    /**
     * Back clicked.
     */
    public void backClicked() {
        option = 5;
    }

    /**
     * Has unread messages boolean.
     *
     * @return the boolean
     */
    public boolean hasUnreadMessages() {
        return userManager.getUser(username).hasUnreadMessages();
    }

    /**
     * Gets all unread messages.
     *
     * @return the all unread messages
     */
    public HashMap<String, ArrayList<Integer>> getAllUnreadMessages() {
        HashMap<String, Integer> countByUsername = userManager.getUser(username).getUnreadMesssageByUsername();
        HashMap<String, ArrayList<Integer>> allMessages = userManager.getUser(username).getMessageMap();

        HashMap<String, ArrayList<Integer>> unreadMessagesByUsername = new HashMap<>();
        for (Map.Entry person : countByUsername.entrySet()) {
            String name = (String) person.getKey();
            Integer unread = countByUsername.get(name);

            ArrayList<Integer> messagesWithName = (ArrayList<Integer>) allMessages.get(name).clone();
            // reverse to get newest messages first
            Collections.reverse(messagesWithName);

            int unreadCount = 0;
            int sizeCount = 0;
            while (unreadCount < unread && sizeCount < messagesWithName.size()) {
                Integer messageId = messagesWithName.get(sizeCount);
                if (messagingSystem.getMessage(messageId).getSentFrom().equals(name)) {
                    unreadMessagesByUsername.putIfAbsent(name, new ArrayList<>());
                    unreadMessagesByUsername.get(name).add(messageId);
                    unreadCount++;
                }
                sizeCount++;
            }
        }

        // reverse to have messages be oldest to newest
        for (Map.Entry person2 : unreadMessagesByUsername.entrySet()) {
            String name2 = (String) person2.getKey();
            Collections.reverse(unreadMessagesByUsername.get(name2));
        }
        return unreadMessagesByUsername;
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
