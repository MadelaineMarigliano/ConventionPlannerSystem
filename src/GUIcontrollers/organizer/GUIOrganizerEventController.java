package GUIcontrollers.organizer;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import use_cases.EventSystem;
import use_cases.UserInfoManager;
import use_cases.UserManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * The type Gui organizer event controller.
 */
public class GUIOrganizerEventController extends AbstractController {

    private String event;
    private String userName;
    private int option;
    private UserManager userManager;

    /**
     * Instantiates a new Gui organizer event controller.
     *
     * @param bundle the bundle
     * @param user   the user
     */
    public GUIOrganizerEventController(UseCaseBundle bundle, String user) {
        super(bundle);
        this.userName = user;
        setControllerType(ControllerType.EVENTORGANIZER);
        this.userManager = bundle.getUserManager();
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
            case 3: //view events you are signed up for
                viewEventsClicked();
                setPopNum(0);
                return null;
            case 4: // return
                setPopNum(1);
                return null;
            case 5:
                downloadScheduleBtnClicked();
                setPopNum(0);
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
            System.out.println("Unenrolled");
        }
        catch(Exception e){
            System.out.println("cant unenroll");
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
            checkOccupancy(eventName, "att");
            es.enroll(getBundle().getUserManager().getUser(userName), es.findEventByName(eventName));
            System.out.println("hi");
        }
        catch(Exception e){
            System.out.println("cant enroll");
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
        option = 4;
    }

    /**
     * Gets appearance mode.
     *
     * @return the appearance mode
     */
    public String getAppearanceMode() {
        return userManager.getUser(userName).getAppearanceMode();
    }

    /**
     * Download schedule btn clicked.
     */
    public void downloadScheduleBtnClicked() {
        File file = getBundle().getScheduleSystem().CreateHTML();
        printSchedule(file);
    }

    private void printSchedule(File file) {
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
