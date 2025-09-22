package Service;

import Model.Score;
import Repository.*;
import java.util.*;

public class ScoreService (ScoreRepository scoreRepository, PlayerRepository playerRepository, PlayerService playerService) {
    private ScoreRepository scoreRepository;
    private PlayerRepository playerRepository;
    private PlayerService playerService;

    public createScore(Score score) {
        this.scoreRepository = score;
    }

    public getScoreById(UUID scoreId) {

    }

    public getAllScores() {

    }

    public getScoresByPlayerId(UUID playerId) {

    }

    public getScoresByPlayerIdOrderByValue(UUID playerId) {

    }

    public getLeaderboard(int limit) {

    }

    public getHighestScoreByPlayerId(UUID playerId) {

    }

    public getScoresAboveValue(int minValue) {

    }

    public getRecentScores() {

    }

    public getTotalCoinsByPlayerId(UUID playerId) {

    }

    public getTotalDistanceByPlayerId(UUID playerId) {

    }

    public updateScore(UUID scoreId, Score updatedScore) {

    }

    public deleteScore(UUID scoreId) {

    }

    public deleteScoresByPlayerId(UUID playerId) {

    }
}
