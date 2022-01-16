package use_cases;
import entities.Event;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.*;


/**
 * The type Schedule system.
 */
public class ScheduleSystem implements Serializable {
    private HashMap<Integer, ArrayList<Event>> Schedule;
    private HashMap<Integer, HashMap<Integer, ArrayList<Event>>> roomSchedule;
    private int eventStart = 9; //the first hour events can start on
    private int eventEnd = 17; //the last hour events can start on

    /**
     * Instantiates a new Schedule system.
     */
    public ScheduleSystem() {
        Schedule = new HashMap<>();
        roomSchedule = new HashMap<>();
        Schedule = this.createMap();
    }

    private HashMap<Integer, ArrayList<Event>> createMap() {
        HashMap<Integer, ArrayList<Event>> x = new HashMap<>();
        for (int i = eventStart; i < eventEnd + 1; i++) {
            ArrayList<Event> y = new ArrayList<>();
            x.put(i, y);
        }
        return x;
    }

    /**
     * Gets schedule.
     *
     * @return the schedule
     */
    public HashMap<Integer, ArrayList<Event>> getSchedule() {
        return Schedule;
    }

    /**
     * Get schedule by room
     *
     * @return the hash map
     */
    public HashMap<Integer, HashMap<Integer, ArrayList<Event>>> getScheduleByRoom() {
        return roomSchedule;
    }

    /**
     * Add room.
     *
     * @param roomID the room id
     */
    public void addRoom(Integer roomID) {
        HashMap<Integer, ArrayList<Event>> x = this.createMap();
        roomSchedule.put(roomID, x);
    }

    /**
     * Add event.
     *
     * @param event the event
     */
    public void addEvent(Event event) {
        Schedule.get(event.getStartTime().getHour()).add(event);
        roomSchedule.get(event.getRoomID()).get(event.getStartTime().getHour()).add(event);
    }

    /**
     * Remove room.
     *
     * @param roomID the room id
     */
    public void removeRoom(Integer roomID) {
        for (ArrayList<Event> lst : roomSchedule.get(roomID).values()) {
            for (Event event : lst) {
                Schedule.get(event.getStartTime().getHour()).remove(event);
            }
        }
        roomSchedule.remove(roomID);
    }

    /**
     * Remove event.
     *
     * @param event the event
     */
    public void removeEvent(Event event) {
        Schedule.get(event.getStartTime().getHour()).remove(event);
        roomSchedule.get(event.getRoomID()).get(event.getStartTime().getHour()).remove(event);
    }

    /**
     * changes the hours events can start at and end at
     *
     * @param eventStart the first hour events can start at
     * @param eventEnd   the last hour events can end at
     */
    public void changeHours(int eventStart, int eventEnd) {
        this.eventEnd = eventEnd;
        this.eventStart = eventStart;
    }

    private int sizeLargestList() {
        int l = 0;
        for (int t: this.Schedule.keySet()) {
            int s = Schedule.get(t).size();
            if (s > l){
                l = s;
            }
        }
        return l;
    }

    /**
     * Create html file.
     *
     * @return the file
     */
    public File CreateHTML() {
        HashMap<Integer, ArrayList<Event>> schedule = this.getSchedule();
        File file = new File("Schedule.html");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter ScheduleHTML = new BufferedWriter(fileWriter);
            ScheduleHTML.write("<html><h1><strong><span style=\"font-family:Arial, Helvetica, sans-serif;" +
                    "text-align:center;color: #28324e;\">Event Schedule</span></strong></h1>" +
                    "<table style=\"font-family:Arial, Helvetica, sans-serif;height: 74px; width: 100%; " +
                    "border-collapse: collapse; border-style: solid;\" border=\"1\"> <tbody>");
            List<Integer> sorted = new ArrayList<>(schedule.keySet());
            Collections.sort(sorted);
            int l = sizeLargestList();
            for (Integer t : sorted) {
                ScheduleHTML.write("<tr><td style=\"background-color:#475577;color:white;\"><b>" + t.toString() +
                        ":00" + "</b></td>");
                ArrayList<Event> eventList = schedule.get(t);
                eventList.sort(new Comparator<Event>() {
                    @Override
                    public int compare(Event o1, Event o2) {
                        return o1.getStartTime().getMinute() - o2.getStartTime().getMinute();
                    }
                });
                for (Event event : eventList) {
                    ScheduleHTML.write("<td>" + event.toString() + "</td>");
                }
                if (eventList.size() < l) {
                    for (int i = eventList.size() ; i < l ; i++) {
                        ScheduleHTML.write("<td> </td>");
                    }
                }
                ScheduleHTML.write("</tr>");
            }
            ScheduleHTML.write("</tbody></table></html>");
            ScheduleHTML.close();

        } catch (Exception e) {//ignore}
        }
        return file;
    }
}
