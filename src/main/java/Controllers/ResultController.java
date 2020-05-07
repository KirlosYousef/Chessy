package Controllers;

import Models.Data.Database;
import Models.Data.Results.ChessAppGameResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The controller of the {@link Views.ResultView}.
 */
public class ResultController {

    /**
     * Defines a database.
     */
    private static Database database = new Database();

    /**
     * Will be called when the user reach the Goal state in the game.
     * It will add the player's data to the database.
     *
     * @param score user's score to be stored in the database.
     * @see Database
     */
    public void GoalReached(int score) {
        LaunchController launchController = new LaunchController();

        launchController.getPlayer().setScore(score);
        database.addToDatabase(launchController.getPlayer());
    }

    /**
     * @return a map of the top 10 players, the map has the player's Name and Score.
     */
    public ObservableList<ChessAppGameResult> getTop10() {
        ObservableList<ChessAppGameResult> observableResult = FXCollections.observableArrayList();
        observableResult.addAll(database.getData());
        return observableResult;
    }
}
