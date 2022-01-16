package GUIcontrollers.settings;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import use_cases.UserManager;

public class GUISettingsPasswordController extends AbstractController {
    private UserManager userManager;

    private int option;
    private String username;

    public GUISettingsPasswordController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.username = username;
        this.userManager = bundle.getUserManager();
        setControllerType(ControllerType.SETTINGSPASSWORD);
    }

    @Override
    public AbstractController run() {
        if (option == 1) { //save
            setPopNum(1);
            return null;
        } else if (option == 2) { //back
            setPopNum(1);
            return null;
        }
        return null;
    }

    public void saveClicked(String newPassword) {
        option = 1;
        userManager.getUser(username).changePassword(newPassword);
    }

    public void backClicked() {
        option = 2;
    }

    public String getAppearanceMode() {
        return userManager.getUser(username).getAppearanceMode();
    }
}
