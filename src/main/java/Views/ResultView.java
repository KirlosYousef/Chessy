package Views;

import Controllers.LaunchController;
import Controllers.ResultController;
import Models.Data.GameData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The last view to appear when the player reaches the Goal state.
 *
 * @see Controllers.ResultController
 */
public class ResultView {


    /**
     * This procedure is responsible for what happens when reaches the goal state.
     */
    public void Goal() {

        GameData gameData = new GameData();

        int score = (int) ((gameData.getNumOfMoves() / Float.parseFloat(gameData.getTimer().getText())) * 100);

        ResultController resultController = new ResultController();
        LaunchController launchController = new LaunchController();

        resultController.GoalReached(score);

        Label winner = new Label("You made it " + launchController.getPlayer().getName() + ", and your score is: "
                + launchController.getPlayer().getScore());
        winner.setId("winnerLabel");
        Label top10 = new Label("Top 10 players:");
        winner.setBackground(Background.EMPTY);
        VBox winnerBox = new VBox();
        VBox vContainer = new VBox();
        HBox dataContainer = new HBox();
        VBox names = new VBox();
        VBox scores = new VBox();

        vContainer.setSpacing(5);
        vContainer.setPadding(new Insets(5, 0, 0, 0));


        resultController.getTop10().forEach(player -> {
            names.getChildren().add(new Label(player.getPlayer()));
            scores.getChildren().add(new Label(String.valueOf(player.getScore())));
        });


        dataContainer.getChildren().addAll(names, scores);
        dataContainer.setSpacing(10);
        dataContainer.setAlignment(Pos.CENTER);

        winnerBox.getChildren().addAll(winner, top10);
        winnerBox.setAlignment(Pos.CENTER);
        winnerBox.setSpacing(2);

        vContainer.getChildren().addAll(winnerBox, dataContainer);
        Scene madeIt = new Scene(vContainer, 300, 220);


        gameData.getWindow().setScene(madeIt);
        gameData.getWindow().show();
    }

}
