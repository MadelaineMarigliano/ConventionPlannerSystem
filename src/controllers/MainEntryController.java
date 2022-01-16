package controllers;


import views.MainEntryView;

import java.util.Scanner;

/**
 * The type Main entry controller.
 */
public class MainEntryController extends AbstractController {
    private MainEntryView view;

    /**
     * Instantiates a new Main entry controller.
     *
     * @param bundle the bundle
     */
    public MainEntryController(UseCaseBundle bundle) {
        super(bundle);
        view = new MainEntryView();
    }

    @Override
    public AbstractController run() {
        Scanner scanner = new Scanner(System.in);
        view.displayWelcomeMessage();
        view.displayMenu();
        String option = getOption(scanner);
        if (option.equals("logout")){
            return null;
        }
        else if (option.equals("quit")){
            setPopNum(-2);
            return null;
        }
        else if (option.equals("1")){
            // Login
            return new LoginController(getBundle());
        }
        else if (option.equals("2")){
            return new SignUpController(getBundle());
        }
        // If we reach here, something has gone wrong so restart this frame in the stack
        return null;

    }
    private String getOption(Scanner scanner){
        String input;
        input = scanner.nextLine();
        boolean isValid = validInput(input);
        while(!isValid){
            view.displayInputError();
            input = scanner.nextLine();
            isValid = validInput(input);
        }
        return input;
    }
    private boolean validInput(String input){
        return input.equals("logout") || input.equals("quit") || input.equals("1") || input.equals("2");
    }
}
