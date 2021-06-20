import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /**
     * Properties.
     */
    private final Rectangle rectangle;
    private final Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor.
     *
     * @param r The rectangle.
     * @param c Color.
     */
    public Block(Rectangle r, Color c) {
        rectangle = r;
        color = c;
        hitListeners = new ArrayList<>();
    }

    /**
     * Constructor with default color (BLACK).
     *
     * @param r rectangle
     */
    public Block(Rectangle r) {
        rectangle = r;
        color = Color.BLACK;
        hitListeners = new ArrayList<>();
    }

    /**
     * @return The rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * Calculates ball's new speed after hitting a Block.
     *
     * @param collisionPoint  Where it hit.
     * @param currentVelocity Current ball velocity
     * @return new Ball velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        //Check if the ball hits a corner. In this case, directionList size = 2.
        List<DIRECTION> directionList
                = this.rectangle.directionOfEdgeWithThePoint(collisionPoint);
        //If hit on one side, reflect one component of velocity.
        if (isOnSingleLine(directionList)) {
            return updateVelocityForEdge(currentVelocity, directionList);
        }
        //If hit corner, make full reflect.
        return updateVelocityForCorner(currentVelocity);
    }

    /**
     * Returns the velocity in case of hitting a corner.
     *
     * @param currentVelocity ball's current velocity.
     * @return full reflected velocity.
     */
    private Velocity updateVelocityForCorner(Velocity currentVelocity) {
        //return new Velocity(-currentVelocity.dx, -currentVelocity.dy);
        return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
    }

    /**
     * In case of hitting an edge, return reflected velocity in a single axis.
     *
     * @param currentVelocity ball's current velocity
     * @param directionList   On which edge the ball hits
     * @return A correct velocity (reflected in 1 axis)
     */
    private Velocity updateVelocityForEdge(Velocity currentVelocity, List<DIRECTION> directionList) {
        //Velocity vtr = new Velocity(currentVelocity.dx, currentVelocity.dy);
        Velocity vtr = new Velocity(currentVelocity.getDx(),
                currentVelocity.getDy());
        //If hit upper or lower edges, reflect Y component.
        if (directionList.get(0) == DIRECTION.UP
                || directionList.get(0) == DIRECTION.DOWN) {
            //vtr.dy *= -1;
            vtr.setDy(-vtr.getDy());
        }
        //Otherwise, reflect X.
        if (directionList.get(0) == DIRECTION.LEFT
                || directionList.get(0) == DIRECTION.RIGHT) {
            //vtr.dx *= -1;
            vtr.setDx(-vtr.getDx());
        }
        //If size>1, check it as well
        if (directionList.size() == 2) {
            if (directionList.get(1) == DIRECTION.UP
                    || directionList.get(1) == DIRECTION.DOWN) {
                //vtr.dy *= -1;
                vtr.setDy(-vtr.getDy());
            }
            if (directionList.get(1) == DIRECTION.LEFT
                    || directionList.get(1) == DIRECTION.RIGHT) {
                //vtr.dx *= -1;
                vtr.setDx(-vtr.getDx());
            }
        }
        //Returns the updated velocity.
        return vtr;
    }

    /**
     * @param directionList List of directions
     * @return Does the list represent a Line(true) or a corner/non(false)
     * Happens if and only if list's size = 1.
     */
    private boolean isOnSingleLine(List<DIRECTION> directionList) {
        return directionList.size() == 1;
    }

    /**
     * Draws the block on the surface.
     *
     * @param d The surface.
     */
    public void drawOn(DrawSurface d) {
        //Paint back-color
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        //Paint black frame.
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * What happens when time passes.
     * Currently, does nothing.
     */
    @Override
    public void timePassed() {
        //Unreachable.
    }

    /**
     * Check if a point is inside a block.
     *
     * @param p the point
     * @return true if it is set in the area of the block.
     */
    public boolean isPointInside(Point p) {
        //If the point is not in the area of the x, ret false.
        if (!(UTIL.isBetween(p.getX(),
                rectangle.getPoints()[0].getX(),
                rectangle.getPoints()[1].getX())
                || UTIL.equals(p.getX(), rectangle.getPoints()[0].getX())
                || UTIL.equals(p.getX(), rectangle.getPoints()[1].getX())
        )) {
            return false;
        }
        //If is on the Y range as well, ret true, false otherwise.
        return UTIL.isBetween(p.getY(),
                rectangle.getPoints()[2].getY(),
                rectangle.getPoints()[1].getY())
                || UTIL.equals(p.getY(), rectangle.getPoints()[2].getY())
                || UTIL.equals(p.getY(), rectangle.getPoints()[1].getY());
    }

    /**
     * Notifies event when got hit.
     *
     * @param hitter The ball which hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(
                this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Remove the block from the game.
     *
     * @param g The game.
     */
    public void removeFromGame(GameLevel g) {
        //Remove from sprites.
        g.getSprites().removeSprite(this);
        //Remove from collidables.
        g.getEnvironment().removeCollidable(this);
    }

    /**
     * Add hit event handler.
     *
     * @param hl Hit listener object.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hit event handler.
     *
     * @param hl Hit listener object.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
