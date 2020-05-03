package Controllers;

import Models.Data.Player;

public class LaunchController {

    private static Player player;

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        LaunchController.player = player;
    }


}
