package Model;

import static View.ChessBoardView.board;

public class GameState {

    /**
     * @return if the current state is a goal or not.
     */
    public static Boolean isGoal() {
        if (!board[2][0].hasPiece()) {
            return board[0][0].getPiece().getType().name().equals("BISHOP")
                    && board[1][0].getPiece().getType().name().equals("BISHOP")
                    && board[2][1].getPiece().getType().name().equals("KING");
        }
        return false;
    }
}
