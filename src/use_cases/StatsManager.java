package use_cases;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The type Stats manager.
 */
public class StatsManager implements Serializable {
    private int numLogins;
    private int sentMessages;
    private HashMap<String, Integer> logins;

    /**
     * Instantiates a new Stats manager.
     */
    public StatsManager() {
        numLogins = 0;
        sentMessages = 0;
        logins = new HashMap<>();
    }

    /**
     * Gets num logins.
     *
     * @return the num logins
     */
    public int getNumLogins() {
        return numLogins;
    }

    /**
     * Gets logins.
     *
     * @return the logins
     */
    public HashMap<String, Integer> getLogins() {
        return logins;
    }

    /**
     * Login.
     *
     * @param username the username
     */
    public void login(String username) {
        if (logins.containsKey(username)) {
            logins.put(username, logins.get(username) + 1);
        } else {
            logins.put(username, 1);
        }
        numLogins++;
    }

    /**
     * Gets sent messages.
     *
     * @return the sent messages
     */
    public int getSentMessages() {
        return sentMessages;
    }

    /**
     * Messages sent.
     *
     * @param n the n
     */
    public void messagesSent(int n) {
        sentMessages += n;
    }


}
