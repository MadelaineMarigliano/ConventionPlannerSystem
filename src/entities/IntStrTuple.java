package entities;

/**
 * The type Int str tuple.
 */
public class IntStrTuple implements Comparable<IntStrTuple> {
    private Integer integer;
    private String string;

    /**
     * Instantiates a new Int str tuple.
     *
     * @param integer the integer
     * @param string  the string
     */
    public IntStrTuple(Integer integer, String string) {
        this.integer = integer;
        this.string = string;
    }

    /**
     * Gets integer.
     *
     * @return the integer
     */
    public Integer getInteger() {
        return integer;
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
    public int compareTo(IntStrTuple o) {
        return integer.compareTo(o.getInteger());
    }
}
