package controllers;

import entities.UserTypes;

/**
 * The type User controller factory.
 */
public class UserControllerFactory {
    /**
     * User factory class, returns Abstract controller for user.
     *
     * @param bundle   the use case bundle
     * @param username the username of the current user
     * @return the abstract controller
     */
    public AbstractController generate(UseCaseBundle bundle, String username) {
        UserTypes userType = bundle.getUserInfoManager().getUserType(bundle.getUserManager().getUser(username));
        switch (userType) {
            case ATTENDEE:
                return new AttendeeController(bundle, username);
            case SPEAKER:
                return new SpeakerController(bundle, username);
            case ORGANIZER:
                return new OrganizerController(bundle, username);
        }
        return null;
    }
}
