package use_cases;

import entities.UserTypes;
import entities.User;
import entities.VIPAttendee;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;

/**
 * A UserManager stores the users creates by it.
 * A UserManager provides a method that can add a new user to the system,
 * and methods that can get a user (or users) from the system.
 * A UserManager provides a method that would check if a username is taken.
 * A UserManager provides methods that enforce the business rules for using user.
 */
public class UserManager implements Serializable {

    private HashMap<String, User> userMap;
    private User adminAccount = new User("Admin", "Account", "admin_account", "1357642", UserTypes.ORGANIZER);
    private final String usernamePattern = "^[a-zA-Z0-9._-]{3,}$";

    /**
     * Construct a UserManager that stores the default user.
     */
    public UserManager() {
        userMap = new HashMap<>();
        userMap.put(adminAccount.getUsername(), adminAccount);
    }

    /**
     * Get all users in string array list.
     *
     * @return the array list
     */
    public ArrayList<ArrayList<String>> getAllUsersInString(){
        ArrayList<ArrayList<String>> userList = new ArrayList<>();
        ArrayList<User> attendeeList = getAllAttendees();
        ArrayList<User> organizerList = getAllOrganizers();
        ArrayList<User> speakerList;
        try {
            speakerList = getAllSpeakers();
        }
        catch (NullPointerException e){
            speakerList = new ArrayList<>();
        }
        for (User attendee : attendeeList){
            ArrayList<String> info = new ArrayList<>();
            info.add(attendee.getUsername());
            info.add(attendee.getFirst());
            info.add(attendee.getLast());
            info.add("Attendee");
            userList.add(info);
        }
        for (User organizer : organizerList){
            ArrayList<String> info = new ArrayList<>();
            info.add(organizer.getUsername());
            info.add(organizer.getFirst());
            info.add(organizer.getLast());
            info.add("Organizer");
            userList.add(info);
        }
        for (User speaker : speakerList){
            ArrayList<String> info = new ArrayList<>();
            info.add(speaker.getUsername());
            info.add(speaker.getFirst());
            info.add(speaker.getLast());
            info.add("Speaker");
            userList.add(info);
        }
        return userList;
    }

    /**
     * Creates a user, and stores it in the system.
     *
     * @param first    firstname of the user
     * @param last     lastname of the user
     * @param username username of the user
     * @param password password of the user
     * @param userType UserType of the user
     * @throws InvalidParameterException if first is empty or only contains whitespace,                                      or if last is empty or only contains whitespace,                                      or if username is empty or only contains whitespace                                      or doesn't fit the pattern "^[a-zA-Z0-9._-]{3,}$",                                      or if the password is empty or only contains whitespace
     */
    public void createUser(String first, String last, String username, String password, UserTypes userType)
            throws InvalidParameterException {
        // All uses of parameter strings are trimmed to remove leading and trailing whitespace.
        String firstTrimmed = first.trim();
        String lastTrimmed = last.trim();
        String usernameTrimmed = username.trim();
        String passwordTrimmed = password.trim();

        checkFirstValid(firstTrimmed);
        checkLastValid(lastTrimmed);
        checkUsernameValid(usernameTrimmed);
        checkPasswordValid(passwordTrimmed);

        checkUsernameUnique(usernameTrimmed);

        User user;
        if (userType == UserTypes.VIP) {
            user = new VIPAttendee(firstTrimmed, lastTrimmed,usernameTrimmed, passwordTrimmed);
        } else {
            user = new User(firstTrimmed, lastTrimmed, usernameTrimmed, passwordTrimmed, userType);
        }
        userMap.put(usernameTrimmed, user);
    }

    /**
     * Deletes the specified user from the system.
     *
     * @param username the username of the specified user
     */
    public void deleteUser(String username){
        userMap.remove(username);
    }

    /**
     * Returns the user with the specified username.
     *
     * @param username username of the user
     * @return the user with the specified username
     * @throws InvalidParameterException if there doesn't exist a user with the specified username
     */
    public User getUser(String username) throws InvalidParameterException {
        // All uses of parameter strings are trimmed to remove leading and trailing whitespace.
        String usernameTrimmed = username.trim();

        if (existsUsername(usernameTrimmed)) {
            return userMap.get(usernameTrimmed);
        } else {
            throw new InvalidParameterException("The username \"" + usernameTrimmed + "\" does not exist.");
        }
    }

    /**
     * Returns a list of user with the specified usernames.
     *
     * @param usernameList usernames of the users
     * @return a list of user with the specified usernames
     * @throws InvalidParameterException if there is a username in usernames such that                                      there doesn't exist a user with that username.
     */
    public ArrayList<User> getUsers(ArrayList<String> usernameList) throws InvalidParameterException {

        ArrayList<User> list = new ArrayList<>();
        for (String username : usernameList) {
            list.add(getUser(username));
        }

        return list;
    }

    /**
     * Returns a list of users such that all user with userType speaker is in the list.
     *
     * @return a list of users such that all user with userType speaker is in the list
     */
    public ArrayList<User> getAllSpeakers(){
        ArrayList<User> list = new ArrayList<>();
        for (User user : userMap.values()) {
            if (user.getUserType().equals(UserTypes.SPEAKER)) {
                list.add(user);
            }
        }
        return list;
    }

    /**
     * Returns a list of users such that all user with userType attendee or userType Organizer is in the list.
     *
     * @return a list of users such that all user with userType attendee or userType Organizer is in the list
     * @throws NullPointerException the null pointer exception
     */
    public ArrayList<User> getAllAttendeesAndOrganizers() throws NullPointerException {
        ArrayList<User> list = new ArrayList<>();
        ArrayList<User> organizerList = getAllOrganizers();
        ArrayList<User> attendeeList = getAllAttendees();
        list.addAll(organizerList);
        list.addAll(attendeeList);

        return list;

    }

    /**
     * Returns true if there exists a user with the specified username.
     *
     * @param username the username that need to be checked
     * @return true if there exists a user with the specified username
     */
    public boolean existsUsername(String username) {
        // All uses of parameter strings are trimmed to remove leading and trailing whitespace.
        String usernameTrimmed = username.trim();

        return userMap.containsKey(usernameTrimmed);
    }

    private void checkUsernameValid(String username) throws InvalidParameterException {
        if (username.isEmpty()) {
            throw new InvalidParameterException("Username cannot be empty.");
        }
        if (!Pattern.matches(usernamePattern, username)) {
            throw new InvalidParameterException("This username is not valid.");
        }
    }

    private void checkFirstValid(String first) throws InvalidParameterException {
        if (first.isEmpty()) {
            throw new InvalidParameterException("First name cannot be empty.");
        }
    }

    private void checkLastValid(String last) throws InvalidParameterException {
        if (last.isEmpty()) {
            throw new InvalidParameterException("Last name cannot be empty.");
        }
    }

    private void checkPasswordValid(String password) throws InvalidParameterException {
        if (password.isEmpty()) {
            throw new InvalidParameterException("Password cannot be empty.");
        }
    }

    private void checkUsernameUnique(String username) throws InvalidParameterException {
        if (existsUsername(username)) {
            throw new InvalidParameterException("This username already exists.");
        }
    }

    private ArrayList<User> getAllAttendees() {
        ArrayList<User> list = new ArrayList<>();
        for (User user : userMap.values()) {
            if (user.getUserType().equals(UserTypes.ATTENDEE)) {
                list.add(user);
            }
        }
        return list;
    }

    private ArrayList<User> getAllOrganizers() {
        ArrayList<User> list = new ArrayList<>();
        for (User user : userMap.values()) {
            if (user.getUserType().equals(UserTypes.ORGANIZER)) {
                list.add(user);
            }
        }
        return list;
    }

    /**
     * Calculate occupancy int.
     *
     * @param username the username
     * @return the int
     */
    public int calculateOccupancy(String username) {
        return getUser(username).calculateOccupancy();
    }

    /**
     * Is vip boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean isVIP(String username) {
        return getUser(username).isVIP();
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    public ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        allUsers.addAll(getAllAttendees());
        allUsers.addAll(getAllOrganizers());
        allUsers.addAll(getAllSpeakers());
        return allUsers;
    }

    /**
     * Get guest code string.
     *
     * @param username the username
     * @return the string
     */
    public String getGuestCode(String username){
        return getUser(username).getGuestCode();
    }

}
