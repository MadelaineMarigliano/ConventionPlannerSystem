package GUIcontrollers.announcement;

import controllers.AbstractController;
import controllers.ControllerType;
import controllers.UseCaseBundle;
import entities.UserTypes;
import use_cases.EventSystem;
import use_cases.MessagingSystem;
import use_cases.UserManager;

import java.util.ArrayList;

/**
 * The type Gui announcement controller.
 */
public class GUIAnnouncementController extends AbstractController {
    private UserManager userManager;

    private int option;
    private String username;
    private String threadWith;
    private MessagingSystem messagingSystem;
    private EventSystem eventSystem;

    /**
     * Instantiates a new Gui announcement controller.
     *
     * @param bundle     the bundle
     * @param username   the username
     * @param threadWith the thread with
     */
    public GUIAnnouncementController(UseCaseBundle bundle, String username, String threadWith) {
        super(bundle);
        this.userManager = bundle.getUserManager();
        this.messagingSystem = bundle.getMessagingSystem();
        this.eventSystem = bundle.getEventSystem();
        this.username = username;
        this.threadWith = threadWith;
        setControllerType(ControllerType.ANNOUNCEMENT);
    }

    @Override
    public AbstractController run() {
        if (option == 1) { //back
            setPopNum(1);
        }
        return null;
    }

    /**
     * Send clicked.
     *
     * @param announcement the announcement
     */
    public void sendClicked(String announcement) {
        sendAnnouncement(announcement);
    }

    /**
     * Back clicked.
     */
    public void backClicked() {
        option = 1;
    }

    private void sendAnnouncement(String message) {
        if (threadWith.equals("ATTENDEES") || threadWith.equals("ORGANIZERS")) {
            messagingSystem.sendUserTypeAnnouncement(userManager.getUser(username), userManager.getAllAttendeesAndOrganizers(), message, threadWith);
        } else if (threadWith.equals("SPEAKERS")) {
            messagingSystem.sendUserTypeAnnouncement(userManager.getUser(username), userManager.getAllSpeakers(), message, threadWith);
        } else {
            ArrayList<String> eventAttendees = eventSystem.findEventByName(threadWith).getAttendeesList();
            messagingSystem.sendEventAnnouncement(userManager.getUser(username), userManager.getUser(username), message, threadWith);
            for (String eventAttendee : eventAttendees) {
                messagingSystem.sendEventAnnouncement(userManager.getUser(username), userManager.getUser(eventAttendee), message, threadWith);
            }
        }
    }

    /**
     * Gets announcements for.
     *
     * @return the announcements for
     */
    public ArrayList<Integer> getAnnouncementsFor() {
        if (threadWith.equals("ATTENDEES") || threadWith.equals("SPEAKERS")) {
            return userManager.getUser(username).getUserAnnouncementsByType(threadWith);
        } else {
            return userManager.getUser(username).getEventAnnouncementsByEvent(threadWith);
        }
    }

    /**
     * Gets announcement.
     *
     * @param messageId the message id
     * @return the announcement
     */
    public String getAnnouncement(Integer messageId) {
        return messagingSystem.getMessage(messageId).getMessage();
    }

    /**
     * Gets sender.
     *
     * @param messageId the message id
     * @return the sender
     */
    public String getSender(Integer messageId) {
        return messagingSystem.getMessage(messageId).getSentFrom();
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
     * Gets thread with.
     *
     * @return the thread with
     */
    public String getThreadWith() {
        return threadWith;
    }

    /**
     * Is organizer messaging speaker boolean.
     *
     * @return the boolean
     */
    public boolean isOrganizerMessagingSpeakers() {
        return userManager.getUser(username).getUserType().equals(UserTypes.ORGANIZER) && threadWith.equals("SPEAKERS");
    }

    public boolean isOrganizerMessagingAttendees() {
        return userManager.getUser(username).getUserType().equals(UserTypes.ORGANIZER) && threadWith.equals("ATTENDEES");
    }

    /**
     * Is speaker messaging attendees boolean.
     *
     * @return the boolean
     */
    public boolean isSpeakerMessagingAttendees() {
        return userManager.getUser(username).getUserType().equals(UserTypes.SPEAKER) && threadWith.equals("ATTENDEES");
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
