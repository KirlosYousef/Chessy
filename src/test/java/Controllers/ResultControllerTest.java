package Controllers;

import Models.Data.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResultControllerTest {

    @BeforeEach
    void setUp() {
        Player player = new Player();
        player.setName("Testing");
        LaunchController.setPlayer(player);
    }

    @AfterEach
    void tearDown() { }

    @Test
    void goalReached() {
        ResultController.GoalReached(100);
    }

    @Test
    void getTop10() {

        ResultController.GoalReached(9999);

        assertTrue(ResultController.getTop10().size() <= 10);

        assertTrue(ResultController.getTop10().containsValue(9999));

        assertTrue(ResultController.getTop10().containsKey("Testing"));
    }
}