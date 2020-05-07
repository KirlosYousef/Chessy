package Models.Data;

import Models.Data.Results.ChessAppGameResult;
import Models.Data.Results.GameResultDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * The database class to save the player names and scores.
 */
public class Database {

    /**
     * Logger for Database class.
     */
    private static Logger logger = LoggerFactory.getLogger(Database.class);

    /**
     * Declares a list of Players.
     *
     * @see Player
     */
    private List<ChessAppGameResult> data;

    /**
     * @return players data.
     * @see #data
     */
    public List<ChessAppGameResult> getData() {
        return data;
    }

    /**
     * Add the {@code player} name and score to the database.
     *
     * @param player of type {@link Player} to be added in the database.
     */
    public void addToDatabase(Player player) {
        GameResultDao gameResultDao = GameResultDao.getInstance();

        logger.debug("Creating Player's game result...");
        ChessAppGameResult gameResult = ChessAppGameResult.builder()
                .player(player.getName())
                .score(player.getScore())
                .build();
        logger.info("Player's game result created!");

        logger.debug("Uploading game result to the database...");
        gameResultDao.persist(gameResult);
        logger.info("Data Uploaded!");

        logger.debug("Fetching Top 10 Players...");
        data = gameResultDao.findBest(10);
        logger.info("Top 10 Players fetched and stored!");
    }
}
