package Controllers;

import Models.Components.Piece;
import Models.Data.MoveResult;
import Models.Types.MoveType;
import Models.Types.PieceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static Models.Data.GameData.*;
import static Models.Data.GameState.isGoal;
import static Views.ChessBoardView.*;
import static Views.ResultView.Goal;

public class GameController {

    private static Logger logger = LoggerFactory.getLogger(GameController.class);

    /**
     * Make a new piece on the cell with the x and y coordinates.
     *
     * @param type the type of the piece to make.
     * @param x    the x coordinate of the the cell to take.
     * @param y    the y coordinate of the the cell to take.
     * @return a piece which has a type and coordinates.
     */
    public Piece makePiece(PieceType type, int x, int y) {

        Piece piece = new Piece(type, x, y);

        piece.setOnMouseReleased(e -> {             //when releasing the mouse
            // check if the mouse released out side the chessBoard then abort the movement
            boolean inSize = toBoard(piece.getLayoutX()) <= 2 && toBoard(piece.getLayoutX()) >= 0
                    && toBoard(piece.getLayoutY()) <= 1 && toBoard(piece.getLayoutY()) >= 0;

            if (!inSize) {
                piece.abortMove();
                logger.warn("Tried to drop the " + piece.getType() + " away!");
            } else {
                int newX = toBoard(piece.getLayoutX());
                int newY = toBoard(piece.getLayoutY());

                MoveResult result = tryMove(piece, newX, newY);

                int x0 = toBoard(piece.getOldX());
                int y0 = toBoard(piece.getOldY());

                switch (result.getType()) { //check the return score
                    case NONE: {
                        piece.abortMove(); //if it's none then call abortMove() which returns the piece back(Not to move)
                        break;
                    }
                    case MOVE: {
                        piece.move(newX, newY);                  //if it's move then move it to the new coordinates
                        board[x0][y0].setPiece(null);            //make the old cell empty
                        board[newX][newY].setPiece(piece);       //take the new cell
                        addMove();                               //Adds a move to the model data
                        logger.info("Piece " + piece.getType() + " Moved");
                        if (isGoal()) {
                            logger.info("Reached goal state!");
                            Goal();
                        }
                        break;
                    }
                }
            }


        });
        return piece;
    }

    /**
     * to change the pixels position to coordinates.
     *
     * @param pixel the pixel position of the piece.
     * @return cell coordinate.
     */
    private int toBoard(double pixel) {
        return (int) (pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

    /**
     * A method which is responsible about the possibility of movement.
     *
     * @param piece is the piece to move.
     * @param newX  the x coordinate of the new cell.
     * @param newY  the y coordinate of the new cell.
     * @return a score to make the move according to it.
     * @see #makePiece(PieceType, int, int).
     */
    private MoveResult tryMove(Piece piece, int newX, int newY) {

        //get the old x and y coordinates
        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());

        if (board[newX][newY].hasPiece()) { // if the the new cell is not empty, do not move
            return new MoveResult(MoveType.NONE);
        } else  //Check if the new cell is empty
        {
            switch (piece.getType().name()) {
                case "KING":  //if the piece to move is a king
                {
                    if (((newX == x0 + 1 || newX == x0 - 1)) && Math.abs(newY - y0) <= 1 ||
                            (newY == y0 + 1 || newY == y0 - 1) && Math.abs(newX - x0) <= 1) {//and the new cell to take is one step far
                        return new MoveResult(MoveType.MOVE);       //then make the move
                    }
                    break;
                }
                case "BISHOP": //if the piece to move is a bishop
                {
                    if ((newX == x0 + 1 || newX == x0 - 1) && (newY == y0 + 1 || newY == y0 - 1)) { //and the new cell to take is in same diagonal
                        return new MoveResult(MoveType.MOVE);       //then make the move
                    }
                    break;
                }
                case "ROOK": //if the piece to move is a rook
                {
                    if (x0 == newX) {                           //and the new cell to take is in same column
                        return new MoveResult(MoveType.MOVE);//then make the move
                    } else if (y0 == newY) {                            //if the new cell to take is in the same row
                        if (newX > x0) {                             //check if it is moving to the right
                            if (!board[x0 + 1][y0].hasPiece()) {     //then check if the middle cell doesn't has piece
                                return new MoveResult(MoveType.MOVE);//then make the move
                            }
                        } else {                           //check if it is moving to the left
                            if (!board[x0 - 1][y0].hasPiece()) {        //then check if the middle cell doesn't has piece
                                return new MoveResult(MoveType.MOVE);   //then make the move
                            }
                        }
                    }
                    break;
                }

            }
        }
        return new MoveResult(MoveType.NONE);
    }


}

