package Controllers;

import Models.Data.Database;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The controller of the {@link Views.ResultView}.
 */
public class ResultController {

    /**
     * Defines a database.
     */
    private static Database database = new Database();

    /**
     * Will be called when the user reach the Goal state in the game.
     * It will add the player's data to the database.
     *
     * @see Database
     * @param score user's score to be stored in the database.
     */
    public static void GoalReached(int score){
        LaunchController.getPlayer().setScore(score);
        database.addToDatabase(LaunchController.getPlayer());
    }

    /**
     * @return a map of the top 10 players, the map has the player's Name and Score.
     */
    public static Map<String, Integer> getTop10() {
        Map<String, Integer> top10 = new HashMap<String,Integer>();
        database.getData().forEach(player -> top10.put(player.getName(), player.getScore()));
        return top10.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
