package Models.Data;

import static Views.ChessBoardView.board;

/**
 * Responsible for the current game state.
 */
public class GameState {

    /**
     * @return if the current state is a goal or not.
     */
    public Boolean isGoal() {
        if (!board[2][0].hasPiece()) {
            return board[0][0].getPiece().getType().name().equals("BISHOP")
                    && board[1][0].getPiece().getType().name().equals("BISHOP")
                    && board[2][1].getPiece().getType().name().equals("KING");
        }
        return false;
    }
}
