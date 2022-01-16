package controllers;


import views.ScheduleView;
import use_cases.EventSystem;
import use_cases.UserManager;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * The type Schedule controller.
 */
public class ScheduleController extends AbstractController {
    private ScheduleView view;

    /**
     * Instantiates a new Schedule controller.
     *
     * @param bundle the bundle
     */
    public ScheduleController(UseCaseBundle bundle) {
        super(bundle);
        this.view = new ScheduleView();
    }

    @Override
    public AbstractController run() {
        Scanner scanner = new Scanner(System.in);
        view.displayOrganizerMenu();
        int option;
        option = selectOption(scanner);
        switch (option) {
            case 1: // create an event
                createEvent(scanner);
                setPopNum(0);
                return null;
            case 2: // delete an event
                deleteEvent(scanner);
                setPopNum(0);
                return null;
            case 3: // return
                setPopNum(1);
                return null;
        }
        return null;
    }

    private int selectOption(Scanner scanner) {
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
            }
            view.optionError();
        } while (true);
    }

    /**
     * Create event.
     *
     * @param scanner the scanner
     */
    public void createEvent(Scanner scanner) {
        UserManager um = getBundle().getUserManager();
        EventSystem es = getBundle().getEventSystem();

        String eventName;
        int roomID;
        int capacity;

        view.eventNamePrompt();
        eventName = scanner.nextLine().trim();

        view.speakerPrompt();
        ArrayList<String> speakerUsernames = new ArrayList<>(Arrays.asList(scanner.nextLine().split(",")));
        //ArrayList<String> speakerUsernames = new ArrayList<>();
        /*try {
            for (String s : Usernames) {
                speakerUsernames.add(s.trim());
            }
        }
        catch (Exception e){
            System.out.println("For loop error");
            System.out.println(e);
        }*/

        try {
            roomID = getRoom(scanner);
            capacity = getCapacity(scanner);
            LocalDateTime startTime = getStartTime(scanner);
            LocalDateTime endTime = getEndTime(scanner);
            es.validEventName(eventName);
            getBundle().getEventSystem().createEvent(eventName, roomID, startTime, endTime,
                    getBundle().getScheduleSystem(), speakerUsernames, capacity, getBundle().getUserManager());
            view.eventCreated();
        } catch (Exception e) {
            view.printException(e);
        }
    }

    private int getRoom(Scanner scanner) throws InvalidParameterException {
        try {
            int roomID;
            view.roomPrompt();
            roomID = scanner.nextInt();
            return roomID;
        } catch (Exception e) {
            throw new InvalidParameterException("Please enter an integer representing the room number");
        }
    }

    private int getCapacity(Scanner scanner) throws InvalidParameterException{
        try {
            int capacity;
            view.capacityPrompt();
            capacity = scanner.nextInt();
            return capacity;
        } catch (Exception e) {
            throw new InvalidParameterException("Please enter an integer representing the capacity");
        }
    }


    private LocalDateTime getStartTime(Scanner scanner) throws InvalidParameterException {
        int hourStart;
        int minStart;

        view.hourStartPrompt();
        try {
            hourStart = scanner.nextInt();
        } catch (Exception e) {
            throw new InvalidParameterException("Please enter an integer");
        }

        view.minPrompt();
        try {
            minStart = scanner.nextInt();
        } catch (Exception e) {
            throw new InvalidParameterException("Please enter an integer");
        }

        return getBundle().getEventSystem().ValidTime(hourStart, minStart);
    }

    private LocalDateTime getEndTime(Scanner scanner) throws InvalidParameterException {
        int hourEnd;
        view.hourEndPrompt();
        try {
            hourEnd = scanner.nextInt();
        } catch (Exception e) {
            throw new InvalidParameterException("Please enter an integer");
        }
        int minEnd;
        view.minPrompt();
        try {
            minEnd = scanner.nextInt();
        } catch (Exception e) {
            throw new InvalidParameterException("Please enter an integer");
        }
        return getBundle().getEventSystem().ValidTime(hourEnd, minEnd);
    }

    /**
     * Delete event.
     *
     * @param scanner the scanner
     */
    public void deleteEvent(Scanner scanner) {
        String eventName;
        view.eventNamePrompt();
        eventName = scanner.nextLine().trim();
        try {
            getBundle().getEventSystem().findEventByName(eventName);
            getBundle().getEventSystem().deleteEvent(getBundle().getEventSystem().findEventByName(eventName),
                    getBundle().getScheduleSystem(),
                    getBundle().getUserManager());
            view.eventDeleted();
        } catch (Exception e) {
            view.printException(e);
        }
    }

}
