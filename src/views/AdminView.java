package views;

/**
 * The type Admin view.
 */
public class AdminView extends AbstractView {
    /**
     * Display menu.
     */
    public void displayMenu() {
        System.out.println("1. Create speaker");
        System.out.println("2. Create organizer");
        System.out.println("3. Delete user");
        System.out.println("4. Return");
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
     * First prompt.
     */
    public void firstPrompt() {
        System.out.println("Enter the first name: ");
    }

    /**
     * Last prompt.
     */
    public void lastPrompt() {
        System.out.println("Enter the last name: ");
    }

    /**
     * Username prompt.
     */
    public void usernamePrompt() {
        System.out.println("Enter the username: ");
    }

    /**
     * Password prompt.
     */
    public void passwordPrompt() {
        System.out.println("Enter the password: ");
    }


    /**
     * Create user success.
     */
    public void createUserSuccess() {
        System.out.println("User created!");
    }

    /**
     * Delete user success.
     */
    public void deleteUserSuccess() {
        System.out.println("User deleted!");
    }
}
