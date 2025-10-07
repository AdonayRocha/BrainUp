package com.brainup.BrainUp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartQuizRequest {
    private String playerName;
}