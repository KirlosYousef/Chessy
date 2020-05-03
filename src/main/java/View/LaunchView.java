package View;

import Controller.PlayerController;
import Model.GameData;
import Model.Player;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import static Controller.PlayerController.getPlayer;
import static View.ChessBoardView.createGame;

public class PlayerView {

    /**
     * @return the first scene of the player name.
     */
    public static Scene playerView() {

        Scene gameScene = new Scene(createGame());
        Label entNameLabel = new Label("Please enter your playerName: ");
        Label wrongInputLabel = new Label("Wrong input!");
        wrongInputLabel.setVisible(false);

        TextField nameTextField = new TextField();
        nameTextField.setMaxWidth(250);

        Button enterButton = new Button("Enter");
        enterButton.setOnAction(e -> { //When click the button, check if the textfield is not empty
            String playerName = nameTextField.getCharacters().toString();
            PlayerController.setPlayer(new Player(playerName));

            if ((!getPlayer().getName().equals(""))) {
                GameData.setWindowScene(gameScene);
            } else {
                wrongInputLabel.setVisible(true);
            }
        });

        //arranging the objects on the screen
        VBox playerLayout = new VBox();
        playerLayout.setSpacing(10);
        playerLayout.setPadding(new Insets(50, 0, 0, 25));

        playerLayout.getChildren().addAll(entNameLabel, nameTextField, enterButton, wrongInputLabel);
        return new Scene(playerLayout, 300, 200);
    }
}
