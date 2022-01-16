package GUIcontrollers.message;

import GUIcontrollers.organizer.GUIOrganizerNewAnnouncementController;
import GUIcontrollers.speaker.GUISpeakerNewAnnouncementController;
import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import entities.UserTypes;
import use_cases.UserManager;

/**
 * The type Gui message entry controller.
 */
public class GUIMessageEntryController extends AbstractController {
    private UserManager userManager;
    private int option;
    private String username;
    private UserTypes type;

    /**
     * Instantiates a new Gui message entry controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIMessageEntryController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.username = username;
        setControllerType(ControllerType.MASSMESSAGE);
        this.userManager = bundle.getUserManager();
        this.type = userManager.getUser(this.username).getUserType();
    }

    @Override
    public AbstractController run() {
        if (option == 1) { // message an individual
            return new GUIMessageHomeController(getBundle(), username);
        } else if (option == 2) { // mass message
            if (type == UserTypes.ORGANIZER) {
                return new GUIOrganizerNewAnnouncementController(getBundle(), username);
            } else if (type == UserTypes.SPEAKER) {
                return new GUISpeakerNewAnnouncementController(getBundle(), username);
            } else {
                return null;
            }
        } else if (option == 3) { // back
            setPopNum(1);
            return null;
        }
        return null;
    }

    /**
     * Message individual clicked.
     */
    public void messageIndividualClicked() {
        option = 1;
    }

    /**
     * Mass message clicked.
     */
    public void massMessageClicked() {
        option = 2;
    }

    /**
     * Back clicked.
     */
    public void backClicked() {
        option = 3;
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
