package views;

/**
 * View for the OrganizerController
 */
public class OrganizerView {
    /**
     * Display menu.
     */
    public void displayMenu() {
        System.out.println("1. My Events");
        System.out.println("2. My Contacts");
        System.out.println("3. My Messages");
        System.out.println("4. Manage Users");
        System.out.println("5. Manage Rooms");
        System.out.println("6. Manage Events");
        System.out.println("7. Log Out");
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
        System.out.println("Please enter a number from 1-7");
    }
}
