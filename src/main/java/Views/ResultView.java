package Views;

import Controllers.LaunchController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;

import static Controllers.ResultController.*;
import static Models.Data.GameData.*;

/**
 * The last view to appear when the player reaches the Goal state.
 *
 * @see Controllers.ResultController
 */
public class ResultView {

    /**
     * This procedure is responsible for what happens when reaches the goal state.
     */
    public static void Goal() {

        int score = (int) ((getNumOfMoves() / Float.parseFloat(getTimer().getText())) * 100);

        GoalReached(score);

        Label winner = new Label("You made it " + LaunchController.getPlayer().getName() + ", and your score is: "
                + LaunchController.getPlayer().getScore());
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

        for(Map.Entry<String, Integer> playerData : getTop10().entrySet())
        {
            names.getChildren().add(new Label(playerData.getKey()));
            scores.getChildren().add(new Label(String.valueOf(playerData.getValue())));
        }

        dataContainer.getChildren().addAll(names,scores);
        dataContainer.setSpacing(10);
        dataContainer.setAlignment(Pos.CENTER);

        winnerBox.getChildren().addAll(winner, top10);
        winnerBox.setAlignment(Pos.CENTER);
        winnerBox.setSpacing(2);

        vContainer.getChildren().addAll(winnerBox,dataContainer);
        Scene madeIt = new Scene(vContainer, 300, 220);


        getWindow().setScene(madeIt);
        getWindow().show();
    }

}
