package com.boardgameApp.Repositories;

import com.boardgameApp.Domain.StartedGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StartedGameRepository extends JpaRepository<StartedGame, Integer> {
    Optional<List<StartedGame>> findAllByUserUserID(String userID);

}
