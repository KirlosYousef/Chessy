package Project;

/**
 * For the player data
 */
public class Player {
    private String name;
    private int score;

    /**
     * @return player name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name to set the player name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return player score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score to set player score
     */
    public void setScore(int score) {
        this.score = score;
    }
}
