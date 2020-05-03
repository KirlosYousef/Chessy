package Main;

import Models.Data.GameData;
import javafx.application.Application;
import javafx.stage.Stage;

import static Views.LaunchView.playerView;

/**
 * 3*2 ChessApp.
 *
 * @author Kirlos Yousef.
 */
public class ChessApp extends Application {

    /**
     * @param args required main method.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Implement the game with the PlayerName scene.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage = GameData.getWindow();
        primaryStage.setTitle("ChessApp");
        primaryStage.setScene(playerView());
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
