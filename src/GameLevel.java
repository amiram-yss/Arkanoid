import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amiram Yassif
 * 314985474
 * Ass6
 */
public class GameLevel implements Animation {
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
    private AnimationRunner runner;
    private boolean running;
    private static final Point INIT_BALL_POINT = new Point(400, 450);
    private static final Point TEST_CORNER_INIT_BALL_POINT = new Point(200, 380);
    LevelInformation levelInfo;
    /**
     * Consts.
     */
    private static final int SCREEN_WIDTH = 800;
    private static final int FPS = 60;
    private static final int BALL_RADIUS = 5;
    private static final int WINNING_PRIZE = 100;

    //private AnimationRunner animationRunner;

    /**
     * Constructor.
     *
     * @param levelInformation Contains data about level.
     */
    public GameLevel(LevelInformation levelInformation) {
        this.levelInfo = levelInformation;
    }

    /**
     * Constructor.
     *  @param levelInformation Contains data about level.
     * @param ar               The animation runner.
     * @param sc                Scoring counter.
     */
    public GameLevel(LevelInformation levelInformation, AnimationRunner ar, Counter sc) {
        this.levelInfo = levelInformation;
        this.runner = ar;
        this.scoreCounter = sc;
    }


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

    private void addBlock(Block bta) {
        //Add listeners.
        bta.addHitListener(blockRemover);
        bta.addHitListener(scoreTrackingListener);
        //Add to environments.
        environment.addCollidable(bta);
        sprites.addSprite(bta);
    }

    /**
     * Arranges the game blocks.
     */
    private void setGameBlocks() {
        for (Block bta : levelInfo.blocks()) {
            addBlock(bta);
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
    }

    /**
     * Getter for runner.
     *
     * @return Runner.
     */
    public AnimationRunner getRunner() {
        return this.runner;
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
     * Adds a single ball to the game.
     *
     * @param b The ball.
     */
    private void addBall(Ball b) {
        sprites.addSprite(b);
        balls.add(b);
    }

    /**
     * Creates a balls array and sets them to the game.
     */
    private void setBalls() {
        balls = new ArrayList<>();
        Ball b;
        List<Velocity> vs = levelInfo.initialBallVelocities();
        for (Velocity v : vs) {
            b = new Ball(
                    INIT_BALL_POINT,
                    BALL_RADIUS,
                    Color.WHITE,
                    this.environment
            );
            b.setVelocity(v);
            addBall(b);
        }
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
        //Set animation handler.
        setAnimationHandler();
    }

    /**
     * Initializes the animation runner.
     */
    private void setAnimationHandler() {
        this.sensor = runner.getGui().getKeyboardSensor();
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
        //Initializes bool representing level is ongoing.
        this.running = true;
        //Insert paddle
        setPaddle(this, levelInfo.paddleWidth());
        //Inits vars for the method
        sensor = runner.getGui().getKeyboardSensor();
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    /**
     * Set the paddle on the screen.
     *
     * @param s     this
     * @param width Paddle's width.
     */
    private void setPaddle(GameLevel s, int width) {
        Paddle p = new Paddle(width);
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
     *
     * @param hitter The ball to remove.
     */
    public void removeBall(Ball hitter) {
        sprites.removeSprite(hitter);
        balls.remove(hitter);
    }

    /**
     * Remove a block from the game.
     *
     * @param beingHit The block to remove.
     */
    private void removeBlock(Block beingHit) {
        beingHit.removeFromGame(this);
    }

    public boolean hasBalls() {
        return ballsCounter.getValue() != 0;
    }

    public boolean hasBlocks() {
        return blocksCounter.getValue() != levelInfo.numberOfBlocksToRemove();
    }

    /**
     * Put the current frame of an animation, on a surface.
     *
     * @param d The drawing surface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.levelInfo.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
//        if (this.sensor.isPressed("p")) {
//            PauseScreen pauseScreen = new PauseScreen(this.sensor, this);
//            this.runner.run(pauseScreen);
//            this.runner.run(this);
//        }
        if (!hasBlocks() || !hasBalls()) {
            if (blocksCounter.getValue() == levelInfo.numberOfBlocksToRemove()) {
                //Win
                scoreCounter.increase(WINNING_PRIZE);
            }
            this.running = false;
        }
    }

    /**
     * Should abort the animation?
     *
     * @return True if should. False otherwise.
     */
    @Override
    public boolean shouldStop() {
        return !running;
    }
}
