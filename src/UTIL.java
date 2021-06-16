import java.util.List;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class UTIL {
    /**
     * For debug use, for breakpoint engagement.
     */
    public static final boolean DEBUG_MODE = false;

    /**
     * For debug use, for breakpoint engagement.
     */
    public static void nop() {
    }

    /**
     * Returns if d1 = d2 without java calculation errors.
     *
     * @param d1 first param
     * @param d2 second param
     * @return are these equals to the level of 8 digs after the decimal.
     */
    public static boolean equals(double d1, double d2) {
        return (Math.abs(d1 - d2) < Math.pow(10, -8));
    }

    /**
     * Prints a directions' list.
     * @param d the list.
     */
    public static void printDirectionsList(List<DIRECTION> d) {
        for (DIRECTION i : d) {
            System.out.println(i);
        }
    }

    /**
     * Is a value between 2 other values?
     * @param val   value.
     * @param x1    Range edge 1.
     * @param x2    Range edge 2.
     * @return      is the value between x1 and x2?
     */
    public static boolean isBetween(double val, double x1, double x2) {
        boolean btn = (val < x1) && (val > x2);
        if ((val > x1) && (val < x2)) {
            btn = true;
        }
        return btn;
    }

    /**
     * Is a value between or equals 2 other values?
     * @param val   value.
     * @param x1    Range edge 1.
     * @param x2    Range edge 2.
     * @return      is the value between or equals x1 and x2?
     */
    public static boolean isBetweenOrEquals(double val, double x1, double x2) {
        return isBetween(val, x1, x2) || equals(val, x1) || equals(val, x2);
    }
}
