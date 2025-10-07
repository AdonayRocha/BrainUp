package com.brainup.BrainUp.controller;

import com.brainup.BrainUp.dto.StartQuizRequest;
import com.brainup.BrainUp.dto.StartQuizResponse;
import com.brainup.BrainUp.entity.Player;
import com.brainup.BrainUp.service.QuizService;
import com.brainup.BrainUp.service.SseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class QuizGameController {
    
    @Autowired
    private QuizService quizService;
    
    @Autowired
    private SseService sseService;
    
    /**
     * Endpoint para iniciar o quiz
     * POST /start
     */
    @PostMapping("/start")
    public ResponseEntity<StartQuizResponse> startQuiz(@RequestBody StartQuizRequest request) {
        try {
            // Valida se o nome do jogador foi fornecido
            if (request.getPlayerName() == null || request.getPlayerName().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            
            // Cria e adiciona o jogador ao quiz
            Player player = quizService.joinQuiz(request.getPlayerName().trim());
            
            // Cria um emitter para o jogador
            SseEmitter playerEmitter = sseService.createPlayerEmitter(player.getId());
            
            // Retorna o ID do jogador
            StartQuizResponse response = new StartQuizResponse(player.getId());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * Endpoint para abrir stream para o admin
     * GET /stream/admin
     */
    @GetMapping(value = "/stream/admin", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamAdmin() {
        return sseService.createAdminEmitter();
    }
    
    /**
     * Endpoint para abrir stream para jogador específico
     * GET /stream/player/{playerId}
     */
    @GetMapping(value = "/stream/player/{playerId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> streamPlayer(@PathVariable UUID playerId) {
        // Verifica se o jogador existe no sistema
        if (!sseService.hasPlayerEmitter(playerId)) {
            // Se não existe, cria um novo emitter
            SseEmitter emitter = sseService.createPlayerEmitter(playerId);
            return ResponseEntity.ok(emitter);
        }
        
        // Se já existe, retorna o existente (na prática, isso criaria um novo devido à implementação)
        SseEmitter emitter = sseService.createPlayerEmitter(playerId);
        return ResponseEntity.ok(emitter);
    }
    
    /**
     * Endpoint para jogador sair do quiz
     * DELETE /exit/{playerId}
     */
    @DeleteMapping("/exit/{playerId}")
    public ResponseEntity<Void> exitQuiz(@PathVariable UUID playerId) {
        boolean success = quizService.exitQuiz(playerId);
        
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Endpoint para obter status do quiz
     * GET /quiz/status
     */
    @GetMapping("/quiz/status")
    public ResponseEntity<QuizStatusResponse> getQuizStatus() {
        var activeQuiz = quizService.getActiveQuiz();
        if (activeQuiz == null) {
            return ResponseEntity.notFound().build();
        }
        
        long activePlayers = activeQuiz.getPlayers().stream()
            .filter(Player::isActive)
            .count();
            
        QuizStatusResponse response = new QuizStatusResponse(
            activeQuiz.getId(),
            activeQuiz.getTitle(),
            activeQuiz.getDescription(),
            activePlayers,
            sseService.getActiveAdminsCount()
        );
        
        return ResponseEntity.ok(response);
    }
    
    // Classe interna para resposta de status
    public static class QuizStatusResponse {
        private UUID quizId;
        private String title;
        private String description;
        private long activePlayers;
        private int activeAdmins;
        
        public QuizStatusResponse(UUID quizId, String title, String description, long activePlayers, int activeAdmins) {
            this.quizId = quizId;
            this.title = title;
            this.description = description;
            this.activePlayers = activePlayers;
            this.activeAdmins = activeAdmins;
        }
        
        // Getters
        public UUID getQuizId() { return quizId; }
        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public long getActivePlayers() { return activePlayers; }
        public int getActiveAdmins() { return activeAdmins; }
        
        // Setters
        public void setQuizId(UUID quizId) { this.quizId = quizId; }
        public void setTitle(String title) { this.title = title; }
        public void setDescription(String description) { this.description = description; }
        public void setActivePlayers(long activePlayers) { this.activePlayers = activePlayers; }
        public void setActiveAdmins(int activeAdmins) { this.activeAdmins = activeAdmins; }
    }
}