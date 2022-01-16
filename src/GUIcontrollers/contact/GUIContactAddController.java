package GUIcontrollers.contact;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import use_cases.ContactManager;
import use_cases.UserManager;

/**
 * The type Gui contact add controller.
 */
public class GUIContactAddController extends AbstractController {
    private UserManager userManager;
    private String username;
    /**
     * The Option.
     */
    int option;

    /**
     * Instantiates a new Gui contact add controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIContactAddController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.userManager = bundle.getUserManager();
        this.username = username;
        setControllerType(ControllerType.CONTACTADD);
    }

    @Override
    public AbstractController run() {
        if (option == 1) { // Back
            setPopNum(1);
            return null;
        }
        return null;
    }

    /**
     * Add clicked.
     *
     * @param usernameAdd the username add
     */
    public void addClicked(String usernameAdd){
        ContactManager cm = getBundle().getContactManager();
        UserManager um = getBundle().getUserManager();

        cm.addPersonalContact(um.getUser(username), um.getUser(usernameAdd));
    }

    /**
     * Back clicked.
     */
    public void backClicked(){
        option = 1;
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
