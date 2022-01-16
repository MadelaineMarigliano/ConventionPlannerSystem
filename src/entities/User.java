package entities;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The type User.
 */
public class User implements Serializable {
    private String first;
    private String last;
    private String username;
    private String password;
    private UserTypes type;
    private ArrayList<String> personalContact;
    private ArrayList<String> beAddedToList;
    private HashMap<String, ArrayList<Integer>> messages;
    private ArrayList<Integer> myEvents;
    private Integer unreadMessages;
    private HashMap<String, Integer> unreadMessagesByUsername;
    private boolean VIP = false;
    //mass messages from organizers
    private HashMap<String, HashMap<String, ArrayList<Integer>>> userTypeAnnouncements;
    //mass messages from speakers
    private HashMap<String, HashMap<String, ArrayList<Integer>>> eventAnnouncements;
    //default color for profile icon
    private String color = "blue";
    //default appearance mode
    private String appearanceMode = "light";

    /**
     * Instantiates a new User.
     *
     * @param first    the first
     * @param last     the last
     * @param username the username
     * @param password the password
     * @param type     the type
     * @throws InvalidParameterException the invalid parameter exception
     */
    public User(String first, String last, String username, String password, UserTypes type) throws InvalidParameterException {
        this.first = first;
        this.last = last;
        this.username = username;
        this.password = password;
        this.messages = new HashMap<>();
        this.type = type;
        personalContact = new ArrayList<>();
        beAddedToList = new ArrayList<>();
        myEvents = new ArrayList<>();
        unreadMessages = 0;
        unreadMessagesByUsername = new HashMap<>();
        userTypeAnnouncements = new HashMap<>();
        eventAnnouncements = new HashMap<>();
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets first.
     *
     * @return the first
     */
    public String getFirst() {
        return first;
    }

    /**
     * Gets last.
     *
     * @return the last
     */
    public String getLast() {
        return last;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return first + " " + last;
    }

    /**
     * Verify boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public boolean verify(String p) {
        return p.equals(password);
    }

    /**
     * Gets user type.
     *
     * @return the user type
     */
    public UserTypes getUserType() {
        return type;
    }

    /**
     * Gets personal contact.
     *
     * @return the personal contact
     */
    public ArrayList<String> getPersonalContact() {
        return personalContact;
    }

    /**
     * Add personal contact.
     *
     * @param username the username
     */
    public void addPersonalContact(String username) {
        personalContact.add(username);
    }

    /**
     * Delete personal contact.
     *
     * @param username the username
     */
    public void deletePersonalContact(String username) {
        personalContact.remove(username);
    }

    /**
     * Gets the list of usernames of users who added this user to their personal contact.
     *
     * @return the list of usernames of users who added this user to their personal contact.
     */
    public ArrayList<String> getBeAddedTo() {
        return beAddedToList;
    }

    /**
     * Adds a username to the list that stores usernames of users who added this user to their personal contact.
     *
     * @param username the username
     */
    public void addBeAddedTo(String username){
        beAddedToList.add(username);
    }

    /**
     * Deletes a username from the list that stores usernames of users who added this user to their personal contact.
     *
     * @param username the username
     */
    public void deleteBeAddedTo(String username){
        beAddedToList.remove(username);
    }

    /**
     * Gets message map.
     *
     * @return the message map
     */
    public HashMap<String, ArrayList<Integer>> getMessageMap() {
        return messages;
    }

    /**
     * Gets message ids.
     *
     * @param username the username
     * @return the message ids
     */
    public ArrayList<Integer> getMessageIds(String username) {
        return messages.get(username);
    }

    /**
     * Add message id.
     *
     * @param username the username
     * @param id       the id
     */
    public void addMessageId(String username, Integer id) {
        messages.putIfAbsent(username, new ArrayList<>());
        messages.get(username).add(id);
    }

    /**
     * Gets my events.
     *
     * @return the my events
     */
    public ArrayList<Integer> getMyEvents() { return myEvents; }

    /**
     * Remove event.
     *
     * @param event the event
     */
    public void removeEvent(Integer event) {
        myEvents.remove(event);
    }

    /**
     * Add event.
     *
     * @param event the event
     */
    public void addEvent(Integer event) {
        myEvents.add(event);
    }

    /**
     * Gets unread message count.
     *
     * @return the unread message count
     */
// for use in GUI
    public Integer getUnreadMessageCount() {
        this.unreadMessages = 0;
        for (Integer value : unreadMessagesByUsername.values()) {
            this.unreadMessages += value;
        }
        return unreadMessages;
    }

    /**
     * Gets unread messsage by username.
     *
     * @return the unread messsage by username
     */
    public HashMap<String, Integer> getUnreadMesssageByUsername() {
        return unreadMessagesByUsername;
    }

    /**
     * Has unread messages boolean.
     *
     * @return the boolean
     */
    public boolean hasUnreadMessages() {
        return getUnreadMessageCount() > 0;
    }

    /**
     * Has unread message from boolean.
     *
     * @param from the from
     * @return the boolean
     */
    public boolean hasUnreadMessageFrom(User from) {
        return unreadMessagesByUsername.containsKey(from.getUsername());
    }

    // no option for adding different values than 1 because messages are sent one at a time
    private void addUnreadMessage() {
        unreadMessages += 1;
    }

    /**
     * Delete unread message.
     *
     * @param read the read
     */
    public void deleteUnreadMessage(Integer read) {
        unreadMessages -= read;
    }

    /**
     * Gets unread message count from.
     *
     * @param from the from
     * @return the unread message count from
     */
    public Integer getUnreadMessageCountFrom(User from) {
        // checked for valid from.getUsername already
        return unreadMessagesByUsername.get(from.getUsername());
    }

    /**
     * Delete unread message count from.
     *
     * @param from the from
     */
    public void deleteUnreadMessageCountFrom(User from) {
        unreadMessagesByUsername.remove(from.getUsername());
    }

    /**
     * Add unread message from.
     *
     * @param from the from
     */
    public void addUnreadMessageFrom(User from) {
        unreadMessagesByUsername.putIfAbsent(from.getUsername(), 0);
        unreadMessagesByUsername.put(from.getUsername(), unreadMessagesByUsername.get(from.getUsername()) + 1);
        addUnreadMessage();
    }

    /**
     * Calculate occupancy int.
     *
     * @return the int
     */
    public int calculateOccupancy() {
        return 1;
    }

    /**
     * Is vip boolean.
     *
     * @return the boolean
     */
    public boolean isVIP() {
        return VIP;
    }

    /**
     * Sets vip.
     *
     * @param b the b
     */
    public void setVIP(boolean b) {
        VIP = b;
    }

    public boolean hasUserAnnouncements() {
        Integer count = 0;
        for (Map.Entry userType : userTypeAnnouncements.entrySet()) {
            String userString = (String) userType.getKey();
            if (userTypeAnnouncements.get(userString) != null) {
                for (Map.Entry sender : userTypeAnnouncements.get(userString).entrySet()) {
                    String senderString = (String) sender.getKey();
                    count += userTypeAnnouncements.get(userString).get(senderString).size();
                }
            }
        }
        return count > 0;
    }

    /**
     * Gets user type announcements.
     *
     * @return the user type announcements
     */
    public HashMap<String, HashMap<String, ArrayList<Integer>>> getUserTypeAnnouncements() {
        return userTypeAnnouncements;
    }

    /**
     * Gets user announcements by type.
     *
     * @param userType the user type
     * @return the user announcements by type
     */
    public ArrayList<Integer> getUserAnnouncementsByType(String userType) {
        ArrayList<Integer> sortedAnnouncements = new ArrayList<>();
        for (Map.Entry sender : userTypeAnnouncements.get(userType).entrySet()) {
            String senderName = (String) sender.getKey();
            sortedAnnouncements.addAll(userTypeAnnouncements.get(userType).get(senderName));
        }
        Collections.sort(sortedAnnouncements);
        return sortedAnnouncements;
    }

    /**
     * Add user type announcement.
     *
     * @param sender    the sender
     * @param group     the group
     * @param messageId the message id
     */
    public void addUserTypeAnnouncement(String sender, String group, Integer messageId) {
        userTypeAnnouncements.putIfAbsent(group, new HashMap<>());
        userTypeAnnouncements.get(group).putIfAbsent(sender, new ArrayList<>());
        userTypeAnnouncements.get(group).get(sender).add(messageId);
    }

    /**
     * Delete user announcement by sender.
     *
     * @param senderName the sender name
     */
    public void deleteUserAnnouncementBySender(String senderName, String group) {
        userTypeAnnouncements.get(group).remove(senderName);
    }

    /**
     * Gets event announcements.
     *
     * @return the event announcements
     */
    public HashMap<String, HashMap<String, ArrayList<Integer>>> getEventAnnouncements() {
        return eventAnnouncements;
    }

    public boolean hasEventAnnouncements() {
        Integer count = 0;
        for (Map.Entry eventName : eventAnnouncements.entrySet()) {
            String eventString = (String) eventName.getKey();
            if (eventAnnouncements.get(eventString) != null) {
                for (Map.Entry sender : eventAnnouncements.get(eventString).entrySet()) {
                    String senderString = (String) sender.getKey();
                    count += eventAnnouncements.get(eventString).get(senderString).size();
                }
            }
        }
        return count > 0;
    }

    /**
     * Gets event announcements by event.
     *
     * @param eventName the event name
     * @return the event announcements by event
     */
    public ArrayList<Integer> getEventAnnouncementsByEvent(String eventName) {
        ArrayList<Integer> sortedAnnouncements = new ArrayList<>();
        for (Map.Entry sender : eventAnnouncements.get(eventName).entrySet()) {
            String senderName = (String) sender.getKey();
            sortedAnnouncements.addAll(eventAnnouncements.get(eventName).get(senderName));
        }
        Collections.sort(sortedAnnouncements);
        return sortedAnnouncements;
    }

    /**
     * Add event announcement.
     *
     * @param eventName the event name
     * @param sender    the sender
     * @param messageId the message id
     */
    public void addEventAnnouncement(String eventName, String sender, Integer messageId) {
        eventAnnouncements.putIfAbsent(eventName, new HashMap<>());
        eventAnnouncements.get(eventName).putIfAbsent(sender, new ArrayList<>());
        eventAnnouncements.get(eventName).get(sender).add(messageId);
    }

    /**
     * Delete event announcement by event.
     *
     * @param eventName the event name
     */
    public void deleteEventAnnouncementByEvent(String eventName) {
        eventAnnouncements.remove(eventName);
    }

    /**
     * Contains event announcements with boolean.
     *
     * @param eventName the event name
     * @return the boolean
     */
    public boolean containsEventAnnouncementsWith(String eventName) {
        return eventAnnouncements.containsKey(eventName);
    }

    /**
     * Delete event announcement by sender and event.
     *
     * @param senderName the sender name
     * @param eventName  the event name
     */
    public void deleteEventAnnouncementBySenderAndEvent(String senderName, String eventName) {
        eventAnnouncements.get(eventName).remove(senderName);
    }

    /**
     * Gets num events.
     *
     * @return the num events
     */
    public Integer getNumEvents() {
        return myEvents.size();
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(String color) {
        this.color = color;
    }


    /**
     * Gets guest code.
     *
     * @return the guest code
     */
    public String getGuestCode() {
        return "";
    }

    /**
     * Gets appearance mode.
     *
     * @return the appearance mode
     */
    public String getAppearanceMode() {
        return appearanceMode;
    }

    /**
     * Sets appearance.
     *
     * @param appearance the appearance
     */
    public void setAppearance(String appearance) {
        this.appearanceMode = appearance;
    }


    /**
     * Change password.
     *
     * @param newPassword the new password
     */
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
