package Models.Data;

import javafx.collections.FXCollections;
import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/**
 * The database class to save the player names and scores.
 */
public class Database {

    /**
     * Declares a list of Players.
     * @see Player
     */
    private List<Player> data;

    private static Logger logger = LoggerFactory.getLogger(Database.class);

    /**
     * @return players data.
     * @see #data
     */
    public List<Player> getData() {
        return data;
    }

    /**
     * @param player add the player name and score to the database.
     */
    public void addToDatabase(Player player) {
        Connection conn = null;
        Statement stmt = null;
        String sql;
        try {
            logger.info("STEP 1: Registering JDBC driver...");
            Class.forName("org.h2.Driver");


            logger.info("STEP 2: Opening a connection...");
            conn = DriverManager.getConnection("jdbc:h2:./players");


            logger.info("STEP 3: Inserting data...");
            while (true) {
                try {
                    stmt = conn.createStatement();
                    sql = String.format("INSERT INTO PLAYERS (playerName, playerScore)  VALUES ( '%s', %2d)",
                            player.getName(), player.getScore());
                    stmt.executeUpdate(sql);

                    conn.commit();
                    break;
                } catch (JdbcSQLSyntaxErrorException e) {
                    logger.info("No table founded!");
                    conn = DriverManager.getConnection("jdbc:h2:./players");
                    //STEP 3: Execute a query
                    logger.info("Creating table...");
                    stmt = conn.createStatement();
                    sql = "CREATE TABLE PLAYERS " +
                            "(playerName varchar(100), " +
                            " playerScore int )";
                    stmt.executeUpdate(sql);
                    conn.commit();
                }
            }
            logger.info("Data inserted");
            logger.info("STEP 4: Getting the data...");
            sql = "select * from (select  * from PLAYERS order by playerScore desc) where rownum <= 10";

            stmt.executeQuery(sql);
            ResultSet rs = stmt.executeQuery(sql);

            logger.info("STEP 5: Storing the data...");
            data = FXCollections.observableArrayList();
            while (rs.next()) {
                Player playerData = new Player();
                playerData.setName(rs.getString("playerName"));
                playerData.setScore(rs.getInt("playerScore"));
                data.add(playerData);
            }

            logger.info("STEP 6: Cleaning-up environment...");
            conn.commit();
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            logger.error("Error with JDBC ", se); // Handle errors for JDBC
        } catch (Exception e) {
            logger.error("Error for class", e); // Handle errors for class
        } finally { // finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                logger.error("Error ", se2);
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                logger.error("SQLException", se);
            } // end finally try
        } // end try
    }
}
