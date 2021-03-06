package Models.Data;

import Models.Types.MoveType;

/**
 * The result of the movement.
 */
public class MoveResult {
    /**
     * Declares a type of move has done.
     *
     * @see MoveType
     */
    private MoveType type;

    /**
     * @param type Sets the type.
     */
    public MoveResult(MoveType type) {
        this.type = type;
    }

    /**
     * @return the type of the movement.
     * @see #type
     */
    public MoveType getType() {
        return type;
    }
}
