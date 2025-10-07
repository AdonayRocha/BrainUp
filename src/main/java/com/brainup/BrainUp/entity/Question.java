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
public class Question {
    
    private UUID id;
    private String text;
    private List<Alternative> alternatives = new ArrayList<>();
    
    public Question(String text) {
        this.text = text;
        this.alternatives = new ArrayList<>();
    }
    
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public List<Alternative> getAlternatives() {
        return alternatives;
    }
    
    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }
    
    public void addAlternative(Alternative alternative) {
        alternatives.add(alternative);
    }
}