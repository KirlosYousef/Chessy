package Models.Data.Results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Class representing the result of a game played by a specific player.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ChessAppGameResult {

    /**
     * The auto generated id of the player.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the player.
     */
    @Column(nullable = false)
    private String player;

    /**
     * The duration of the game.
     */
    @Column(nullable = false)
    private Integer score;
}
