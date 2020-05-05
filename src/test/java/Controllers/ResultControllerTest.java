package Controllers;

import Models.Data.Player;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ResultControllerTest {

    @BeforeEach
    void setUp() {
        Player player = new Player();
        player.setName("Tester");
        LaunchController.setPlayer(player);
    }

    @BeforeAll
    static void dropTableIfExists() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:./players");
        try {
            Statement stmt = conn.createStatement();
            String sql = "DROP TABLE PLAYERS;";
            stmt.executeUpdate(sql);
            conn.commit();
        } catch (SQLSyntaxErrorException e){
            // There is already no tables.
            assertEquals("Table \"PLAYERS\" not found; SQL statement:\n" +
                    "DROP TABLE PLAYERS; [42102-200]", e.getMessage());
        }
    }

    @Test
    void testGoalReached() {
        ResultController.GoalReached(100);
    }

    @Test
    void testGetTop10(){
        int score = new Random().nextInt(10000);

        ResultController.GoalReached(score);

        assertTrue(ResultController.getTop10().size() <= 10);

        assertTrue(ResultController.getTop10().containsKey("Tester"));

        assertTrue(ResultController.getTop10().containsValue(score));
    }
}