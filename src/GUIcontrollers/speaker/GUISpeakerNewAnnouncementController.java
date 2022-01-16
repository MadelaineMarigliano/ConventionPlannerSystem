package GUIcontrollers.speaker;

import GUIcontrollers.announcement.GUIAnnouncementHomeController;
import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import use_cases.EventSystem;
import use_cases.MessagingSystem;
import use_cases.UserManager;

import java.util.ArrayList;

/**
 * The type Gui speaker new announcement controller.
 */
public class GUISpeakerNewAnnouncementController extends AbstractController {

    private int option;
    private String username;
    private UseCaseBundle useCaseBundle;
    private UserManager userManager;

    /**
     * Instantiates a new Gui speaker new announcement controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUISpeakerNewAnnouncementController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.username = username;
        this.useCaseBundle = bundle;
        this.userManager = bundle.getUserManager();
        setControllerType(ControllerType.SPEAKERNEWANNOUNCEMENT);
    }

    @Override
    public AbstractController run() {
        if (option == 1) { // Back
            setPopNum(1);
            return new GUIAnnouncementHomeController(getBundle(), username);
        }

        return null;
    }

    /**
     * Send clicked.
     *
     * @param eventNames   the event names
     * @param announcement the announcement
     */
    public void sendClicked(ArrayList<String> eventNames, String announcement){
        MessagingSystem ms = useCaseBundle.getMessagingSystem();
        EventSystem es = useCaseBundle.getEventSystem();
        UserManager um = useCaseBundle.getUserManager();

        for(String eventName: eventNames){
            ArrayList<String> attendeeNames = new ArrayList<>(es.findEventByName(eventName).getAttendeesList());

            ms.sendEventAnnouncement(um.getUser(username), um.getUser(username), announcement, eventName);
            for(String attendeeName: attendeeNames){
                ms.sendEventAnnouncement(um.getUser(username), um.getUser(attendeeName), announcement, eventName);
            }
        }
    }

    /**
     * Back clicked.
     */
    public void backClicked() {
        option = 1;
    }

    /**
     * Get username string.
     *
     * @return the string
     */
    public String getUsername(){
        return username;
    }

    /**
     * Get all events in string array list.
     *
     * @return the array list
     */
    public ArrayList<ArrayList<String>> getAllEventsInString(){
        return useCaseBundle.getEventSystem().getAllEventsInString();
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