package com.boardgameApp.Controller;

import com.boardgameApp.Domain.*;
import com.boardgameApp.Repositories.*;
import com.boardgameApp.Util.FileUploadUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameRepository gameRepository;

    GameController(GameRepository repository){
        this.gameRepository = repository;
    }

    /*
    Get everything that's in the map /games
     */

    @GetMapping
    public Iterable<Game> getAll() {
        return gameRepository.findAll();
    }

    /*
    Create a game a.k.a. Post details of a game to the database
     */

    @PostMapping("/addGame")
    ResponseEntity<Game> newGame(@RequestBody Game newGame) {
        List<Game> allGames = gameRepository.findAll();
        for (Game game : allGames) {
            if(game.getTitle().equals(newGame.getTitle())){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        Game game = gameRepository.save(newGame);
        return new ResponseEntity<>(game, HttpStatus.CREATED);

    }

    @PutMapping("/save/{id}")
    public String saveImageOfGame(
            @PathVariable String id,
            @RequestParam("image") MultipartFile multipartFile)
            throws IOException {
            Game game = gameRepository.getById(id);

            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            game.setImage(fileName);

            Game saveGameImage = gameRepository.save(game);
            
            //TODO is it better to keep this as a title or to do this with the id?
            String uploadDir = "game-image/" + saveGameImage.getTitle().toLowerCase().replace(" ", "-");

            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

            return "Success";
    }

    /*
    Update a game by putting the id (gameID) in the request
    */

    @PutMapping("/{gameID}")
    ResponseEntity<Game> updateGame(@RequestBody Game newGame, @PathVariable String gameID) {
        Optional<Game> optionalGame = gameRepository.findById(gameID);

        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            game.setTitle(newGame.getTitle());
            game.setStartingPoints(newGame.getStartingPoints());
            game.setMaxPlayers(newGame.getMaxPlayers());
            game.setMinPlayers(newGame.getMinPlayers());
            game.setPointsGoDown(newGame.getPointsGoDown());


            return ResponseEntity.ok(gameRepository.save(game));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    Delete a game by putting the id (gameID) in the request
     */
    @DeleteMapping("/{gameID}")
    public ResponseEntity<HttpStatus> deleteGame(@PathVariable String gameID) {

        if ((!gameRepository.existsById(gameID))) {
            return ResponseEntity.notFound().build();
        }
        gameRepository.deleteById(gameID);
        return ResponseEntity.noContent().build();
    }

}