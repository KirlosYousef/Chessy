package View;

import Controller.PlayerController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static Model.GameData.*;

public class GoalView {

    /**
     * This procedure is responsible for what happens when reaches the goal state.
     */
    public static void Goal() {
        Database database = new Database();
        PlayerController.getPlayer().setScore((int) ((getNumOfMoves() / Float.parseFloat(getTimer().getText())) * 100));
        database.addToDatabase(PlayerController.getPlayer());

        Label winner = new Label("You made it " + PlayerController.getPlayer().getName() + ", and your score is: " + PlayerController.getPlayer().getScore());
        Label top10 = new Label("Top 10 players:");
        winner.setBackground(Background.EMPTY);
        VBox winnerBox = new VBox();
        VBox vContainer = new VBox();
        HBox dataContainer = new HBox();
        VBox names = new VBox();
        VBox scores = new VBox();

        vContainer.setSpacing(5);
        vContainer.setPadding(new Insets(5, 0, 0, 0));

        database.getData().forEach(player1 -> names.getChildren().add(new Label(player1.getName())));
        database.getData().forEach(player1 -> scores.getChildren().add(new Label(String.valueOf(player1.getScore()))));

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
