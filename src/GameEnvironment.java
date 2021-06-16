import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class GameEnvironment {
    public static final double SCREEN_HEIGHT = 600;
    public static final double SCREEN_WIDTH = 800;
    public static final double BORDER_SHORT_EDGE = 20;
    public static final int UPPER_BORDER_INDEX = 0;
    public static final int RIGHT_BORDER_INDEX = 1;
    public static final int LOWER_BORDER_INDEX = 2;
    public static final int LEFT_BORDER_INDEX = 3;
    public static final int NUM_BLOCK_LINES = 6;
    public static final int MAX_BLOCKS_IN_LINE = 12;
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HIGHT = 20;
    public static final int STARTING_INDEX_OF_BLOCKS = 3;

    /**
     * Getter for collidables.
     * @return Collidables.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * Setter for collidables.
     * @param collidableList Collidables.
     */
    public void setCollidables(List<Collidable> collidableList) {
        this.collidables = collidableList;
    }

    private List<Collidable> collidables;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        collidables = new ArrayList<>();
        setUpperBorderBlock();
        setRightBorderBlock();
        setLowerBorderBlock();
        setLeftBorderBlock();
    }

    /**
     * Adds hit listeners.
     * @param hl    Hit listener object.
     */
    public void addHitListener(HitListener hl) {
        this.getLowerBorderBlock().addHitListener(hl);
        this.getUpperBorderBlock().addHitListener(hl);
        this.getRightBorderBlock().addHitListener(hl);
        this.getLeftBorderBlock().addHitListener(hl);
    }

    /**
     * Sets the death region.
     * @param hl    Hit listener object.
     */
    public void addHitListenerToLowerBorderBlock(HitListener hl) {
        this.getLowerBorderBlock().addHitListener(hl);
    }

    /**
     * Set upper border.
     */
    private void setUpperBorderBlock() {
        Block upperBlock = new Block(new Rectangle(
                new Point(0, BORDER_SHORT_EDGE),
                SCREEN_WIDTH,
                BORDER_SHORT_EDGE
        ),
                Color.blue);
        this.addCollidable(upperBlock);
    }

    /**
     * Set lower border.
     */
    private void setLowerBorderBlock() {
        this.addCollidable(
                new Block(
                        new Rectangle(
                                new Point(
                                        0,
                                        SCREEN_HEIGHT),
                                SCREEN_WIDTH,
                                BORDER_SHORT_EDGE
                        ),
                        Color.blue
                )
        );
    }

    /**
     * Set right border.
     */
    private void setRightBorderBlock() {
        Block rightBlock = new Block(
                new Rectangle(
                        new Point(SCREEN_WIDTH - BORDER_SHORT_EDGE,
                                BORDER_SHORT_EDGE),
                        BORDER_SHORT_EDGE,
                        SCREEN_HEIGHT - BORDER_SHORT_EDGE
                ),
                Color.blue
        );
        this.addCollidable(rightBlock);
    }

    /**
     * Set left border.
     */
    private void setLeftBorderBlock() {
        Block leftBlock = new Block(
                new Rectangle(
                        new Point(0, BORDER_SHORT_EDGE),
                        BORDER_SHORT_EDGE,
                        SCREEN_HEIGHT - BORDER_SHORT_EDGE
                ),
                Color.blue
        );
        this.addCollidable(leftBlock);
    }

    /**
     * Upper block getter.
     * @return  Upper block.
     */
    public Block getUpperBorderBlock() {
        return (Block) this.collidables.get(UPPER_BORDER_INDEX);
    }
    /**
     * Upper right getter.
     * @return  Right block.
     */
    public Block getRightBorderBlock() {
        return (Block) this.collidables.get(RIGHT_BORDER_INDEX);
    }

    /**
     * Lower block getter.
     * @return  Lower block.
     */
    public Block getLowerBorderBlock() {
        return (Block) this.collidables.get(LOWER_BORDER_INDEX);
    }

    /**
     * Left block getter.
     * @return  Left block.
     */
    public Block getLeftBorderBlock() {
        return (Block) this.collidables.get(LEFT_BORDER_INDEX);
    }

    /**
     * Get all collidables sharing a point.
     * @param p The point.
     * @return  The objects.
     */
    public List<Collidable> getCollidablesSharingPoint(Point p) {
        List<Collidable> ltr = new ArrayList<>();
        for (Collidable c : collidables) {
            if (((Block) c).isPointInside(p)) {
                ltr.add(c);
            }
        }
        return ltr;
    }

    /**
     * Is border.
     * @param block The block.
     * @return      Is the block a border.
     */
    public boolean isBorder(Block block) {
        return (this.getUpperBorderBlock() == block)
                || (this.getLowerBorderBlock() == block)
                || (this.getRightBorderBlock() == block)
                || (this.getLeftBorderBlock() == block);
    }

    /**
     * Get list of object that share a point.
     * @param p The point.
     * @return  List of collidables.
     */
    public List<Collidable> pointOnBlocks(Point p) {
        List<Collidable> ptr = new ArrayList<>();
        for (Collidable c : collidables) {
            if (((Collidable) c).isPointInside(p)) {
                ptr.add(c);
            }
        }
        return ptr;
    }

    /**
     * Get borders of the environment.
     * @return  Block array of borders.
     */
    public Block[] getBorders() {
        return new Block[]{
                getUpperBorderBlock(),
                getRightBorderBlock(),
                getLowerBorderBlock(),
                getLeftBorderBlock()
        };
    }

    /**
     * add the given collidable to the environment.
     * @param c Collidable.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     *
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory The trajectory.
     * @return  Collision info with the next collidable.
     */

    public CollisionInfo getClosestCollision(Line trajectory) {
        Line tmp = null, shortest = null;
        Point holder = null, toReturn = null;
        Collidable cHolder = null;

        for (Collidable c : collidables) {
            holder = trajectory.closestIntersectionToStartOfLine(
                    c.getCollisionRectangle());
            if (holder == null) {
                continue;
            }
            tmp = new Line(trajectory.start().getCopy(), holder);
            if (shortest == null) {
                shortest = tmp;
                cHolder = c;
            }
            if (tmp.length() < shortest.length()) {
                shortest = tmp;
                cHolder = c;
            }
        }
        if (shortest == null) {
            return null;
        }
        return new CollisionInfo(shortest.end(), cHolder);
    }

    /**
     * If a point is inside a collidable.
     * @param p The point.
     * @return  True if yes.
     */
    public boolean isPointInsideCollidable(Point p) {
        return !pointOnBlocks(p).isEmpty();
    }

    /**
     * Deprecated method.
     * Draws all collidables on the surface.
     * Irrelevant, due to Sprite class.
     * @param d Draw surface.
     */
    @Deprecated
    public void drawAllOn(DrawSurface d) {
        for (Collidable c : collidables) {
            ((Block) c).drawOn(d);
        }
    }

    /**
     * Remove collidable from the list.
     *
     * @param c The collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Returns if the block is the lower border (death region).
     *
     * @param b The block.
     * @return True if this is the bottom border.
     */
    public boolean isLowerBorderBlock(Block b) {
        return b == this.getLowerBorderBlock();
    }

    /**
     * Is the collidable a block (not a border or a paddle)?
     * @param b Block
     * @return  True if block, false otherwise.
     */
    public boolean isBlock(Block b) {
        int index = this.collidables.indexOf(b);
        return index > STARTING_INDEX_OF_BLOCKS;
    }
}
