package GUIcontrollers.contact;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import use_cases.UserManager;

/**
 * The type Gui contact home controller.
 */
public class GUIContactHomeController extends AbstractController {
    private UserManager userManager;

    private String username;
    private int option;

    /**
     * Instantiates a new Gui contact home controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIContactHomeController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.userManager = bundle.getUserManager();
        this.username = username;
        setControllerType(ControllerType.CONTACTHOME);
    }

    @Override
    public AbstractController run() {
        switch (option) {
            case 1: // View contacts
                setPopNum(0);
                return new GUIContactViewController(getBundle(), username);
            case 2: // Add contact
                setPopNum(0);
                return new GUIContactAddController(getBundle(), username);
            case 3: // Remove contact
                setPopNum(0);
                return new GUIContactRemoveController(getBundle(), username);
            case 4: // Back
                setPopNum(1);
                return null;
        }
        return null;
    }

    /**
     * View clicked.
     */
    public void viewClicked(){
        option = 1;
    }

    /**
     * Add clicked.
     */
    public void addClicked(){
        option = 2;
    }

    /**
     * Remove clicked.
     */
    public void removeClicked(){
        option = 3;
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
        return userManager.getUser(username).getAppearanceMode();
    }

}
