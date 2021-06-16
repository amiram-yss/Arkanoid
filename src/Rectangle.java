import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class Rectangle {
    private Point upperLeft;
    private double width, height;
    // Create a new rectangle with location and width/height.

    private static final int UP = 0;
    private static final int RT = 1;
    private static final int DN = 2;
    private static final int LF = 3;

    /**
     * Constructor.
     * @param upperLeft Upper left point.
     * @param width     Width of rectangle.
     * @param height    Height of rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
    }

    /**
     * Setter for width.
     * @param w Width.
     * @return  this.
     */
    public Rectangle setWidth(double w) {
        this.width = w;
        return this;
    }

    /**
     * Setter for height.
     * @param h  Height.
     * @return        this.
     */
    public Rectangle setHeight(double h) {
        this.height = h;
        return this;
    }

    /**
     * Setter for upper left point.
     * @param p Point to set as upper left point.
     * @return  this.
     */
    public Rectangle setUpperLeftPoint(Point p) {
        this.upperLeft = p;
        return this;
    }

    /**
     * Move the rectangle in X (horizontal) axis.
     * @param d Distance.
     * @return  this.
     */
    public Rectangle moveInXAxis(double d) {
        this.upperLeft.setX(this.upperLeft.getX() + d);
        return this;
    }

    /**
     * Move the rectangle in Y (horizontal) axis.
     * @param d Distance.
     * @return  this.
     */
    public Rectangle moveInYAxis(double d) {
        this.upperLeft.setY(this.upperLeft.getY() + d);
        return this;
    }

    /**
     * Get all corners of the rectangle.
     * @return Array of 4 points.
     */
    public Point[] getPoints() {
        return new Point[]{
                this.upperLeft.getCopy(),
                this.upperLeft.getCopy()
                .setX(this.upperLeft.getX() + width),
                this.upperLeft.getCopy()
                .setX(this.upperLeft.getX() + width)
                .setY(this.upperLeft.getY() + height),
                this.upperLeft.getCopy()
                .setY(this.upperLeft.getY() + height)
        };
    }

    /**
     * Get all lines of the rectangle.
     * @return Array of 4 lines.
     */
    public Line[] getLines() {
        return new Line[]{
                new Line(getPoints()[UP], getPoints()[RT]),
                new Line(getPoints()[RT], getPoints()[DN]),
                new Line(getPoints()[DN], getPoints()[LF]),
                new Line(getPoints()[LF], getPoints()[UP])
        };
    }

    /**
     * Get the directions of the edge that a point is sitting on.
     * @param p The point.
     * @return  A list of directions.
     */
    public List<DIRECTION> directionOfEdgeWithThePoint(Point p) {
        List<DIRECTION> ltr = new ArrayList<>();
        Line[] edges = this.getLines();
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].isPointOnLine(p)) {
                switch (i) {
                    case UP:
                        ltr.add(DIRECTION.UP);
                        break;
                    case RT:
                        ltr.add(DIRECTION.RIGHT);
                        break;
                    case DN:
                        ltr.add(DIRECTION.DOWN);
                        break;
                    case LF:
                        ltr.add(DIRECTION.LEFT);
                        break;
                    default:
                        break;
                }
            }
        }
        return ltr;
    }
    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line Line.
     * @return  List of intersection points with the rectangle.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> ltr = new ArrayList<>();
        Point intersection = null;
        for (Line l : getLines()) {
            intersection = line.intersectionWith(l);
            if (intersection != null) {
                ltr.add(intersection);
            }
        }
        return ltr;
    }

    /**
     * Return the width of the rectangle.
     * @return  Rectangle's width.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Return the height of the rectangle.
     * @return  Rectangle's height.
     */
    public double getHeight() {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.

    /**
     * Upper left point getter.
     * @return  upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Deprecated.
     * Draw rectangle on a draw surface.
     * Irrelevant due to sprite interface.
     * @param d The dreaw surface.
     * @param c Color.
     * @return  this.
     */
    @Deprecated
    public Rectangle drawOn(DrawSurface d, Color c) {
        d.setColor(c);
        d.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) width, (int) height);
        return this;
    }
}
