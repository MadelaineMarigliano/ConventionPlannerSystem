package use_cases;

import entities.Room;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * The type Room manager.
 */
public class RoomManager implements Serializable {
    private ArrayList<Room> roomList = new ArrayList<>();

    /**
     * Checks to see if a roomID is available.
     *
     * @param roomID the room id
     * @return the boolean
     */
// If isRoomIDAvailable, it means no room exists with the roomID
    public boolean isRoomIDAvailable(int roomID) {
        for (Room room: roomList){
            if (room.getRoomID() == roomID){
                return false;
            }
        }
        return true;
    }

    /**
     * Does room exist boolean.
     *
     * @param roomID the room id
     * @return the boolean
     */
    public boolean doesRoomExist(int roomID) {
        return !isRoomIDAvailable(roomID);
    }

    /**
     * Create room with RoomID.
     *
     * @param roomID the room id
     * @return the room
     * @throws InvalidParameterException the invalid parameter exception
     */
    public Room createRoom(int roomID) throws InvalidParameterException{
        if (doesRoomExist(roomID)){
            throw new InvalidParameterException("Room already exists.");
        }
        return new Room(roomID);
    }

    /**
     * Create room with RoomID and maxCapacity.
     *
     * @param roomID      the room id
     * @param maxCapacity the max capacity
     * @return the room
     * @throws InvalidParameterException the invalid parameter exception
     */
    public Room createRoom(int roomID, int maxCapacity) throws InvalidParameterException{
        if (doesRoomExist(roomID)){
            throw new InvalidParameterException("Room already exists.");
        }
        return new Room(roomID, maxCapacity);
    }

    /**
     * Add room.
     *
     * @param roomID         the room id
     * @param max_capacity   the max capacity
     * @param scheduleSystem the schedule system
     * @throws InvalidParameterException the invalid parameter exception
     */
    public void addRoom(int roomID, int max_capacity, ScheduleSystem scheduleSystem) throws InvalidParameterException{
        if (doesRoomExist(roomID)){
            throw new InvalidParameterException("Room already exists.");
        }
        roomList.add(createRoom(roomID, max_capacity));
        scheduleSystem.addRoom(roomID);
    }

    /**
     * Add room.
     *
     * @param roomID         the room id
     * @param scheduleSystem the schedule system
     * @throws InvalidParameterException the invalid parameter exception
     */
    public void addRoom(int roomID, ScheduleSystem scheduleSystem) throws InvalidParameterException{
        if (doesRoomExist(roomID)){
            throw new InvalidParameterException("Room already exists.");
        }
        roomList.add(createRoom(roomID));
        scheduleSystem.addRoom(roomID);
    }


    /**
     * Delete room.
     *
     * @param roomID   the room id
     * @param schedule the schedule
     * @throws InvalidParameterException the invalid parameter exception
     */
    public void deleteRoom(int roomID, ScheduleSystem schedule) throws InvalidParameterException{
        if (!doesRoomExist(roomID)){
            throw new InvalidParameterException("Room does not exist.");
        }
        roomList.removeIf(room -> room.getRoomID() == roomID);
        schedule.removeRoom(roomID);
    }

    /**
     * Gets room list.
     *
     * @return the room list
     */
    public ArrayList<Room> getRoomList() {
        return roomList;
    }
}