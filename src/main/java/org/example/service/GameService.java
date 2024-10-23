package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.GameResultDto;
import org.example.dto.GetPlayerDto;
import org.example.model.Game;
import org.example.model.GameMove;
import org.example.model.GameResult;
import org.example.model.Player;
import org.example.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@AllArgsConstructor
@Service
public class GameService {
    private final GameRepository gameRepository;
    
    public Game startGame(GetPlayerDto playeOne, GetPlayerDto playerTwo) {
        Game game = new Game().builder()
                              .playerOne(new Player().builder()
                                      .playerName(playeOne.getName())
                                      .gameMove(playeOne.getGameMove()).build())
                              .build();
        if (Objects.isNull(playerTwo)){  //means play vs machine
            int pick = new Random().nextInt(GameMove.values().length);
            GameMove machineGameMove = GameMove.values()[pick];
            game.setPlayerTwo(new Player().builder().playerName("machine").gameMove(machineGameMove).build());
            game.setGameResult(compareMoves(game.getPlayerOne().getGameMove(), machineGameMove));
        }else {
            game.setGameResult(compareMoves(game.getPlayerOne().getGameMove(), game.getPlayerTwo().getGameMove()));
        }
        game.setGameDate(new Date());
        gameRepository.save(game);

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
    
    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }
    
    /*public List<Game> findGamesByPlayerOneName(String playerOneName) {
        return gameRepository.findGamesByPlayerOneName(playerOneName);
    }*/
}
