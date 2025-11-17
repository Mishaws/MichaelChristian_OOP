package com.mygame.tp10; // Pastikan package Anda benar

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Character {

    public Vector2 position;
    private Texture texture;
    public Color tint; // Variabel baru untuk warna

    // Variabel kecepatan baru
    public final float WALK_SPEED = 100f;
    public final float RUN_SPEED = 250f;
    public final float BUFF_SPEED = 400f; // Kecepatan saat di-buff
    public float currentSpeed = 0f;

    // Variabel baru untuk Health
    private int health;
    private int maxHealth = 100;

    // Variabel baru untuk efek Strategy
    private boolean isHealing = false;
    private float healFlashTimer = 0f;

    private boolean isBuffed = false;
    private float buffTimer = 0f;

    private ICharacterState currentState;
    private IFightingStrategy currentStrategy;
    private String combatLog = "None";

    public Character(Texture texture) {
        this.texture = texture;
        this.position = new Vector2(100, 100);
        this.tint = new Color(Color.WHITE);
        this.health = this.maxHealth;

        this.currentState = new StandingState();
        this.currentStrategy = new AggressiveStrategy(); // Default strategy
    }

    public void update(float deltaTime) {
        // 1. State Pattern: Handle transisi
        currentState.handleInput(this);

        // 2. Handle efek (buff/heal)
        updateEffects(deltaTime);

        // 3. State Pattern: Handle pergerakan & speed
        currentState.update(this, deltaTime);

        // 4. Handle Warna & Tint
        applyTints();

        // 5. Strategy Pattern: Handle input untuk ganti & eksekusi
        handleStrategyInput();
    }

    private void updateEffects(float deltaTime) {
        // Hitung mundur timer buff
        if (isBuffed) {
            buffTimer -= deltaTime;
            if (buffTimer <= 0) {
                isBuffed = false;
                buffTimer = 0;
            }
        }

        // Hitung mundur timer kilat heal
        if (isHealing) {
            healFlashTimer -= deltaTime;
            if (healFlashTimer <= 0) {
                isHealing = false;
                healFlashTimer = 0;
            }
        }
    }

    private void applyTints() {
        // Terapkan warna dasar dari state
        currentState.applyTint(this);

        // Timpa warna jika ada efek
        if (isHealing) {
            this.tint.set(Color.GREEN); // Kilat hijau
        }
        if (isBuffed) {
            this.tint.set(Color.RED); // Warna merah saat buff
        }
    }

    public void render(SpriteBatch batch) {
        // Terapkan tint sebelum menggambar
        batch.setColor(this.tint);
        batch.draw(texture, position.x, position.y);
        // Reset warna batch ke default
        batch.setColor(Color.WHITE);
    }

    private void handleStrategyInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            this.setStrategy(new AggressiveStrategy());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            this.setStrategy(new DefensiveStrategy());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            this.performCombatAction();
        }
    }

    // --- Metode Helper untuk Strategy ---

    // KODE BARU (BENAR)
    public void applySpeedBuff(float duration) {
        isBuffed = true;
        buffTimer = duration;
        this.currentSpeed = BUFF_SPEED; // <-- TAMBAHKAN BARIS INI
    }

    public void heal(int amount) {
        this.health += amount;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
        isHealing = true;
        healFlashTimer = 0.2f; // Kilat hijau selama 0.2 detik
    }

    public boolean isBuffed() {
        return isBuffed;
    }

    // --- Setters & Getters ---

    public void setState(ICharacterState newState) {
        this.currentState = newState;
    }

    public String getCurrentStateName() {
        return currentState.getName();
    }

    public void setStrategy(IFightingStrategy newStrategy) {
        this.currentStrategy = newStrategy;
    }

    public void performCombatAction() {
        this.currentStrategy.execute(this);
    }

    public String getCurrentStrategyName() {
        return currentStrategy.getName();
    }

    public void setCombatLog(String log) {
        this.combatLog = log;
    }

    public String getCombatLog() {
        return this.combatLog;
    }

    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public float getBuffTimer() { return buffTimer; }
}
