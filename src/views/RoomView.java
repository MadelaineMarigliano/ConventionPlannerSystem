package views;


import entities.Event;
import entities.Room;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type Room presenter.
 */
public class RoomView extends AbstractView {
    /**
     * Display room menu.
     */
    public void displayRoomMenu(){
        System.out.println("1. Add Room");
        System.out.println("2. Delete Room");
        System.out.println("3. View Available rooms");
        System.out.println("4. Go Back");
    }

    /**
     * Room id prompt.
     */
    public void roomIdPrompt(){
        System.out.println("Enter Room ID");
    }

    /**
     * Invalid Room ID.
     */
    public void invalidRoomId(){
        System.out.println("Invalid Room ID");
    }

    /**
     * Ask for input.
     */
    public void askForInput() {
        System.out.println("Enter input");
    }

    /**
     * Option error.
     */
    public void optionError() {
        System.out.println("Invalid input");
    }

    /**
     * Print room.
     *
     * @param rooms the rooms
     */
    public void printRoom(ArrayList<Room> rooms) {
        System.out.println(rooms.toString());
    }

}
