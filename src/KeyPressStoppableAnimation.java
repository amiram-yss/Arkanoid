import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class KeyPressStoppableAnimation implements Animation {

    private final Animation content;
    private final String key;
    private final KeyboardSensor keyboardSensor;
    private boolean running;
    private boolean isAlreadyPressed;
    /**
     * Constructor.
     * @param sensor Keyboard sensor.
     * @param k Key to listen to.
     * @param animation Content of the stoppable animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String k, Animation animation) {
        this.content = animation;
        this.key = k;
        this.keyboardSensor = sensor;
        running = true;
        isAlreadyPressed = true;
    }

    /**
     * Put the current frame of an animation, on a surface.
     *
     * @param d The drawing surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.keyboardSensor.isPressed(key)) {
            isAlreadyPressed = false;
        }
        if (this.keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)
                && !isAlreadyPressed) {
            this.running = false;
        }
        content.doOneFrame(d);

    }

    /**
     * Should abort the animation?
     *
     * @return True if should. False otherwise.
     */
    @Override
    public boolean shouldStop() {
        return !running;
    }
}
