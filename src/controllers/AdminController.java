package controllers;

import entities.UserTypes;
import use_cases.UserManager;
import views.AdminView;

import java.util.Scanner;

/**
 * Controller for organizer to add and remove users from the system.
 */
public class AdminController extends AbstractController {
    private AdminView view;

    /**
     * An instance of the UserManager.
     */
    UserManager um;
    /**
     * The current user's username.
     */
    String username;

    /**
     * Instantiates a new Admin controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public AdminController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.username = username;
        view = new AdminView();
        um = bundle.getUserManager();
    }

    @Override
    public AbstractController run() {
        Scanner scanner = new Scanner(System.in);
        int option;
        view.displayMenu();
        option = selectOption(scanner);

        switch (option) {
            case 1: // Create Speaker
                createUser(scanner, UserTypes.SPEAKER);
                setPopNum(0);
                return null;
            case 2: // Create Organizer
                createUser(scanner, UserTypes.ORGANIZER);
                setPopNum(0);
                return null;
            case 3: // Delete User
                //deleteUser(scanner);
                setPopNum(0);
                return null;
            case 4: // Return
                setPopNum(1);
                return null;
        }
        return null;
    }

    private int selectOption(Scanner scanner) {
        String input;
        do {
            view.optionPrompt();
            input = scanner.nextLine();
            switch (input.trim()) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
            }
            view.optionError();
        } while (true);
    }

    private void createUser(Scanner scanner, UserTypes type) {
        String first;
        String last;
        String un;
        String password;

        view.firstPrompt();
        first = scanner.nextLine();
        view.lastPrompt();
        last = scanner.nextLine();
        view.usernamePrompt();
        un = scanner.nextLine();
        view.passwordPrompt();
        password = scanner.nextLine();

        try {
            um.createUser(first, last, un, password, type);
            view.createUserSuccess();
        } catch (Exception e) {
            view.printException(e);
        }
    }
    /*
    private void deleteUser(Scanner scanner) {
        String un;

        presenter.usernamePrompt();
        un = scanner.nextLine();

        try {
            if (!this.username.equals(un)) {
                um.deleteUser(un);
            }
            presenter.deleteUserSuccess();
        } catch (Exception e) {
            presenter.printError(e);
        }
    }*/
}
