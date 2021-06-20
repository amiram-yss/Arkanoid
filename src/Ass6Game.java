import java.util.ArrayList;
import java.util.List;
import biuoop.GUI;
import biuoop.DrawSurface;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class Ass6Game {
    public static List<LevelInformation> getLevels() {
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level4());
        return levels;
    }
    public static List<LevelInformation> getLevel1() {
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new Level1());
        return levels;
    }
    /**
     * Runs the application.
     * @param args  useless for this ass
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(60, gui);
        GameFlow gf = new GameFlow(ar);
        gf.runLevels(getLevels());
    }
}
