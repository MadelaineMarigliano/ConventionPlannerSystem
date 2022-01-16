package controllers;


import com.sun.org.glassfish.external.statistics.Stats;
import use_cases.*;
import views.MessagingView;
import entities.UserTypes;

import java.util.*;

/**
 * The Message Controller.
 */
public class MessageController extends AbstractController {
    private MessagingView view;

    private String username;


    /**
     * Instantiates a new Message controller.
     *
     * @param useCase  UseCaseBundle which contains all use cases
     * @param username the username of the user
     */
    public MessageController(UseCaseBundle useCase, String username) {
        super(useCase);
        this.username = username;
        this.view = new MessagingView();
    }

    @Override
    public AbstractController run() {
        Scanner scanner = new Scanner(System.in);
        UserManager um = getBundle().getUserManager();
        UserInfoManager uim = getBundle().getUserInfoManager();

        if (uim.getUserType(um.getUser(username)).equals(UserTypes.ATTENDEE)) {
            return attendeeRun(scanner);
        } else if (uim.getUserType(um.getUser(username)).equals(UserTypes.SPEAKER)) {
            return speakerRun(scanner);
        } else {
            return organizerRun(scanner);
        }
    }

    private AbstractController attendeeRun(Scanner s) {
        view.displayAttendeeMenu();
        int option;
        option = selectOptionAttendee(s);
        switch (option) {
            case 1: // send msg
                sendMessage(s);
                return null;
//            case 2: //view msg
//                viewRecentMessage(s);
//                return null;
            case 2: // view unread messages
                viewUnreadMessages(s);
                return null;
            case 3: //view msg hist
                viewMessageHistory(s);
                return null;
            case 4: // return
                setPopNum(1);
                return null;
        }
        return null;
    }

    private AbstractController speakerRun(Scanner s) {
        view.displaySpeakerMenu();
        int option;
        option = selectOptionSpeaker(s);
        switch (option) {
            case 1: // send msg
                sendMessage(s);
                return null;
//            case 2: //view msg
//                viewRecentMessage(s);
//                return null;
            case 2: // view unread messages
                viewUnreadMessages(s);
                return null;
            case 3: //view msg hist
                viewMessageHistory(s);
                return null;
            case 4: // msg all att of events
                eventMessenger(s);
                return null;
            case 5: // msg mltp events
                messageEvents(s);
                return null;
            case 6: // msg all events
                messageAllEvents(s);
                return null;
            case 7: // return
                setPopNum(1);
                return null;
        }
        return null;
    }

    private AbstractController organizerRun(Scanner s) {
        view.displayOrganizerMenu();
        int option;
        option = selectOptionOrganizer(s);
        switch (option) {
            case 1: // send msg
                sendMessage(s);
                return null;
//            case 2: //view msg
//                viewRecentMessage(s);
//                return null;
            case 2: // view unread messages
                viewUnreadMessages(s);
                return null;
            case 3: //view msg hist
                viewMessageHistory(s);
                return null;
            case 4: // message all speakers
                messageAllSpeakers(s);
                return null;
            case 5: // message all attendees
                messageAllAttendees(s);
                return null;
            case 6: // return
                setPopNum(1);
                return null;
        }
        return null;
    }

    private int selectOptionAttendee(Scanner scanner) {
        String input;
        do {
            view.optionPrompt();
            input = scanner.nextLine();
            switch (input.trim()) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
            }
            view.optionError();
        } while (true);
    }

    private int selectOptionSpeaker(Scanner scanner) {
        String input;
        do {
            view.optionPrompt();
            input = scanner.nextLine();
            switch (input.trim()) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
                case "5":
                    return 5;
                case "6":
                    return 6;
                case "7":
                    return 7;
            }
            view.optionError();
        } while (true);
    }

    private int selectOptionOrganizer(Scanner scanner) {
        String input;
        do {
            view.optionPrompt();
            input = scanner.nextLine();
            switch (input.trim()) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
                case "5":
                    return 5;
                case "6":
                    return 6;
            }
            view.optionError();
        } while (true);
    }


    /**
     * Sends message to user
     *
     * @param scanner the scanner
     */
    public void sendMessage(Scanner scanner) {
        MessagingSystem ms = getBundle().getMessagingSystem();
        UserManager um = getBundle().getUserManager();
        StatsManager sm = getBundle().getStatsManager();

        String recipient;
        String message;
        view.messageRecipientPrompt();
        recipient = scanner.nextLine().trim();
        view.messageContentPrompt();
        message = scanner.nextLine().trim();

        try {
            ms.sendMessage(um.getUser(username), um.getUser(recipient), message);
            sm.messagesSent(1);
        } catch (Exception e) {
            view.printException(e);
        }

    }

//    /**
//     * View most recent message from user.
//     *
//     * @param s the scanner
//     */
//    public void viewRecentMessage(Scanner s) {
//        MessagingSystem ms = getBundle().getMessagingSystem();
//        UserManager um = getBundle().getUserManager();
//
//        String from;
//        view.viewMessageFromPrompt();
//        from = s.nextLine().trim();
//
//        try {
//            String message;
//            message = ms.viewMessage(um.getUser(username), um.getUser(from));
//            view.viewMessage(message);
//        } catch (Exception e) {
//            view.printException(e);
//        }
//
//    }

    /**
     * View unread messages.
     *
     * @param s the s
     */
    public void viewUnreadMessages(Scanner s) {
        MessagingSystem ms = getBundle().getMessagingSystem();
        UserManager um = getBundle().getUserManager();

        try {
            ms.hasUnreadMessages(um.getUser(username));

            String from;
            view.viewUnreadMessagesFromPrompt();
            from = s.nextLine().trim();

            try {
                ArrayList<String> messages;
                messages = ms.getUnreadMessages(um.getUser(username), um.getUser(from));
                for (String message : messages) {
                    view.viewMessage(message);
                }
            } catch (Exception e) {
                view.printException(e);
            }
        } catch (Exception e) {
            view.printException(e);
        }
    }

    /**
     * View message history with user.
     *
     * @param s the scanner
     */
    public void viewMessageHistory(Scanner s) {
        MessagingSystem ms = getBundle().getMessagingSystem();
        UserManager um = getBundle().getUserManager();
        UserInfoManager uim = getBundle().getUserInfoManager();

        String from;
        view.viewMessageFromPrompt();
        from = s.nextLine().trim();

        try {
            ArrayList<Integer> messages = uim.getMessageIds(um.getUser(username), um.getUser(from));
            ArrayList<Integer> messagesCopy = (ArrayList<Integer>) messages.clone();
            for (Integer i : messagesCopy) {
                String message = ms.getMessageString(i);
                view.viewMessage(message);
            }

            // viewMessageHistory shows all messages with from so it's equivalent to viewUnreadMessages
            // next 3 lines updates unreadMessages and unreadMessagesByUsername
            // 'unread' messages are being read
            Integer unread = um.getUser(username).getUnreadMessageCountFrom(um.getUser(from));
            um.getUser(username).deleteUnreadMessageCountFrom(um.getUser(from));
//            um.getUser(username).deleteUnreadMessage(unread);
        } catch (Exception e) {
            view.printException(e);
        }

    }


    /**
     * Message all attendees in program.
     *
     * @param s the scanner
     */
    public void messageAllAttendees(Scanner s) {
        MessagingSystem ms = getBundle().getMessagingSystem();
        UserManager um = getBundle().getUserManager();
        StatsManager sm = getBundle().getStatsManager();

        String message;
        view.messageContentPrompt();
        message = s.nextLine().trim();

        try {
            ms.massMessage(um.getUser(username), um.getAllAttendeesAndOrganizers(), message);
            int n = um.getAllAttendeesAndOrganizers().size();
            sm.messagesSent(n);
        } catch (Exception e) {
            view.printException(e);
        }
    }

    /**
     * Message all speakers in program.
     *
     * @param s the scanner
     */
    public void messageAllSpeakers(Scanner s) {
        MessagingSystem ms = getBundle().getMessagingSystem();
        UserManager um = getBundle().getUserManager();
        StatsManager sm = getBundle().getStatsManager();

        String message;
        view.messageContentPrompt();
        message = s.nextLine().trim();
        try {
            ms.massMessage(um.getUser(username), um.getAllSpeakers(), message);
            int n = um.getAllSpeakers().size();
            sm.messagesSent(n);
        } catch (Exception e) {
            view.printException(e);
        }
    }

    /**
     * Event Messenger.
     *
     * @param s the scanner
     */
    public void eventMessenger(Scanner s) {
        view.eventPrompt();
        String event = s.nextLine().trim();
        view.messageContentPrompt();
        String message = s.nextLine().trim();
        eventMessaging(event, message);
    }

    private void eventMessaging(String eventName, String message) {
        EventSystem es = getBundle().getEventSystem();
        MessagingSystem ms = getBundle().getMessagingSystem();
        UserManager um = getBundle().getUserManager();
        StatsManager sm = getBundle().getStatsManager();

        try{
            ArrayList<String> attendees = es.findEventByName(eventName).getAttendeesList();
            if (attendees.isEmpty()) {
                view.noAttendees(eventName);
            }
            ms.massMessage(um.getUser(username), um.getUsers(attendees), message);
            int n = um.getUsers(attendees).size();
            sm.messagesSent(n);
        }catch(Exception e){
            view.printException(e);
        }



    }


    /**
     * Message multiple events.
     *
     * @param s the scanner
     */
    public void messageEvents(Scanner s) {
        view.listofEventsPrompt();
        String events;
        events = s.nextLine().trim();
        view.messageContentPrompt();
        String message;
        message = s.nextLine().trim();

        String[] e = events.split(",");
        for (String s1 : e) {
            eventMessaging(s1.trim(), message);
        }
    }

    /**
     * Message all events.
     *
     * @param s the scanner
     */
    public void messageAllEvents(Scanner s) {
        EventSystem es = getBundle().getEventSystem();
        UserManager um = getBundle().getUserManager();
        UserInfoManager uim = getBundle().getUserInfoManager();

        view.messageContentPrompt();
        String message = s.nextLine().trim();
        try {
            for (Integer i : uim.viewEvents(um.getUser(username))) {
                eventMessaging(es.findEventByID(i).getName(), message);
            }
        } catch (Exception e) {
            view.printException(e);
        }
    }

}
