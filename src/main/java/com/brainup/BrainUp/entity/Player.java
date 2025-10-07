package com.brainup.BrainUp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    
    private UUID id;
    private String name;
    private int score = 0;
    private boolean active = true;
    
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.active = true;
    }
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void incrementScore(int points) {
        this.score += points;
    }
}