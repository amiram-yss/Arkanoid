import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {

    final Animation content;
    final String key;
    final KeyboardSensor keyboardSensor;
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
    }

    /**
     * Put the current frame of an animation, on a surface.
     *
     * @param d The drawing surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        content.doOneFrame(d);
    }

    /**
     * Should abort the animation?
     *
     * @return True if should. False otherwise.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
