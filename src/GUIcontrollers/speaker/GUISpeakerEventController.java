package GUIcontrollers.speaker;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import use_cases.EventSystem;
import use_cases.UserInfoManager;
import use_cases.UserManager;

import java.util.ArrayList;

/**
 * The type Gui speaker event controller.
 */
public class GUISpeakerEventController extends AbstractController {
    private String username;
    private UserManager userManager;
    private int option;

    /**
     * Instantiates a new Gui speaker event controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUISpeakerEventController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.username = username;
        this.userManager = bundle.getUserManager();
        setControllerType(ControllerType.EVENTSPEAKER);
    }

    @Override
    public AbstractController run() {
        if (option == 1) { // Go Back
            setPopNum(1);
            return null;
        }
        return null;
    }

    /**
     * Back clicked.
     */
    public void backClicked(){
        option = 1;
    }

    /**
     * View events clicked string.
     *
     * @return the string
     */
    public String viewEventsClicked() {
        ArrayList<Integer> eventList;

        UserManager um = getBundle().getUserManager();
        EventSystem es = getBundle().getEventSystem();
        UserInfoManager uim = getBundle().getUserInfoManager();
        eventList = uim.viewEvents(um.getUser(username));
        String word =" ";
        for (Integer i : eventList) {
            word += "Event name: " + es.findEventByID(i).getName().toString()  + ", Starts at: " + es.findEventByID(i).getStartTime() + ", Ends at: " +es.findEventByID(i).getEndTime().toString()
                    + ", In room: " + es.findEventByID(i).getRoomID().toString() + "\n";
        }
        return word;

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
