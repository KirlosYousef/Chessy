package Models.Data;

/**
 * For the player data.
 */
public class Player {
    /**
     * Declares the player's name.
     */
    private String name;

    /**
     * Declares the player's score.
     */
    private int score;

    /**
     * Player constructor with name parameter.
     *
     * @param name Player's name.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Player constructor.
     */
    public Player() {
    }

    /**
     * @return player name.
     * @see #name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name to set the player name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return player score.
     * @see #score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score to set player score.
     */
    public void setScore(int score) {
        this.score = score;
    }
}
