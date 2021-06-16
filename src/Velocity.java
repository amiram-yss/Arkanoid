
import java.util.Random;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class Velocity {
    private static final short ZERO_ANGLE = 0;
    private static final short RIGHT_ANGLE = 90;
    private static final short FLAT_ANGLE = 180;
    private static final short FULL_ANGLE = 360;
    private static final double MICRO_ANGLE_VALUE = Math.pow(10, -6);
    /**
     * dx getter.
     *
     * @return dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * dx setter.
     *
     * @param dxVar New DX value.
     */
    public void setDx(double dxVar) {
        this.dx = dxVar;
    }

    /**
     * dy getter.
     *
     * @return dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * dy setter.
     *
     * @param dyVar dy
     */
    public void setDy(double dyVar) {
        this.dy = dyVar;
    }

    private double dx, dy;

    /**
     * Represents movement in one time unit.
     *
     * @param dx change in x axis
     * @param dy change in y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Get a new point representing a given point, after moving it for 1 time
     * unit in this velocity.
     *
     * @param p the point.
     * @return the point with the new location.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Copies a velocity object.
     *
     * @return copy of this.velocity
     */
    public Velocity createCopy() {
        return new Velocity(this.dx, this.dy);
    }

    /**
     * creates dx, dy based on angel(0 for up) and speed.
     *
     * @param angle angel of velocity
     * @param speed size of velocity vector
     * @return a new velocity based on angel and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(RIGHT_ANGLE - angle)) * speed;
        if (angle == ZERO_ANGLE || angle == FLAT_ANGLE) {
            dx = MICRO_ANGLE_VALUE;
        }
        double dy = -(Math.sin(Math.toRadians(angle + RIGHT_ANGLE)) * speed);
        return new Velocity(dx, dy);
    }

    /**
     * Generate random velocity.
     *
     * @param max max speed
     * @return new random velocity (not faster than max)
     */
    public static Velocity generateRandomVelocity(int max) {
        Random r = new Random();
        return fromAngleAndSpeed(r.nextInt(max), FULL_ANGLE);
    }

    /**
     * Overrides object.toString().
     * @return  A string representation of Velocity.
     */
    public String toString() {
        return "<" + dx + "," + dy + ">";
    }
}
