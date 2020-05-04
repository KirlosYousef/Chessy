package Views;

import Models.Components.Piece;
import Models.Components.Tile;
import Models.Types.PieceType;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Controllers.GameController;

import static Models.Data.GameData.*;
import static javafx.scene.paint.Color.WHITE;


/**
 * To control everything within the board.
 *
 * @see GameController
 */
public class ChessBoardView extends Pane {

    /**
     * Logger for Database class.
     */
    private static Logger logger = LoggerFactory.getLogger(ChessBoardView.class);

    /**
     * A game controller.
     */
    private static GameController gameController = new GameController();

    /**
     * Declares the tile size.
     */
    public static final int TILE_SIZE = 100;

    /**
     * Declares the tile width.
     */
    public static final int WIDTH = 3;

    /**
     * Declares the tile height.
     */
    public static final int HEIGHT = 2;

    /**
     * Make a tile array which represents the board cells.
     * @see Tile
     */
    public static Tile[][] board = new Tile[WIDTH][HEIGHT];


    /**
     * Creates a pane with size the fits the number of tiles * it's size.
     * adding the tileGroups and pieceGroup into it.
     *
     * @return the pane with all content.
     */
    public static Parent createGame() {
        //Groups to collect tiles and pieces.
        Group tileGroup = new Group();
        Group pieceGroup = new Group();

        Pane root = new Pane();
        long startTime = System.currentTimeMillis();
        Label timerLabel = new Label("Time: ");

        Label movesLabel = new Label("Moves: ");
        Label moves = new Label("0");
        getTimer().setTextFill(WHITE);
        timerLabel.setTextFill(WHITE);
        movesLabel.setTextFill(WHITE);
        moves.setTextFill(WHITE);
        HBox timerBox = new HBox();
        HBox movesBox = new HBox();
        timerBox.getChildren().addAll(timerLabel, getTimer());
        movesBox.getChildren().addAll(movesLabel, moves);
        movesBox.setTranslateX(237);

        new AnimationTimer() { //To continue updating the timer and moves labels
            @Override
            public void handle(long now) {
                long elapsedMillis = System.currentTimeMillis() - startTime;
                getTimer().setText(Long.toString(elapsedMillis / 1000));
                moves.setText(String.valueOf(getNumOfMoves()));
            }
        }.start();


        root.getChildren().addAll(tileGroup, pieceGroup, timerBox, movesBox);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 != 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);

                Piece piece = null;

                if (x == 0 && y == 0) {
                    piece = gameController.makePiece(PieceType.KING, x, y);
                } else if ((x == 1 || x == 2) && y == 0) {
                    piece = gameController.makePiece(PieceType.BISHOP, x, y);
                } else if ((x == 0 || x == 1)) {
                    piece = gameController.makePiece(PieceType.ROOK, x, y);
                }

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
        logger.info("Game created!");
        return root;
    }
}
