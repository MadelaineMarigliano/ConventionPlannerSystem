package GUIcontrollers.statistics;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import entities.DblStrTuple;
import entities.IntStrTuple;
import use_cases.EventSystem;
import use_cases.StatsManager;
import use_cases.UserManager;

import java.util.*;

/**
 * The type Stats controller.
 */
public class GUIStatsController extends AbstractController {
    private UserManager um;
    private StatsManager sm;
    private EventSystem es;
    private String username;


    /**
     * Instantiates a new Stats controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIStatsController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.username = username;
        um = bundle.getUserManager();
        sm = bundle.getStatsManager();
        es = bundle.getEventSystem();
        setControllerType(ControllerType.STATS);
    }

    /**
     * Gets total users.
     *
     * @return the total users
     */
    public int getTotalUsers() {
        int a = um.getAllAttendeesAndOrganizers().size();
        int b = um.getAllSpeakers().size();
        return a + b;
    }

    /**
     * Gets num logins.
     *
     * @return the num logins
     */
    public int getNumLogins() {
        return sm.getNumLogins();
    }

    /**
     * Total messages sent int.
     *
     * @return the int
     */
    public int totalMessagesSent() {
        return sm.getSentMessages();
    }

    @Override
    public AbstractController run() {
        setPopNum(1);
        return null;
    }

    /**
     * Events attendance order array list.
     *
     * @return the array list
     */
    public ArrayList<IntStrTuple> eventsAttendanceOrder() {
        ArrayList<IntStrTuple> tuples = new ArrayList<>();

        for (int i = 0; i < es.getEventList().size(); i++) {
            Integer n = es.getEventList().get(i).getNumAttendees();
            String s = es.getEventList().get(i).getName();
            IntStrTuple tuple = new IntStrTuple(n, s);
            tuples.add(tuple);
        }
        Collections.sort(tuples, Collections.reverseOrder());
        return tuples;
    }

    /**
     * Events percentage array list.
     *
     * @return the array list
     */
    public ArrayList<DblStrTuple> eventsPercentage() {

        ArrayList<DblStrTuple> tuples = new ArrayList<>();
        for (int i = 0; i < es.getEventList().size(); i++) {
            Double d = es.getEventList().get(i).getPercentAttending();
            String s = es.getEventList().get(i).getName();
            DblStrTuple tuple = new DblStrTuple(d,s);
            tuples.add(tuple);
        }
        Collections.sort(tuples, Collections.reverseOrder());
        return tuples;
    }

    /**
     * Most attending users array list.
     *
     * @return the array list
     */
    public ArrayList<IntStrTuple> mostAttendingUsers() {
        ArrayList<IntStrTuple> tuples = new ArrayList<>();
        for (int i = 0; i < um.getAllAttendeesAndOrganizers().size(); i++) {
            Integer n = um.getAllAttendeesAndOrganizers().get(i).getNumEvents();
            String s = um.getAllAttendeesAndOrganizers().get(i).getUsername();
            IntStrTuple tuple = new IntStrTuple(n, s);
            tuples.add(tuple);
        }
        Collections.sort(tuples, Collections.reverseOrder());
        return tuples;
    }

    /**
     * Gets total attendance.
     *
     * @return the total attendance
     */
    public Integer getTotalAttendance() {
        Integer total = 0;
        for (int i = 0; i < es.getEventList().size(); i++) {
            total += es.getEventList().get(i).getNumAttendees();
        }
        return total;
    }

    /**
     * Attendees per event double.
     *
     * @return the double
     */
    public double attendeesPerEvent() {
        int numAttendees = um.getAllAttendeesAndOrganizers().size();
        int numEvents = es.getEventList().size();
        if (numEvents * numAttendees == 0) {
            return 0.0;
        }
        return (double) numAttendees / numEvents;
    }

    /**
     * Attendees per user double.
     *
     * @return the double
     */
    public double attendeesPerUser() {
        int numAttendees = um.getAllAttendeesAndOrganizers().size();
        int numEvents = es.getEventList().size();
        if (numEvents * numAttendees == 0) {
            return 0.0;
        }
        return (double) numEvents / numAttendees;
    }

    /**
     * User logins array list.
     *
     * @return the array list
     */
    public ArrayList<IntStrTuple> userLogins() {
        ArrayList<IntStrTuple> tuples = new ArrayList<>();
        HashMap<String, Integer> logins = sm.getLogins();
        for (Map.Entry<String, Integer> entry : logins.entrySet()) {
            String username = entry.getKey();
            Integer numLogins = entry.getValue();
            IntStrTuple tuple = new IntStrTuple(numLogins, username);
            tuples.add(tuple);
        }
        Collections.sort(tuples, Collections.reverseOrder());
        return tuples;
    }

    /**
     * Gets appearance mode.
     *
     * @return the appearance mode
     */
    public String getAppearanceMode() {
        return um.getUser(username).getAppearanceMode();
    }
}
