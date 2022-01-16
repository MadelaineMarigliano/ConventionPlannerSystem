package use_cases;

import entities.*;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * The type Event system.
 */
public class EventSystem implements Serializable {
    private ArrayList<Event> eventList;
    private int size = 1;
    private Integer eventStart = 0; //the first hour events can start on
    private Integer eventEnd = 23; //the last hour events can start on


    /**
     * Instantiates a new Event system.
     */
    public EventSystem() {
        this.eventList = new ArrayList<>();
    }

    /**
     * Return the Event attributed to eventID.
     *
     * @param eventID the event id
     * @return the event
     * @throws NullPointerException the null pointer exception
     */
    public Event findEventByID(Integer eventID) throws NullPointerException {
        for (Event e : eventList) {
            if (e.getEventID().equals(eventID)) {
                return e;
            }
        }
        throw new NullPointerException("This event does not exist");
    }

    /**
     * Return the Event attributed to name.
     *
     * @param name the name
     * @return the event
     * @throws NullPointerException the null pointer exception
     */
    public Event findEventByName(String name) throws NullPointerException {
        for (Event e : eventList) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        throw new NullPointerException("This event does not exist");
    }

    /**
     * Determines whether User a is eligible to enroll in Event e.
     *
     * @param a the user
     * @param e the event
     * @return the enrolment status
     */
    public boolean canEnroll(User a, Event e) {
        boolean s1 = false;
        boolean s2 = true;
        boolean s3 = false;
        if (!e.getAttendeesList().contains(a.getUsername())) {
            s1 = true;
        }
        int i = 0;
        while (i < a.getMyEvents().size() && s2) {
            LocalDateTime os = findEventByID(a.getMyEvents().get(i)).getStartTime();
            LocalDateTime oend = findEventByID(a.getMyEvents().get(i)).getEndTime();
            LocalDateTime estart = e.getStartTime();
            LocalDateTime eend = e.getEndTime();
            boolean c1 = os.isAfter(estart) && os.isBefore(eend) && oend.isAfter(eend);
            boolean c1a = os.isEqual(estart) && oend.isAfter(eend);
            boolean c2 = os.isBefore(estart) && oend.isAfter(estart) && oend.isBefore(eend);
            boolean c2a = os.isBefore(estart) && oend.isEqual(eend);
            boolean c3 = os.isBefore(estart) && oend.isAfter(eend);
            boolean c4 = os.isAfter(estart) && os.isBefore(eend) && oend.isAfter(estart) && oend.isBefore(eend);
            boolean c4a = os.isEqual(estart) && oend.isAfter(estart) && oend.isBefore(eend);
            boolean c4b = os.isAfter(estart) && os.isBefore(eend) && oend.isEqual(eend);
            boolean c4c = os.isEqual(estart) && oend.isEqual(eend);
            if (c1 || c1a || c2 || c2a || c3 || c4 || c4a || c4b || c4c) {
                s2 = false;
            }
            i++;
        }
        if (e.getAttendeesList().size() + a.calculateOccupancy() <= e.getCapacity()) {
            s3 = true;
        }
        return s1 && s2 && s3;
    }

    /**
     * Creates an event running from s to e called name with speaker speaking,
     * located in the room identified by RoomID.
     *
     * @param name           the name
     * @param RoomID         the room id
     * @param s              the start time of the event
     * @param e              the end time of the event
     * @param scheduleSystem the schedule system
     * @param speakers       the speakers
     * @param capacity       the capacity
     * @param userManager    the user manager
     * @throws InvalidParameterException the invalid parameter exception
     */
    public void createEvent(String name, int RoomID, LocalDateTime s, LocalDateTime e,
                            ScheduleSystem scheduleSystem, ArrayList<String> speakers, int capacity, UserManager userManager) throws InvalidParameterException {
        if (!roomAvailable(RoomID, s, e, scheduleSystem)) {
            throw new InvalidParameterException("The room is not available or does not exist"); }


        if (speakers.get(0).equals("")) {
            Event event = new Event(name, RoomID, s, e, size, capacity);
            size++;
            eventList.add(event);
            scheduleSystem.addEvent(event);


        } else if (speakers.size() == 1) {
            if (speakerAvailable(userManager.getUser(speakers.get(0)), s, e)) {
                throw new InvalidParameterException("This speaker is not available");
            }
            try {
                ValidSpeaker(speakers.get(0), userManager);
            } catch (Exception l) {
                throw new InvalidParameterException(l.getMessage());
            }
            SpeakerEvent event = new SpeakerEvent(name, RoomID, s, e, size, speakers.get(0), capacity);
            userManager.getUser(speakers.get(0)).addEvent(size);
            size++;
            eventList.add(event);
            scheduleSystem.addEvent(event);


        } else {
            for (String s1 : speakers) {
                if (speakerAvailable(userManager.getUser(s1), s, e)) {
                    throw new InvalidParameterException("This speaker is not available");
                }
            }
            for (String s1 : speakers) {
                userManager.getUser(s1).addEvent(size);
            }
            PanelEvent event = new PanelEvent(name, RoomID, s, e, size, speakers, capacity);
            size++;
            eventList.add(event);
            scheduleSystem.addEvent(event);
        }
        System.out.println("event created successfully");
    }


    /**
     * Determines whether a room, identified by roomID,
     * is available for use between s and e with the given scheduleSystem.
     *
     * @param roomID         the room id
     * @param s              the start time of the room booking
     * @param e              the end of the room booking
     * @param scheduleSystem the schedule system
     * @return the availability of the room
     */
    private boolean roomAvailable(int roomID, LocalDateTime s, LocalDateTime e, ScheduleSystem scheduleSystem) {
        if (!scheduleSystem.getScheduleByRoom().containsKey(roomID)) {
            return false;
        }
        for (Event event : scheduleSystem.getScheduleByRoom().get(roomID).get(s.getHour())) {
            if (overlap(s, e, event.getStartTime(), event.getEndTime())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines whether speaker is available to speak between s and e.
     *
     * @param speaker the speaker
     * @param s       the start of the speaking time
     * @param e       the end of the speaking time
     * @return the availability of the speaker
     */
    private boolean speakerAvailable(User speaker, LocalDateTime s, LocalDateTime e) {
        for (int i : speaker.getMyEvents()) {
            Event event = this.findEventByID(i);
            if (overlap(s, e, event.getStartTime(), event.getEndTime())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether the time interval bounded by s and e overlaps with
     * the time interval bounded by otherS and otherE.
     *
     * @param s      the start time of one of the time intervals
     * @param e      the end time of one of the time intervals
     * @param otherS the start time of the other time interval
     * @param otherE the end time of the other time interval
     * @return the overlapping status of the two time intervals
     */
    private boolean overlap(LocalDateTime s, LocalDateTime e, LocalDateTime otherS, LocalDateTime otherE) {
        if (s.isAfter(otherS) && s.isBefore(otherE)) {
            return true;
        }
        if (e.isAfter(otherS) && e.isBefore(otherE)) {
            return true;
        }
        return s.getHour() == otherS.getHour();
    }

    /**
     * Deletes Event e from the ScheduleSystem s and UserManager userManager.
     *
     * @param e           the event
     * @param s           the schedule system
     * @param userManager the user manager
     * @throws InvalidParameterException the invalid parameter exception
     */
    public void deleteEvent(Event e, ScheduleSystem s, UserManager userManager) {
        this.eventList.remove(e);
        s.removeEvent(e);
        for (String attendee : e.getAttendeesList()) {
            User a = userManager.getUser(attendee);
            a.getMyEvents().remove(e.getEventID());
        }
        // User speaker = userManager.getUser(e.getSpeakerUsername());
        //speaker.getMyEvents().remove(e.getEventID());
    }

    /**
     * Enrolls User a into Event e.
     *
     * @param a the user
     * @param e the event
     * @throws InvalidParameterException the invalid parameter exception
     */
    public void enroll(User a, Event e) throws InvalidParameterException {

        if (canEnroll(a, e)) {
            e.getAttendeesList().add(a.getUsername());
            a.addEvent(e.getEventID());
            e.addAttendee(a.calculateOccupancy());
        }
    }

    /**
     * Unenrolls User a from Event e.
     *
     * @param a the User
     * @param e the event
     */
    public void unenroll(User a, Event e) {
        if (e.getAttendeesList().contains(a.getUsername())) {
            e.getAttendeesList().remove(a.getUsername());
            a.removeEvent(e.getEventID());
        }
    }

    /**
     * Returns the time where the hour is given by hour
     * and the minute is given by min.
     *
     * @param hour the hour
     * @param min  the min
     * @return the time
     * @throws InvalidParameterException the invalid parameter exception
     */
    public LocalDateTime ValidTime(Integer hour, Integer min) throws InvalidParameterException {
        if (!(eventStart <= hour && hour <= eventEnd)) {
            throw new InvalidParameterException("Please enter an hour between " + eventStart.toString()
                    + " and " + eventEnd.toString());
        }
        if (!(0 <= min && min <= 59)) {
            throw new InvalidParameterException("Please enter a valid minute");
        }
        return LocalDate.now().atTime(hour, min, 0);
    }


    /**
     * Checks if speakerUsername is a valid username for a Speaker under UserManager u.
     * If speakerUsername is valid, return the Speaker.
     *
     * @param speakerUsername the username of speaker
     * @param u               the UserManager
     * @throws InvalidParameterException the invalid parameter exception
     */
    public void ValidSpeaker(String speakerUsername, UserManager u) throws InvalidParameterException {
        try {
            u.getUser(speakerUsername);
        } catch (Exception e) {
            throw new InvalidParameterException("The speaker" + speakerUsername + "does not exist");
        }
    }

    /**
     * Changes the earliest hour that events can begin to eventStart
     * and the latest hour events can end to eventEnd under ScheduleSystem s.
     *
     * @param eventStart the first hour events can start at
     * @param eventEnd   the last hour events can end at
     * @param s          the schedule system
     */
    public void changeHours(int eventStart, int eventEnd, ScheduleSystem s) {
        this.eventEnd = eventEnd;
        this.eventStart = eventStart;
        s.changeHours(eventStart, eventEnd);
    }

    /**
     * Checks if eventName is empty.
     *
     * @param eventName the event name
     * @throws InvalidParameterException the invalid parameter exception
     */
    public void validEventName(String eventName) throws InvalidParameterException {
        if (eventName.isEmpty()) {
            throw new InvalidParameterException("The event name is empty");
        }
    }

    /**
     * Gets event list.
     *
     * @return the event list
     */
    public ArrayList<Event> getEventList() {
        return eventList;
    }

    /**
     * Gets all events in string.
     *
     * @return the all events in string
     */
    public ArrayList<ArrayList<String>> getAllEventsInString() {
        ArrayList<ArrayList<String>> allEvents = new ArrayList<>();

        for (Event event : eventList) {
            ArrayList<String> eventInfo = new ArrayList<>();
            eventInfo.add(event.getName());
            eventInfo.add(event.getStartTime().toString());
            eventInfo.add(String.valueOf(event.getRoomID()));
            allEvents.add(eventInfo);
        }

        return allEvents;

    }

}
