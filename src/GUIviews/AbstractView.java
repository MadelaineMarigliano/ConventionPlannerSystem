package GUIviews;

import GUIApplication.GUIApplication;
import javafx.scene.Scene;

/**
 * The type Abstract view.
 */
public abstract class AbstractView {
    /**
     * The Application.
     */
    protected GUIApplication application;

    /**
     * Set application.
     *
     * @param application the application
     */
    public void setApplication(GUIApplication application){
        this.application = application;
    }

    /**
     * Create scene scene.
     *
     * @return the scene
     */
    public abstract Scene createScene();
}