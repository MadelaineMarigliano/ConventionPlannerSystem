package views;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * The view for the contact controller
 */
public class ContactView extends AbstractView {
    /**
     * Display menu.
     */
    public void displayMenu() {
        System.out.println("1. View contacts");
        System.out.println("2. Add contact");
        System.out.println("3. Remove contact");
        System.out.println("4. Return");
    }

    /**
     * Display contacts.
     *
     * @param contactUsernames the contact usernames
     * @param contactNames     the contact names
     */
    public void displayContacts(ArrayList<String> contactUsernames, ArrayList<String> contactNames) {
        for (int i = 0; i < contactUsernames.size(); i++) {
            System.out.println(contactNames.get(i) + " (" + contactUsernames.get(i) + ")");
        }
    }

    /**
     * Option prompt.
     */
    public void optionPrompt() {
        System.out.println("Select an option:");
    }

    /**
     * Option error.
     */
    public void optionError() {
        System.out.println("Please enter a number from 1-4.");
    }

    /**
     * Add contact prompt.
     */
    public void addContactPrompt() {
        System.out.println("Enter a username:");
    }

    /**
     * Add contact success.
     */
    public void addContactSuccess() {
        System.out.println("Contact added");
    }

    /**
     * Remove contact prompt.
     */
    public void removeContactPrompt() {
        System.out.println("Enter a username:");
    }

    /**
     * Remove contact success.
     */
    public void removeContactSuccess() {
        System.out.println("Contact removed");
    }

}
