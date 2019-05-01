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
     * @param type Sets the type
     */
    public MoveResult(MoveType type){
        this.type = type;
    }
}
