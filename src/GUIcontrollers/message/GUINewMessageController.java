package GUIcontrollers.message;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import entities.User;
import use_cases.MessagingSystem;
import use_cases.UserManager;

/**
 * The type Gui new message controller.
 */
public class GUINewMessageController extends AbstractController {
    private UserManager userManager;

    private int option;
    private String sendTo;
    private String message;
    private MessagingSystem messagingSystem;
    private String username;

    /**
     * Instantiates a new Gui new message controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUINewMessageController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.userManager = bundle.getUserManager();
        this.username = username;
        this.messagingSystem = bundle.getMessagingSystem();
        setControllerType(ControllerType.MESSAGENEW);
    }

    @Override
    public AbstractController run() {
        if (option == 1) { //send message
            setPopNum(1);
            sendMessage(username, sendTo, message);
            return new GUIMessageController(getBundle(), username, sendTo);
        } else if (option == 2) { //back
            setPopNum(1);
            return new GUIMessageHomeController(getBundle(), username);
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
     * Valid send to boolean.
     *
     * @param sendTo the send to
     * @return the boolean
     */
    public boolean validSendTo(String sendTo) {
        return userManager.existsUsername(sendTo);
    }

    private void sendMessage(String sendFrom, String sendTo, String message) {
        User from = userManager.getUser(sendFrom);
        User to = userManager.getUser(sendTo);
        messagingSystem.sendMessage(from, to, message);
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
