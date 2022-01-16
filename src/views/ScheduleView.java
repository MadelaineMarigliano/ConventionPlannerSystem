package views;

/**
 * The type Schedule presenter.
 */
public class ScheduleView extends AbstractView {
    /**
     * Display organizer menu.
     */
    public void displayOrganizerMenu() {
        System.out.println("1. Create an event");
        System.out.println("2. Delete an event");
        System.out.println("3. Return");
    }

    /**
     * Option prompt.
     */
    public void optionPrompt() {
        System.out.println("Select an option:");
    }

    /**
     * Option error.
     */
    public void optionError() {
        System.out.println("Please enter a number representing one of the options.");
    }

    /**
     * Event name prompt.
     */
    public void eventNamePrompt() {
        System.out.println("Enter the name of the event");
    }

    /**
     * Event created.
     */
    public void eventCreated() {
        System.out.println("Event was created successfully for today");
    }

    /**
     * Event Deleted.
     */
    public void eventDeleted() {
        System.out.println("Event was deleted successfully");
    }


    /**
     * Room prompt.
     */
    public void roomPrompt() {
        System.out.println("Enter the room number");
    }


    /**
     * Capacity prompt.
     */
    public void capacityPrompt() { System.out.println("Enter the capacity"); }

    /**
     * Speaker prompt.
     */
    public void speakerPrompt() {
        System.out.println("Enter the usernames of the speakers separated by commas (if none press enter)");
    }

    /**
     * Hour start prompt.
     */
    public void hourStartPrompt() {
        System.out.println("Enter the hour (e.g. 13) the event starts at");
    }

    /**
     * Hour end prompt.
     */
    public void hourEndPrompt() {
        System.out.println("Enter the hour (e.g. 13) the event ends at");
    }

    /**
     * Min prompt.
     */
    public void minPrompt() {
        System.out.println("Enter the minute (e.g. 46)");
    }


}

