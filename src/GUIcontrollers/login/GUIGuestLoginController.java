package GUIcontrollers.login;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import entities.VIPAttendee;
import use_cases.EventSystem;
import use_cases.StatsManager;
import use_cases.UserInfoManager;
import use_cases.UserManager;

import java.util.ArrayList;

/**
 * The type Gui guest login controller.
 */
public class GUIGuestLoginController extends AbstractController {
    private StatsManager statsManager;
    private UserManager userManager;

    private UserInfoManager userInfoManager;
    private int option; //1 = signup, 2 = back, 3 = good credentials
    private String username;

    /**
     * Instantiates a new Gui guest login controller.
     *
     * @param bundle the bundle
     */
    public GUIGuestLoginController(UseCaseBundle bundle){
        super(bundle);
        this.userManager = bundle.getUserManager();
        this.userInfoManager = bundle.getUserInfoManager();
        this.statsManager = bundle.getStatsManager();
        setControllerType(ControllerType.GUESTLOGIN);
    }
    @Override
    public AbstractController run() {
        if (option == 1){ // Back
            setPopNum(1);
            return null;
        }
        else if (option == 2){ // Good Credentials
            setPopNum(0);
            return null;
            //GUIUserControllerFactory factory = new GUIUserControllerFactory();
            //return factory.generate(getBundle(), username);
        }
        return null;
    }

    /**
     * Back clicked.
     */
    public void backClicked(){ option = 1; }

    /**
     * Good login.
     */
    public void goodLogin(){ option = 2; }

    /**
     * Validate credentials boolean.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    public boolean validateCredentials(String username, String password){

        boolean goodCredentials =  userManager.existsUsername(username) && userManager.isVIP(username) &&
                ((VIPAttendee) userManager.getUser(username)).verifyGuestCode(password);
        if (goodCredentials){ this.username = username; }
        return goodCredentials;
    }

    public String viewEventsClicked() {
        ArrayList<Integer> eventList;

        UserManager um = getBundle().getUserManager();
        EventSystem es = getBundle().getEventSystem();
        UserInfoManager uim = getBundle().getUserInfoManager();
        eventList = uim.viewEvents(um.getUser(username));
        StringBuilder word = new StringBuilder(" ");
        for (Integer i : eventList) {
            word.append("Event name: ").append(es.findEventByID(i).getName().toString()).append(", Starts at: ").append(es.findEventByID(i).getStartTime()).append(", Ends at: ").append(es.findEventByID(i).getEndTime().toString()).append(", In room: ").append(es.findEventByID(i).getRoomID().toString()).append("\n");
        }
        if (word.toString() == " "){
            word = new StringBuilder("NO REGISTERED EVENTS");}
        return word.toString();

    }
}
