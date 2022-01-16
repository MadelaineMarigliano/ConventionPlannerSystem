package views;

/**
 * The type Main entry view.
 */
public class MainEntryView {
    /**
     * Display welcome message.
     */
    public void displayWelcomeMessage() {
        System.out.println("Welcome to the Convention Planner System!\n");
    }

    /**
     * Display menu.
     */
    public void displayMenu() {
        System.out.println("Here are your options: ");
        System.out.println("\t 1. Log in");
        System.out.println("\t 2. Sign Up for a new account");
        System.out.println("Enter 1 or 2: ");
    }

    /**
     * Display input error.
     */
    public void displayInputError() {
        System.out.println("Please enter one of the options above.");
    }
}
