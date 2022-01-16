package controllers;


import entities.Event;
import views.EventView;
import entities.UserTypes;
import use_cases.EventSystem;
import use_cases.UserManager;
import use_cases.UserInfoManager;


import java.io.File;
import java.security.InvalidParameterException;
import java.util.*;


/**
 * Controller for viewing and managing events.
 */
public class EventController extends AbstractController {
    private EventView view;
    private String username;

    /**
     * Instantiates a new Event controller.
     *
     * @param bundle   the bundle
     * @param Username the username
     */
    public EventController(UseCaseBundle bundle, String Username) {
        super(bundle);
        this.username = Username;
        this.view = new EventView();
    }

    @Override
    public AbstractController run() {
        Scanner scanner = new Scanner(System.in);
        if (getBundle().getUserManager().getUser(username).getUserType().equals(UserTypes.ATTENDEE)) {
            attendeeRun(scanner);
            return null;
        }

        if (getBundle().getUserManager().getUser(username).getUserType().equals(UserTypes.SPEAKER)) {
            speakerRun(scanner);
            return null;
        }

        if (getBundle().getUserManager().getUser(username).getUserType().equals(UserTypes.ORGANIZER)) {
            organizerRun(scanner);
            return null;
        }
        return null;
    }

    private void speakerRun(Scanner scanner) {
        view.displaySpeakerMenu();
        int option;
        option = selectOptionSpeaker(scanner);
        switch (option) {
            case 1: // view events they are speaking at
                viewEvents(username);
                setPopNum(0);
                break;
            case 2: // return
                setPopNum(1);
                break;
        }
    }

    private void organizerRun(Scanner scanner) {
        view.displayOrganizerMenu();
        int option;
        option = selectOptionOrganizer(scanner);
        switch (option) {
            case 1: // enrol
                enrol(scanner);
                setPopNum(0);
                break;
            case 2: //unenrol
                unenrol(scanner);
                setPopNum(0);
                break;
            case 3: //view events you are signed up for
                viewEvents(username);
                setPopNum(0);
                break;
            case 4: //view event schedule to HTML
                viewEventSchedule();
                setPopNum(0);
                break;
            case 5: //view event schedule in Text
                viewTextSchedule();
                setPopNum(0);
                break;
            case 6: // return
                setPopNum(1);
                break;
        }
    }

    private void attendeeRun(Scanner scanner) {
        view.displayAttendeeMenu();
        int option;
        option = selectOptionAttendee(scanner);
        switch (option) {
            case 1: // Enrol in event
                enrol(scanner);
                setPopNum(0);
                break;
            case 2: //Unenrol in event
                unenrol(scanner);
                setPopNum(0);
                break;
            case 3: //view event
                viewEvents(username);
                setPopNum(0);
                break;
            case 4: // return
                setPopNum(1);
                break;
        }
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
     * View event schedule.
     */
    public void viewEventSchedule() {
        File file = getBundle().getScheduleSystem().CreateHTML();
        view.printSchedule(file);
    }

    public void viewTextSchedule() {
        HashMap<Integer, ArrayList<Event>> schedule = getBundle().getScheduleSystem().getSchedule();
        view.printScheduleToText(schedule);
    }

    /**
     * Enrol.
     *
     * @param scanner the scanner
     */
    public void enrol(Scanner scanner) {
        String eventName;
        view.eventNamePrompt();
        eventName = scanner.nextLine().trim();
        EventSystem es = getBundle().getEventSystem();
        try {
            String type;
            if (getBundle().getUserManager().getUser(username).getUserType().equals(UserTypes.VIP)) {
                type = "vip";
            } else {
                type = "att";
            }
            checkOccupancy(eventName, type);
            es.enroll(getBundle().getUserManager().getUser(username), es.findEventByName(eventName));
        } catch (Exception e) {
            view.printException(e);
        }
    }

    /**
     * Unenrol.
     *
     * @param scanner the scanner
     */
    public void unenrol(Scanner scanner) {
        String eventName;
        view.eventNamePrompt();
        eventName = scanner.nextLine().trim();
        EventSystem es = getBundle().getEventSystem();
        try {
            getBundle().getEventSystem().unenroll(getBundle().getUserManager().getUser(username), es.findEventByName(eventName));
        } catch (Exception e) {
            view.printException(e);
        }
    }

    /**
     * View events.
     *
     * @param username the username
     */
    public void viewEvents(String username) {
        ArrayList<Integer> eventList;
        try {
            UserManager um = getBundle().getUserManager();
            EventSystem es = getBundle().getEventSystem();
            UserInfoManager uim = getBundle().getUserInfoManager();
            eventList = uim.viewEvents(um.getUser(username));
            for (Integer i : eventList) {
                view.printEvent(es.findEventByID(i).getStartTime(), es.findEventByID(i).getEndTime(),
                        es.findEventByID(i).getName(), es.findEventByID(i).getRoomID());
            }
        } catch (Exception e) {
            view.printException(e);
        }

    }

    private void checkOccupancy(String event, String type) throws InvalidParameterException {
        EventSystem es = getBundle().getEventSystem();
        int occupancy = 0;
        for (String u : es.findEventByName(event).getAttendeesList()) {
            occupancy = occupancy + getBundle().getUserManager().calculateOccupancy(u);
        }

        // if type is vip and occupancy > capacity - 2 throw
        if (type.equals("vip") && occupancy > es.findEventByName(event).getCapacity() - 2) {
            throw new InvalidParameterException("This event is full");
        }
        if (occupancy == es.findEventByName(event).getCapacity()) {
            throw new InvalidParameterException("This event is full");
        }
    }
}


