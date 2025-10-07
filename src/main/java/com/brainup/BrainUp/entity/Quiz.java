package com.brainup.BrainUp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    
    private UUID id;
    private String title;
    private String description;
    private List<Question> questions = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    
    public Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
        this.players = new ArrayList<>();
    }
    
    public Quiz(String title, String description) {
        this.title = title;
        this.description = description;
        this.questions = new ArrayList<>();
        this.players = new ArrayList<>();
    }
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Question> getQuestions() {
        return questions;
    }
    
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    public void addQuestion(Question question) {
        questions.add(question);
    }
    
    public List<Player> getPlayers() {
        return players;
    }
    
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }
    
    public void removePlayer(Player player) {
        players.remove(player);
    }
}