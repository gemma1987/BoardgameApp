package com.boardgameApp.Service;

import com.boardgameApp.Repositories.GameRepository;
import com.boardgameApp.Repositories.PersonRepository;
import org.springframework.stereotype.Service;

// holds the business logic and call methods in the repository layer
@Service
public class BoardgameAppServices {
    private PersonRepository personRepository;
    private GameRepository gameRepository;

    public BoardgameAppServices(PersonRepository personRepository, GameRepository gameRepository) {
        this.personRepository = personRepository;
        this.gameRepository = gameRepository;
    }



}
