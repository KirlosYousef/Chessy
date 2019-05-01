package Project;

public class MoveResult {

    private MoveType type;

    /**
     * @return the type of the movement
     */
    public MoveType getType() {
        return type;
    }

    /**
     * Gets the type and set it
     * @param type
     */
    public MoveResult(MoveType type){
        this.type = type;
    }
}
