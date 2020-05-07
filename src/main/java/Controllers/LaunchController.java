package Controllers;

import Models.Data.Player;

/**
 * Controls the Player's data at the {@link Views.LaunchView}.
 */
public class LaunchController {

    /**
     * A player of type {@link Player}.
     */
    private static Player player;

    /**
     * @return player's data.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Defines a new Player.
     *
     * @param player to be defined.
     */
    public void setPlayer(Player player) {
        LaunchController.player = player;
    }

}
