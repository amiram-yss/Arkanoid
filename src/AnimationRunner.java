import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class AnimationRunner {
    private GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;
    private static final String WINDOW_TITLE = "Arkanoid";
    private static final short WINDOW_HEIGHT = 600;
    private static final short WINDOW_WIDTH = 800;
    private static final short MILLISECONDS_IN_SECOND = 1000;

    /**
     * Constructor.
     * @param fps       Frames per Second.
     * @param guiRef    Reference to the GUI (or null, for a new GUI).
     */
    public AnimationRunner(int fps, GUI guiRef) {
        this.framesPerSecond = fps;
        if (guiRef == null) {
            gui = new GUI(WINDOW_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT);
        } else {
            this.gui = guiRef;
        }
        sleeper = new Sleeper();
    }

    /**
     * GUI getter.
     * @return  GUI.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * GUI setter.
     * @param guiRef Reference to the GUI to set.
     */
    public void setGui(GUI guiRef) {
        this.gui = guiRef;
    }

    /**
     * Runs animation, according to animation settings, and draw-surfaces.
     * @param animation The animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = MILLISECONDS_IN_SECOND / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        //gui.close();
    }

    /**
     * Shuts animation and program down.
     */
    public void terminate() {
        gui.close();
    }
}