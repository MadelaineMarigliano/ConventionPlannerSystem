package use_cases;

import entities.User;
import entities.UserTypes;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * The type User info manager.
 */
public class UserInfoManager implements Serializable {

    /**
     * Returns true if the password is correct.
     *
     * @param user     the user
     * @param password password that need to be verified
     * @return true if the password is correct
     */
    public boolean loginVerify(User user, String password){
        // All uses of parameter strings are trimmed to remove leading and trailing whitespace.
        String passwordTrimmed = password.trim();

        return user.verify(passwordTrimmed);
    }

    /**
     * Returns the userType of the user with the specified username.
     *
     * @param user the user
     * @return the userType of the user with the specified username
     */
    public UserTypes getUserType(User user){
        return user.getUserType();
    }

    /**
     * Returns a list of messageIds. The list consists of all the ids of the messages sent from
     * self to user, and from user to self. The list is sorted, so the id of the latest message
     * would appear in the end of the list.
     *
     * @param self a user
     * @param user a user
     * @return a list of messageIds. The list consists of all the ids of the messages send from          self to user, and from user to self. The list is sorted, so the id of the latest message          would appear in the end of the list.
     * @throws NullPointerException if there is no messages sent from self to user, or from user to self
     */
    public ArrayList<Integer> getMessageIds(User self, User user) throws NullPointerException {
        if (self.getMessageMap().containsKey(user.getUsername())) {
            return self.getMessageIds(user.getUsername());
        } else {
            throw new NullPointerException("No Messaging History.");
        }
    }

    /**
     * Get messaged users set.
     *
     * @param user the user
     * @return the set
     */
    public Set<String> getMessagedUsers (User user){
        return user.getMessageMap().keySet();
    }

    /**
     * Returns a list of eventIds. The list consists of all the ids of the events that the specified
     * user attend.
     *
     * @param user a user
     * @return returns a list of eventIds. The list consists of all the ids of the events that the specified  user attend
     */
    public ArrayList<Integer> viewEvents(User user){
        return user.getMyEvents();
    }

    /**
     * Deletes message history between user1 and user2 that stores in user1's account.
     *
     * @param user1 a User
     * @param user2 a User
     */
    public void deleteMessageHistoryOfUser(User user1, User user2){
       user1.getMessageMap().remove(user2.getUsername());
    }

    /**
     * Delete announcements from organizer user.
     *
     * @param toDelete the to delete
     * @param allUsers the all users
     * @throws InvalidParameterException the invalid parameter exception
     */
    public void deleteAnnouncementsFromOrganizerUser(User toDelete, ArrayList<User> allUsers) throws InvalidParameterException {
        if (!toDelete.getUserType().equals(UserTypes.ORGANIZER)) {
            throw new InvalidParameterException("user must be an organizer");
        } else {
            for (User user : allUsers) {
                for (Map.Entry group : user.getUserTypeAnnouncements().entrySet()) {
                    String groupName = (String) group.getKey();
                    if (user.getUserTypeAnnouncements().get(groupName).containsKey(toDelete.getUsername())) {
                        user.deleteUserAnnouncementBySender(toDelete.getUsername(), groupName);
                    }
                }
            }
        }
    }

    /**
     * Delete announcements from speaker user.
     *
     * @param toDelete the to delete
     * @param allUsers the all users
     * @throws InvalidParameterException the invalid parameter exception
     */
    public void deleteAnnouncementsFromSpeakerUser(User toDelete, ArrayList<User> allUsers) throws InvalidParameterException {
        if (!toDelete.getUserType().equals(UserTypes.SPEAKER)) {
            throw new InvalidParameterException("user must be a speaker");
        } else {
            for (User user : allUsers) {
                for (Map.Entry event : user.getEventAnnouncements().entrySet()) {
                    String eventName = (String) event.getKey();
                    if (user.getEventAnnouncements().get(eventName).containsKey(toDelete.getUsername())) {
                        user.deleteEventAnnouncementBySenderAndEvent(toDelete.getUsername(), eventName);
                    }
                }
            }
        }
    }

}
