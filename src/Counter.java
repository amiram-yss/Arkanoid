/**
 * @author Amiram Yassif
 *  314985474
 *  ass6
 */
public class Counter {
    private static final short DEFAULT_VALUE = 0;
    private static final short DEFAULT_INCREASE_DECREASE_VALUE = 1;
    private int value;

    /**
     * Constructor starts at 0.
     */
    public Counter() {
        value = DEFAULT_VALUE;
    }

    /**
     * Constructor starts at a given value.
     * @param startingValue starting value.
     */
    public Counter(int startingValue) {
        value = startingValue;
    }

    /**
     * Add number to current count.
     * @param number The number.
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * subtract number from current count.
     * @param number The number.
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Add 1 to current count.
     */
    public void increase() {
        this.increase(DEFAULT_INCREASE_DECREASE_VALUE);
        System.out.println(this.value);
    }

    /**
     * Subtract 1 from current count.
     */
    public void decrease() {
        this.decrease(DEFAULT_INCREASE_DECREASE_VALUE);
    }

    /**
     * Return counter's value.
     * @return The value.
      */
    int getValue() {
        return this.value;
    }
}
