package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.GameResultDto;
import org.example.model.Game;
import org.example.model.GameMove;
import org.example.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    //http://localhost:8080/game/start?playerOneName=moein&playerOneGameMove=SCISSORS&playerTwoName=mahdi&playerTwoGameMove=PAPER  --->  vs human
    //http://localhost:8080/game/start?playerOneName=moein&playerOneGameMove=SCISSORS  --->  vs machine
    @GetMapping("/start")
    public ResponseEntity<GameResultDto> startGame(@RequestParam String playerOneName,
                                                   @RequestParam GameMove playerOneGameMove,
                                                   @RequestParam(required = false) String playerTwoName,
                                                   @RequestParam(required = false) GameMove playerTwoGameMove) {
        Game game = gameService.startGame(playerOneName,playerOneGameMove,playerTwoName,playerTwoGameMove);
        GameResultDto gameResultDto = new GameResultDto().builder()
                                                            .playerOne(game.getPlayerOne())
                                                            .playerTwo(game.getPlayerTwo())
                                                            .gameResult(game.getGameResult())
                                                            .gameDate(game.getGameDate())
                                                            .build();
        return new ResponseEntity<>(gameResultDto, HttpStatus.OK);
    }
    @GetMapping("/findAllGames")
    public ResponseEntity<List<Game>> findAllGames() {
        return new ResponseEntity<>(gameService.findAllGames(), HttpStatus.OK);
    }
    /*@PostMapping("/findGamesByPlayerOneName")
    public List<Game> findGamesByPlayerOneName(String playerOneName) {
        return gameService.findGamesByPlayerOneName(playerOneName);
    }*/
}
