package com.boardgameApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.boardgameApp.Domain.Game;
import org.springframework.stereotype.Repository;

/*
The long is the id(primary key) of the class, in this case isbn
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}