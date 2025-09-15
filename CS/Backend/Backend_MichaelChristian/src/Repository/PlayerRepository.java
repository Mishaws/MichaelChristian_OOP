package Repository;
import Model.Player;

public class BaseRepository<Player, UUID> extends PlayerRepository {
    void findByUsername(String username) {
        public Optional<Player> findByUsername(String username)
        {
            return allData.stream()
                    .filter(player ->
                            player.getUsername().equals(username))
                    .findFirst();
        }

     void findTopPlayersByHighScore(int limit) {

        }

        void findByHighscoreGreaterThan(int minScore) {

        }
        void findAllByOrderByTotalCoinsDesc(){

        }
        void findAllByOrderByTotalDistanceTravelledDesc(){

        }
        void save(Player player);
        void getID(Player entity);
    }
}