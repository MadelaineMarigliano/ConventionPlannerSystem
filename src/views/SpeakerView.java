package views;

/**
 * View for the SpeakerController
 */
public class SpeakerView {

    /**
     * Display menu.
     */
    public void displayMenu() {
        System.out.println("1. My Events");
        System.out.println("2. My Contacts");
        System.out.println("3. My Messages");
        System.out.println("4. Log Out");
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
