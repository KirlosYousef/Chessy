package Models.Components;

import Models.Types.PieceType;
import Views.ChessBoardView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


/**
 * Contains the image and the information about the piece.
 *
 * @see Piece
 */
public class Piece extends StackPane {


    /**
     * Gets The Tile Size.
     */
    private static Integer TILE_SIZE = new ChessBoardView().TILE_SIZE;
    /**
     * Declares a the type of piece.
     *
     * @see PieceType
     */
    private PieceType type;
    /**
     * Declares where the mouse pressed.
     */
    private double mouseX, mouseY;
    /**
     * Declares The position of the piece before the movement.
     */
    private double oldX, oldY;

    /**
     * @param type the type of piece.
     * @param x    the x coordinate.
     * @param y    the y coordinate.
     */
    public Piece(PieceType type, int x, int y) {
        this.type = type;

        ImageView imageView = new ImageView();
        Image image;
        move(x, y);

        String kingImg = "/pictures/White_King.png";
        String bishopImg = "/pictures/White_Bishop.png";
        String rookImg = "/pictures/White_Rook.png";

        image = new Image(type == PieceType.KING ? kingImg : (type == PieceType.BISHOP ? bishopImg : rookImg));

        imageView.setImage(image);
        imageView.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3 * 2) / 2);
        imageView.setTranslateY((TILE_SIZE - TILE_SIZE * 0.3 * 2) / 2);
        getChildren().add(imageView);

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldX, e.getSceneY() - mouseY + oldY);
        });
    }

    /**
     * @return the type of the piece.
     */
    public PieceType getType() {
        return type;
    }

    /**
     * @return the pixel of x of the piece.
     */
    public double getOldX() {
        return oldX;
    }

    /**
     * @return the pixel of y of the piece.
     */
    public double getOldY() {
        return oldY;
    }

    /**
     * Function to move the piece to the new coordinates * the size of the tile.
     *
     * @param x new x coordinate.
     * @param y new y coordinate.
     */
    public void move(int x, int y) {
        oldX = x * TILE_SIZE;
        oldY = y * TILE_SIZE;
        relocate(oldX, oldY);
    }

    /**
     * To get back the piece to the old cell (Not to move).
     */
    public void abortMove() {
        relocate(oldX, oldY);
    }

}
