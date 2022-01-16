package entities;

import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;

/**
 * The type Event.
 */
public class Event implements Serializable {
    private Integer roomID;
    private String name;
    // attendeesList is list of attendees usernames
    private ArrayList<String> attendeesList;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int capacity;
    private Integer eventID;
    private Integer numAttendees;


    /**
     * Instantiates a new Event.
     *
     * @param name      the name
     * @param roomID    the room id
     * @param startTime the start time
     * @param endTime   the end time
     * @param eventID   the event id
     * @param capacity  the capacity
     */
    public Event(String name, int roomID, LocalDateTime startTime, LocalDateTime endTime, int eventID, int capacity) {
        this.roomID = roomID;
        this.attendeesList = new ArrayList<>();
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.eventID = eventID;
        this.numAttendees = 0;
        this.capacity = capacity;
    }

    /**
     * Gets attendees list.
     *
     * @return the attendees list
     */
    public ArrayList<String> getAttendeesList() {
        return attendeesList;
    }

    /**
     * Gets capacity.
     *
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Get end time local date time.
     *
     * @return the local date time
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Gets room id.
     *
     * @return the room id
     */
    public Integer getRoomID() {
        return roomID;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets event id.
     *
     * @return the event id
     */
    public Integer getEventID() {
        return eventID;
    }

    /**
     * toString for event
     *
     * @return the string
     */
    public String toString() {
        return "Name: " + this.getName() + ". Start Time: " + this.getStartTime().toLocalTime().toString() + ". End Time: "
                + this.getEndTime().toLocalTime().toString() + ". Room: " + this.getRoomID().toString();
    }

    /**
     * Gets num attendees.
     *
     * @return the num attendees
     */
    public Integer getNumAttendees() {
        return numAttendees;
    }

    /**
     * Gets percent attending.
     *
     * @return the percent attending
     */
    public Double getPercentAttending() {
        if (getNumAttendees() * getCapacity() == 0.0) {
            return 0.0;
        } else {
            Double n1 = new Double(getNumAttendees());
            Double n2 = new Double(getCapacity());
            return (n1/n2);
        }
    }

    public void addAttendee(int n) {
        numAttendees += n;
    }
}
