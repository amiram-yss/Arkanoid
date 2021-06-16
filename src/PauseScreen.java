import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class PauseScreen implements Animation {
    private final KeyboardSensor keyboard;
    private final Game caller;
    private boolean stop;

    /**
     * Constructor.
     * @param k Keyboard sensor.
     * @param callerRef The animation called this.
     */
    public PauseScreen(KeyboardSensor k, Game callerRef) {
        this.keyboard = k;
        this.stop = false;
        this.caller = callerRef;
    }
    /**
     * Put the current frame of an animation, on a surface.
     *
     * @param d The drawing surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
            caller.getRunner().run(caller);
        }
    }

    /**
     * Should abort the animation?
     *
     * @return True if should. False otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
