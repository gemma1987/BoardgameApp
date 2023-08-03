package com.boardgameApp.Service;

import com.boardgameApp.Repositories.GameRepository;
import com.boardgameApp.Repositories.UserRepository;
import org.springframework.stereotype.Service;

// holds the business logic and call methods in the repository layer
@Service
public class BoardgameAppServices {
    private UserRepository userRepository;
    private GameRepository gameRepository;

    public BoardgameAppServices(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }



}
