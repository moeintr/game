package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Game;
import org.example.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GameService {
    private final GameRepository gameRepository;
    public Game startGame(Game game) {

        return null;
    }
    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }
    public List<Game> findGamesByPlayerOneName(String playerOneName) {
        return gameRepository.findGamesByPlayerOneName(playerOneName);
    }
}
