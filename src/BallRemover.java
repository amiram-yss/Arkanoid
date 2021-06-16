/**
 * @author Amiram Yassif
 * 314985474
 * Ass6
 */
public class BallRemover implements HitListener {

    private Counter counter;
    private Game game;

    /**
     * Constructor.
     * @param game      The game.
     * @param counter   The game's counter.
     */
    public BallRemover(Game game, Counter counter) {
        this.counter = counter;
        this.game = game;
    }

    /**
     * Event when ball hits a block.
     * @param beingHit  The block.
     * @param hitter    The ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        game.removeBall(hitter);
    }
}
