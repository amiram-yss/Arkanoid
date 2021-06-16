import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amiram Yassif
 * 314985474
 * Ass6
 */
public class Game {
    /**
     * Consts.
     */
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final double BORDER_SHORT_EDGE = 20;
    private static final int NUM_BLOCK_LINES = 6;
    private static final int MAX_BLOCKS_IN_LINE = 12;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int BALL_SPEED = 5; //TESTING 25
    private static final int BALL_RADIUS = 5;
    private static final int BLOCKS_STARTING_NUMBER = 57;
    private static final Point BALL_0_STARTING_POINT = new Point(350, 400);
    private static final Point BALL_1_STARTING_POINT = new Point(250, 400);
    private static final Point BALL_2_STARTING_POINT = new Point(300, 400);
    private static final double BALL_0_ANGEL = -22.5;
    private static final double BALL_1_ANGEL = -45;
    private static final double BALL_2_ANGEL = -15;
    private static final int WINNING_PRIZE = 100;

    /**
     * Environment getter.
     *
     * @return Environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Environment setter.
     *
     * @param en Environment.
     */
    public void setEnvironment(GameEnvironment en) {
        this.environment = en;
    }

    /**
     * Sprites getter.
     *
     * @return Sprites.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * Sprites setter.
     *
     * @param sp Sprites.
     */
    public void setSprites(SpriteCollection sp) {
        this.sprites = sp;
    }

    /**
     * Properties.
     */
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private List<Ball> balls;
    private KeyboardSensor sensor;
    private Counter blocksCounter, ballsCounter, scoreCounter;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;

    /**
     * @return The keyboard sensor
     */
    public KeyboardSensor getSensor() {
        return this.sensor;
    }

    /**
     * @return Array of 6 colors
     */
    private Color[] linesColorsArray() {
        return new Color[]{
                Color.GREEN,
                Color.BLUE,
                Color.ORANGE,
                Color.CYAN,
                Color.GRAY,
                Color.WHITE
        };
    }

    /**
     * Arranges the game blocks.
     */
    private void setGameBlocks() {
        Color[] clrs = linesColorsArray();
        for (int i = 0; i < NUM_BLOCK_LINES; i++) {
            for (int j = 0; j < MAX_BLOCKS_IN_LINE - i; j++) {
                Block bta = new Block(
                        new Rectangle(
                                new Point(
                                        (SCREEN_WIDTH
                                                - BORDER_SHORT_EDGE)
                                                - (j + 1) * BLOCK_WIDTH,
                                                (BORDER_SHORT_EDGE
                                                * 5
                                                + BORDER_SHORT_EDGE * i)
                                                + BLOCK_HEIGHT),
                                        BLOCK_WIDTH,
                                BLOCK_HEIGHT),
                                clrs[i]);
                //Add listeners.
                bta.addHitListener(blockRemover);
                bta.addHitListener(scoreTrackingListener);
                //Add to environments.
                environment.addCollidable(bta);
                sprites.addSprite(bta);
            }
        }
    }

    /**
     * Set removing tool.
     */
    private void setRemovers() {
        //Set block-removers.
        this.blockRemover = new BlockRemover(this, this.blocksCounter);
        //Set ball-remover.
        this.ballRemover = new BallRemover(this, this.ballsCounter);
        //Registering events
        environment.addHitListener(this.blockRemover);
        environment.addHitListenerToLowerBorderBlock(ballRemover);
    }

    /**
     * Adds collidable to the collection.
     *
     * @param c The collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Set the blocksCounter for the blocks got hit.
     */
    private void setCounters() {
        this.blocksCounter = new Counter();
        this.scoreCounter = new Counter();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s The sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Sets the borders (as blocks).
     */
    private void setBorders() {
        sprites.addSprite(environment.getUpperBorderBlock());
        sprites.addSprite(environment.getLowerBorderBlock());
        sprites.addSprite(environment.getRightBorderBlock());
        sprites.addSprite(environment.getLeftBorderBlock());
    }

    /**
     * Creates a balls array and sets them to the game.
     */
    private void setBalls() {
        balls = new ArrayList<>();
        //Set first ball + speed.
        Ball b1 = new Ball(
                BALL_0_STARTING_POINT, BALL_RADIUS, Color.RED, environment);
        b1.setVelocity(Velocity.fromAngleAndSpeed(BALL_0_ANGEL, BALL_SPEED));
        //The second one.
        Ball b2 = new Ball(
                BALL_1_STARTING_POINT, BALL_RADIUS, Color.magenta, environment);
        b2.setVelocity(Velocity.fromAngleAndSpeed(BALL_1_ANGEL, BALL_SPEED));
        //And the third.
        Ball b3 = new Ball(
                BALL_2_STARTING_POINT, BALL_RADIUS, Color.BLUE, environment);
        b3.setVelocity(Velocity.fromAngleAndSpeed(BALL_2_ANGEL, BALL_SPEED));
        //Add them to sprite collections.
        sprites.addSprite(b1);
        sprites.addSprite(b2);
        sprites.addSprite(b3);
        //Add them to List balls.
        balls.add(b1);
        balls.add(b2);
        balls.add(b3);
        //Set counter.
        ballsCounter = new Counter(balls.size());
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        //Set collections
        environment = new GameEnvironment();
        sprites = new SpriteCollection();
        //Set Counter for blocks hit and scores.
        setCounters();
        //Set block remover
        setRemovers();
        //Borders
        setBorders();
        //Set balls
        setBalls();
        //Set score counter
        setScoreCounterAndIndicator();
        //Set blocks
        setGameBlocks();
    }

    /**
     * Sets score indicator.
     */
    private void setScoreCounterAndIndicator() {
        this.scoreTrackingListener = new ScoreTrackingListener(
                this.scoreCounter);
        this.scoreIndicator = new ScoreIndicator(scoreCounter);
        this.addSprite(scoreIndicator);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        //Inits vars for the method
        Sleeper sleeper = new Sleeper();
        GUI gui = new GUI("game", SCREEN_WIDTH, SCREEN_HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        sensor = gui.getKeyboardSensor();

        //Insert paddle
        setPaddle(this);

        //Video settings
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        //Animation
        while (true) {
            //Handling equal timing
            long startTime = System.currentTimeMillis();

            //Paint all sprites on the surface
            d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            //And tell them time passed.
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (blocksCounter.getValue() == BLOCKS_STARTING_NUMBER) {
                // Win
                this.scoreCounter.increase(WINNING_PRIZE);
                gui.close();
                return;
            }
            if (ballsCounter.getValue() == 0) {
                // Lose
                gui.close();
                return;
            }
        }
    }

    /**
     * Set the paddle on the screen.
     *
     * @param s this
     */
    private void setPaddle(Game s) {
        Paddle p = new Paddle();
        p.addToGame(this);
    }


    /**
     * Removes a block, in case it was hit.
     *
     * @param beingHit The block which was hit.
     * @param hitter   The ball which hit it.
     */
    public void blockHit(Block beingHit, Ball hitter) {
        if (environment.isBlock(beingHit)) {
            //Hit a regular block
            removeBlock(beingHit);
            blocksCounter.increase();
        } else {
            if (environment.isLowerBorderBlock(beingHit)) {
                //You lose 1 live >3
                removeBall(hitter);
                this.ballsCounter.decrease();
            }
        }
    }

    /**
     * Remove a ball from game.
     * @param hitter The ball to remove.
     */
    public void removeBall(Ball hitter) {
        sprites.removeSprite(hitter);
        balls.remove(hitter);
    }

    /**
     * Remove a block from the game.
     * @param beingHit  The block to remove.
     */
    private void removeBlock(Block beingHit) {
        beingHit.removeFromGame(this);
    }
}
