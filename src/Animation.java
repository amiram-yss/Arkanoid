import biuoop.DrawSurface;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public interface Animation {
    /**
     * Put the current frame of an animation, on a surface.
     * @param d The drawing surface to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should abort the animation?
     * @return  True if should. False otherwise.
     */
    boolean shouldStop();
}
