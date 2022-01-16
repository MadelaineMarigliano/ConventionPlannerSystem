package entities;

import java.io.Serializable;

/**
 * An enum used to identify the type of user.
 */
public enum UserTypes implements Serializable {
    /**
     * Represents an organizer user.
     */
    ORGANIZER,
    /**
     * Represents an attendee user.
     */
    ATTENDEE,
    /**
     * Represents a speaker user.
     */
    SPEAKER,

    /**
     * Vip user types.
     */
    VIP
}
