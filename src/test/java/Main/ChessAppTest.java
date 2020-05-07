package Main;

import Models.Data.GameData;
import Models.Types.PieceType;
import Views.LaunchView;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobotException;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessAppTest extends ApplicationTest {

    @BeforeAll
    public static void turnOffSystemWarnings() {
        System.err.close();
        System.setErr(System.out);
    }

    @Override
    public void start(Stage primaryStage) {
        GameData gameData = new GameData();
        Scene launchScene = new LaunchView().LaunchScene();

        primaryStage = gameData.getWindow();
        primaryStage.setTitle("ChessApp");
        primaryStage.setScene(launchScene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.toFront();
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void testWriteValidPlayerName() throws FxRobotException {

        clickOn("#nameTextField").write("Tester");

        clickOn("#enterButton");

        Exception e = assertThrows(FxRobotException.class, () -> moveTo("#wrongInputLabel"));
        assertEquals("the query \"#wrongInputLabel\" returned no nodes.", e.getMessage());
    }

    @Test
    public void testWriteUnValidPlayerName() {
        clickOn("#nameTextField").write("");

        clickOn("#enterButton");

        moveTo("#wrongInputLabel");
    }

    @Test
    public void testMovingPieceOutOfTheGameWindow() {

        clickOn("#nameTextField").write("Tester");

        clickOn("#enterButton");

        drag("#" + PieceType.KING.name() + "00");

        dropTo(0, 0);

        moveTo("#" + PieceType.KING.name() + "00"); // Make sure that the node/Piece back on its place.
    }

    @Test
    public void testMovingPieceToOccupiedTile() {

        clickOn("#nameTextField").write("Tester");

        clickOn("#enterButton");

        drag("#" + PieceType.KING.name() + "00");

        dropTo("#tile01");

        moveTo("#" + PieceType.KING.name() + "00"); // Make sure that the node/Piece back on its place.
    }

    @Test
    public void testMovingKingToIllegalTile() {

        clickOn("#nameTextField").write("Tester");

        clickOn("#enterButton");

        drag("#" + PieceType.KING.name() + "00");

        dropTo("#tile21");

        moveTo("#" + PieceType.KING.name() + "00"); // Make sure that the node/King back on its place.
    }

    @Test
    public void testMovingBishopToIllegalTile() {

        clickOn("#nameTextField").write("Tester");

        clickOn("#enterButton");

        drag("#" + PieceType.BISHOP.name() + "20");

        dropTo("#tile21");

        moveTo("#" + PieceType.BISHOP.name() + "20"); // Make sure that the node/Bishop back on its place.
    }

    @Test
    public void testMovingRookToIllegalTile() {

        clickOn("#nameTextField").write("Tester");

        clickOn("#enterButton");

        drag("#" + PieceType.ROOK.name() + "01");

        dropTo("#tile21");

        drag("#" + PieceType.ROOK.name() + "11");

        dropTo("#tile21");

        drag("#" + PieceType.ROOK.name() + "01");

        dropTo("#tile11");

        drag("#" + PieceType.ROOK.name() + "21");

        dropTo("#tile01");

        moveTo("#" + PieceType.ROOK.name() + "21"); // Make sure that the node/Rook back on its place.
    }

    @Test
    public void testFullWinGame() {

        clickOn("#nameTextField").write("Tester");

        clickOn("#enterButton");

        drag("#" + PieceType.ROOK.name() + "11");
        dropTo("#tile21");

        drag("#" + PieceType.BISHOP.name() + "20");
        dropTo("#tile11");

        drag("#" + PieceType.ROOK.name() + "21");
        dropTo("#tile20");

        drag("#" + PieceType.BISHOP.name() + "10");
        dropTo("#tile21");

        drag("#" + PieceType.KING.name() + "00");
        dropTo("#tile10");

        drag("#" + PieceType.BISHOP.name() + "11");
        dropTo("#tile00");

        drag("#" + PieceType.KING.name() + "10");
        dropTo("#tile11");

        drag("#" + PieceType.BISHOP.name() + "21");
        dropTo("#tile10");

        drag("#" + PieceType.ROOK.name() + "20");
        dropTo("#tile21");

        drag("#" + PieceType.KING.name() + "11");
        dropTo("#tile20");

        drag("#" + PieceType.ROOK.name() + "21");
        dropTo("#tile11");

        drag("#" + PieceType.KING.name() + "20");
        dropTo("#tile21");

        moveTo("#winnerLabel"); // Make sure that the node/Label did appear (Means game finished).
    }

}