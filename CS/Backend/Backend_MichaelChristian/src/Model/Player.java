package Model;
import java.time.LocalDateTime;
import java.util.UUID;

public class Player {
    private UUID playerID;
    private String username;
    private int totalCoins;
    private int highScore;
    private int totalDistance;
    private LocalDateTime createdAt;

    public Player(String username) {
        this.playerID = UUID.randomUUID();
        this.username = username;
        this.totalCoins = 0;
        this.highScore = 0;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getPlayerID(){
        return playerID;
    }

    public void updateHighScore(int newScore) {
        if (newScore > highScore){
            this.highScore = newScore;
        }
    }

    public void addCoins(int coins){
        totalCoins += coins;
    }

    public void addDistance(int distance){
        totalDistance += distance;
    }

    @Override
    public void showDetail(){
        System.out.println("Player ID: " + playerID);
        System.out.println("Username: " + username);
        System.out.println("High Score: " + highScore);
        System.out.println("Total Coins: " + totalCoins);
        System.out.println("Total Distance: "+ totalDistance);
        System.out.println("Created At: " + createdAt);

    }
}
