package View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * To make a new tile which is a rectangle.
 */
public class Tile extends Rectangle {

    /**
     * Declares a piece.
     * @see Piece
     */
    private Piece piece;

    /**
     * @return if piece is not null, then it has a piece.
     */
    public boolean hasPiece(){
        return piece != null;
    }

    /**
     * @return the piece.
     * @see #piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * @param piece Set this piece on this tile.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }


    /**
     * To make the tile with width, height, location and color.
     * @param light if it is a white cell.
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public Tile(boolean light, int x, int y){
        setWidth(ChessBoardView.TILE_SIZE);
        setHeight(ChessBoardView.TILE_SIZE);

        relocate(x * ChessBoardView.TILE_SIZE, y * ChessBoardView.TILE_SIZE);

        setFill(light ? Color.WHITE : Color.GRAY);
    }
}
