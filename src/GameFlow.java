import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class GameFlow {
    private static final byte WINNING_PRIZE = 100;
    private final AnimationRunner animationRunner;
    private final Counter scoreCounter;
    private boolean lost;

    /**
     * Constructor.
     *
     * @param ar The animation handler.
     */
    public GameFlow(AnimationRunner ar) {
        this.animationRunner = ar;
        scoreCounter = new Counter();
        lost = false;
    }

    /**
     * Run the levels in the order given in the list.
     *
     * @param levels List of the level's order.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo,
                    this.animationRunner,
                    scoreCounter);

            level.initialize();
            level.run();
            if (!level.hasBalls()) {
                //YOU LOSE :(
                lost = true;
                showYouLoose();
                break;
            }
            if (!level.hasBlocks()) {
                //Level up, heres 100 pts for you big boyyyy <3
                scoreCounter.increase(WINNING_PRIZE);
            }
        }
        if (!lost) {
            showYouWin();
        }
        animationRunner.terminate();
    }

    /**
     * GG mate, you won! Let me congratulate you with a charming winning screen.
     */
    private void showYouWin() {
        KeyPressStoppableAnimation youLoseScreen
                = new KeyPressStoppableAnimation(
                animationRunner.getGui().getKeyboardSensor(),
                KeyboardSensor.SPACE_KEY,
                new YouWinScreen(
                        animationRunner.getGui().getKeyboardSensor(),
                        scoreCounter.getValue()
                )
        );
        animationRunner.run(youLoseScreen);
    }

    /**
     * Boohoo. You lost. Now get the Game Over screen you deserve.
     */
    private void showYouLoose() {
        KeyPressStoppableAnimation youLoseScreen
                = new KeyPressStoppableAnimation(
                animationRunner.getGui().getKeyboardSensor(),
                KeyboardSensor.SPACE_KEY,
                new YouLoseScreen(
                        animationRunner.getGui().getKeyboardSensor(),
                        scoreCounter.getValue()
                )
        );
        animationRunner.run(youLoseScreen);
    }
}
