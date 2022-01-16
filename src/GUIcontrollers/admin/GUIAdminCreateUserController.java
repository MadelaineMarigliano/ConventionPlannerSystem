package GUIcontrollers.admin;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import entities.UserTypes;
import use_cases.UserManager;

import java.security.InvalidParameterException;

/**
 * The type Gui admin create user controller.
 */
public class GUIAdminCreateUserController extends AbstractController {
    private UserManager userManager;
    private int option = 0;
    private String username;

    /**
     * Instantiates a new Gui admin create user controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIAdminCreateUserController(UseCaseBundle bundle, String username){
        super(bundle);
        this.userManager = bundle.getUserManager();
        setControllerType(ControllerType.ADMINCREATEUSER);
        this.username = username;
    }

    @Override
    public AbstractController run() {
        if (option != 1){
            return null;
        }
        setPopNum(1);
        return null;
    }

    /**
     * Back clicked.
     */
    public void backClicked(){ option = 1; }

    /**
     * Submit clicked.
     */
    public void submitClicked(){ option = 1; }

    /**
     * Valid entries boolean.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param username  the username
     * @param password  the password
     * @param types     the types
     * @return the boolean
     */
    public boolean validEntries(String firstName, String lastName, String username, String password, UserTypes types){
        try{
            userManager.createUser(firstName, lastName, username, password, types);
            return true;
        }
        catch (InvalidParameterException e){
            return false;
        }
    }

    /**
     * Get type from string user types.
     *
     * @param type the type
     * @return the user types
     */
    public UserTypes getTypeFromString(String type){
        if (type.equals("Organizer")){ return UserTypes.ORGANIZER; }
        if (type.equals("Speaker")){ return UserTypes.SPEAKER; }
        if (type.equals("Attendee")){ return UserTypes.ATTENDEE; }
        return UserTypes.VIP;
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
