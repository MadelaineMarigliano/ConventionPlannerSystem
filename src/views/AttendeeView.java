package views;

/**
 * The view for AttendeeController
 */
public class AttendeeView {
    /**
     * Display menu.
     */
    public void displayMenu() {
        System.out.println("1. My events");
        System.out.println("2. My contacts");
        System.out.println("3. My messages");
        System.out.println("4. Log out");
    }

    /**
     * Ask for input.
     */
    public void askForInput() {
        System.out.println("Select an option:");
    }

    /**
     * Option error.
     */
    public void optionError() {
        System.out.println("Please enter a number from 1-4.");
    }
}
