package com.boardgameApp.Service;

import com.boardgameApp.Domain.Game;
import com.boardgameApp.Domain.MyGame;
import com.boardgameApp.Domain.User;
import com.boardgameApp.Repositories.GameRepository;
import com.boardgameApp.Repositories.MyGameRepository;
import com.boardgameApp.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyGameService {
    private UserRepository userRepository;
    private GameRepository gameRepository;
    private MyGameRepository myGameRepository;

    public MyGameService(UserRepository userRepository, GameRepository gameRepository, MyGameRepository myGameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.myGameRepository = myGameRepository;
    }


    public MyGame addGameToUsers(
            String gameId,
            List<String> userIDs
    ) {
        List<User> users = new ArrayList<>() {};
        for (String userId: userIDs
             ) {
            users.add(userRepository.getById(userId));
        }
        Game game = gameRepository.getById(gameId);

        MyGame myGame = new MyGame(game, users);

        myGameRepository.save(myGame);
        gameRepository.save(game);

        return myGame;
    }

    public MyGame updateMyGameDetails(
            MyGame newMyGame,
            int gamecounter)
    {
        MyGame myGame = myGameRepository.getById(gamecounter);

        myGame.setWinningType(newMyGame.getWinningType());
        myGame.setWinningOrLosingAmount(newMyGame.getWinningOrLosingAmount());

        return myGame;
    }
}
