package Views;

import Controllers.LaunchController;
import Models.Data.GameData;
import Models.Data.Player;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import static Views.ChessBoardView.createGame;


/**
 * The first view to appear when the game starts.
 *
 * @see LaunchController
 */
public class LaunchView {

    /**
     * @return the first scene of the player name.
     */
    public Scene LaunchScene() {
        LaunchController launchController = new LaunchController();
        ChessBoardView chessBoardView = new ChessBoardView();
        GameData gameData = new GameData();

        Label entNameLabel = new Label("Please enter your playerName: ");
        Label wrongInputLabel = new Label("Wrong input!");
        wrongInputLabel.setId("wrongInputLabel");
        wrongInputLabel.setVisible(false);

        TextField nameTextField = new TextField();
        nameTextField.setId("nameTextField");
        nameTextField.setMaxWidth(250);

        Button enterButton = new Button("Enter");
        enterButton.setId("enterButton");

        enterButton.setOnAction(e -> { //When click the button, check if the textfield is not empty
            String playerName = nameTextField.getCharacters().toString();
            launchController.setPlayer(new Player(playerName));

            if ((!launchController.getPlayer().getName().equals(""))) {
                Scene gameScene = new Scene(createGame());
                gameData.setWindowScene(gameScene);
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
