import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class YouLoseScreen implements Animation {
    private final KeyboardSensor keyboard;
    private GameLevel caller;
    private int score;
    /**
     * Constructor.
     * @param k Keyboard sensor.
     * @param gameScore current game score.
     */
    public YouLoseScreen(KeyboardSensor k, int gameScore) {
        this.score = gameScore;
        this.keyboard = k;
    }

    /**
     * Constructor.
     * @param k Keyboard sensor.
     * @param callerRef The animation called this.
     * @param gameScore current game score.
     */
    public YouLoseScreen(int gameScore, KeyboardSensor k, GameLevel callerRef) {
        this.score = gameScore;
        this.keyboard = k;
        this.caller = callerRef;
    }
    /**
     * Put the current frame of an animation, on a surface.
     *
     * @param d The drawing surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(
                10,
                d.getHeight() / 2,
                "You are a loser and nobody likes you :'(. SCORE: " + score,
                32
        );
    }

    /**
     * Should abort the animation?
     *
     * @return True if should. False otherwise.
     */
    @Override
    public boolean shouldStop()  {
        return true;
    }
}
