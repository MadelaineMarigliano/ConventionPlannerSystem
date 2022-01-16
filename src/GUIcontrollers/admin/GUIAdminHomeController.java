package GUIcontrollers.admin;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import use_cases.UserManager;

/**
 * The type Gui admin home controller.
 */
public class GUIAdminHomeController extends AbstractController {
    private int option;
    private UserManager userManager;
    private String username;

    /**
     * Instantiates a new Gui admin home controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIAdminHomeController(UseCaseBundle bundle, String username){
        super(bundle);
        this.username = username;
        userManager = bundle.getUserManager();
        setControllerType(ControllerType.ADMINHOME);
    }

    @Override
    public AbstractController run() {
        switch (option){
            case 1: // Create user
                return new GUIAdminCreateUserController(getBundle(), username);
            case 2: // Delete User
                return new GUIAdminDeleteUserController(getBundle(), username);
            case 3: // Return
                setPopNum(1);
                return null;
        }
        return null;
    }


    /**
     * Back clicked.
     */
    public void backClicked(){ option = 3; }

    /**
     * Create user clicked.
     */
    public void createUserClicked(){ option = 1; }

    /**
     * Delete user clicked.
     */
    public void deleteUserClicked(){ option = 2; }

    /**
     * Gets appearance mode.
     *
     * @return the appearance mode
     */
    public String getAppearanceMode() {
        return userManager.getUser(username).getAppearanceMode();
    }
}
