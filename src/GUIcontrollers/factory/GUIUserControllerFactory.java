package GUIcontrollers.factory;

import GUIcontrollers.attendee.GUIAttendeeController;
import GUIcontrollers.organizer.GUIOrganizerController;
import GUIcontrollers.speaker.GUISpeakerController;
import controllers.*;
import entities.UserTypes;

/**
 * The type Gui user controller factory.
 */
public class GUIUserControllerFactory {
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
                return new GUIAttendeeController(bundle, username);
            case SPEAKER:
                return new GUISpeakerController(bundle, username);
            case ORGANIZER:
                return new GUIOrganizerController(bundle, username);
        }
        return null;
    }
}
