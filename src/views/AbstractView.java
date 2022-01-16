package views;

/**
 * The type Abstract view.
 */
public abstract class AbstractView {
    /**
     * Print exception.
     *
     * @param e the e
     */
    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }
}
