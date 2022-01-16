package entities;
import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The type Room.
 */
public class Room implements Serializable {


    private int roomID;
    private int maxCapacity;
    private ArrayList<LocalDateTime[]> schedule;

    /**
     * Instantiates a new Room.
     *
     * @param roomID the room id
     */
    public Room(int roomID){
        this.roomID = roomID;
        this.maxCapacity = 2;
        this.schedule = new ArrayList<>();
    }

    /**
     * Instantiates a new Room.
     *
     * @param roomID      the room id
     * @param maxCapacity the max capacity
     */
    public Room(int roomID, int maxCapacity){
        this.roomID = roomID;
        this.maxCapacity = maxCapacity;
        this.schedule = new ArrayList<>();
    }


    /**
     * Get room id int.
     *
     * @return the int
     */
    public Integer getRoomID(){
        return this.roomID;
    }

    /**
     * Set room id.
     *
     * @param roomID the room id
     */
    public void setRoomID(int roomID){
        this.roomID = roomID;
    }

    /**
     * Get schedule array list.
     *
     * @return the array list
     */
    public ArrayList<LocalDateTime[]> getSchedule(){
        return this.schedule;
    }

    /**
     * Set schedule.
     *
     * @param schedule the schedule
     */
    public void setSchedule(ArrayList<LocalDateTime[]> schedule){
        this.schedule = schedule;
    }

    @Override
    public String toString(){
        return "Room number: " + roomID + " Max Capacity: " + maxCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomID == room.roomID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomID);
    }
}
