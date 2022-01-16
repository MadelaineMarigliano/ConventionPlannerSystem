package use_cases;

import entities.Message;
import entities.User;
import entities.UserTypes;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * System which sends and views messages.
 */
public class MessagingSystem implements Serializable {

    private Integer numMessages = 1;
    private HashMap<Integer, Message> allMessages;

    /**
     * Instantiates a new Messaging system.
     */
    public MessagingSystem() {
        this.allMessages = new HashMap<>();
    }

    /**
     * Sends message.
     *
     * @param from    User who message is from
     * @param to      User who message is to
     * @param message the message
     * @throws InvalidParameterException Throws invalid parameter exception if User from is not authorized to send message to User to
     */
    public void sendMessage(User from, User to, String message) throws InvalidParameterException {
        if (from.getUserType() == UserTypes.SPEAKER && to.getUserType() == UserTypes.SPEAKER || from.getUserType() == UserTypes.SPEAKER && to.getUserType() == UserTypes.ORGANIZER) {
            throw new InvalidParameterException("You can only message attendees");
        } else {
            Message m = new Message(from.getUsername(), to.getUsername(), message, numMessages);
            allMessages.put(m.getID(), m);
            numMessages++;

            to.addMessageId(from.getUsername(), m.getID());
            to.addUnreadMessageFrom(from);

            from.addMessageId(to.getUsername(), m.getID());
        }

    }

    /**
     * Sends message to a list of recipients
     *
     * @param sender     The user sending the message
     * @param recipients The list of Users who will be recieving the message
     * @param message    the message
     */
    public void massMessage(User sender, ArrayList<User> recipients, String message) {
        if (recipients.size() != 0) {
            for (User person : recipients) {
                this.sendMessage(sender, person, message);
            }
        }
    }

//    /**
//     * View message string.
//     *
//     * @param self User who is viewing message
//     * @param from User whose message you want to view
//     * @return the message
//     * @throws NullPointerException throws null pointer exception when no message was received from this user
//     */
//    public String viewMessage(User self, User from) throws NullPointerException {
//        if (self.getMessageMap().containsKey(from.getUsername())) {
//            ArrayList<Integer> messages = self.getMessageMap().get(from.getUsername());
//            Collections.reverse(messages);
//            for (Integer i : messages) {
//                Collections.reverse(messages);
//                if (allMessages.get(i).getSentFrom().equals(from.getUsername())) {
//                    return allMessages.get(i).getMessage();
//                }
//            }
//        }
//
//        throw new NullPointerException("No message received.");
//
//    }

    /**
     * Has unread messages.
     *
     * @param self the self
     * @throws InvalidParameterException the invalid parameter exception
     */
    public void hasUnreadMessages(User self) throws InvalidParameterException {
        if (!self.hasUnreadMessages()) {
            throw new InvalidParameterException("You have no unread messages!");
        }
    }

    /**
     * Gets unread messages.
     *
     * @param self the self
     * @param from the from
     * @return the unread messages
     * @throws InvalidParameterException the invalid parameter exception
     */
    public ArrayList<String> getUnreadMessages(User self, User from) throws InvalidParameterException {
        if (self.hasUnreadMessageFrom(from)) {
            ArrayList<Integer> messages = self.getMessageIds(from.getUsername());
            ArrayList<Integer> messagesCopy = (ArrayList<Integer>) messages.clone();
            // reverse to get newest messages first
            Collections.reverse(messagesCopy);

            Integer unread = self.getUnreadMessageCountFrom(from);
            ArrayList<String> unreadMessages = new ArrayList<>();
            int i = 0;
            int size = 0;
            while (i < unread && size < messagesCopy.size()) {
                Integer messageId = messagesCopy.get(size);
                if (allMessages.get(messageId).getSentFrom().equals(from.getUsername())) {
                    unreadMessages.add(getMessage(messageId).getMessage());
                    i++;
                }
                size++;
            }

            // reverse to print out messages in order
            Collections.reverse(unreadMessages);
            // updates unreadMessageCountFrom and unreadMessage
            self.deleteUnreadMessageCountFrom(from);
            // 'unread' messages are being read
            self.deleteUnreadMessage(unread);

            return unreadMessages;
        } else {
            throw new InvalidParameterException("No unread messages from this user.");
        }
    }


    /**
     * Gets num messages.
     *
     * @return the number of messages
     */
    public Integer getNumMessages() {
        return numMessages;
    }


    /**
     * Gets message.
     *
     * @param i The message ID
     * @return the message objectio
     * @throws InvalidParameterException throws invalid parameter exception when message does not exist
     */
    public Message getMessage(Integer i) throws InvalidParameterException {
        if (!allMessages.containsKey(i)) {
            throw new InvalidParameterException("The message doesn't exist.");
        } else {
            return allMessages.get(i);
        }

    }

    /**
     * Gets message string.
     *
     * @param i the message ID
     * @return the message
     * @throws InvalidParameterException throws invalid parameter exception when message does not exist
     */
    public String getMessageString(Integer i) throws InvalidParameterException {
        if (i > numMessages) {
            throw new InvalidParameterException("The message doesn't exist.");
        } else {
            return allMessages.get(i).toString();
        }
    }

    /**
     * Deleted messages user send or received from all messages
     *
     * @param username the username of the user you want to delete
     */
    public void deleteMessagesOfUser(String username) {
        Collection<Message> messages = allMessages.values();
        ArrayList<Message> messagesCopy = new ArrayList<>(messages);
        for (Message m : messagesCopy) {
            if (m.getSentFrom().equals(username) || m.getSentTo().equals(username)) {
                allMessages.remove(m.getID());
            }
        }
    }

    /**
     * Send event announcement.
     *
     * @param sender    the sender
     * @param sendTo    the send to
     * @param message   the message
     * @param eventName the event name
     * @throws InvalidParameterException the invalid parameter exception
     */
    public void sendEventAnnouncement(User sender, User sendTo, String message, String eventName) throws InvalidParameterException {
        if (message.isEmpty()) {
            throw new InvalidParameterException("Cannot send an empty announcement.");
        } else {
            Message m = new Message(sender.getUsername(), sendTo.getUsername(), message, numMessages);
            allMessages.put(m.getID(), m);
            numMessages++;

            sendTo.addEventAnnouncement(eventName, sender.getUsername(), m.getID());
        }
    }

    /**
     * Send user type announcement.
     *
     * @param sender  the sender
     * @param sendTo  the send to
     * @param message the message
     * @param group   the group
     * @throws InvalidParameterException the invalid parameter exception
     */
    public void sendUserTypeAnnouncement(User sender, ArrayList<User> sendTo, String message, String group) throws InvalidParameterException {
        if (sendTo.isEmpty()) {
            throw new InvalidParameterException("There are no users of this type");
        } else {
            if (message.isEmpty()) {
                throw new InvalidParameterException("Message is empty");
            } else {
                for (User user : sendTo) {
                    Message m = new Message(sender.getUsername(), user.getUsername(), message, numMessages);
                    numMessages++;

                    allMessages.put(m.getID(), m);

                    user.addUserTypeAnnouncement(sender.getUsername(), group, m.getID());
                }
                if (sender.getUserType().equals(UserTypes.ORGANIZER) && group.equals("SPEAKERS")) {
                    Message m = new Message(sender.getUsername(), sender.getUsername(), message, numMessages);
                    numMessages++;

                    allMessages.put(m.getID(), m);

                    sender.addUserTypeAnnouncement(sender.getUsername(), group, m.getID());
                }
            }
        }
    }


}
