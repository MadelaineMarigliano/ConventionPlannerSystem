package GUIcontrollers.schedule;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import use_cases.EventSystem;
import use_cases.UserManager;

import java.util.ArrayList;

/**
 * The type Gui schedule delete event controller.
 */
public class GUIScheduleDeleteEventController extends AbstractController {
    private EventSystem es;
    private UserManager userManager;
    private String username;

    /**
     * Instantiates a new Gui schedule delete event controller.
     *
     * @param bundle the bundle
     */
    public GUIScheduleDeleteEventController(UseCaseBundle bundle, String username) {
        super(bundle);
        es = getBundle().getEventSystem();
        setControllerType(ControllerType.SCHEDULEDELETEEVENT);
        this.userManager = bundle.getUserManager();
        this.username = username;
    }

    @Override
    public AbstractController run() {
        System.out.println(es.getAllEventsInString());
        setPopNum(1);
        return null;
    }

    /**
     * Get all event info array list.
     *
     * @return the array list
     */
    public ArrayList<ArrayList<String>> getAllEventInfo(){ return es.getAllEventsInString(); }

    /**
     * Delete event.
     *
     * @param eventName the event name
     */
    public void deleteEvent(String eventName){
        getBundle().getEventSystem().deleteEvent(getBundle().getEventSystem().findEventByName(eventName),
                getBundle().getScheduleSystem(),
                getBundle().getUserManager());
    }

    public String getAppearanceMode() {
        return userManager.getUser(username).getAppearanceMode();
    }
}
