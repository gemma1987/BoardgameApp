package com.boardgameApp.Controller;

import com.boardgameApp.Domain.*;
import com.boardgameApp.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    UserController(UserRepository repository){
        this.userRepository = repository;
    }

    /*
    Only maps get requests, so gets everything from the map users
     */

    @GetMapping
    public Iterable<User> getAll() {

        return userRepository.findAll();
    }

    /*
    Only maps post requests, so every newly created user goes into the database table users
     */

    @PostMapping
    public String createUser(@RequestBody User newUser) {
        List<User> allUsers = userRepository.findAll();

        for (User  user : allUsers) {
            if(newUser.getUserName().equals(user.getUserName())){
                return "UserName " + user +" already exists";
            }
        }
        userRepository.save(newUser);
        return "Created person: " + newUser;
    }

    /*
    Creates a new user by checking whether the user is already in the database or not.
    If not, the user is created.
     */

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody User newUser) {
        List<User> allUsers = userRepository.findAll();

        for (User  user : allUsers) {
            if(newUser.getUserName().equals(user.getUserName())){
                return "A person with the username " + user.getUserName() +" already exists";
            }
        }
        userRepository.save(newUser);
        return "Created a new account with username: " + newUser.getUserName();
    }

    /*
    Updates the login status for a user to true after logging in
     */

    @PutMapping("/login")
    public String loginUser(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setUserName(other.getUserName());
                user.setUserTypeID(other.getUserTypeID());
                user.setLoggedIn(true);
                userRepository.save(user);
                return "Success";
            }
        }
        return "Failure";
    }

    /*
    Updates the login status for a user to false after logging out
     */

    @PostMapping("/logOut")
    public Status logUserOut(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setUserName(other.getUserName());
                user.setUserTypeID(other.getUserTypeID());
                user.setLoggedIn(false);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

    /*
    Deletes a user by putting the userID in the request
     */

    @DeleteMapping("/{userName}")
    public ResponseEntity<User> deleteUser(@PathVariable String userName) {

        if ((!userRepository.existsById(userName))) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(userName);
        return ResponseEntity.ok().build();
    }
}
