package entities;

import java.security.InvalidParameterException;

/**
 * The type Vip attendee.
 */
public class VIPAttendee extends User {
    private String CHARSET="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private int CODE_LEN=8;
    /**
     * The Guest code.
     */
    String guestCode;

    /**
     * Instantiates a new User.
     *
     * @param first    the first
     * @param last     the last
     * @param username the username
     * @param password the password
     * @throws InvalidParameterException the invalid parameter exception
     */
    public VIPAttendee(String first, String last, String username, String password) throws InvalidParameterException {
        super(first, last, username, password, UserTypes.ATTENDEE);
        do {
            guestCode = generateCode(CODE_LEN);
        } while(guestCode.equals(password));
        setVIP(true);
    }

    private String generateCode(int n) {
        StringBuilder code = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (CHARSET.length() * Math.random());
            code.append(CHARSET.charAt(index));
        }
        return code.toString();
    }

    @Override
    public int calculateOccupancy() {
        return 2;
    }

    /**
     * Verify guest code boolean.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean verifyGuestCode(String s) {
        return guestCode.equals(s);
    }

    @Override
    public String getGuestCode() {
        return guestCode;
    }
}
