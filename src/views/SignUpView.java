package views;

/**
 * The type Sign up view.
 */
public class SignUpView extends AbstractView {
    /**
     * Display menu.
     */
    public void displayMenu(){

    }

    /**
     * Invalid details.
     */
    public void invalidDetails(){
        System.out.println("That's not valid entry! Please try again.");
    }

    /**
     * Ask for username.
     */
    public void askForUsername() {
        System.out.println("Enter a username: ");
    }

    /**
     * Ask for first name.
     */
    public void askForFirstName(){
        System.out.println("What is your first name: ");
    }

    /**
     * Ask for last name.
     */
    public void askForLastName(){
        System.out.println("What is your last name: ");
    }

    /**
     * Ask for password.
     */
    public void askForPassword(){
        System.out.println("Enter a password");
    }

    /**
     * Sign up success.
     */
    public void signUpSuccess() {
        System.out.println("Your new account was created!");
    }
}
