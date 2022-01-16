package entities;

/**
 * The type Dbl str tuple.
 */
public class DblStrTuple implements Comparable<DblStrTuple> {
    private Double doub;
    private String string;

    /**
     * Instantiates a new Dbl str tuple.
     *
     * @param doub   the doub
     * @param string the string
     */
    public DblStrTuple(Double doub, String string) {
        this.doub = doub;
        this.string = string;
    }

    /**
     * Gets double.
     *
     * @return the double
     */
    public Double getDouble() {
        return doub;
    }

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        return string;
    }


    @Override
    public int compareTo(DblStrTuple o) {
        return doub.compareTo(o.getDouble());
    }
}
