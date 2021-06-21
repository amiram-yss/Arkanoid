import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructor.
     */
    public SpriteCollection() {
        sprites = new ArrayList<>();
    }

    /**
     * Add a sprite to the list.
     *
     * @param s The new sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Draw the sprites on the canvas.
     *
     * @param d The canvas.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> cpy = new ArrayList<>(sprites);
        for (Sprite s : cpy) {
            s.drawOn(d);
        }
    }

    /**
     * Notifies all time lapse has passed.
     */
    public void notifyAllTimePassed() {
        int i = 0;
        List<Sprite> cpy = new ArrayList<>(sprites);
        for (Sprite s : cpy) {
            s.timePassed();
        }
    }

    /**
     * Remove a sprite from the list.
     *
     * @param s The sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
}
