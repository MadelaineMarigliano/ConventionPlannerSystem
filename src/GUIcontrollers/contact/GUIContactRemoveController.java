package GUIcontrollers.contact;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import use_cases.ContactManager;
import use_cases.UserManager;

/**
 * The type Gui contact remove controller.
 */
public class GUIContactRemoveController extends AbstractController {
    private UserManager userManager;
    private String username;
    /**
     * The Option.
     */
    int option;

    /**
     * Instantiates a new Gui contact remove controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIContactRemoveController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.userManager = bundle.getUserManager();
        this.username = username;
        setControllerType(ControllerType.CONTACTREMOVE);
    }

    @Override
    public AbstractController run() {
        switch (option) {
            case 1: // Remove Contact

            case 2: // Back
                setPopNum(1);
                return null;

        }
        return null;
    }

    /**
     * Remove clicked.
     *
     * @param usernameDelete the username delete
     */
    public void removeClicked(String usernameDelete){
        ContactManager cm = getBundle().getContactManager();
        UserManager um = getBundle().getUserManager();

        cm.deletePersonalContact(um.getUser(username), um.getUser(usernameDelete));

        option = 1;
    }

    /**
     * Back clicked.
     */
    public void backClicked(){
        option = 2;
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
