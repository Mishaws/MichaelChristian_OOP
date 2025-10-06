package com.michael.backend.service;
import com.michael.backend.model.Score;
import com.michael.backend.repository.PlayerRepository;
import com.michael.backend.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

    @Transactional
    public Score createScore(Score score) {
        if (!playerRepository.existsById(score.getPlayerId())) {
            throw new RuntimeException("Player not found with ID: " + score.getPlayerId());
        }
        Score savedScore = scoreRepository.save(score);
        playerService.updatePlayerStats(
                savedScore.getPlayerId(),
                savedScore.getValue(),
                savedScore.getCoinsCollected(),
                savedScore.getDistanceTravelled()
        );
        return savedScore;
    }

    public Optional<Score> getScoreById(UUID scoreId) {
        return scoreRepository.findById(scoreId);
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    public List<Score> getScoresByPlayerId(UUID playerId) {
        return scoreRepository.findByPlayerId(playerId);
    }

    public List<Score> getScoresByPlayerIdOrderByValue(UUID playerId) {
        return scoreRepository.findByPlayerIdOrderByValueDesc(playerId);
    }

    public List<Score> getLeaderboard(int limit) {
        return scoreRepository.findTopScores(limit);
    }
}