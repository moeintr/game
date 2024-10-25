package org.example.repository;

import org.example.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    //List<Game> findGamesByPlayerOneName(String playerOneName);
    @Query("select g from Game g left join fetch Player p on g.playerOne.playerId = p.playerId")
    List<Game> findGames();
}
