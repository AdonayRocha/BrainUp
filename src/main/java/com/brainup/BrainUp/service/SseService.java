package com.brainup.BrainUp.service;

import com.brainup.BrainUp.entity.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.UUID;

@Service
public class SseService {
    
    private final ConcurrentHashMap<UUID, SseEmitter> playerEmitters = new ConcurrentHashMap<>();
    private final CopyOnWriteArrayList<SseEmitter> adminEmitters = new CopyOnWriteArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    // Timeout de 30 minutos
    private static final long SSE_TIMEOUT = 30L * 60 * 1000;
    
    public SseEmitter createPlayerEmitter(UUID playerId) {
        SseEmitter emitter = new SseEmitter(SSE_TIMEOUT);
        
        emitter.onCompletion(() -> playerEmitters.remove(playerId));
        emitter.onTimeout(() -> playerEmitters.remove(playerId));
        emitter.onError((ex) -> playerEmitters.remove(playerId));
        
        playerEmitters.put(playerId, emitter);
        
        try {
            emitter.send(SseEmitter.event()
                .name("connected")
                .data("Player connected"));
        } catch (IOException e) {
            emitter.completeWithError(e);
        }
        
        return emitter;
    }
    
    public SseEmitter createAdminEmitter() {
        SseEmitter emitter = new SseEmitter(SSE_TIMEOUT);
        
        emitter.onCompletion(() -> adminEmitters.remove(emitter));
        emitter.onTimeout(() -> adminEmitters.remove(emitter));
        emitter.onError((ex) -> adminEmitters.remove(emitter));
        
        adminEmitters.add(emitter);
        
        try {
            emitter.send(SseEmitter.event()
                .name("admin.connected")
                .data("Admin connected"));
        } catch (IOException e) {
            emitter.completeWithError(e);
        }
        
        return emitter;
    }
    
    public void sendPlayerJoinedEvent(Player player) {
        sendToAdmins("player.joined", player);
    }
    
    public void sendPlayerExitedEvent(Player player) {
        sendToAdmins("player.exited", player);
    }
    
    public void sendToPlayer(UUID playerId, String eventName, Object data) {
        SseEmitter emitter = playerEmitters.get(playerId);
        if (emitter != null) {
            try {
                String jsonData = objectMapper.writeValueAsString(data);
                emitter.send(SseEmitter.event()
                    .name(eventName)
                    .data(jsonData));
            } catch (IOException e) {
                playerEmitters.remove(playerId);
                emitter.completeWithError(e);
            }
        }
    }
    
    public void sendToAdmins(String eventName, Object data) {
        String jsonData;
        try {
            jsonData = objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            return; // Se não conseguir serializar, não envia
        }
        
        adminEmitters.removeIf(emitter -> {
            try {
                emitter.send(SseEmitter.event()
                    .name(eventName)
                    .data(jsonData));
                return false;
            } catch (IOException e) {
                emitter.completeWithError(e);
                return true;
            }
        });
    }
    
    public void removePlayerEmitter(UUID playerId) {
        SseEmitter emitter = playerEmitters.remove(playerId);
        if (emitter != null) {
            emitter.complete();
        }
    }
    
    public boolean hasPlayerEmitter(UUID playerId) {
        return playerEmitters.containsKey(playerId);
    }
    
    public int getActivePlayersCount() {
        return playerEmitters.size();
    }
    
    public int getActiveAdminsCount() {
        return adminEmitters.size();
    }
}