package views;

import java.security.InvalidParameterException;

/**
 * The Messaging presenter.
 */
public class MessagingView extends AbstractView {
    /**
     * Displays attendee menu.
     */
    public void displayAttendeeMenu() {
        System.out.println("1. Send message");
//        System.out.println("2. View message");
        System.out.println("2. View unread messages");
        System.out.println("3. View messaging history");
        System.out.println("4: Return");
    }

    /**
     * Displays Speaker Menu
     */
    public void displaySpeakerMenu() {
        System.out.println("1. Send message");
//        System.out.println("2. View message");
        System.out.println("2. View unread messages");
        System.out.println("3. View messaging history");
        System.out.println("4. Message all attendees of event");
        System.out.println("5. Message all attendees of multiple events");
        System.out.println("6. Message attendees of all events you gave talk to");
        System.out.println("7. Return");
    }

    /**
     * Displays organizer menu.
     */
    public void displayOrganizerMenu() {
        System.out.println("1. Send message");
//        System.out.println("2. View message");
        System.out.println("2. View unread messages");
        System.out.println("3. View messaging history");
        //System.out.println("4. Reply message");
        System.out.println("4. Message all speakers");
        System.out.println("5. Message all attendees");
        System.out.println("6. Return");
    }

    /**
     * Option prompt.
     */
    public void optionPrompt() {
        System.out.println("Select an option:");
    }

    /**
     * Wrong option error.
     */
    public void optionError() {
        System.out.println("Please enter a number representing one of the options.");
    }

    /**
     * Enter User to Message prompt
     */
    public void messageRecipientPrompt() {
        System.out.println("Enter the username of the user you would like to message:");
    }

    /**
     * Enter content of message prompt
     */
    public void messageContentPrompt() {
        System.out.println("Enter the message you would like to send:");
    }

    /**
     * Enter user whose message to view
     */
    public void viewMessageFromPrompt() {
        System.out.println("Enter the username of the user whose messages you would like to view:");
    }

    /**
     * View unread messages from prompt.
     */
    public void viewUnreadMessagesFromPrompt() {
        System.out.println("Enter the username of the user whose unread messages you would like to view:");
    }

    /**
     * View message.
     *
     * @param message the message
     */
    public void viewMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints list of prompt
     */
    public void listofEventsPrompt() {
        System.out.println("Enter the names of the events whose attendees you would like to message separated by commas:");
    }

    /**
     * Event message prompt
     */
    public void eventPrompt() {
        System.out.println("Enter the name of the event whose attendees you would like to message:");
    }

    /**
     * Prints event has no attendees
     *
     * @param name name of event
     */
    public void noAttendees(String name) {
        System.out.println(name + " has no attendees. ");
    }
}
