package Service;

import Model.Player;
import Repository.*;
import java.util.*;


public class PlayerService {
    private PlayerRepository PlayerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.PlayerRepository = playerRepository;
    }

    public boolean existByUsername(String Username) {
        this.PlayerRepository = ;
    }

    public createPlayer(Player player) {
        PlayerRepository createPlayer;
        this.PlayerRepository = createPlayer;
    }

    public getPlayerById(UUID playerID) {|
        this.PlayerRepository =;
    }

    public getAllPlayer() {
        this.
    }

    public updatePlayer(UUID playerId, Player updatePlayer) {

    }

    public deletePlayer(UUID playerId) {

    }

    public deletePlayerByUsername(String username) {

    }

    public updatePlayerStats(UUID playerId, int scoreValue, int coinsCollected, int  distanceTravelled) {

    }

    public getLeaderboardByHighScore(int limit) {

    }

    public getLeaderboardByTotalCoins() {

    }

    public getLeaderboardByTotalDistance() {
        
    }
}


