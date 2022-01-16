package GUIcontrollers.schedule;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import use_cases.UserManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Gui schedule create event controller.
 */
public class GUIScheduleCreateEventController extends AbstractController {
    private int option;
    private String username;
    private UserManager userManager;

    /**
     * Instantiates a new Gui schedule create event controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIScheduleCreateEventController(UseCaseBundle bundle, String username){
        super(bundle);
        setControllerType(ControllerType.SCHEDULECREATEEVENT);
        this.username = username;
        this.userManager = bundle.getUserManager();
    }
    @Override
    public AbstractController run() {
        if (option == 1){
            setPopNum(1);
            return null;
        }
        return null;
    }

    /**
     * Create event.
     *
     * @param eventName the event name
     * @param speakers  the speakers
     * @param roomID    the room id
     * @param capacity  the capacity
     * @param timeArray the time array
     */
    public void createEvent(String eventName, String speakers, int roomID, int capacity, int[] timeArray) {
        eventName = eventName.trim();

        ArrayList<String> speakerUsernames = new ArrayList<>(Arrays.asList(speakers.trim().split(",")));


        LocalDateTime startTime = getValidTime(timeArray[0], timeArray[1]);
        LocalDateTime endTime = getValidTime(timeArray[2], timeArray[3]);

        getBundle().getEventSystem().createEvent(eventName, roomID, startTime, endTime,
               getBundle().getScheduleSystem(), speakerUsernames, capacity, getBundle().getUserManager());

    }

    /**
     * Back button clicked.
     */
    public void backButtonClicked(){ option = 1; }

    /**
     * Gets appearance mode.
     *
     * @return the appearance mode
     */
    public String getAppearanceMode() {
        return userManager.getUser(username).getAppearanceMode();
    }

    private LocalDateTime getValidTime(int hourStart, int minStart){
        return getBundle().getEventSystem().ValidTime(hourStart, minStart);
    }

}
