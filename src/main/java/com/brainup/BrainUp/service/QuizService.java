package com.brainup.BrainUp.service;

import com.brainup.BrainUp.entity.Quiz;
import com.brainup.BrainUp.entity.Player;
import com.brainup.BrainUp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.Optional;

@Service
public class QuizService {
    
    @Autowired
    private QuizRepository quizRepository;
    
    @Autowired
    private SseService sseService;
    
    public Quiz getActiveQuiz() {
        return quizRepository.getQuiz();
    }
    
    public Player joinQuiz(String playerName) {
        Quiz activeQuiz = quizRepository.getQuiz();
        
        Player player = new Player(playerName);
        player.setId(UUID.randomUUID());
        
        // Adiciona o jogador ao quiz
        activeQuiz.addPlayer(player);
        
        // Notifica os administradores
        sseService.sendPlayerJoinedEvent(player);
        
        return player;
    }
    
    public boolean exitQuiz(UUID playerId) {
        Quiz activeQuiz = quizRepository.getQuiz();
        
        // Procura o jogador no quiz
        Optional<Player> playerOpt = activeQuiz.getPlayers().stream()
            .filter(p -> p.getId().equals(playerId))
            .findFirst();
            
        if (playerOpt.isPresent()) {
            Player player = playerOpt.get();
            player.setActive(false);
            
            // Remove o emitter do jogador
            sseService.removePlayerEmitter(playerId);
            
            // Notifica os administradores
            sseService.sendPlayerExitedEvent(player);
            
            return true;
        }
        
        return false;
    }
    

}