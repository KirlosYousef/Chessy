package Controllers;

import Models.Data.Database;
import java.util.*;
import java.util.stream.Collectors;

public class ResultController {

    private static Database database = new Database();

    public static void GoalReached(int score){
        LaunchController.getPlayer().setScore(score);
        database.addToDatabase(LaunchController.getPlayer());
    }

    public static Map<String, Integer> getTop10() {
        Map<String, Integer> top10 = new HashMap<String,Integer>();
        database.getData().forEach(player -> top10.put(player.getName(), player.getScore()));
        return top10.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
