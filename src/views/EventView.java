package views;

import entities.Event;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

/**
 * The type Event presenter.
 */
public class EventView extends AbstractView {
    /**
     * Display attendee menu.
     */
    public void displayAttendeeMenu() {
        System.out.println("1. Enrol in an event");
        System.out.println("2. Unenrol in an event");
        System.out.println("3. View events");
        System.out.println("4: Return");
    }

    /**
     * Display organizer menu.
     */
    public void displayOrganizerMenu() {
        System.out.println("1. Enrol in an event");
        System.out.println("2. Unenrol in an event");
        System.out.println("3. View events you are signed up for");
        System.out.println("4. View event schedule to HTML");
        System.out.println("5. View event schedule to text");
        System.out.println("6. Return");
    }

    /**
     * Display speaker menu.
     */
    public void displaySpeakerMenu() {
        System.out.println("1. View events you are speaking at");
        System.out.println("2. Return");
    }

    /**
     * Option prompt.
     */
    public void optionPrompt(){
        System.out.println("Select an option:");
    }

    /**
     * Option error.
     */
    public void optionError(){
        System.out.println("Please enter a number representing one of the options.");
    }

    /**
     * Print event.
     *
     * @param startTime the start time
     * @param endTime   the end time
     * @param eventName the event name
     * @param roomID    the room id
     */
    public void printEvent(LocalDateTime startTime, LocalDateTime endTime, String eventName, Integer roomID) {
        System.out.println("Event name: " + eventName + ", Starts at: " + startTime.toString() + ", Ends at: " +
                endTime.toString() + ", In room: " + roomID.toString());
    }

    /*public void printSchedule(HashMap<Integer, ArrayList<Event>> schedule) {
        List<Integer> sorted = new ArrayList<>(schedule.keySet());
        Collections.sort(sorted);
        for (Integer hour : sorted) {
            System.out.println("Events at " + hour.toString() + ":00 :" + schedule.get(hour).toString());
        }
    }
*/

    /**
     * Prints schedule
     *
     * @param file the file
     */
    public void printSchedule(File file) {
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Event name prompt.
     */
    public void eventNamePrompt() {System.out.println("Enter the name of the event: ");}

    public void printScheduleToText(HashMap<Integer, ArrayList<Event>> schedule) {
        List<Integer> sorted = new ArrayList<>(schedule.keySet());
        Collections.sort(sorted);
        for (Integer hour : sorted) {
            System.out.println("Events at " + hour.toString() + ":00 :" + schedule.get(hour).toString());
        }
    }
}
