package GUIcontrollers.message;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import entities.User;
import use_cases.MessagingSystem;
import use_cases.UserInfoManager;
import use_cases.UserManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Gui message controller.
 */
public class GUIMessageController extends AbstractController {
    private UserManager userManager;

    private int option;
    private String username;
    private MessagingSystem messagingSystem;
    private UserInfoManager userInfoManager;
    private String sendTo;

    /**
     * Instantiates a new Gui message controller.
     *
     * @param bundle   the bundle
     * @param username the username
     * @param sendTo   the send to
     */
    public GUIMessageController(UseCaseBundle bundle, String username, String sendTo) {
        super(bundle);
        this.userManager = bundle.getUserManager();
        this.userInfoManager = bundle.getUserInfoManager();
        this.messagingSystem = bundle.getMessagingSystem();
        this.username = username;
        this.sendTo = sendTo;
        setControllerType(ControllerType.MESSAGE);
    }

    @Override
    public AbstractController run() {
        if (option == 1) { // back
            setPopNum(1);
        }
        return null;
    }

    /**
     * Send clicked.
     *
     * @param message the message
     */
    public void sendClicked(String message) {
        sendMessage(message);
    }

    /**
     * Back clicked.
     */
    public void backClicked() {
        option = 1;
    }

    /**
     * Gets messages with.
     *
     * @return the messages with
     */
    public ArrayList<Integer> getMessagesWith() {
        HashMap<String, ArrayList<Integer>> allMessages = userManager.getUser(username).getMessageMap();
        return allMessages.get(this.sendTo);
    }

    /**
     * Gets message.
     *
     * @param messageId the message id
     * @return the message
     */
    public String getMessage(Integer messageId) {
        return messagingSystem.getMessage(messageId).getMessage();
    }

    /**
     * Gets sender.
     *
     * @param messageId the message id
     * @return the sender
     */
    public String getSender(Integer messageId) {
        return messagingSystem.getMessage(messageId).getSentFrom();
    }

    private void sendMessage(String message) {
        User from = userManager.getUser(username);
        User to = userManager.getUser(sendTo);
        messagingSystem.sendMessage(from, to, message);
    }

    /**
     * Gets send to.
     *
     * @return the send to
     */
    public String getSendTo() {
        return sendTo;
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
     * Saw unread messages with.
     *
     * @param from the from
     */
    public void sawUnreadMessagesWith(String from) {
        userManager.getUser(username).deleteUnreadMessageCountFrom(userManager.getUser(from));
    }

    /**
     * Gets unread messages count.
     *
     * @param from the from
     * @return the unread messages count
     */
    public Integer getUnreadMessagesCount(String from) {
        return userManager.getUser(username).getUnreadMessageCountFrom(userManager.getUser(from));
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
