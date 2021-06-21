import java.util.ArrayList;
import java.util.List;

import biuoop.GUI;

/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class Ass6Game {
    private static final byte LEVELS_NUMBER = 4;

    /**
     * Get a simple list of each level in a row.
     *
     * @return List of levels' information.
     */
    public static List<LevelInformation> getLevels() {
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level4());
        return levels;
    }

    /**
     * Get level by index (as a new level reference).
     *
     * @param index Index (in range of [1,4])
     * @return The relevant level information, or null if out of range.
     */
    public static LevelInformation getLevelByIndex(int index) {
        if (index == 1) {
            return new Level1();
        }
        if (index == 2) {
            return new Level2();
        }
        if (index == 3) {
            return new Level3();
        }
        if (index == 4) {
            return new Level4();
        }
        return null;
    }

    /**
     * Is string parsable to positive int.
     *
     * @param s String.
     * @return True is parsable.
     */
    public static boolean isInt(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts args[] to level list, according to indexes.
     *
     * @param args Args caught from OS.
     * @return List of levels in the correct order.
     */
    public static List<LevelInformation> convertArgsToLevelsList(String[] args) {
        List<LevelInformation> ltr = new ArrayList<>();
        for (String s : args) {
            if (isInt(s)) {
                if (Integer.parseInt(s) >= 1
                        && Integer.parseInt(s) <= LEVELS_NUMBER) {
                    ltr.add(getLevelByIndex(Integer.parseInt(s)));
                }
            }
        }
        if (ltr.isEmpty()) {
            ltr = getLevels();
        }
        return ltr;
    }

    /**
     * Runs the application.
     *
     * @param args useless for this ass
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(60, gui);
        GameFlow gf = new GameFlow(ar);
        gf.runLevels(convertArgsToLevelsList(args));
    }
}
