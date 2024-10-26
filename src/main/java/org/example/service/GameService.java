package org.example.service;

import lombok.AllArgsConstructor;
import org.example.exception.NotFoundException;
import org.example.model.Game;
import org.example.model.GameMove;
import org.example.model.GameResult;
import org.example.model.Player;
import org.example.repository.GameRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@AllArgsConstructor
@Service
public class GameService {
    private final GameRepository gameRepository;
    private final SimpMessagingTemplate messagingTemplate;
    
    public Game startGame(String playerOneName, GameMove playerOneGameMove) {
        Game game = new Game().builder()
                              .playerOne(new Player().builder()
                                            .playerName(playerOneName)
                                            .gameMove(playerOneGameMove).build())
                              .build();
        game.setStartGameDate(new Date());
        gameRepository.save(game);

        return game;
    }

    public Game finishGame(int gameId, String playerTwoName, GameMove playerTwoGameMove) throws NotFoundException {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new NotFoundException("Game not found"));

        if (Objects.isNull(playerTwoName) || Objects.isNull(playerTwoGameMove)){  //means play vs machine
            int pick = new Random().nextInt(GameMove.values().length);
            GameMove machineGameMove = GameMove.values()[pick];
            game.setPlayerTwo(new Player().builder().playerName("machine").gameMove(machineGameMove).build());
            game.setGameResult(compareMoves(game.getPlayerOne().getGameMove(), machineGameMove));
        }else { //means play vs human
            game.setPlayerTwo(new Player().builder().playerName(playerTwoName).gameMove(playerTwoGameMove).build());
            game.setGameResult(compareMoves(game.getPlayerOne().getGameMove(), playerTwoGameMove));
        }
        game.setFinishGameDate(new Date());
        gameRepository.save(game);

        messagingTemplate.convertAndSend("/topic/game-updates", game);
        return game;
    }

    private GameResult compareMoves(GameMove movePlayerOne, GameMove movePlayerTwo) {
        if (movePlayerOne.equals(movePlayerTwo))
        {
            return GameResult.EQUAL;
        } else if (movePlayerOne.equals(GameMove.PAPER) && movePlayerTwo.equals(GameMove.ROCK) ||
                   movePlayerOne.equals(GameMove.ROCK) && movePlayerTwo.equals(GameMove.SCISSORS) ||
                   movePlayerOne.equals(GameMove.SCISSORS) && movePlayerTwo.equals(GameMove.PAPER))
        {
            return GameResult.WIN_PLAYER_ONE;
        }else
        {
            return GameResult.WIN_PLAYER_TWO;
        }
    }

    public Game findGameById(int gameId) throws NotFoundException {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new NotFoundException("Game not found"));
        return game;
    }
    
    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    public List<Game> findGames() {
        return gameRepository.findGames();
    }
    
    /*public List<Game> findGamesByPlayerOneName(String playerOneName) {
        return gameRepository.findGamesByPlayerOneName(playerOneName);
    }*/
}
