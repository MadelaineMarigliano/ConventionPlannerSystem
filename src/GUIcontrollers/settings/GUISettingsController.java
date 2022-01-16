package GUIcontrollers.settings;

import GUIcontrollers.GUIMainEntryController;
import GUIcontrollers.organizer.GUIOrganizerController;
import GUIcontrollers.speaker.GUISpeakerController;
import GUIcontrollers.attendee.GUIAttendeeController;
import controllers.AbstractController;
import controllers.ControllerType;
import controllers.MainEntryController;
import controllers.UseCaseBundle;
import entities.UserTypes;
import use_cases.*;

import java.util.ArrayList;
import java.util.Set;

/**
 * The type Gui settings controller.
 */
public class GUISettingsController extends AbstractController {
    private UserManager userManager;
    private MessagingSystem messagingSystem;
    private UserInfoManager userInfoManager;
    private ContactManager contactManager;
    private EventSystem eventSystem;
    private ScheduleSystem scheduleSystem;

    private int option;
    private String username;

    /**
     * Instantiates a new Gui settings controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUISettingsController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.username = username;
        setControllerType(ControllerType.SETTINGS);
        this.userManager = bundle.getUserManager();
        this.messagingSystem = bundle.getMessagingSystem();
        this.userInfoManager = bundle.getUserInfoManager();
        this.contactManager = bundle.getContactManager();
        this.eventSystem = bundle.getEventSystem();
        this.scheduleSystem = bundle.getScheduleSystem();
    }

    @Override
    public AbstractController run() {
        if (option == 1) { //change password
            return new GUISettingsPasswordController(getBundle(), username);
        } else if (option == 2) { //delete account
            setPopNum(2);
            return new GUIMainEntryController(getBundle());
        } else if (option == 3) { //back clicked
            setPopNum(1);
            if (userManager.getUser(username).getUserType().equals(UserTypes.ATTENDEE)) {
                return new GUIAttendeeController(getBundle(), username);
            } else if (userManager.getUser(username).getUserType().equals(UserTypes.ORGANIZER)) {
                return new GUIOrganizerController(getBundle(), username);
            } else if (userManager.getUser(username).getUserType().equals(UserTypes.SPEAKER)){
                return new GUISpeakerController(getBundle(), username);
            }
        }
        return null;
    }

    /**
     * Change password clicked.
     */
    public void changePasswordClicked() {
        option = 1;
    }

    /**
     * Delete account clicked.
     */
    public void deleteAccountClicked() {
        option = 2;
        deleteUser(username);
    }

    /**
     * Back clicked.
     */
    public void backClicked() {
        option = 3;
    }

    /**
     * Gets appearance mode.
     *
     * @return the appearance mode
     */
    public String getAppearanceMode() {
        return userManager.getUser(username).getAppearanceMode();
    }

    /**
     * Gets color choice.
     *
     * @return the color choice
     */
    public String getColorChoice() {
        return userManager.getUser(username).getColor();
    }

    /**
     * Change appearance.
     *
     * @param appearance the appearance
     */
    public void changeAppearance(String appearance) {
        userManager.getUser(username).setAppearance(appearance);
    }

    /**
     * Change color.
     *
     * @param color the color
     */
    public void changeColor(String color) {
        userManager.getUser(username).setColor(color);
    }

    private void deleteUser(String username){
        if (userManager.getUser(username).getUserType().equals(UserTypes.ORGANIZER) || userManager.getUser(username).getUserType().equals(UserTypes.SPEAKER)) {
            deleteAnnouncementsFrom(username);
        }
        deleteMessageHistories(username);
        messagingSystem.deleteMessagesOfUser(username);
        deleteContacts(username);
        if(userInfoManager.getUserType(userManager.getUser(username)) == UserTypes.SPEAKER){
            deleteAllSpokenEvents(username);
        }else{
            unenrollAllEvents(username);
        }
        userManager.deleteUser(username);
    }

    private void deleteMessageHistories(String username){
        Set<String> messagedUsers = userInfoManager.getMessagedUsers(userManager.getUser(username));
        for(String s: messagedUsers){
            userInfoManager.deleteMessageHistoryOfUser(userManager.getUser(s), userManager.getUser(username));
        }
    }

    private void deleteContacts(String username){
        ArrayList<String> usersThisAdded = new ArrayList<>(contactManager.getPersonalContact(userManager.getUser(username)));
        ArrayList<String> usersAddedThis = new ArrayList<>(contactManager.getBeAddedTo(userManager.getUser(username)));

        for(String thisAdded: usersThisAdded){
            contactManager.deletePersonalContact(userManager.getUser(username), userManager.getUser(thisAdded));
        }

        for(String addedThis: usersAddedThis){
            contactManager.deletePersonalContact(userManager.getUser(addedThis), userManager.getUser(username));
        }
    }

    private void unenrollAllEvents(String username){
        ArrayList<Integer> enrolledEvents = new ArrayList<>(userInfoManager.viewEvents(userManager.getUser(username)));
        for(int eventId: enrolledEvents){
            eventSystem.unenroll(userManager.getUser(username), eventSystem.findEventByID(eventId));
        }

    }

    private void deleteAllSpokenEvents(String username){
        ArrayList<Integer> spokenEvents = new ArrayList<>(userInfoManager.viewEvents(userManager.getUser(username)));
        for(int eventId: spokenEvents){
            eventSystem.deleteEvent(eventSystem.findEventByID(eventId), scheduleSystem, userManager);
        }
    }

    private void deleteAnnouncementsFrom(String username) {
        if (userManager.getUser(username).getUserType().equals(UserTypes.ORGANIZER)) {
            userInfoManager.deleteAnnouncementsFromOrganizerUser(userManager.getUser(username), userManager.getAllUsers());
        }
        if (userManager.getUser(username).getUserType().equals(UserTypes.SPEAKER)) {
            userInfoManager.deleteAnnouncementsFromSpeakerUser(userManager.getUser(username), userManager.getAllUsers());
        }
    }
}
