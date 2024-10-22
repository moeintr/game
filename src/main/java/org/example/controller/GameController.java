package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.Game;
import org.example.service.GameService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GameController {
    private final GameService gameService;
    public Game startGame(Game game) {
        return null;
    }
    public List<Game> findAllGames() {
        return null;
    }
    public List<Game> findGamesByPlayerOneName() {
        return null;
    }
}
