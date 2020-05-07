package Models.Data.Results;


public class GameResultExample {

    public static void main(String[] args) {
        GameResultDao gameResultDao = GameResultDao.getInstance();
        ChessAppGameResult gameResult = ChessAppGameResult.builder()
                .player("Test")
                .score(145)
                .build();
        gameResultDao.persist(gameResult);
        System.out.println(gameResult);
        System.out.println(gameResultDao.findBest(10));
    }

}
