/**
 * @author Amiram Yassif
 * 314985474
 * Ass6
 */
public class BlockRemover implements HitListener {

    private Counter counter;
    private GameLevel gameLevel;

    /**
     * Constructor.
     * @param gameLevel      The game.
     * @param counter   The game's counter.
     */
    public BlockRemover(GameLevel gameLevel, Counter counter) {
        this.counter = counter;
        this.gameLevel = gameLevel;
    }

    /**
     * Hit event listener.
     * @param beingHit  Block.
     * @param hitter    Ball.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        gameLevel.blockHit(beingHit, hitter);
    }
}
