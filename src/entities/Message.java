package entities;

import java.io.Serializable;

/**
 * The type Message.
 */
public class Message implements Serializable {
    private String sentFrom;
    private String sentTo;
    private String message;
    private Integer ID;

    /**
     * Instantiates a new Message.
     *
     * @param sentFrom Username of user who send this messsage
     * @param sentTo   Username of user who is receiving this message
     * @param message  the message
     * @param ID       the message ID
     */
    public Message(String sentFrom, String sentTo, String message, Integer ID) {
        this.sentFrom = sentFrom;
        this.sentTo = sentTo;
        this.message = message;
        this.ID = ID;
    }

    /**
     * Gets sent from.
     *
     * @return Username of user who send message
     */
    public String getSentFrom() {
        return sentFrom;
    }

    /**
     * Gets sent to.
     *
     * @return Username of user who received this message
     */
    public String getSentTo() {
        return sentTo;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    public String toString() {
        return "From: " + sentFrom + "\n" + "To: " + sentTo + "\n" + message;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getID() {
        return ID;
    }


}


