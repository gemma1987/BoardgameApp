package com.boardgameApp.Controller;

import com.boardgameApp.Domain.*;
import com.boardgameApp.Repositories.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class PersonController {

    private final PersonRepository personRepository;

    PersonController(PersonRepository repository){
        this.personRepository = repository;
    }

    /*
    Only maps get requests, so gets everything from the map users
     */

    @GetMapping
    public Iterable<Person> getAll() {

        return personRepository.findAll();
    }

    /*
    Only maps post requests, so every newly created user goes into the database table users
     */

    @PostMapping
    public String createUser(@RequestBody Person person) {

        List<Person> allUsers = personRepository.findAll();

        for (Person user: allUsers) {
            if(user.getUserName().equals(person.getUserName())){
                return "UserName " + person +" already exists";
            }
        }
        personRepository.save(person);
        return "Created person: " + person;
    }

    /*
    Creates a new user by checking whether the user is already in the database or not.
    If not, the user is created.
     */

    @PostMapping("/register")
    ResponseEntity<Person> registerUser(@Valid @RequestBody Person newPerson) {
        List<Person> allUsers = personRepository.findAll();
        for (Person user : allUsers) {
            if (user.equals(newPerson)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        Person person = personRepository.save(newPerson);
        return new ResponseEntity<>(person, HttpStatus.CREATED);

    }

    /*
    Updates the login status for a user to true after logging in
     */

    @PutMapping("/login")
    public String loginUser(@Valid @RequestBody Person person) {
        List<Person> people = personRepository.findAll();
        for (Person other : people) {
            if (other.equals(person)) {
                person.setUserName(other.getUserName());
                person.setUserTypeID(other.getUserTypeID());
                person.setUserID(other.getUserID());
                person.setLoggedIn(true);
                personRepository.save(person);
                return "Success";
            }
        }
        return "Failure";
    }

    /*
    Updates the login status for a user to false after logging out
     */

    @PostMapping("/logOut")
    public Status logUserOut(@Valid @RequestBody Person person) {
        List<Person> people = personRepository.findAll();
        for (Person other : people) {
            if (other.equals(person)) {
                person.setUserName(other.getUserName());
                person.setUserTypeID(other.getUserTypeID());
                person.setUserID(other.getUserID());
                person.setLoggedIn(false);
                personRepository.save(person);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }

    /*
    Deletes a user by putting the userID in the request
     */

    @DeleteMapping("/{userID}")
    public ResponseEntity<Person> deleteUser(@PathVariable Integer userID) {

        if ((!personRepository.existsById(userID))) {
            return ResponseEntity.notFound().build();
        }
        personRepository.deleteById(userID);
        return ResponseEntity.ok().build();
    }
}
