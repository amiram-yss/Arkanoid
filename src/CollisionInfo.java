/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class CollisionInfo {
    /**
     * Properties.
     */
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor.
     *
     * @param collisionPoint The collision point.
     * @param object         The object collided.
     */
    public CollisionInfo(Point collisionPoint, Collidable object) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = object;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * Overrides to string for printing methods.
     *
     * @return String format.
     */
    public String toString() {
        return String.format("Obj: " + this.collisionObject + "Point: "
                + this.collisionPoint);
    }
}
