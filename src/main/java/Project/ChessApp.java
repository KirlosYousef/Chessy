package Project;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 3*2 ChessApp
 *
 * @author Kirlos Yousef
 */
public class ChessApp extends Application {

    //set the tile_size, width and the height of the tile
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 3;
    public static final int HEIGHT = 2;

    private Tile[][] board = new Tile[WIDTH][HEIGHT]; //make a tile array which represents the board cells

    //new groups to collect tiles and pieces
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();

    /**
     * Creates a pane with size the fits the number of tiles * it's size
     * adding the tileGroups and pieceGroup into it
     *
     * @return the pane with all content
     */
    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        root.getChildren().addAll(tileGroup, pieceGroup);

        for (int y = 0; y < HEIGHT; y++){
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 != 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);

                Piece piece = null;

                if (x==0 && y==0){
                    piece = makePiece(PieceType.KING, x, y);
                }

                else if ((x==1||x==2) && y == 0){
                    piece = makePiece(PieceType.BISHOP, x, y);
                }

                else if ((x == 0||x==1) && y == 1){
                    piece = makePiece(PieceType.ROOK, x, y);
                }

                if (piece != null) {
                    tile.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
        return root;
    }

    /**
     * A function which is responsible about the possibility of movement
     * @param piece is the piece to move
     * @param newX the x coordinate of the new cell
     * @param newY the y coordinate of the new cell
     * @return a result to make the move according to it
     * @see #makePiece(PieceType, int, int)
     */
    private MoveResult tryMove(Piece piece, int newX, int newY){
        if(board[newX][newY].hasPiece()){ // if the the new cell is not empty, do not move
            return new MoveResult(MoveType.NONE);
        }

        //get the old x and y coordinates
        int x0 = toBoard(piece.getOldX());
        int y0 = toBoard(piece.getOldY());

        if (!board[newX][newY].hasPiece()) //Check if the new cell is empty
             {
            if (piece.getType().name().equals("ROOK")){  //if the piece to move is a rook
                if (x0==newX){                           //and the new cell to take is in same column
                    return new MoveResult(MoveType.MOVE);//then make the move
                }
                else if (y0 == newY){                            //if the new cell to take is in the same row
                    if (newX > x0) {                             //check if it is moving to the right
                        if (!board[x0 + 1][y0].hasPiece()) {     //then check if the middle cell doesn't has piece
                            return new MoveResult(MoveType.MOVE);//then make the move
                        }
                    }
                    else if (newX < x0) {                           //check if it is moving to the left
                        if (!board[x0 - 1][y0].hasPiece()) {        //then check if the middle cell doesn't has piece
                            return new MoveResult(MoveType.MOVE);   //then make the move
                        }
                    }
                }
            }
            else if (piece.getType().name().equals("BISHOP")){  //if the piece to move is a bishop
                if ((newX == x0+1 || newX == x0-1) && (newY == y0+1 || newY == y0-1)){ //and the new cell to take is in same diagonal
                    return new MoveResult(MoveType.MOVE);       //then make the move
                }
            }
            else if (piece.getType().name().equals("KING")){    //if the piece to move is a king
                if (((newX == x0 + 1 || newX == x0 - 1)) && Math.abs(newY-y0) <=1 ||
                        (newY == y0 + 1 || newY == y0 - 1) && Math.abs(newX-x0) <=1 ) {//and the new cell to take is one step far
                    return new MoveResult(MoveType.MOVE);       //then make the move
                }
            }
        }
        return new MoveResult(MoveType.NONE);
    }

    /**
     * to change the pixels position to coordinates
     * @param pixel the pixel position of the piece
     * @return cell coordinate
     */
    private int toBoard(double pixel){
        return (int)(pixel + TILE_SIZE /2) / TILE_SIZE;
    }

    /**
     * Implement the game with the scene we made
     * @see #createContent()
     * @throws Exception in case of wrong data
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("ChessApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Make a new piece on the cell with the x and y coordinates
     * @param type the type of the piece to make
     * @param x the x coordinate of the the cell to take
     * @param y the y coordinate of the the cell to take
     * @return a piece which has a type and coordinates
     */
    private Piece makePiece(PieceType type, int x, int y){

        Piece piece = new Piece(type, x, y);

        piece.setOnMouseReleased(e -> {             //when releasing the mouse
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            MoveResult result = tryMove(piece, newX, newY);

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            switch (result.getType()){ //check the return result
                case NONE:
                    piece.abortMove(); //if it's none then call abortMove() which returns the piece back(Not to move)
                    break;
                case MOVE:
                    piece.move(newX, newY);                  //if it's move then move it to the new coordinates
                    board[x0][y0].setPiece(null);            //make the old cell empty
                    board[newX][newY].setPiece(piece);       //take the new cell
                    break;
            }
        });

        return piece;
    }

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }


}
