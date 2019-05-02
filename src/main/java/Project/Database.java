package Project;

import javafx.collections.FXCollections;

import java.sql.*;
import java.util.List;

public class Database {

    private List<Player> data;

    public List<Player> getData() {
        return data;
    }


    public void addToDatabase(Player player) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName("org.h2.Driver");

            //STEP 2: Open a connection
            conn = DriverManager.getConnection("jdbc:h2:file:./database/players", "", "");

            // STEP 3: INSERT DATA
            stmt = conn.createStatement();
            String sql = String.format("INSERT INTO PLAYERS (playerName, playerScore)  VALUES ( '%s', %2d)",
                    player.getName(), player.getScore());
            stmt.executeUpdate(sql);

            // STEP 4: GET DATA
            sql = "select * from (select  * from PLAYERS order by playerScore desc) where rownum <= 10 ";
            stmt.executeQuery(sql);
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 5: STORE DATA
            data = FXCollections.observableArrayList();
            while (rs.next()) {
                Player playerData = new Player();
                playerData.setName(rs.getString("playerName"));
                playerData.setScore(rs.getInt("playerScore"));
                data.add(playerData);
            }

            // STEP 6: Clean-up environment
            conn.commit();
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try

    }


}
