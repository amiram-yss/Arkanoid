import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class Paddle implements Sprite, Collidable {
    private static final double SCREEN_WIDTH = 800;
    private static final double UPPER_LEFT_Y_VALUE = 560;
    private static final int PADDLE_PAINTED_HEIGHT = 20;
    private static final double PADDLE_ACTUAL_HEIGHT = 0.01;
    private static final double STARTING_X_VALUE = 100;
    private static final double PADDLE_SPEED = 5;
    private static final double PADDLE_PARTS = 5;
    private static final double ANGEL = 30;
    private static final double BALL_SPEED = 5; //TESTING 25

    private KeyboardSensor keyboardSensor;
    private Rectangle rectangle;
    private int paddleWidth;

    /**
     * Constructor.
     */
    public Paddle() {
        this.paddleWidth = 100;
        rectangle = new Rectangle(
                new Point(STARTING_X_VALUE, UPPER_LEFT_Y_VALUE),
                (SCREEN_WIDTH - paddleWidth) / 2,
                PADDLE_ACTUAL_HEIGHT);
    }

    public Paddle(int width) {
        this.paddleWidth = width;
        rectangle = new Rectangle(
                new Point((SCREEN_WIDTH - width) / 2, UPPER_LEFT_Y_VALUE),
                width,
                PADDLE_ACTUAL_HEIGHT);
    }

    /**
     * Moves the paddle left in 5 pixels.
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX()
                < PADDLE_PAINTED_HEIGHT + PADDLE_SPEED) {
            return;
        }
        this.rectangle.moveInXAxis(-PADDLE_SPEED);
    }

    /**
     * Moves the paddle right in 5 pixels.
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX()
                > SCREEN_WIDTH - PADDLE_PAINTED_HEIGHT - paddleWidth - PADDLE_SPEED) {
            return;
        }
        this.rectangle.moveInXAxis(PADDLE_SPEED);
    }

    /**
     * Get the paddle rectangle.
     *
     * @return The paddle's rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          Ball
     * @param collisionPoint  Point of collision.
     * @param currentVelocity Current velocity.
     * @return Updated velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = collisionPoint.getX()
                - this.getCollisionRectangle().getUpperLeft().getX();
        double[] dots = new double[]{
                0,
                1 * (paddleWidth / PADDLE_PARTS),
                2 * (paddleWidth / PADDLE_PARTS),
                3 * (paddleWidth / PADDLE_PARTS),
                4 * (paddleWidth / PADDLE_PARTS),
                5 * (paddleWidth / PADDLE_PARTS)
        };
        if (UTIL.isBetweenOrEquals(x, dots[0], dots[1])) {
            return Velocity.fromAngleAndSpeed(-2 * ANGEL, BALL_SPEED);
        } else if (UTIL.isBetweenOrEquals(x, dots[1], dots[2])) {
            return Velocity.fromAngleAndSpeed(-ANGEL, BALL_SPEED);
        } else if (UTIL.isBetweenOrEquals(x, dots[2], dots[3])) {
            return new Velocity(
                    currentVelocity.getDx(), -Math.abs(currentVelocity.getDy()));
        } else if (UTIL.isBetweenOrEquals(x, dots[3], dots[4])) {
            return Velocity.fromAngleAndSpeed(ANGEL, BALL_SPEED);
        }
        return Velocity.fromAngleAndSpeed(2 * ANGEL, BALL_SPEED);
    }

    @Override
    public boolean isPointInside(Point p) {
        if (!(UTIL.isBetween(p.getX(),
                rectangle.getPoints()[0].getX(),
                rectangle.getPoints()[1].getX())
                || UTIL.equals(p.getX(), rectangle.getPoints()[0].getX())
                || UTIL.equals(p.getX(), rectangle.getPoints()[1].getX())
        )) {
            return false;
        }
        return UTIL.isBetween(p.getY(),
                rectangle.getPoints()[2].getY(),
                rectangle.getPoints()[1].getY())
                || UTIL.equals(p.getY(), rectangle.getPoints()[2].getY())
                || UTIL.equals(p.getY(), rectangle.getPoints()[1].getY());
    }

    /**
     * Draws the paddle on the surface.
     *
     * @param d The draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.RED);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                PADDLE_PAINTED_HEIGHT);
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                PADDLE_PAINTED_HEIGHT);
    }

    /**
     * Sprite time update actions.
     */
    @Override
    public void timePassed() {
        if (keyboardSensor.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboardSensor.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Adds the paddle to a game.
     *
     * @param g A game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
        this.keyboardSensor = g.getSensor();
    }
}
