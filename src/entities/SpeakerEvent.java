package entities;

import java.time.LocalDateTime;

/**
 * The type Speaker event.
 */
public class SpeakerEvent extends Event {

    private String speakerUsername;

    /**
     * Instantiates a new Event.
     *
     * @param name            the name
     * @param roomID          the room id
     * @param startTime       the start time
     * @param endTime         the end time
     * @param eventID         the event id
     * @param speakerUsername the username of the speaker
     * @param capacity        the capacity
     */
    public SpeakerEvent(String name, int roomID, LocalDateTime startTime, LocalDateTime endTime, int eventID, String speakerUsername, int capacity) {
        super(name, roomID, startTime, endTime, eventID, capacity);
        this.speakerUsername = speakerUsername;
    }

    /**
     * Gets username of speaker of event
     *
     * @return username of speaker
     */
    public String getSpeakerUsername() {
        return speakerUsername;
    }

    /**
     *
     * To string for SpeakerEvent
     *
     * @return string representation of event
     */
    @Override
    public String toString() {
        return "Name: " + this.getName() + ". Start Time: " + this.getStartTime().toLocalTime().toString() + ". End Time: "
                + this.getEndTime().toLocalTime().toString() + ". Speaker: " + this.getSpeakerUsername() + ". Room: " + this.getRoomID().toString();
    }
}
