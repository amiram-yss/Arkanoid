import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * create line between 2 points.
     *
     * @param start starting point of the line
     * @param end   ending point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * create line between 2 points coordination.
     *
     * @param x1 starting point x val.
     * @param y1 starting point y val.
     * @param x2 ending point x val.
     * @param y2 ending point y val.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * return the len og the line.
     *
     * @return double representing length
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Return middle point's middle point.
     *
     * @return point
     */
    public Point middle() {
        return new Point(Math.abs((start.getX() + end.getX()) / 2),
                Math.abs((start.getY() + end.getY()) / 2));
    }

    /**
     * Returns the start point of the line.
     * @return  Starting point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the ending point of the line.
     * @return  Ending point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Is intersecting with another line?
     *
     * @param other the other line
     * @return true if intersecting, false if not.
     */
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }

    /**
     * Is the line vertical?
     *
     * @param line the line
     * @return true if vertical, false otherwise.
     */
    private static boolean isVertical(Line line) {
        return line.start.getX() == line.end.getX();
    }

    /**
     * Gives a linear representation of an equation with a known slope
     * and a known point.
     *
     * @param onLine    Point on the line.
     * @param dx        X component of the representing linear equation.
     * @param dy        Y component of the representing linear equation.
     * @return          Linear equation representation of the line.
     */
    public static double[] toLinearEquation(Point onLine, double dx, double dy) {
        if (dx == 0) {
            return new double[]{
                    Double.POSITIVE_INFINITY,
                    Double.POSITIVE_INFINITY};
        }
        double m = (dy / dx);
        //Calculate the free component's value
        double n = onLine.getY() - (m * onLine.getX());
        return new double[]{m, n};
    }

    /**
     * Gives a linear representation of an equation between 2 points.
     *
     * @param line line.
     * @return let y=mx+n be a function, returns an array [m,n] of the linear
     * equation, crossing both points.
     */
    public static double[] toLinearEquation(Line line) {
        double dy = line.end.getY() - line.start.getY();
        double dx = (line.end.getX() - line.start.getX());
        //m = dy/dx
        double m = (dy / dx);
        //Calculate the free component's value
        double n = line.start.getY() - m * line.start.getX();
        return new double[]{m, n};
    }

    /**
     * Get intersection point, if one of the lines is vertical.
     *
     * @param other other line
     * @return Point of intersection if
     */
    private Point getVerticalIntersection(Line other) {
        double m, n, y;
        //Check if each line is vertical
        boolean isThisVertical = isVertical(this);
        boolean isOtherVertical = isVertical(other);
        //If both vertical, ret null.
        if (isThisVertical && isOtherVertical) {
            return null;
        }

        //If this line is vertical
        if (isThisVertical) {
            m = toLinearEquation(other)[0];
            n = toLinearEquation(other)[1];
            y = m * this.start.getX() + n;
            //Check if there is a point both lines share
            if (inRange(this.start.getY(), this.end.getY(), y)
                    && inRange(other.start.getX(),
                    other.end.getX(), this.start.getX())) {
                return new Point(this.start.getX(), y);
            }
        }

        //If other line is vertical
        if (isOtherVertical) {
            m = toLinearEquation(this)[0];
            n = toLinearEquation(this)[1];
            y = m * other.start.getX() + n;
            //Check if there is a point both lines share
            if (inRange(other.start.getY(), other.end.getY(), y)
                    && inRange(this.start.getX(), this.end.getX(), other.start.getX())) {
                return new Point(other.start.getX(), y);
            }
        }
        //If not, return null.
        return null;
    }

    /**
     * Is a value between 2 edges?
     *
     * @param edge1 edge1
     * @param edge2 edge2
     * @param var   this is the arg to be checked.
     * @return is edge1<=var<=edge2 or edge2<=var<=edge1? If yes true,
     * false otherwise.
     */
    private boolean inRange(double edge1, double edge2, double var) {
        if (((var < edge1) || UTIL.equals(var, edge1))
                && ((var > edge2) || UTIL.equals(var, edge2))) {
            return true;
        }
        return ((var < edge2) || UTIL.equals(var, edge2))
                && ((var > edge1) || UTIL.equals(var, edge1));
    }

    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     *
     * @param other the line this line intersects (or not) with.
     * @return Point of intersection or null if they don't intersect.
     */
    //
    public Point intersectionWith(Line other) {
        //The point
        Point ptr = getVerticalIntersection(other);
        //Intersect with a vertical line? ret the point
        if (ptr != null) {
            return ptr;
        }
        //If not vertical, check regular occasion.
        ptr = getIntersectionForNonVerticalLines(other);
        //If don't intersect, ret null
        if (!this.checkIfOnBothLines(ptr, other)) {
            ptr = null;
        }
        return ptr;
    }

    /**
     * check if a point is on this line and on the other.
     *
     * @param ptr   the point
     * @param other the other line
     * @return if the point is set on both lines ret true. false otherwise.
     */
    private boolean checkIfOnBothLines(Point ptr, Line other) {
        //if point does not exist, ret false.
        if (ptr == null) {
            return false;
        }
        //for each axis, check if the point is in the range of the lines.
        if (!inRange(this.start.getX(), this.end.getX(), ptr.getX())) {
            return false;
        }
        if (!inRange(this.start.getY(), this.end.getY(), ptr.getY())) {
            return false;
        }
        if (!inRange(other.start.getX(), other.end.getX(), ptr.getX())) {
            return false;
        }
        if (!inRange(other.start.getY(), other.end.getY(), ptr.getY())) {
            return false;
        }
        //if in all ranges, return true.
        return true;
    }

    /**
     * If this and the other lines aren't vertical, it will get the intersection
     * point (as if the lines were not limited).
     *
     * @param other the other line
     * @return the point where both lines intersect.
     * null if lines are parallel.
     */
    private Point getIntersectionForNonVerticalLines(Line other) {
        //Make linear equation representation of bot lines
        double[] thisLinearRepresentation = toLinearEquation(this);
        double[] otherLinearRepresentation = toLinearEquation(other);

        //calc the difference between both lines.
        double dm = otherLinearRepresentation[0] - thisLinearRepresentation[0];
        //Parallel? ret null.
        if (dm == 0) {
            return null;
        }
        //get the delta between free vals.
        double dn = thisLinearRepresentation[1] - otherLinearRepresentation[1];
        //Solve 0=f(x)-g(x) and return the result for x val.
        double x = dn / dm;
        //Calc Y val.
        double y = (thisLinearRepresentation[0] * x) + thisLinearRepresentation[1];
        return new Point(x, y);
    }

    /**
     * return true is the lines are equal, false otherwise.
     *
     * @param other other line
     * @return are equal? true if yes.
     */
    public boolean equals(Line other) {
        boolean isSame = (this.start.equals(other.start)
                && this.end.equals(other.end));
        boolean isFlipped = (this.start.equals(other.end)
                && this.end.equals(other.start));
        //Return if the starting and ending points are equal between both lines
        //or flipped the other way around.
        return isSame || isFlipped;
    }

    /**
     * Generates a random line based on given range.
     *
     * @param minX minX range
     * @param minY minY range
     * @param maxX maxX range
     * @param maxY maxY range
     * @return  Random line.
     */
    public static Line generateRandomLine(
            double minX, double minY, double maxX, double maxY) {
        Random r = new Random();
        //Generate random coordinates based on the random.
        double x1 = (r.nextDouble() * (maxX - minX)) + minX;
        double x2 = (r.nextDouble() * (maxX - minX)) + minX;
        double y1 = (r.nextDouble() * (maxY - minY)) + minY;
        double y2 = (r.nextDouble() * (maxY - minY)) + minY;
        //Return a new line.
        return new Line(x1, y1, x2, y2);
    }

    /**
     * Deprecated.
     * Paints the line on a draw surface.
     * Irrelevant due to sprite interface.
     * @param ds    Draw surface.
     * @param c     Color.
     */
    @Deprecated
    public void drawOn(DrawSurface ds, Color c) {
        ds.setColor(c);
        ds.drawLine((int) this.start.getX(),
                (int) this.start.getY(),
                (int) this.end.getX(),
                (int) this.end.getY());
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect rectangle
     * @return first impact.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pts = rect.intersectionPoints(this);
        if (pts.isEmpty()) {
            return null;
        }
        Point ptr = null;
        Point closest = pts.get(0);
        double minLen = Double.MAX_VALUE;
        for (Point p : pts) {
            if (UTIL.isBetween(p.getX(), this.start.getX(), this.end.getX())
                    && (p.distance(start) < closest.distance(start))) {
                closest = p;
            }
        }
        return closest;
    }

    /**
     * Solve linear equation.
     * @param mn    Y = mX+n
     * @param x     X value.
     * @return      Y value.
     */
    public static Point solveYforX(double[] mn, double x) {
        return new Point(x, mn[0] * x + mn[1]);
    }

    /**
     * Is a point set on a linear equation?
     * @param p Point.
     * @return  Is it on this line? True if yes.
     */
    public boolean isPointOnLine(Point p) {
        if (isVertical(this) && UTIL.equals(p.getX(), this.start.getX())) {
            return (UTIL.isBetween(p.getY(), start.getY(), end.getY())
                    || p.getY() == this.start.getY()
                    || p.getY() == this.end.getY());
        }
        if (!(UTIL.isBetween(p.getX(), start.getX(), end.getX())
                || UTIL.equals(p.getX(), start.getX())
                || UTIL.equals(p.getX(), end.getX()))) {
            return false;
        }
        double[] lRep = toLinearEquation(this);
        if (!p.equals(new Point(p.getX(), lRep[0] * p.getX() + lRep[1]))) {
            return false;
        }
        return true;
    }
}






















