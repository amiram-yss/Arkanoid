import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class PauseScreen implements Animation {
    private final KeyboardSensor keyboard;
    private GameLevel caller;
//    private boolean stop;

    /**
     * Constructor.
     * @param k Keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
//        this.stop = false;
    }
    /**
     * Put the current frame of an animation, on a surface.
     *
     * @param d The drawing surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * Should abort the animation?
     *
     * @return True if should. False otherwise.
     */
    @Override
    public boolean shouldStop()  {
//        return this.stop;
        return true;
    }
}
