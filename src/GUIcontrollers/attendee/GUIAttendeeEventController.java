package GUIcontrollers.attendee;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import entities.UserTypes;
import use_cases.EventSystem;
import use_cases.UserInfoManager;
import use_cases.UserManager;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * The type Gui attendee event controller.
 */
public class GUIAttendeeEventController extends AbstractController {

    private String event;
    private String userName;
    private int option;
    private UserManager userManager;

    /**
     * Instantiates a new Gui attendee event controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIAttendeeEventController(UseCaseBundle bundle, String username) {
        super(bundle);
        setControllerType(ControllerType.EVENTATTENDEE);
        this.userManager = bundle.getUserManager();
        this.userName = username;
    }

    @Override
    public AbstractController run() {
        switch (option) {
            case 1: // enrol
                enrol(event);
                setPopNum(0);
                return null;
            case 2: //unenrol
                unenrol(event);
                setPopNum(0);
                return null;
            case 3: // Go Back
                setPopNum(1);
                return null;
        }
        return null;
    }


    /**
     * Enrol clicked.
     *
     * @param eventName the event name
     */
    public void enrolClicked(String eventName) {
        option = 1;
        event = eventName;
    }

    /**
     * Unenrol clicked.
     *
     * @param eventName the event name
     */
    public void unenrolClicked(String eventName) {
        option = 2;
        event = eventName;
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
        eventList = uim.viewEvents(um.getUser(userName));
        String word =" ";
        for (Integer i : eventList) {
            word += "Event name: " + es.findEventByID(i).getName().toString()  + ", Starts at: " + es.findEventByID(i).getStartTime() + ", Ends at: " +es.findEventByID(i).getEndTime().toString()
                    + ", In room: " + es.findEventByID(i).getRoomID().toString() + "\n";
        }
        return word;

    }

    /**
     * Unenrol.
     *
     * @param event the event
     */
    public void unenrol(String event) {
        String eventName = event;
        EventSystem es = getBundle().getEventSystem();

        try {
            getBundle().getEventSystem().unenroll(getBundle().getUserManager().getUser(userName), es.findEventByName(eventName));

        }
        catch(Exception e){

        }
    }

    /**
     * Enrol.
     *
     * @param event the event
     */
    public void enrol(String event) {
        String eventName = event;
        EventSystem es = getBundle().getEventSystem();

        try {
            String type;
            if (getBundle().getUserManager().getUser(userName).getUserType().equals(UserTypes.VIP)) {
                type = "vip";
            } else {
                type = "att";
            }
            checkOccupancy(eventName, type);
            es.enroll(getBundle().getUserManager().getUser(userName), es.findEventByName(eventName));

        }
        catch(Exception e){

        }
    }

    private void checkOccupancy(String event, String type) throws InvalidParameterException {
        EventSystem es = getBundle().getEventSystem();
        int occupancy = 0;
        for (String u : es.findEventByName(event).getAttendeesList()) {
            occupancy = occupancy + getBundle().getUserManager().calculateOccupancy(u);
        }

        // if type is vip and occupancy > capacity - 2 throw
        if (type.equals("vip") && occupancy > es.findEventByName(event).getCapacity() - 2) {
            throw new InvalidParameterException("This event is full");
        }
        if (occupancy == es.findEventByName(event).getCapacity()) {
            throw new InvalidParameterException("This event is full");
        }
    }

    /**
     * Back clicked.
     */
    public void backClicked(){
        option = 3;
    }

    /**
     * Gets appearance mode.
     *
     * @return the appearance mode
     */
    public String getAppearanceMode() {
        return userManager.getUser(userName).getAppearanceMode();
    }
}
