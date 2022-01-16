package controllers;


import gateways.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The type File system controller.
 */
public class FileSystemController {
    // We will deal with use_case packages in the format
    // [EventSystem, MessagingSystem, RoomManager, ScheduleSystem, UserManager]
    private final EventSystemGateway eventSystemGateway;
    private final MessagingSystemGateway messagingSystemGateway;
    private final RoomManagerGateway roomManagerGateway;
    private final ScheduleSystemGateway scheduleSystemGateway;
    private final UserManagerGateway userManagerGateway;
    private final ContactManagerGateway contactManagerGateway;
    private final UserInfoManagerGateway userInfoManagerGateway;
    private final StatsManagerGateway statsManagerGateway;

    /**
     * Instantiates a new File system controller.
     */
    public FileSystemController(){
        eventSystemGateway = new EventSystemGateway();
        messagingSystemGateway = new MessagingSystemGateway();
        roomManagerGateway = new RoomManagerGateway();
        scheduleSystemGateway = new ScheduleSystemGateway();
        userManagerGateway = new UserManagerGateway();
        contactManagerGateway = new ContactManagerGateway();
        userInfoManagerGateway = new UserInfoManagerGateway();
        statsManagerGateway = new StatsManagerGateway();
    }

    /**
     * Load array list.
     *
     * @return the array list
     * @throws ClassNotFoundException the class not found exception
     */
    public ArrayList<Object> load() throws ClassNotFoundException {
        ArrayList<Object> use_cases = new ArrayList<>();
        use_cases.add(eventSystemGateway.readFromFile());
        use_cases.add(messagingSystemGateway.readFromFile());
        use_cases.add(roomManagerGateway.readFromFile());
        use_cases.add(scheduleSystemGateway.readFromFile());
        use_cases.add(userManagerGateway.readFromFile());
        use_cases.add(contactManagerGateway.readFromFile());
        use_cases.add(userInfoManagerGateway.readFromFile());
        use_cases.add(statsManagerGateway.readFromFile());
        return use_cases;
    }

    /**
     * Deload.
     *
     * @param bundle the bundle
     * @throws IOException the io exception
     */
    public void deload(UseCaseBundle bundle) throws IOException {
        eventSystemGateway.saveToFile(bundle.getEventSystem());
        messagingSystemGateway.saveToFile(bundle.getMessagingSystem());
        roomManagerGateway.saveToFile(bundle.getRoomManager());
        scheduleSystemGateway.saveToFile(bundle.getScheduleSystem());
        userManagerGateway.saveToFile(bundle.getUserManager());
        contactManagerGateway.saveToFile(bundle.getContactManager());
        userInfoManagerGateway.saveToFile(bundle.getUserInfoManager());
        statsManagerGateway.saveToFile(bundle.getStatsManager());
    }
}
