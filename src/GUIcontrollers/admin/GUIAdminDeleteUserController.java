package GUIcontrollers.admin;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import entities.UserTypes;
import use_cases.*;
import java.util.ArrayList;
import java.util.Set;

/**
 * The type Gui admin delete user controller.
 */
public class GUIAdminDeleteUserController extends AbstractController {
    private UserManager userManager;

    private String username;

    /**
     * Instantiates a new Gui admin delete user controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIAdminDeleteUserController(UseCaseBundle bundle, String username){
        super(bundle);
        this.userManager = bundle.getUserManager();
        setControllerType(ControllerType.ADMINDELETEUSER);
        this.username = username;
    }

    @Override
    public AbstractController run() {
        setPopNum(1);
        return null;
    }

    /**
     * Get all user info array list.
     *
     * @return the array list
     */
    public ArrayList<ArrayList<String>> getAllUserInfo(){
        return userManager.getAllUsersInString();
    }

    /**
     * Delete user.
     *
     * @param username the username
     */
    public void deleteUser(String username){
        MessagingSystem ms = getBundle().getMessagingSystem();
        UserInfoManager uim = getBundle().getUserInfoManager();
        UserManager um = getBundle().getUserManager();

        if (userManager.getUser(username).getUserType().equals(UserTypes.ORGANIZER) || userManager.getUser(username).getUserType().equals(UserTypes.SPEAKER)) {
            deleteAnnouncementsFrom(username);
        }
        deleteMessageHistories(username);
        ms.deleteMessagesOfUser(username);
        deleteContacts(username);
        if(uim.getUserType(um.getUser(username)) == UserTypes.SPEAKER){
            deleteAllSpokenEvents(username);
        }else{
            unenrollAllEvents(username);
        }
        userManager.deleteUser(username);
    }

    private void deleteMessageHistories(String username){
        UserManager um = getBundle().getUserManager();
        UserInfoManager uim = getBundle().getUserInfoManager();

        Set<String> messagedUsers = uim.getMessagedUsers(um.getUser(username));
        for(String s: messagedUsers){
            uim.deleteMessageHistoryOfUser(um.getUser(s), um.getUser(username));
        }
    }

    private void deleteContacts(String username){
        UserManager um = getBundle().getUserManager();
        ContactManager cm = getBundle().getContactManager();

        ArrayList<String> usersThisAdded = new ArrayList<>(cm.getPersonalContact(um.getUser(username)));
        ArrayList<String> usersAddedThis = new ArrayList<>(cm.getBeAddedTo(um.getUser(username)));

        for(String thisAdded: usersThisAdded){
            cm.deletePersonalContact(um.getUser(username), um.getUser(thisAdded));
        }

        for(String addedThis: usersAddedThis){
            cm.deletePersonalContact(um.getUser(addedThis), um.getUser(username));
        }
    }

    private void unenrollAllEvents(String username){
        UserManager um = getBundle().getUserManager();
        UserInfoManager uim = getBundle().getUserInfoManager();
        EventSystem es = getBundle().getEventSystem();

        ArrayList<Integer> enrolledEvents = new ArrayList<>(uim.viewEvents(um.getUser(username)));
        for(int eventId: enrolledEvents){
            es.unenroll(um.getUser(username), es.findEventByID(eventId));
        }

    }

    private void deleteAllSpokenEvents(String username){
        UserManager um = getBundle().getUserManager();
        UserInfoManager uim = getBundle().getUserInfoManager();
        EventSystem es = getBundle().getEventSystem();
        ScheduleSystem ss = getBundle().getScheduleSystem();

        ArrayList<Integer> spokenEvents = new ArrayList<>(uim.viewEvents(um.getUser(username)));
        for(int eventId: spokenEvents){
            es.deleteEvent(es.findEventByID(eventId), ss, um);
        }
    }

    private void deleteAnnouncementsFrom(String username) {
        UserManager userManager = getBundle().getUserManager();
        UserInfoManager userInfoManager = getBundle().getUserInfoManager();

        if (userManager.getUser(username).getUserType().equals(UserTypes.ORGANIZER)) {
            userInfoManager.deleteAnnouncementsFromOrganizerUser(userManager.getUser(username), userManager.getAllUsers());
        }
        if (userManager.getUser(username).getUserType().equals(UserTypes.SPEAKER)) {
            userInfoManager.deleteAnnouncementsFromSpeakerUser(userManager.getUser(username), userManager.getAllUsers());
        }
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
