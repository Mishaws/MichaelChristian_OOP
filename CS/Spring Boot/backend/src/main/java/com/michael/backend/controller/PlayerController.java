package com.michael.backend.controller; // Sesuaikan dengan nama package Anda

import com.michael.backend.model.Player; // Sesuaikan dengan nama package Anda
import com.michael.backend.service.PlayerService; // Sesuaikan dengan nama package Anda
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST Controller untuk mengelola operasi terkait Player.
 * Menangani permintaan HTTP dan memberikan respons dalam format JSON.
 */
@RestController
@RequestMapping("/api/players") // Base path untuk semua endpoint di controller ini
public class PlayerController {

    // 3. Hubungkan Controller dengan Service menggunakan Dependency Injection
    @Autowired
    private PlayerService playerService;

    // 4a. GET /api/players - Get Semua Player
    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players); // Mengembalikan daftar player dengan status 200 OK
    }

    // 4b. GET /api/players/{playerId} - Get Player Berdasarkan ID
    @GetMapping("/{playerId}")
    public ResponseEntity<?> getPlayerById(@PathVariable UUID playerId) {
        Optional<Player> player = playerService.getPlayerById(playerId);
        if (player.isPresent()) {
            return ResponseEntity.ok(player.get()); // Ditemukan, kembalikan data dengan status 200 OK
        } else {
            // Tidak ditemukan, kembalikan status 404 NOT FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found with ID: " + playerId);
        }
    }

    // 4c. GET /api/players/username/{username} - Get Player Berdasarkan Username
    @GetMapping("/username/{username}")
    public ResponseEntity<?> getPlayerByUsername(@PathVariable String username) {
        Optional<Player> player = playerService.getPlayerByUsername(username);
        if (player.isPresent()) {
            return ResponseEntity.ok(player.get()); // Ditemukan, kembalikan data
        } else {
            // Tidak ditemukan, kembalikan status 404 NOT FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found with username: " + username);
        }
    }

    // 4d. GET /api/players/check-username/{username} - Cek Ketersediaan Username
    @GetMapping("/check-username/{username}")
    public boolean checkUsername(@PathVariable String username) {
        // Langsung memanggil service dan mengembalikan boolean (true jika ada, false jika tidak)
        return playerService.isUsernameExists(username);
    }

    // 5a. POST /api/players - Membuat Player Baru
    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody Player player) {
        try {
            Player newPlayer = playerService.createPlayer(player);
            // Sukses, kembalikan player baru dengan status 201 CREATED
            return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Gagal (misal: username sudah ada), kembalikan pesan error dengan status 400 BAD REQUEST
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 6a. PUT /api/players/{playerId} - Memperbarui Player
    @PutMapping("/{playerId}")
    public ResponseEntity<?> updatePlayer(@PathVariable UUID playerId, @RequestBody Player player) {
        try {
            Player updatedPlayer = playerService.updatePlayer(playerId, player);
            return ResponseEntity.ok(updatedPlayer); // Sukses, kembalikan data player yang sudah diupdate
        } catch (RuntimeException e) {
            // Gagal (misal: ID tidak ditemukan), kembalikan pesan error dengan status 404 NOT FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 6b. DELETE /api/players/{playerId} - Menghapus Player
    @DeleteMapping("/{playerId}")
    public ResponseEntity<Void> deletePlayer(@PathVariable UUID playerId) {
        try {
            playerService.deletePlayer(playerId);
            // Sukses, kembalikan status 204 NO CONTENT (artinya berhasil tanpa ada body respons)
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Gagal (ID tidak ditemukan), kembalikan status 404 NOT FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 7. Endpoint untuk Leaderboard
    // 7a. GET /api/players/leaderboard/high-score
    @GetMapping("/leaderboard/high-score")
    public ResponseEntity<List<Player>> getLeaderboardByHighScore(@RequestParam(defaultValue = "10") int limit) {
        List<Player> leaderboard = playerService.getLeaderboardByHighScore(limit);
        return ResponseEntity.ok(leaderboard);
    }

    // 7b. GET /api/players/leaderboard/total-coins
    @GetMapping("/leaderboard/total-coins")
    public ResponseEntity<List<Player>> getLeaderboardByTotalCoins() {
        List<Player> leaderboard = playerService.getLeaderboardByTotalCoins();
        return ResponseEntity.ok(leaderboard);
    }

    // 7c. GET /api/players/leaderboard/total-distance
    @GetMapping("/leaderboard/total-distance")
    public ResponseEntity<List<Player>> getLeaderboardByTotalDistance() {
        List<Player> leaderboard = playerService.getLeaderboardByTotalDistance();
        return ResponseEntity.ok(leaderboard);
    }
}