package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.exception.NotFoundException;
import org.example.model.Game;
import org.example.model.GameMove;
import org.example.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    //http://localhost:8080/game/start?playerOneName=moein&playerOneGameMove=SCISSORS  --->  start game
    //http://localhost:8080/game/finish?gameId=1&playerTwoName=mahdi&playerTwoGameMove=PAPER  --->  vs human finish
    //http://localhost:8080/game/finish?gameId=1 --->  vs machine finish
    @PostMapping("/start")
    public ResponseEntity<Game> startGame(@RequestParam String playerOneName,
                                          @RequestParam GameMove playerOneGameMove) {
        Game game = gameService.startGame(playerOneName,playerOneGameMove);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
    @PostMapping("/finish")
    public ResponseEntity<Game> finishGame(@RequestParam int gameId,
                                           @RequestParam(required = false) String playerTwoName,
                                           @RequestParam(required = false) GameMove playerTwoGameMove) throws NotFoundException {
        Game game = gameService.finishGame(gameId, playerTwoName, playerTwoGameMove);
        broadcastGameUpdate(game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
    @MessageMapping("/game-updates")
    @SendTo("/topic/game-updates")
    public Game broadcastGameUpdate(Game game) {
        return game;
    }
    @GetMapping("/findGameById")
    public ResponseEntity<Game> findGameById(@RequestParam int gameId) throws NotFoundException {
        return new ResponseEntity<>(gameService.findGameById(gameId), HttpStatus.OK);
    }
    @GetMapping("/findAllGames")
    public ResponseEntity<List<Game>> findAllGames() {
        return new ResponseEntity<>(gameService.findAllGames(), HttpStatus.OK);
    }
    @GetMapping("/findGames")
    public ResponseEntity<List<Game>> findGames() {
        return new ResponseEntity<>(gameService.findGames(), HttpStatus.OK);
    }
}
