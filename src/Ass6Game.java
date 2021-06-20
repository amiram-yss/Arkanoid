/**
 * @author Amiram Yassif
 * 314985474
 * ass6
 */
public class Ass6Game {
    /**
     * Runs the application.
     * @param args  useless for this ass
     */
    public static void main(String[] args) {
        GameLevel gameLevel = new GameLevel(new Level1());
        gameLevel.initialize();
        gameLevel.run();
    }
}
