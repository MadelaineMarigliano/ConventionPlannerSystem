package controllers;


import views.LoginView;
import use_cases.UserManager;
import use_cases.UserInfoManager;

import java.util.Scanner;

/**
 * Controller for logging in.
 */
public class LoginController extends AbstractController{
    private LoginView view;
    private UserManager um;
    private UserInfoManager uim;

    /**
     * Instantiates a new Login controller.
     *
     * @param bundle the bundle
     */
    public LoginController(UseCaseBundle bundle){
        super(bundle);
        this.um = bundle.getUserManager();
        this.uim = bundle.getUserInfoManager();
        view = new LoginView();
    }
    @Override
    public AbstractController run() {
        Scanner scanner = new Scanner(System.in);
        String username = getUsername(scanner);
        if (username.equals("quit")){
            setPopNum(-2);
            return null;
        }
        else if (username.equals("sign up")){
            setPopNum(1);
            return new SignUpController(getBundle());
        }
        boolean goodPassword = verifyPassword(scanner, username);
        if (!goodPassword){
            setPopNum(1);
            return null;
        }
        setPopNum(1);
        UserControllerFactory factory = new UserControllerFactory();
        return factory.generate(getBundle(), username);

    }

    private String getUsername(Scanner scanner){
        boolean valid;
        String usernameIn;
        do {
            view.askForUsername();
            usernameIn = scanner.nextLine();
            valid = um.existsUsername(usernameIn) || usernameIn.equals("sign up") || usernameIn.equals("quit");
            if(!valid){
                view.userNotFound();
            }
        }
        while(!valid);
        return usernameIn;
    }

    private boolean verifyPassword(Scanner scanner, String username){
        boolean valid;
        String passwordIn;
        do{
            view.askForPassword();
            passwordIn = scanner.nextLine();

            if (passwordIn.equals("back")){
                return false;
            }

            valid = uim.loginVerify(um.getUser(username), passwordIn);

            if(!valid){
                view.incorrectPassword();
            }
        }
        while(!valid);
        return true;
    }

}









