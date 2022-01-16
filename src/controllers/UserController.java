package controllers;

import controllers.AbstractController;

/**
 * The type User controller.
 */
public abstract class UserController extends AbstractController {
    private String username;

    /**
     * Instantiates a new User controller.
     *
     * @param bundle   the bundle
     * @param username the username
     */
    public UserController(UseCaseBundle bundle, String username) {
        super(bundle);
        this.username = username;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }
}
