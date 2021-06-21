import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class Level3 implements LevelInformation {
    private static final int INIT_BALLS_NUM = 3;
    private static final int PADDLE_SPEED = 5;
    private static final int PADDLE_WIDTH = 100;
    private static final String LEVEL_NAME = "Just like the good ol' times... (ass5)";
    private static final int INIT_BLOCKS_NUM = 40;
    private static final double BALL_0_ANGEL = 30;
    private static final double BALL_1_ANGEL = 0;
    private static final double BALL_2_ANGEL = -30;
    private static final double BALL_SPEED = 5;
    private static final int MAX_BLOCKS_IN_LINE = 10;
    private static final int SCREEN_WIDTH = 800;
    private static final int BORDER_SHORT_EDGE = 20;
    private static final double BLOCK_HEIGHT = 30;
    private static final double BLOCK_WIDTH = 60;
    private Sprite background;
    private static final int NUM_BLOCK_LINES = 5;

    /**
     * Number of balls.
     *
     * @return Number of balls.
     */
    @Override
    public int numberOfBalls() {
        return INIT_BALLS_NUM;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return List of all velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ltr = new ArrayList<>();
        ltr.add(Velocity.fromAngleAndSpeed(BALL_0_ANGEL, BALL_SPEED));
        ltr.add(Velocity.fromAngleAndSpeed(BALL_1_ANGEL, BALL_SPEED));
        ltr.add(Velocity.fromAngleAndSpeed(BALL_2_ANGEL, BALL_SPEED));
        return ltr;
    }

    /**
     * @return Paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * @return Paddle width.
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return Level name.
     */
    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return background sprite.
     */
    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(42, 128, 21));
                d.fillRectangle(0, 0, 800, 800);
                d.setColor(new Color(128, 128, 128));
                d.fillRectangle(100, 300, 5, 300);
                d.setColor(new Color(62, 58, 57));
                d.fillRectangle(85, 400, 35, 300);
                d.setColor(Color.DARK_GRAY);
                d.fillRectangle(65, 450, 75, 200);
                d.setColor(new Color(180, 255, 240));
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        d.fillRectangle(
                                70 + 17 * i,
                                460 + 25 * j,
                                14,
                                21);
                    }
                }
                Point beaconCenter = new Point(102, 300);
                d.setColor(Color.WHITE);
                for (int i = 0; i < 18; i++) {
                    d.drawLine(
                            (int) beaconCenter.getX(),
                            (int) beaconCenter.getY(),
                            (int) Velocity.fromAngleAndSpeed(20 * i, 20).applyToPoint(beaconCenter).getX(),
                            (int) Velocity.fromAngleAndSpeed(20 * i, 20).applyToPoint(beaconCenter).getY()
                    );
                }
                d.setColor(Color.YELLOW);
                d.fillCircle(
                        (int) beaconCenter.getX(),
                        (int) beaconCenter.getY(),
                        9
                );
                d.setColor(Color.RED);
                d.fillCircle(
                        (int) beaconCenter.getX(),
                        (int) beaconCenter.getY(),
                        7
                );
                d.setColor(Color.WHITE);
                d.fillCircle(
                        (int) beaconCenter.getX(),
                        (int) beaconCenter.getY(),
                        5
                );
            }

            @Override
            public void timePassed() {

            }
        };
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return Blocks list.
     */
    @Override
    public List<Block> blocks() {
        Block bta;
        List<Block> ltr = new ArrayList<>();
        for (int i = 0; i < NUM_BLOCK_LINES; i++) {
            for (int j = 0; j < MAX_BLOCKS_IN_LINE - i; j++) {
                bta = new Block(
                        new Rectangle(
                                new Point(
                                        (SCREEN_WIDTH
                                                - BORDER_SHORT_EDGE)
                                                - (j + 1) * BLOCK_WIDTH,
                                        (BLOCK_HEIGHT
                                                * 5
                                                + BLOCK_HEIGHT * i)
                                                + BLOCK_HEIGHT),
                                BLOCK_WIDTH,
                                BLOCK_HEIGHT),
                        linesColorsArray()[i]);
                ltr.add(bta);
            }
        }
        return ltr;
    }

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return Number of blocks that should be removed
     * before the level is considered to be "cleared".
     */
    @Override
    public int numberOfBlocksToRemove() {
        return INIT_BLOCKS_NUM;
    }

    /**
     * Return column colors in the order.
     *
     * @return column colors in the order.
     */
    private Color[] linesColorsArray() {
        return new Color[]{
                Color.DARK_GRAY,
                Color.RED,
                Color.YELLOW,
                Color.BLUE,
                Color.WHITE
        };
    }

}
