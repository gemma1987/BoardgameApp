package com.boardgameApp.Service;

import com.boardgameApp.Domain.Game;
import com.boardgameApp.Domain.StartedGame;
import com.boardgameApp.Domain.User;
import com.boardgameApp.Repositories.GameRepository;
import com.boardgameApp.Repositories.StartedGameRepository;
import com.boardgameApp.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StartedGameService {
    private UserRepository userRepository;
    private GameRepository gameRepository;
    private StartedGameRepository startedGameRepository;

    public StartedGameService(UserRepository userRepository, GameRepository gameRepository, StartedGameRepository startedGameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.startedGameRepository = startedGameRepository;
    }


    public StartedGame addGameToUsers(
            String gameId,
            List<String> userIDs
    ) {
        List<User> users = new ArrayList<>() {};
        for (String userId: userIDs
             ) {
            users.add(userRepository.getById(userId));
        }
        Game game = gameRepository.getById(gameId);

        StartedGame startedGame = new StartedGame(game, users);

        startedGameRepository.save(startedGame);
        gameRepository.save(game);

        return startedGame;
    }

    public StartedGame updateMyGameDetails(
            StartedGame newStartedGame,
            int gamecounter)
    {
        StartedGame startedGame = startedGameRepository.getById(gamecounter);

        startedGame.setWinningType(newStartedGame.getWinningType());
        startedGame.setWinningOrLosingAmount(newStartedGame.getWinningOrLosingAmount());

        return startedGame;
    }
}
