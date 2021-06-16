/**
 * @author Amiram Yassif
 * 314985474
 * Ass6
 */
public class BlockRemover implements HitListener {

    private Counter counter;
    private Game game;

    /**
     * Constructor.
     * @param game      The game.
     * @param counter   The game's counter.
     */
    public BlockRemover(Game game, Counter counter) {
        this.counter = counter;
        this.game = game;
    }

    /**
     * Hit event listener.
     * @param beingHit  Block.
     * @param hitter    Ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        game.blockHit(beingHit, hitter);
    }
}
