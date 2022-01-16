package controllers;


import views.OrganizerView;

import java.util.Scanner;

/**
 * A controller for an Organizer user.
 */
public class OrganizerController extends UserController {
    private OrganizerView view;

    /**
     * Instantiates a new Organizer controller.
     *
     * @param bundle   the use case bundle
     * @param username the username of the current user
     */
    public OrganizerController(UseCaseBundle bundle, String username) {
        super(bundle, username.trim());
        view = new OrganizerView();
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
            case 4: // Manage Users
                return new AdminController(getBundle(), getUsername());
            case 5: // Manage Rooms
                return new RoomController(getBundle());
            case 6: // Manage Events
                return new ScheduleController(getBundle());
            case 7: // Log Out
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
                case "5":
                    return 5;
                case "6":
                    return 6;
                case "7":
                    return 7;
            }
            view.optionError();
        } while (true);
    }

}
