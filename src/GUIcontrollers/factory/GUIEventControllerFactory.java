package GUIcontrollers.factory;

import GUIcontrollers.attendee.GUIAttendeeEventController;
import GUIcontrollers.organizer.GUIOrganizerEventController;
import GUIcontrollers.speaker.GUISpeakerEventController;
import controllers.*;
import entities.UserTypes;

/**
 * The type Gui event controller factory.
 */
public class GUIEventControllerFactory {
    /**
     * Generate abstract controller.
     *
     * @param bundle   the bundle
     * @param username the username
     * @return the abstract controller
     */
    public AbstractController generate(UseCaseBundle bundle, String username) {
        UserTypes userType = bundle.getUserInfoManager().getUserType(bundle.getUserManager().getUser(username));
        switch (userType) {
            case ATTENDEE:
                return new GUIAttendeeEventController(bundle, username);
            case SPEAKER:
                return new GUISpeakerEventController(bundle, username);
            case ORGANIZER:
                return new GUIOrganizerEventController(bundle, username);

        }
        return null;
    }
}
