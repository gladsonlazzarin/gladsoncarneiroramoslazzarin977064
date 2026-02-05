package com.gladson.seletivo.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlbumNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public AlbumNotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Notifica todos os clientes conectados sobre novo álbum
     */
    public void notifyNewAlbum(String albumName) {
        messagingTemplate.convertAndSend("/topic/new-album", albumName);
    }
}
