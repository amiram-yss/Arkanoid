/**
 * @author Amiram Yassif
 * 314985474
 * Ass6
 */
public class ScoreTrackingListener implements HitListener {

    private static final short POINTS_INCREASE_FOR_BLOCK = 5;
    private Counter currentScore;

    /**
     * Constructor.
     * @param scrCounter counter.
     */
    public ScoreTrackingListener(Counter scrCounter) {
        this.currentScore = scrCounter;
    }

    /**
     * When hit happens, run this method.
     *
     * @param beingHit The block which has been hit.
     * @param hitter   The ball which hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //Each hit with a regular block.
        this.currentScore.increase(POINTS_INCREASE_FOR_BLOCK);
    }
}
