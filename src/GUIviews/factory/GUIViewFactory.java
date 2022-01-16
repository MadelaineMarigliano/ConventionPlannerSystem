package GUIviews.factory;

import GUIcontrollers.*;
import GUIcontrollers.admin.GUIAdminCreateUserController;
import GUIcontrollers.admin.GUIAdminDeleteUserController;
import GUIcontrollers.admin.GUIAdminHomeController;
import GUIcontrollers.announcement.GUIAnnouncementController;
import GUIcontrollers.announcement.GUIAnnouncementHomeController;
import GUIcontrollers.attendee.GUIAttendeeController;
import GUIcontrollers.attendee.GUIAttendeeEventController;
import GUIcontrollers.contact.GUIContactAddController;
import GUIcontrollers.contact.GUIContactHomeController;
import GUIcontrollers.contact.GUIContactRemoveController;
import GUIcontrollers.contact.GUIContactViewController;
import GUIcontrollers.login.GUIGuestLoginController;
import GUIcontrollers.login.GUILoginController;
import GUIcontrollers.message.*;
import GUIcontrollers.organizer.GUIOrganizerController;
import GUIcontrollers.organizer.GUIOrganizerEventController;
import GUIcontrollers.organizer.GUIOrganizerNewAnnouncementController;
import GUIcontrollers.room.GUIRoomController;
import GUIcontrollers.schedule.GUIScheduleCreateEventController;
import GUIcontrollers.schedule.GUIScheduleDeleteEventController;
import GUIcontrollers.schedule.GUIScheduleHomeController;
import GUIcontrollers.settings.GUISettingsController;
import GUIcontrollers.settings.GUISettingsPasswordController;
import GUIcontrollers.signup.GUISignUpController;
import GUIcontrollers.speaker.GUISpeakerController;
import GUIcontrollers.speaker.GUISpeakerEventController;
import GUIcontrollers.speaker.GUISpeakerNewAnnouncementController;
import GUIcontrollers.statistics.GUIStatsController;
import GUIviews.AbstractView;
import GUIviews.GUIMainEntryView;
import GUIviews.statistics.GUIStatsView;
import GUIviews.admin.GUIAdminCreateUserView;
import GUIviews.admin.GUIAdminDeleteUserView;
import GUIviews.admin.GUIAdminHomeView;
import GUIviews.announcement.GUIAnnouncementHomeView;
import GUIviews.announcement.GUIAnnouncementView;
import GUIviews.attendee.GUIAttendeeEventView;
import GUIviews.attendee.GUIAttendeeView;
import GUIviews.contact.GUIContactAddView;
import GUIviews.contact.GUIContactHomeView;
import GUIviews.contact.GUIContactRemoveView;
import GUIviews.contact.GUIContactViewView;
import GUIviews.login.GUIGuestLoginView;
import GUIviews.login.GUILoginView;
import GUIviews.message.*;
import GUIviews.organizer.GUIOrganizerEventView;
import GUIviews.organizer.GUIOrganizerNewAnnouncementView;
import GUIviews.organizer.GUIOrganizerView;
import GUIviews.room.GUIRoomView;
import GUIviews.schedule.GUIScheduleCreateEventView;
import GUIviews.schedule.GUIScheduleDeleteEventView;
import GUIviews.schedule.GUIScheduleHomeView;
import GUIviews.settings.GUISettingsPasswordView;
import GUIviews.settings.GUISettingsView;
import GUIviews.signup.GUISignUpView;
import GUIviews.speaker.GUISpeakerEventView;
import GUIviews.speaker.GUISpeakerNewAnnouncementView;
import GUIviews.speaker.GUISpeakerView;
import controllers.AbstractController;
import controllers.ControllerType;

/**
 * The type Gui view factory.
 */
public class GUIViewFactory {
    /**
     * Generate view abstract view.
     *
     * @param controller the controller
     * @return the abstract view
     */
    public AbstractView generateView(AbstractController controller){
        ControllerType type = controller.getControllerType();
        switch (type){
            case ADMINHOME:
                return new GUIAdminHomeView((GUIAdminHomeController) controller);
            case ADMINCREATEUSER:
                return new GUIAdminCreateUserView((GUIAdminCreateUserController) controller);
            case ADMINDELETEUSER:
                return new GUIAdminDeleteUserView((GUIAdminDeleteUserController) controller);
            case ANNOUNCEMENT:
                return new GUIAnnouncementView((GUIAnnouncementController) controller);
            case ANNOUNCEMENTHOME:
                return new GUIAnnouncementHomeView((GUIAnnouncementHomeController) controller);
            case ATTENDEE:
                return new GUIAttendeeView((GUIAttendeeController) controller);
            case CONTACTHOME:
                return new GUIContactHomeView((GUIContactHomeController) controller);
            case CONTACTVIEW:
                return new GUIContactViewView((GUIContactViewController)controller);
            case CONTACTADD:
                return new GUIContactAddView((GUIContactAddController) controller);
            case CONTACTREMOVE:
                return new GUIContactRemoveView((GUIContactRemoveController) controller);
            case EVENTORGANIZER:
                return new GUIOrganizerEventView((GUIOrganizerEventController) controller);
            case EVENTATTENDEE:
                return new GUIAttendeeEventView((GUIAttendeeEventController) controller);
            case EVENTSPEAKER:
                return new GUISpeakerEventView((GUISpeakerEventController) controller);
            case LOGIN:
                return new GUILoginView((GUILoginController) controller);
            case GUESTLOGIN:
                return new GUIGuestLoginView((GUIGuestLoginController) controller);
            case MAINENTRY:
                return new GUIMainEntryView((GUIMainEntryController) controller);
            case MASSMESSAGE:
                return new GUIMessageEntryView((GUIMessageEntryController) controller);
            case MESSAGE:
                return new GUIMessageView((GUIMessageController) controller);
            case MESSAGEHOME:
                return new GUIMessageHomeView((GUIMessageHomeController) controller);
            case MESSAGENEW:
                return new GUINewMessageView((GUINewMessageController) controller);
            case MESSAGEUNREAD:
                return new GUIMessageUnreadView((GUIMessageUnreadController) controller);
            case MESSAGEUNREADHOME:
                return new GUIMessageUnreadHomeView((GUIMessageUnreadHomeController) controller);
            case ORGANIZER:
                return new GUIOrganizerView((GUIOrganizerController) controller);
            case ORGANIZERNEWANNOUNCEMENT:
                return new GUIOrganizerNewAnnouncementView((GUIOrganizerNewAnnouncementController) controller);
            case ROOM:
                return new GUIRoomView((GUIRoomController) controller);
            case SCHEDULEHOME:
                return new GUIScheduleHomeView((GUIScheduleHomeController) controller);
            case SCHEDULECREATEEVENT:
                return new GUIScheduleCreateEventView((GUIScheduleCreateEventController) controller);
            case SCHEDULEDELETEEVENT:
                return new GUIScheduleDeleteEventView((GUIScheduleDeleteEventController) controller);
            case SETTINGS:
                return new GUISettingsView((GUISettingsController) controller);
            case SETTINGSPASSWORD:
                return new GUISettingsPasswordView((GUISettingsPasswordController) controller);
            case SIGNUP:
                return new GUISignUpView((GUISignUpController) controller);
            case SPEAKER:
                return new GUISpeakerView((GUISpeakerController) controller);
            case SPEAKERNEWANNOUNCEMENT:
                return new GUISpeakerNewAnnouncementView((GUISpeakerNewAnnouncementController)controller);
            case STATS:
                return new GUIStatsView((GUIStatsController) controller);
        }
        return null;
    }


}
