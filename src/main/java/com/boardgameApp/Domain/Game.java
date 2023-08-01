package com.boardgameApp.Domain;

import com.boardgameApp.Service.MyGenerator;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
// tells the program to call the table 'book'.
@Table(name = "game")

// marks a property to be ignored
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Game implements Serializable{
    private String title;

    @Id
    @GeneratedValue(generator = MyGenerator.generatorName)
    @GenericGenerator(name = MyGenerator.generatorName, strategy = "com.boardgameApp.Service.MyGenerator")
    private String id;

    public Game(String title) {
        this.title = title;
    }

    public Game() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

