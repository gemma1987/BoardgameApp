package com.boardgameApp.Controller;

import com.boardgameApp.Domain.Game;
import com.boardgameApp.Domain.StartedGame;
import com.boardgameApp.Repositories.GameRepository;
import com.boardgameApp.Repositories.StartedGameRepository;
import com.boardgameApp.Repositories.UserRepository;
import com.boardgameApp.Service.StartedGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/StartedGame")
public class StartedGameController {

    private final StartedGameRepository startedGameRepository;
    private final StartedGameService startedGameService;

    public StartedGameController(
            UserRepository userRepository,
            GameRepository gameRepository,
            StartedGameRepository startedGameRepository,
            StartedGameService startedGameService
    ) {
        this.startedGameRepository = startedGameRepository;
        this.startedGameService = startedGameService;
    }

    /*
    Gets all added myGames from a user
     */

    @GetMapping
    public ResponseEntity<List<Game>> getAll(
            @RequestParam String userID
    ) {
        Optional<List<StartedGame>> optionalMyGames = this.startedGameRepository.findAllByUserUserID(userID);
        if(optionalMyGames.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<StartedGame> startedGames = optionalMyGames.get();
        List<Game> games = new ArrayList<>();

        for(StartedGame startedGame : startedGames){
            games.add(startedGame.getGame());
        }

        return ResponseEntity.ok(games);
    }

    /*
    Adds a game to a user, thus creating a myGame
     */

    @PostMapping
    public ResponseEntity<StartedGame> addGameToUser(
            @RequestParam String gameId,
            @RequestParam List<String> userIDs
    ) {
        StartedGame startedGame = this.startedGameService.addGameToUsers(gameId, userIDs);
        if(startedGame == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(startedGame);
    }

    /*
    Updates a myGame with borrowed details with the myBook id in the request
     */
    //TODO How to get this properly working with adding games to multiple players
    @PutMapping("/gameDetails/{id}")
    public ResponseEntity<StartedGame> updateMyGameDetails (
            @RequestBody StartedGame newStartedGame,
            @PathVariable int gameNumber

    ) {

            StartedGame startedGame = this.startedGameService.updateMyGameDetails(newStartedGame, gameNumber);

            if (startedGame == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(startedGameRepository.save(startedGame));
        }

}
