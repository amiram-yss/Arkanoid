/**
 * @author Amiram Yassif
 * 314985474
 * Ass6
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl    Hit listener.
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl    Hit listener.
     */
    void removeHitListener(HitListener hl);
}
