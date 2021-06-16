import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class Ball implements Sprite {
    /**
     * Properties.
     */
    private Point center;


    private Point nextIntersectionPoint;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    private static int ctr = 0;
    /**
     * Consts.
     */
    private static final int NO_MOVE = 0;
    private static final int DEGREES = 360;
    private static final int MAX_RADIUS_SPEED_REDUCER = 50;
    private static final int MAX_SPEED = 70;
    private static final int MIN_SPEED = 2;
    private static final double SPEED_FACTOR = 0.1;

    /**
     * Builds a ball with Point param, velocity = 0.
     *
     * @param center Point representing the center
     * @param r      int representing radius
     * @param color  The color of the ball
     * @param ge     Game environment.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment ge) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(NO_MOVE, NO_MOVE);
        this.gameEnvironment = ge;
        nextIntersectionPoint = null;
    }

    /**
     * Builds a ball using center coordinates, velocity = 0.
     *
     * @param x     x of center point
     * @param y     y of center point
     * @param r     int representing radius
     * @param color color
     * @param ge    The environment.
     */
    public Ball(double x, double y, int r, java.awt.Color color,
                GameEnvironment ge) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.gameEnvironment = ge;
        nextIntersectionPoint = null;
    }

    /**
     * @return X value of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return Y value of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return Color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on a given drawSurface.
     *
     * @param surface the surface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        //Set the color to the ball's color, and print it.
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Overrides from Sprite interface.
     * Updates location of the ball after 1 period of time.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Get the X value of the right edge point of the ball.
     *
     * @return the X value as a double
     */
    public double getMaxX() {
        return center.getX() + radius;
    }

    /**
     * Get the X value of the left edge point of the ball.
     *
     * @return the X value as a double
     */
    public double getMinX() {
        return center.getX() - radius;
    }

    /**
     * Get the Y value of the upper edge point of the ball.(lower Y->high Y val).
     *
     * @return the Y value as a double
     */
    public double getMaxY() {
        return center.getY() + radius;
    }

    /**
     * Get the Y value of the lower edge point of the ball.(lower Y->high Y val).
     *
     * @return the Y value as a double
     */
    public double getMinY() {
        return center.getY() - radius;
    }

    /**
     * set the right edge of the ball to a certain point given in the params.
     *
     * @param value the point's value
     */
    public void setMaxX(double value) {
        this.center = new Point(value - radius, this.center.getY());
    }

    /**
     * set the right edge of the ball to a certain point given in the params.
     *
     * @param value the point's value
     */
    public void setMinX(double value) {
        this.center = new Point(value + radius, this.center.getY());
    }

    /**
     * set the lower edge of the ball to a certain point given in the params.
     *
     * @param value the point's value
     */
    public void setMaxY(double value) {
        this.center = new Point(this.center.getX(), value - radius);
    }

    /**
     * set the top edge of the ball to a certain point given in the params.
     *
     * @param value the point's value
     */
    public void setMinY(double value) {
        this.center = new Point(this.center.getX(), value + radius);
    }

    /**
     * sets the velocity of the ball, directly from the params.
     *
     * @param v the velocity to set as the property
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * sets the velocity of the ball, from dx dy vals.
     *
     * @param dx dx for the velocity params
     * @param dy dy for the velocity params
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return Velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Create a new ball on a random point in given range.
     * Radius param is given as well
     *
     * @param r         int representing a radius.
     * @param boardMinX int representing the minimum X value of the board.
     * @param boardMinY int representing the minimum Y value of the board.
     * @param boardMaxX int representing the maximum X value of the board.
     * @param boardMaxY int representing the maximum Y value of the board.
     * @param ge        The game environment.
     * @return A new ball with the wanted parameters.
     */
    public static Ball generateBallInRandomLocationAndColor(
            int r, int boardMinX, int boardMinY, int boardMaxX, int boardMaxY,
            GameEnvironment ge) {
        Random rand = new Random();
        //Will create location of center point randomly.must be: minXY+r<loc<max
        int x = rand.nextInt(
                (int) (boardMaxX - boardMinX - 2 * r)) + (int) (boardMinX + r);
        int y = rand.nextInt(
                (int) (boardMaxY - boardMinY - 2 * r)) + (int) (boardMinY + r);
        //Create the ball based on the random XY given above.
        Ball btr = new Ball(x, y, r, new Color(rand.nextFloat(),
                rand.nextFloat(), rand.nextFloat()), ge);
        //Create random velocity, based on random angle and speed.
        btr.velocity = Velocity.fromAngleAndSpeed(
                rand.nextInt(DEGREES),
                r >= MAX_RADIUS_SPEED_REDUCER
                        ? MIN_SPEED : (MAX_SPEED - r) * SPEED_FACTOR);
        return btr;
    }

    /**
     * Move one step based on the velocity prop.
     * Add dx,dy from velocity to the ball's current position.
     * If not possible (moves out of border), bounce it back to the frame.
     *
     * @param boardMinX frame min x.
     * @param boardMinY frame min y.
     * @param boardMaxX frame max x.
     * @param boardMaxY frame max y.
     */
    @Deprecated
    public void moveOneStep(
            int boardMinX, int boardMinY, int boardMaxX, int boardMaxY) {
        //Add dx, dy to the current center point.
        this.center = this.getVelocity().applyToPoint(this.center);
        this.velocity = this.velocity.createCopy();
        //If the new X val is too low:
        if (this.getMinX() <= boardMinX) {
            //set the left side of the ball in the minX val of frame + the delta
            this.setMinX(boardMinX - this.getMinX() + boardMinX);
            //reflect X component to the opposite side.
            //this.velocity.dx *= -1;
            this.velocity.setDx(-this.velocity.getDx());
        }
        //If the new X value is too high:
        if (boardMaxX <= this.getMaxX()) {
            //set the right side of the ball in the maxX val of frame - the delta
            this.setMaxX(boardMaxX - (this.getMaxX() - boardMaxX));
            //reflect X component to the opposite side.
            //this.velocity.dx *= -1;
            this.velocity.setDx(-this.velocity.getDx());
        }
        //If the new Y val is too low:
        if (this.getMinY() <= boardMinY) {
            //set the upper side of the ball in the minY val of frame-the delta
            this.setMinY(boardMinY - this.getMinY() + boardMinY);
            //reflect Y component to the opposite side.
            //this.velocity.dy *= -1;
            this.velocity.setDy(-this.velocity.getDy());
        }
        //If the new Y val is too high:
        if (boardMaxY <= this.getMaxY()) {
            //set the lower side of the ball in maxY of the border + delta.
            this.setMaxY(boardMaxY - (this.getMaxY() - boardMaxY));
            //reflect velocity's Y component.
            //this.velocity.dy *= -1;
            this.velocity.setDy(-this.velocity.getDy());
        }


    }

    /**
     * @param newLocation the location of the ball in the next move
     * @return Will it collide in the next move?
     */
    public boolean isCollidingInTheNextMovement(Point newLocation) {
        return !(center.distance(newLocation)
                < center.distance(getClosestIntersectionPoint()));
    }

    /**
     * Moves the ball one step.
     * in case no collision occurred, it will sum velocity to the center.
     * Otherwise, it depends on where the ball landed
     */
    public void moveOneStep() {
        //Check where it supposes to land.
        Point newLocation = this.velocity.applyToPoint(this.center);
        //No collision puts the ball whithout any changes
        if (!isCollidingInTheNextMovement(newLocation)) {
            center = new Point(newLocation);
            return;
        }
        //If a hit occurred: check the new velocity.
        Line traj = new Line(this.center, newLocation);
        var collisionInfo = gameEnvironment
                .getClosestCollision(traj);
        Velocity newVelocity = collisionInfo.collisionObject().hit(
                this,
                collisionInfo.collisionPoint(),
                this.velocity
        );
        //If it hits the pedal, run the method which was built for the pedal
        if (collisionInfo.collisionObject().getClass().equals(Paddle.class)) {
            this.velocity = (((Paddle) collisionInfo.collisionObject()).hit(
                    this, collisionInfo.collisionPoint(), velocity));
            this.center = (((Paddle) (collisionInfo.collisionObject()))
                    .hit(this, collisionInfo.collisionPoint(), velocity)
                    .applyToPoint(this.center));
            return;
        }
        /*
         * Otherwise it hit a block. Reflect the velocity to the opposite side.
         * if there is a block hiding the way, just make a full reflection.
         */
        if (gameEnvironment.isPointInsideCollidable(
                newVelocity.applyToPoint(center))) {
            //(-velocity.dx, -velocity.dy);
            newVelocity = new Velocity(-velocity.getDx(), -velocity.getDy());
        }
        this.velocity = newVelocity;
        this.center = this.velocity.applyToPoint(this.center);
    }

    /**
     * @return Trajectory line from the center of the ball
     * to the border (as if there were no obstacles).
     */
    private Line createTrajectoryFromBallToEndOfTheBoard() {
        //Convert to linear equation, and check intersections with the borders.
        double[] mn = Line.toLinearEquation(
                center,
                //velocity.dx,
                velocity.getDx(),
                //velocity.dy
                velocity.getDy()
        );
        Point p2 = Line.solveYforX(mn, GameEnvironment.SCREEN_WIDTH);
        Point p1 = Line.solveYforX(mn, 0);
        //Choose the relevant intersection and return the line.
        //if (this.velocity.dx > 0) {
        if (this.velocity.getDx() > 0) {
            return new Line(center, p2);
        }
        return new Line(center, p1);
    }

    /**
     * @return Intersection point of the trajectory with the border
     * (as if there were no obstacles).
     */
    public Point getIntersectionPointWithBorders() {
        //Build the trajectory.
        Point ptr = null;
        Line fullLengthLine = createTrajectoryFromBallToEndOfTheBoard();
        /*
         * Check each border block, return the one in the direction of
         * the velocity
         */

        for (Collidable c : gameEnvironment.getBorders()) {
            ptr = fullLengthLine.closestIntersectionToStartOfLine(
                    c.getCollisionRectangle());
            if (ptr == null) {
                continue;
            }
            if ((velocity.getDx() > 0) && (ptr.getX() > this.center.getX())) {
                return ptr;
            }
            if ((velocity.getDx() < 0) && (ptr.getX() < this.center.getX())) {
                return ptr;
            }
        }
        return ptr;
    }

    /**
     * @return Intersection line with the border (according to ball's location
     * and angel of speed).
     */
    public Line getIntersectionLineWithBorders() {
        return new Line(center, getIntersectionPointWithBorders());
    }

    /**
     * @return The closest intersection point, with each available collidable
     * in gameEnvironment.
     */
    public Point getClosestIntersectionPoint() {
        Point pHolder = null, ptr = null;
        //Get the line to the border
        Line route = getIntersectionLineWithBorders();
        // For each collidable check for intersection points.
        for (Collidable c : gameEnvironment.getCollidables()) {
            pHolder = route.closestIntersectionToStartOfLine(
                    c.getCollisionRectangle());
            //If doesn't exist, continue to the next object.
            if (pHolder == null) {
                continue;
            }
            if (ptr == null) {
                ptr = pHolder;
            }
            //Check if it is the shortest rout. If so, update the ptr to return.
            if (this.center.distance(ptr) > this.center.distance(pHolder)) {
                ptr = pHolder;
            }
        }
        return ptr;
    }

    /**
     * @return The closest intersection trajectory.
     */
    public Line getClosestIntersectionLine() {
        return new Line(this.center, getClosestIntersectionPoint());
    }

    /**
     * Overrides Object.toString().
     *
     * @return Ball string representation.
     */
    @Override
    public String toString() {
        return "Ball{"
                + "center=" + center
                + ", nextIntersectionPoint=" + nextIntersectionPoint
                + ", radius=" + radius
                + ", color=" + color
                + ", velocity=" + velocity
                + ", gameEnvironment=" + gameEnvironment
                + '}';
    }
}
