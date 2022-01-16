package GUIcontrollers.signup;

import GUIcontrollers.factory.GUIUserControllerFactory;
import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import entities.UserTypes;
import use_cases.UserManager;

import java.security.InvalidParameterException;

/**
 * The type Gui sign up controller.
 */
public class GUISignUpController extends AbstractController {
    private UserManager userManager;
    private int option; //1 = back, 2 = createAccount
    private String username;

    /**
     * Instantiates a new Gui sign up controller.
     *
     * @param bundle the bundle
     */
    public GUISignUpController(UseCaseBundle bundle){
        super(bundle);
        this.userManager = bundle.getUserManager();
        setControllerType(ControllerType.SIGNUP);
    }

    @Override
    public AbstractController run() {
        if (option == 1){
            setPopNum(1);
            return null;
        }
        else if (option == 2){
            setPopNum(1);
            GUIUserControllerFactory factory = new GUIUserControllerFactory();
            return factory.generate(getBundle(), this.username);
        }
        return null;
    }

    /**
     * Back clicked.
     */
    public void backClicked(){ option = 1; }

    /**
     * Submit clicked.
     */
    public void submitClicked(){ option = 2; }

    /**
     * Valid entries boolean.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param username  the username
     * @param password  the password
     * @return the boolean
     */
    public boolean validEntries(String firstName, String lastName, String username, String password){
        try{
            userManager.createUser(firstName, lastName, username, password, UserTypes.ATTENDEE);
            this.username = username;
            return true;
        }
        catch(InvalidParameterException e){
            return false;
        }
    }
}
