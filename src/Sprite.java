import biuoop.DrawSurface;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d The surface.
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
