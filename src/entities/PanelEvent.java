package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The type Panel event.
 */
public class PanelEvent extends Event {

    /**
     * The Speakers.
     */
    ArrayList<String> speakers;

    /**
     * Instantiates a new Event.
     *
     * @param name      the name
     * @param roomID    the room id
     * @param startTime the start time
     * @param endTime   the end time
     * @param eventID   the event id
     * @param speakers  the speakers of the event
     * @param capacity  the capacity
     */
    public PanelEvent(String name, int roomID, LocalDateTime startTime, LocalDateTime endTime, int eventID, ArrayList<String> speakers, int capacity) {
        super(name, roomID, startTime, endTime, eventID, capacity);
        this.speakers = speakers;
    }

    /**
     * Gets speaker usernames
     *
     * @return An arraylist containing the usernames of speakers of event
     */
    public ArrayList<String> getSpeakers() {
        return speakers;
    }

    /**
     * ToString for event
     *
     * @return the string reprentation of panel event
     */
    @Override
    public String toString() {
        String usernames = null;
        for (String s: speakers){
            usernames = s + " ";
        }
        return "Name: " + this.getName() + ". Start Time: " + this.getStartTime().toLocalTime().toString() + ". End Time: "
                + this.getEndTime().toLocalTime().toString() + ". Speakers: " + usernames + ". Room: " + this.getRoomID().toString();
    }
}

