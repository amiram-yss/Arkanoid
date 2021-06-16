import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Amiram Yassif
 * 314985474
 * Ass6
 */
public class ScoreIndicator implements Sprite {

    private static final int CONTROL_WIDTH = 800;
    private static final int CONTROL_HEIGHT = 20;
    private static final int HORIZONTAL_MIDDLE = 400;
    private Counter scoreCounter;

    /**
     * Constructor.
     * @param scrCounter Counter.
     */
    public ScoreIndicator(Counter scrCounter) {
        this.scoreCounter = scrCounter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, CONTROL_WIDTH, CONTROL_HEIGHT);
        d.setColor(Color.DARK_GRAY);
        d.drawText(HORIZONTAL_MIDDLE, CONTROL_HEIGHT,
                "Score:" + this.scoreCounter.getValue(), CONTROL_HEIGHT);
    }

    /**
     * Sprite interface's time passed method.
     * Unreachable.
     */
    @Override
    public void timePassed() {
        //Unreachable
    }
}
