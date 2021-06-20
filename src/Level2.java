import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Level2 implements LevelInformation {
    private static final int INIT_BALLS_NUM = 3;
    private static final int PADDLE_SPEED = 5;
    private static final int PADDLE_WIDTH = 600;
    private static final String LEVEL_NAME = "Sunshininging";
    private static final int INIT_BLOCKS_NUM = 15;
    private static final int BORDER_WIDTH = 20;
    private static final double BALL_0_ANGEL = -45;
    private static final double BALL_1_ANGEL = -30;
    private static final double BALL_2_ANGEL = -15;
    private static final double BALL_SPEED = 5;
    private static final int MAX_BLOCKS_IN_LINE = 10;
    private static final int SCREEN_WIDTH = 800;
    private static final int BORDER_SHORT_EDGE = 20;
    private static final double BLOCK_HEIGHT = 30;
    private static final double BLOCK_WIDTH = (760 / 15);
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
     * The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return List of all velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ltr = new ArrayList<>();
        //ltr.add(Velocity.fromAngleAndSpeed(0, BALL_SPEED));
        ltr.add(Velocity.fromAngleAndSpeed(10,5));
        ltr.add(Velocity.fromAngleAndSpeed(20,5));
        ltr.add(Velocity.fromAngleAndSpeed(30,5));
        ltr.add(Velocity.fromAngleAndSpeed(40,5));
        ltr.add(Velocity.fromAngleAndSpeed(-10,5));
        ltr.add(Velocity.fromAngleAndSpeed(-20,5));
        ltr.add(Velocity.fromAngleAndSpeed(-30,5));
        ltr.add(Velocity.fromAngleAndSpeed(-40,5));
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
     * Returns a sprite with the background of the level
     *
     * @return background sprite.
     */
    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {

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
        for (int i = 1; i <= INIT_BLOCKS_NUM ; i++) {
            ltr.add(
                    new Block(
                            new Rectangle(
                                    new Point(
                                            SCREEN_WIDTH - BORDER_WIDTH - (i * BLOCK_WIDTH),
                                            180),
                                    50,
                                    30),
                            blocksColorsArray()[i-1]
                    ));
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
     * @return column colors in the order.
     */
    private Color[] blocksColorsArray() {
        return new Color[] {
                Color.CYAN,
                Color.CYAN,
                Color.PINK,
                Color.PINK,
                Color.BLUE,
                Color.BLUE,
                Color.GREEN,
                Color.GREEN,
                Color.GREEN,
                Color.YELLOW,
                Color.YELLOW,
                Color.ORANGE,
                Color.ORANGE,
                Color.RED,
                Color.RED,
        };
    }

}
