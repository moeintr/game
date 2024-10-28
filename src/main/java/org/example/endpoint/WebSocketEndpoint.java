package org.example.endpoint;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.example.model.Game;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/game-updates")
public class WebSocketEndpoint {
    private static Map<Session, Integer> sessions = new ConcurrentHashMap<>();

    @OnMessage
    public void onMessage(Session session, int gameId) {
        sessions.put(session, gameId);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    public static void broadcastGameUpdate(Game game) throws IOException, EncodeException {
        for (Map.Entry<Session, Integer> entry : sessions.entrySet()) {
            if (game.getGameId().equals(entry.getValue())) {
                entry.getKey().getBasicRemote().sendObject(game);
            }
        }
    }
}