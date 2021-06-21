import java.util.List;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public interface LevelInformation {
    /**
     * Number of balls.
     *
     * @return Number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return List of all velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return Paddle speed.
     */
    int paddleSpeed();

    /**
     * @return Paddle width.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return Level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return background sprite.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return Blocks list.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return Number of blocks that should be removed
     * before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}
