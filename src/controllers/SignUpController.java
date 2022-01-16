package controllers;

import controllers.UseCaseBundle;
import views.SignUpView;
import entities.UserTypes;
import use_cases.UserManager;

import java.util.Scanner;

/**
 * The type Sign up controller.
 */
public class SignUpController extends AbstractController {
    private SignUpView view;
    private UserManager um;

    /**
     * Instantiates a new Sign up controller.
     *
     * @param bundle the bundle
     */
    public SignUpController(UseCaseBundle bundle){
        super(bundle);
        this.um = bundle.getUserManager();
        view = new SignUpView();
    }

    @Override
    public AbstractController run() {
        Scanner scanner = new Scanner(System.in);
        view.askForFirstName();
        String firstName = scanner.nextLine();
        view.askForLastName();
        String lastName = scanner.nextLine();
        view.askForUsername();
        String username = getUsername(scanner);
        view.askForPassword();
        String password = scanner.nextLine();

        try {
            um.createUser(firstName, lastName, username, password, UserTypes.ATTENDEE);
            setPopNum(1);
            view.signUpSuccess();
            getBundle().getStatsManager().login(username);
            UserControllerFactory factory = new UserControllerFactory();
            return factory.generate(getBundle(), username);
        } catch (Exception e) {
            view.printException(e);
            return null;
        }
    }

    private String getUsername(Scanner scanner){
        return scanner.nextLine();
    }

}
