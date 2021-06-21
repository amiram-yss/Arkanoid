import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Amiram Yassif
 * 314985474
 * Ass6
 */
public class ScoreIndicator implements Sprite {

    private static final int CONTROL_WIDTH = 800;
    private static final int FONT_SIZE = 18;
    private static final int HORIZONTAL_MIDDLE = 350;
    private static final int NAME_X_VALUE = HORIZONTAL_MIDDLE + 150;
    private final String name;
    private final Counter scoreCounter;

    /**
     * Constructor.
     * @param scrCounter Counter.
     * @param levelName Level's name.
     */
    public ScoreIndicator(Counter scrCounter, String levelName) {
        this.scoreCounter = scrCounter;
        this.name = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, CONTROL_WIDTH, FONT_SIZE);
        d.setColor(Color.DARK_GRAY);
        d.drawText(HORIZONTAL_MIDDLE, FONT_SIZE,
                "Score:" + this.scoreCounter.getValue(), FONT_SIZE);
        d.drawText(NAME_X_VALUE, FONT_SIZE,
                name, FONT_SIZE);
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
