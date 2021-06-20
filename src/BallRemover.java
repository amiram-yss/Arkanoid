/**
 * @author Amiram Yassif
 * 314985474
 * Ass6
 */
public class BallRemover implements HitListener {

    private Counter counter;
    private GameLevel gameLevel;

    /**
     * Constructor.
     * @param gameLevel      The game.
     * @param counter   The game's counter.
     */
    public BallRemover(GameLevel gameLevel, Counter counter) {
        this.counter = counter;
        this.gameLevel = gameLevel;
    }

    /**
     * Event when ball hits a block.
     * @param beingHit  The block.
     * @param hitter    The ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        gameLevel.removeBall(hitter);
    }
}
