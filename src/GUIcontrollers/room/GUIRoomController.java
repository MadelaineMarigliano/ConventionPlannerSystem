package GUIcontrollers.room;

import controllers.*;
import controllers.UseCaseBundle;
import use_cases.RoomManager;
import use_cases.ScheduleSystem;
import use_cases.UserManager;

import java.util.logging.*;

import java.util.ArrayList;

/**
 * The type Gui room controller.
 */
public class GUIRoomController extends AbstractController {
    /**
     * Instantiates a new Room controller.
     *
     * @param bundle the bundle
     */

    private int option;
    private int id;
    private int capacity;
    private final UserManager userManager;
    private final String username;
    /**
     * The Logger.
     */
    Logger logger = Logger.getLogger(GUIRoomController.class.getName());

    /**
     * Instantiates a new Gui room controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public GUIRoomController(UseCaseBundle bundle, String username) {
        super(bundle);
        setControllerType(ControllerType.ROOM);
        this.userManager = bundle.getUserManager();
        this.username = username;
    }

    @Override
    public AbstractController run() {
        switch (option) {
            case 1: // Add Room
                addRoom(id, capacity);
                setPopNum(0);
                return null;
            case 2: //Remove Room
                deleteRoom(id);
                setPopNum(0);
                return null;
            case 3:
                setPopNum(1);
                return null;
        }
        return null;
    }

    private void deleteRoom(int id)  {
        int roomID;

        try{
            RoomManager rm = getBundle().getRoomManager();
            ScheduleSystem ss = getBundle().getScheduleSystem();
            roomID= id;

            rm.deleteRoom(roomID, ss);
        }
        catch (Exception e){
            logger.log(Level.ALL,"Room doesn't exist");
        }
    }

    private void addRoom(int id, int capacity) {
        int roomID;
        int roomCapacity;

        try{
            RoomManager rm = getBundle().getRoomManager();
            ScheduleSystem ss = getBundle().getScheduleSystem();
            roomID= id;
            roomCapacity = capacity;

            rm.addRoom(roomID, roomCapacity, ss);
        }
        catch (Exception e){
            logger.log(Level.ALL,"Room already  exist");
        }
    }

    /**
     * View room string.
     *
     * @return the string
     */
    public String viewRoom() {
        option = 3;
        RoomManager rm = getBundle().getRoomManager();
        ArrayList rooms = rm.getRoomList();
        StringBuilder word = new StringBuilder(" ");
        for (int i =0; i< rooms.size(); i++){
            word.append(String.valueOf(rooms.get(i)));
            word.append(" , ");
        }
        return word.toString();
    }


    /**
     * Add clicked.
     *
     * @param roomId       the room id
     * @param roomCapacity the room capacity
     */
    public void addClicked(int roomId, int roomCapacity){
        option = 1;
        id = roomId;
        capacity = roomCapacity;

    }

    /**
     * Del clicked.
     *
     * @param roomId the room id
     */
    public void delClicked(int roomId){
        option = 2;
        id = roomId;
    }

    /**
     * Back clicked.
     */
    public void backClicked(){
        option = 3;
    }

    /**
     * Gets appearance mode.
     *
     * @return the appearance mode
     */
    public String getAppearanceMode() {
        return userManager.getUser(username).getAppearanceMode();
    }
}
