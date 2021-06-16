/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return Collision rectangle.
     */
    Rectangle getCollisionRectangle();



    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint    Collision point.
     * @param currentVelocity   Current velocity.
     * @param hitter The ball hitting.
     * @return  Updated velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * check if a point is inside the collidable.
     * @param p The point
     * @return  Is it inside the collidable
     */
     boolean isPointInside(Point p);
}
