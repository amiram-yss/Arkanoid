/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit The block being hit.
     * @param hitter The ball hitting the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}