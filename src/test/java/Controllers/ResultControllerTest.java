package Controllers;

import Models.Data.Player;
import Models.Data.Results.ChessAppGameResult;
import Models.Data.Results.GameResultDao;
import org.junit.jupiter.api.*;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ResultControllerTest {

    private static GameResultDao gameResultDao = GameResultDao.getInstance();

    private static ResultController resultController = new ResultController();

    private static LaunchController launchController = new LaunchController();

    @BeforeAll
    static void deleteAllRowsOfTheTable() {
        gameResultDao.deleteAll();
    }

    @BeforeEach
    void setUp() {
        Player player = new Player();
        player.setName("Tester");
        launchController.setPlayer(player);
    }

    @Test
    void testDataAddedToTheTableWhenGoalReached() {
        resultController.GoalReached(100);

        assertTrue(gameResultDao.findAll().stream().map(ChessAppGameResult::getPlayer)
                .anyMatch(name -> name.equals(launchController.getPlayer().getName())));
    }

    @Test
    void testGetTop10() {
        int score = new Random().nextInt(10000);

        resultController.GoalReached(score);

        assertTrue(resultController.getTop10().size() <= 10);

        assertTrue(resultController.getTop10().stream().map(ChessAppGameResult::getPlayer)
                .anyMatch(playerName -> playerName.equals(launchController.getPlayer().getName())));

        assertTrue(resultController.getTop10().stream().map(ChessAppGameResult::getScore)
                .anyMatch(playerScore -> playerScore == launchController.getPlayer().getScore()));
    }
}