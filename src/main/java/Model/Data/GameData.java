package Model;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

    public static int getNumOfMoves() {
        return numOfMoves;
    }

    public static Stage getWindow() {
        return window;
    }

    public static void setWindowScene(Scene scene) {
        window.setScene(scene);
    }

    public static Label getTimer() {
        return timer;
    }

    public static void addMove() {
        numOfMoves++;
    }
}
