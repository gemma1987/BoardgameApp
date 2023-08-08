package com.boardgameApp.Repositories;

import com.boardgameApp.Domain.MyGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyGameRepository extends JpaRepository<MyGame, int> {
    Optional<List<MyGame>> findAllByUserUserID(String userID);

}
