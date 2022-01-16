package GUIcontrollers;

import GUIcontrollers.login.GUIGuestLoginController;
import GUIcontrollers.login.GUILoginController;
import GUIcontrollers.signup.GUISignUpController;
import controllers.*;

/**
 * The type Main entry controller.
 */
public class GUIMainEntryController extends AbstractController {
    private int option;

    /**
     * Instantiates a new Main entry controller.
     *
     * @param bundle the bundle
     */
    public GUIMainEntryController(UseCaseBundle bundle) {
        super(bundle);
        setControllerType(ControllerType.MAINENTRY);
    }

    @Override
    public AbstractController run() {

        if(option == 1){

            System.out.println("Login selected");
            return new GUILoginController(getBundle());
        }
        else if (option == 2){
            System.out.println("Sign Up selected");
            return new GUISignUpController(getBundle());
        }
        else if (option == 3){
            System.out.println("Guest login");
            return new GUIGuestLoginController(getBundle());
        }
        // If we reach here, something has gone wrong so restart this frame in the stack
        //setPopNum(-2);
        return null;

    }

    /**
     * Login clicked.
     */
    public void loginClicked(){
        option = 1;
    }

    /**
     * Sign up clicked.
     */
    public void signUpClicked(){
        option = 2;
    }

    /**
     * Guest login clicked.
     */
    public void guestLoginClicked(){ option = 3; }

}
