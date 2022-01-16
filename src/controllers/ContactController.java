package controllers;


import use_cases.ContactManager;
import use_cases.UserManager;
import views.ContactView;
//
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A controller for viewing and managing contacts.
 */
public class ContactController extends AbstractController {
    private ContactView view;

    /**
     * The Username.
     */
    String username;
    /**
     * The Cm.
     */
    ContactManager cm;
    /**
     * The Um.
     */
    UserManager um;

    /**
     * Instantiates a new Contact controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public ContactController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.username = username;
        this.cm = getBundle().getContactManager();
        this.um = getBundle().getUserManager();
        view = new ContactView();
    }

    @Override
    public AbstractController run() {
        Scanner scanner = new Scanner(System.in);
        view.displayMenu();
        int option;

        option = selectOption(scanner);


        switch (option) {
            case 1: // View contacts
                showContacts();
                setPopNum(0);
                return null;
            case 2: // Add contact
                addContact(scanner);
                setPopNum(0);
                return null;
            case 3: // Remove contact
                removeContact(scanner);
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

    /**
     * Show contacts.
     */
    public void showContacts() {
        ArrayList<String> contactUsernames;
        ArrayList<String> contactNames = new ArrayList<>();

        try {
            contactUsernames = cm.getPersonalContact(um.getUser(username));
            for (String contactUsername : contactUsernames) {
                contactNames.add(um.getUser(contactUsername).getName());
            }
            view.displayContacts(contactUsernames, contactNames);
        } catch (Exception e) {
            view.printException(e);
        }
    }

    /**
     * Add contact.
     *
     * @param scanner the scanner
     */
    public void addContact(Scanner scanner) {
        String input;
        view.addContactPrompt();
        try {
            input = scanner.nextLine();
            cm.addPersonalContact(um.getUser(username), um.getUser(input));
            view.addContactSuccess();
        } catch (Exception e) {
            view.printException(e);
        }
    }

    /**
     * Remove contact.
     *
     * @param scanner the scanner
     */
    public void removeContact(Scanner scanner) {
        String input;
        view.removeContactPrompt();
        try {
            input = scanner.nextLine();
            cm.deletePersonalContact(um.getUser(username), um.getUser(input));
            view.removeContactSuccess();
        } catch (Exception e) {
            view.printException(e);
        }
    }
}
