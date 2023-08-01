package com.boardgameApp.Controller;

import com.boardgameApp.Domain.*;
import com.boardgameApp.Repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class GameController {

    private final GameRepository gameRepository;

    GameController(GameRepository repository){
        this.gameRepository = repository;
    }

    /*
    Get everything that's in the map /books
     */

    @GetMapping
    public Iterable<Game> getAll() {
        return gameRepository.findAll();
    }

    /*
    Create a book a.k.a. Post details of a book to the database
     */

    @PostMapping
    ResponseEntity<Game> newBook(@RequestBody Game newGame) {

        Game game = gameRepository.save(newGame);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    /*
    Delete a book by putting the id (isbn) in the request
     */

    @DeleteMapping("/{isbn}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long isbn) {

        if ((!gameRepository.existsById(isbn))) {
            return ResponseEntity.notFound().build();
        }
        gameRepository.deleteById(isbn);
        return ResponseEntity.noContent().build();
    }

    /*
    Update a book by putting the id (isbn) in the request
    */

    @PutMapping("/{isbn}")
    ResponseEntity<Game> updateGame(@RequestBody Game newGame, @PathVariable Long isbn) {
        Optional<Game> optionalGame = gameRepository.findById(isbn);

        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            game.setTitle(newGame.getTitle());

            return ResponseEntity.ok(gameRepository.save(game));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}