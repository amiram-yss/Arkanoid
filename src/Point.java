/**
 * @author Amiram Yassif
 * 314985474
 * ass3
 */

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class Point {
    private double x;
    private double y;

    private static final short BALL_RADIUS = 3;
    /**
     * creates point based on coords.
     *
     * @param x x coord
     * @param y y coord
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor.
     * @param point Point to copy.
     */
    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    /**
     * return the distance of this point to the other point.
     *
     * @param other other point
     * @return distance as double.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * return true is the points are equal, false otherwise.
     *
     * @param other other point
     * @return true if equal, false otherwise.
     */
    public boolean equals(Point other) {
        return UTIL.equals(this.x, other.x)
                && UTIL.equals(this.y, other.y);
    }

    /**
     * Return the x and y values of this point.
     *
     * @return x value of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the y and y values of this point.
     *
     * @return y value of the point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Set x value of the point.
     *
     * @param xVal new value to update
     * @return this.
     */
    public Point setX(double xVal) {
        this.x = xVal;
        return this;
    }

    /**
     * Set y value of the point.
     *
     * @param yVal new value to update
     * @return this.
     */
    public Point setY(double yVal) {
        this.y = yVal;
        return this;
    }

    /**
     * Irrelevant.
     * Draws the point as a dot on the screen.
     * Irrelevant due to sprite interface.
     * @param ds the surface
     * @param c  the color
     */
    @Deprecated
    public void drawOn(DrawSurface ds, Color c) {
        ds.setColor(c);
        new Ball(this.x, this.y, BALL_RADIUS, c, null).drawOn(ds);
    }

    /**
     * Creates a copy of the point.
     * @return  A copy.
     */
    public Point getCopy() {
        return new Point(this.x, this.y);
    }

    /**
     * Overrides object.toString().
     * @return  String representation of the point.
     */
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
