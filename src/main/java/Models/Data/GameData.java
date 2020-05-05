package Models.Data;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The data to be used during the game.
 */
public class GameData {

    /**
     * Declares the number of movements player has made.
     */
    private static int numOfMoves;

    /**
     * Declares the playing time.
     */
    private static Label timer = new Label("0");

    /**
     * Declares a window of type Stage.
     * @see Stage
     */
    private static Stage window = new Stage();

    /**
     * @return number of movements player has made so far.
     */
    public static int getNumOfMoves() {
        return numOfMoves;
    }

    /**
     * @return the game window.
     */
    public static Stage getWindow() {
        return window;
    }

    /**
     * @param scene sets the current scene to the window.
     */
    public static void setWindowScene(Scene scene) {
        window.setScene(scene);
    }

    /**
     * @return the game timer.
     */
    public static Label getTimer() {
        return timer;
    }

    /**
     * Increases the number of movements every time the player preforms a new move.
     */
    public static void addMove() {
        numOfMoves++;
    }
}
