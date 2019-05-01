package Project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import static Project.ChessApp.TILE_SIZE;

/**
 *Contains the image and the information about the piece
 */
public class Piece extends StackPane {
    private Image image;
    private PieceType type;

    private double mouseX, mouseY;
    private double oldX, oldY;

    private ImageView imageView = new ImageView();

    /**
     * @return the type of the piece
     */
    public PieceType getType() {
        return type;
    }

    /**
     * @return the pixel of x of the piece
     */
    public double getOldX() {
        return oldX;
    }

    /**
     * @return the pixel of y of the piece
     */
    public double getOldY() {
        return oldY;
    }

    /**
     * @param type the type of piece
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Piece(PieceType type, int x, int y) {
        this.type = type;

        move(x, y);

        image = new Image(type == PieceType.KING ? "file:src/main/java/ChessPiece/White_King.png" :
                (type == PieceType.BISHOP ? "file:src/main/java/ChessPiece/White_Bishop.png" :
                        "file:src/main/java/ChessPiece/White_Rook.png"));

        imageView.setImage(image);
//        imageView.fitHeightProperty();
//        imageView.fitWidthProperty();
//        imageView.setPreserveRatio(true);
//        imageView.setSmooth(true);
//        imageView.setCache(true);
        imageView.setTranslateX((TILE_SIZE - TILE_SIZE * 0.3 * 2) / 2);
        imageView.setTranslateY((TILE_SIZE - TILE_SIZE * 0.3 * 2) / 2);
        getChildren().add(imageView);

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });
        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + oldX,
                    e.getSceneY() - mouseY + oldY);
        });
    }

    /**
     * Function to move the piece to the new coordinates * the size of the tile
     * @param x new x coordinate
     * @param y new y coordinate
     */
    public void move(int x, int y) {
        oldX = x * TILE_SIZE;
        oldY = y * TILE_SIZE;
        relocate(oldX, oldY);
    }

    /**
     * To get back the piece to the old cell (Not to move)
     */
    public void abortMove(){
        relocate(oldX, oldY);
    }

}
