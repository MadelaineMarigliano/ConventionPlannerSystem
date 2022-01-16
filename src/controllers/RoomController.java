package controllers;


import views.RoomView;
import use_cases.RoomManager;
import use_cases.ScheduleSystem;

import java.util.Scanner;

/**
 * The type Room controller.
 */
public class RoomController extends AbstractController{
    private RoomView view;

    /**
     * Instantiates a new Room controller.
     *
     * @param bundle the bundle
     */
    public RoomController(UseCaseBundle bundle){
        super(bundle);
        view = new RoomView();
    }

    @Override
    public AbstractController run() {
        Scanner scanner = new Scanner(System.in);
        view.displayRoomMenu();

        int option;
        option = selectOption(scanner);

        switch (option) {
            case 1: // Add Room
                addRoom(scanner);
                setPopNum(0);
                return null;
            case 2: //Remove Room
                deleteRoom(scanner);
                setPopNum(0);
                return null;
            case 3: //view available rooms
                viewRoom(scanner);
                setPopNum(0);
                return null;
            case 4:
                setPopNum(1);
                return null;
        }
        return null;
    }

    private int selectOption(Scanner scanner) {
        String input;
        do {
            view.askForInput();
            input = scanner.nextLine();
            switch (input.trim()) {
                case "1":
                    return 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
            }
            view.optionError();
        } while (true);
    }

    /**
     * Add room.
     *
     * @param scanner the scanner
     */
    public void addRoom(Scanner scanner){
        view.roomIdPrompt();
        int roomId;

        try {
            roomId = scanner.nextInt();
            RoomManager rm = getBundle().getRoomManager();
            ScheduleSystem ss = getBundle().getScheduleSystem();

            rm.addRoom(roomId, ss);
        }
        catch (Exception e){
            view.invalidRoomId();
        }


    }

    /**
     * Delete room.
     *
     * @param scanner the scanner
     */
    public void deleteRoom(Scanner scanner){
        view.roomIdPrompt();
        int roomId;
        try {
            roomId = scanner.nextInt();

            RoomManager rm = getBundle().getRoomManager();
            ScheduleSystem ss = getBundle().getScheduleSystem();

            rm.deleteRoom(roomId, ss);
        }
        catch(Exception e) {
            view.invalidRoomId();
        }
    }

    /**
     * View room.
     *
     * @param scanner the scanner
     */
    public void viewRoom(Scanner scanner){
        RoomManager rm = getBundle().getRoomManager();
        view.printRoom(rm.getRoomList());
    }


}
