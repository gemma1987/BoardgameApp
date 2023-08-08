package com.boardgameApp.Controller;

import com.boardgameApp.Domain.Game;
import com.boardgameApp.Domain.MyGame;
import com.boardgameApp.Repositories.GameRepository;
import com.boardgameApp.Repositories.MyGameRepository;
import com.boardgameApp.Repositories.UserRepository;
import com.boardgameApp.Service.MyGameService;
import com.boardgameApp.Util.FileUploadUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/MyGame")
public class MyGameController {

    private final MyGameRepository myGameRepository;
    private final MyGameService myGameService;

    public MyGameController(
            UserRepository userRepository,
            GameRepository gameRepository,
            MyGameRepository myGameRepository,
            MyGameService myGameService
    ) {
        this.myGameRepository = myGameRepository;
        this.myGameService = myGameService;
    }

    /*
    Gets all added myGames from a user
     */

    @GetMapping
    public ResponseEntity<List<Game>> getAll(
            @RequestParam String userID
    ) {
        Optional<List<MyGame>> optionalMyGames = this.myGameRepository.findAllByUserUserID(userID);
        if(optionalMyGames.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<MyGame> myGames = optionalMyGames.get();
        List<Game> games = new ArrayList<>();

        for(MyGame myGame : myGames){
            games.add(myGame.getGame());
        }

        return ResponseEntity.ok(games);
    }

    /*
    Adds a game to a user, thus creating a myGame
     */

    @PostMapping
    public ResponseEntity<MyGame> addGameToUser(
            @RequestParam String gameId,
            @RequestParam List<String> userIDs
    ) {
        MyGame myGame = this.myGameService.addGameToUsers(gameId, userIDs);
        if(myGame == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(myGame);
    }

    /*
    Updates a myGame with borrowed details with the myBook id in the request
     */
    //TODO How to get this properly working with adding games to multiple players
    @PutMapping("/gameDetails/{id}")
    public ResponseEntity<MyGame> updateMyGameDetails (
            @RequestBody MyGame newMyGame,
            @PathVariable String gameNumber

    ) {

            MyGame myGame = this.myGameService.updateMyGameDetails(newMyGame, gameNumber);

            if (myBook == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(myBookRepository.save(myBook));
        }
    /*
    Updates a myBook with a rating with the myBook id in the request
     */

    @PutMapping("/bookRating/{id}")
    public ResponseEntity<MyBook> updateMyBookRating(
            @RequestBody MyBook newMyBook,
            @PathVariable Integer id
    ) {
        MyBook myBook = this.myBookService.updateMyBookRating(newMyBook, id);

        if (myBook == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(myBookRepository.save(myBook));
    }
}
