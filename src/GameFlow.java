import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

public class GameFlow {
    private AnimationRunner animationRunner;
    KeyPressStoppableAnimation pauseScreen;
    private List<LevelInformation> levels;
    Counter scoreCounter;

    /**
     * Constructor.
     * @param ar The animation handler.
     */
    public GameFlow(AnimationRunner ar) {
        this.animationRunner = ar;
        scoreCounter = new Counter();
        pauseScreen = new KeyPressStoppableAnimation(
                ar.getGui().getKeyboardSensor(),
                "p",
                new PauseScreen(ar.getGui().getKeyboardSensor()));
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo,
                    this.animationRunner,
                    scoreCounter);

            level.initialize();
            level.run();
            if (!level.hasBalls()) {
                //YOU LOSE :(
                break;
            }
            if (!level.hasBlocks()) {
                //LEVEL UP!
            }
        }

        animationRunner.terminate();
    }
}
