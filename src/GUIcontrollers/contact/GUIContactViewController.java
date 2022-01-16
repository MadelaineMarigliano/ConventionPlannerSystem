package GUIcontrollers.contact;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import use_cases.ContactManager;
import use_cases.UserManager;

import java.util.ArrayList;


/**
 * The type Gui contact view controller.
 */
public class GUIContactViewController extends AbstractController {
    private UserManager userManager;
    private String username;
    private UseCaseBundle useCaseBundle;
    /**
     * The Option.
     */
    int option;

    /**
     * Instantiates a new Gui contact view controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIContactViewController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.userManager = bundle.getUserManager();
        this.username = username;
        this.useCaseBundle = bundle;
        setControllerType(ControllerType.CONTACTVIEW);
    }

    @Override
    public AbstractController run() {
        if (option == 1) { // Back
            setPopNum(1);
            return null;
        }
        return null;
    }

    /**
     * Back clicked.
     */
    public void backClicked(){
        option = 1;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get all contacts in string array list.
     *
     * @return the array list
     */
    public ArrayList<ArrayList<String>> getAllContactsInString(){
        ContactManager cm = useCaseBundle.getContactManager();
        UserManager um = useCaseBundle.getUserManager();

        ArrayList<ArrayList<String>> allContactsInString = new ArrayList<>();

        ArrayList<String> contactNames = cm.getPersonalContact(um.getUser(username));
        for(String contactName: contactNames){
            ArrayList<String> contactInString = new ArrayList<>();
            contactInString.add(um.getUser(contactName).getFirst());
            contactInString.add(um.getUser(contactName).getLast());
            contactInString.add(contactName);

            allContactsInString.add(contactInString);
        }

        return allContactsInString;
    }

    /**
     * Gets appearance mode.
     *
     * @return the appearance mode
     */
    public String getAppearanceMode() {
        return userManager.getUser(username).getAppearanceMode();
    }
}
