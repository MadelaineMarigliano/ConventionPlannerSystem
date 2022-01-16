package GUIcontrollers.login;


import GUIcontrollers.signup.GUISignUpController;
import GUIcontrollers.factory.GUIUserControllerFactory;
import controllers.*;
import use_cases.StatsManager;
import use_cases.UserInfoManager;
import use_cases.UserManager;

/**
 * The type Gui login controller.
 */
public class GUILoginController extends AbstractController {
    private StatsManager statsManager;
    private UserManager userManager;
    private UserInfoManager userInfoManager;
    private int option; //1 = signup, 2 = back, 3 = good credentials
    private String username;

    /**
     * Instantiates a new Gui login controller.
     *
     * @param bundle the bundle
     */
    public GUILoginController(UseCaseBundle bundle){
        super(bundle);
        this.userManager = bundle.getUserManager();
        this.userInfoManager = bundle.getUserInfoManager();
        this.statsManager = bundle.getStatsManager();
        setControllerType(ControllerType.LOGIN);
    }
    @Override
    public AbstractController run() {
        if (option == 1){ // Sign Up
            setPopNum(1);
            return new GUISignUpController(getBundle());
        }
        else if (option == 2){ // Back
            setPopNum(1);
            return null;
        }
        else if (option == 3){ // Good Credentials
            setPopNum(1);
            GUIUserControllerFactory factory = new GUIUserControllerFactory();
            statsManager.login(username);
            return factory.generate(getBundle(), username);
        }
        return null;
    }

    /**
     * Sign up clicked.
     */
    public void signUpClicked(){ option = 1; }

    /**
     * Back clicked.
     */
    public void backClicked(){ option = 2; }

    /**
     * Good login.
     */
    public void goodLogin(){ option = 3; }

    /**
     * Validate credentials boolean.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    public boolean validateCredentials(String username, String password){
        boolean goodCredentials =  userManager.existsUsername(username) &&
                userInfoManager.loginVerify(userManager.getUser(username), password);
        if (goodCredentials){ this.username = username; }
        return goodCredentials;
    }
}
