package controllers;

/**
 * The type Abstract controller.
 */
public abstract class AbstractController {
    private UseCaseBundle bundle;
    private int popNum = 0;
    private ControllerType controllerType;

    /**
     * Instantiates a new Abstract controller.
     *
     * @param bundle the bundle
     */
    public AbstractController (UseCaseBundle bundle) {
        this.bundle = bundle;
    }

    /**
     * Run abstract controller.
     *
     * @return the abstract controller
     */
    public abstract AbstractController run();

    /**
     * Gets pop num.
     *
     * @return the pop num
     */
    public int getPopNum() {
        return popNum;
    }

    /**
     * Sets pop num.
     *
     * @param n the n
     */
    public void setPopNum(int n) {
        popNum = n;
    }

    /**
     * Gets bundle.
     *
     * @return the bundle
     */
    public UseCaseBundle getBundle() {
        return bundle;
    }

    /**
     * Set controller type.
     *
     * @param controllerType the controller type
     */
    public void setControllerType(ControllerType controllerType){ this.controllerType = controllerType; }

    /**
     * Get controller type controller type.
     *
     * @return the controller type
     */
    public ControllerType getControllerType(){ return controllerType; }
}
