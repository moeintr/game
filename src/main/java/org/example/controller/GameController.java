package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.GameResultDto;
import org.example.dto.GetPlayerDto;
import org.example.model.Game;
import org.example.model.GameMove;
import org.example.model.GameResult;
import org.example.model.Player;
import org.example.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    //http://localhost:8080/game/start?name=moein&gameMove=SCISSORS
    @GetMapping("/start")
    public GameResultDto startGame(@ModelAttribute GetPlayerDto playeOne, @RequestParam(value = "playerTwo", required = false) GetPlayerDto playerTwo) {
        Game game = gameService.startGame(playeOne, playerTwo);
        GameResultDto gameResultDto = new GameResultDto().builder()
                                                            .gameMovePlayeOne(playeOne.getGameMove())
                                                            .gameMovePlayeTwo(game.getPlayerTwo().getGameMove())
                                                            .gameResult(game.getGameResult())
                                                            .build();
        return gameResultDto;
    }
    @PostMapping("/findAllGames")
    public List<Game> findAllGames() {
        return gameService.findAllGames();
    }
    /*@PostMapping("/findGamesByPlayerOneName")
    public List<Game> findGamesByPlayerOneName(String playerOneName) {
        return gameService.findGamesByPlayerOneName(playerOneName);
    }*/
}
