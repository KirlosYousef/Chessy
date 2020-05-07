package Main;

import Models.Data.GameData;
import Views.LaunchView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        GameData gameData = new GameData();
        Scene launchScene = new LaunchView().LaunchScene();

        primaryStage = gameData.getWindow();
        primaryStage.setTitle("ChessApp");
        primaryStage.setScene(launchScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
