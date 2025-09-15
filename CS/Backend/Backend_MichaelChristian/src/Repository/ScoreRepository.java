package Repository;
import Model.Score;

public class ScoreRepository {
    void findByPlayerId(UUID, playerId){

    }
    void findByPlayerIdOrderByValueDesc(UUID playerId){

    }
    void findTopScores(int limit){

    }
    void findHighestScoreByPlayerId(UUID playerId){

    }
    void findByValueGreaterThan(Integer minValue){

    }
    void findAllByOrderByCreatedAtDesc(){

    }
    void getTotalCoinsByPlayerId(UUID playerId){
        public Integer getTotalCoinsByPlayerId(UUID playerId) {
            return allData.stream()
                    .filter(score ->
                            score.getPlayerId().equals(playerId))
                    .mapToInt(Score::getCoinsCollected)
                    .sum();
        }
    }
    void getTotalDistanceByPlayerId(UUID playerId) {

    }

    save(Score score);
    getID(Score entity);
}
