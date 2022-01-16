package GUIcontrollers.schedule;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import use_cases.UserManager;

/**
 * The type Gui schedule home controller.
 */
public class GUIScheduleHomeController extends AbstractController {

    private int option;
    private String username;
    private UserManager userManager;

    /**
     * Instantiates a new Gui schedule home controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIScheduleHomeController(UseCaseBundle bundle, String username){
        super(bundle);
        setControllerType(ControllerType.SCHEDULEHOME);
        this.username = username;
        this.userManager = bundle.getUserManager();
    }

    @Override
    public AbstractController run() {
        if (option == 1){
            return new GUIScheduleCreateEventController(getBundle(), username);
        }
        else if (option == 2){
            return new GUIScheduleDeleteEventController(getBundle(), username);
        }
        else if (option == 3){
            setPopNum(1);
            return null;
        }
        return null;
    }

    /**
     * Create event clicked.
     */
    public void createEventClicked(){ option = 1; }

    /**
     * Delete event clicked.
     */
    public void deleteEventClicked(){ option = 2; }

    /**
     * Back clicked.
     */
    public void backClicked(){ option = 3; }

    /**
     * Gets appearance mode.
     *
     * @return the appearance mode
     */
    public String getAppearanceMode() {
        return userManager.getUser(username).getAppearanceMode();
    }
}
