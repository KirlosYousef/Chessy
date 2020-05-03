package Controller;

import Model.Player;

public class PlayerController {
    private static Player player;

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        PlayerController.player = player;
    }
}
