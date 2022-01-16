package controllers;


import views.SpeakerView;

import java.util.Scanner;

/**
 * A controller for a Speaker user.
 */
public class SpeakerController extends UserController {
    private SpeakerView view;

    /**
     * Instantiates a new Speaker controller.
     *
     * @param bundle   the use case bundle
     * @param username the username of the current user
     */
    public SpeakerController(UseCaseBundle bundle, String username) {
        super(bundle, username.trim());
        view = new SpeakerView();
    }

    @Override
    public AbstractController run() {
        Scanner scanner = new Scanner(System.in);
        view.displayMenu();
        int option;
        option = selectOption(scanner);
        switch (option) {
            case 1: // My Events
                return new EventController(getBundle(), getUsername());
            case 2: // My Contacts
                return new ContactController(getBundle(), getUsername());
            case 3: // My Messages
                return new MessageController(getBundle(), getUsername());
            case 4: // Log Out
                setPopNum(1);
                return null;
        }
        return null;
    }

    private int selectOption(Scanner scanner) {
        String input;
        do {
            view.askForInput();
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
}
