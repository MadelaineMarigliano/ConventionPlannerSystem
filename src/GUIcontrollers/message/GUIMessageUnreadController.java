package GUIcontrollers.message;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import entities.User;
import use_cases.MessagingSystem;
import use_cases.UserManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Gui message unread controller.
 */
public class GUIMessageUnreadController extends AbstractController {
    private UserManager userManager;
    private int option;
    private String username;
    private String sendTo;
    private String message;
    private MessagingSystem messagingSystem;

    /**
     * Instantiates a new Gui message unread controller.
     *
     * @param bundle   the bundle
     * @param username the username
     * @param sendTo   the send to
     */
    public GUIMessageUnreadController(UseCaseBundle bundle, String username, String sendTo) {
        super(bundle);
        this.userManager = bundle.getUserManager();
        this.username = username;
        this.sendTo = sendTo;
        this.messagingSystem = bundle.getMessagingSystem();
        setControllerType(ControllerType.MESSAGEUNREAD);
    }

    @Override
    public AbstractController run() {
        if (option == 1) { //send message
            setPopNum(1);
            sendMessage(message);
            return new GUIMessageController(getBundle(), username, sendTo);
        } else if (option == 2) { //back
            setPopNum(1);
            return null;
        }
        return null;
    }

    /**
     * Send clicked.
     *
     * @param sendTo  the send to
     * @param message the message
     */
    public void sendClicked(String sendTo, String message) {
        this.sendTo = sendTo;
        this.message = message;
        option = 1;
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
     * Gets send to.
     *
     * @return the send to
     */
    public String getSendTo() {
        return sendTo;
    }

    private void sendMessage(String message) {
        User from = userManager.getUser(this.username);
        User to = userManager.getUser(this.sendTo);
        messagingSystem.sendMessage(from, to, message);
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
     * Gets unread message count.
     *
     * @return the unread message count
     */
    public Integer getUnreadMessageCount() {
        return userManager.getUser(username).getUnreadMessageCountFrom(userManager.getUser(this.sendTo));
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
     * Saw unread messages with.
     */
    public void sawUnreadMessagesWith() {
        userManager.getUser(username).deleteUnreadMessageCountFrom(userManager.getUser(sendTo));
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
