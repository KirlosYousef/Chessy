package Project;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 3*2 ChessApp.
 *
 * @author Kirlos Yousef.
 */
public class ChessApp extends Application {

    /**
     * Declaring a chessboard of type ChessBoard.
     * @see ChessBoard
     */
    private ChessBoard chessBoard = new ChessBoard();

    /**
     * @param args required main method.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Implement the game with the PlayerName scene.
     *
     * @see ChessBoard#playerScene().
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage = chessBoard.getWindow();
        primaryStage.setTitle("ChessApp");
        primaryStage.setScene(chessBoard.playerScene());
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
