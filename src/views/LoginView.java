package views;

/**
 * The type Login view.
 */
public class LoginView {
    /**
     * Ask for username.
     */
    public void askForUsername(){
        System.out.println("Enter username: ");
    }

    /**
     * User not found.
     */
    public void userNotFound(){
        System.out.println("User not found. Please try again or enter \"sign up\" to create an account");
    }

    /**
     * Ask for password.
     */
    public void askForPassword(){
        System.out.println("Enter password: ");
    }

    /**
     * Incorrect password.
     */
    public void incorrectPassword(){

        System.out.println("Incorrect password! Please try again or enter \"back\" to go back to the main page");
    }
}
