package com.boardgameApp.Domain;

import com.boardgameApp.Service.MyGenerator;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
// tells the program to call the table 'game'.
@Table(name = "game")

// marks a property to be ignored
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Game implements Serializable{
    private String title;

    //GenericGenerator is being called to give in the generated value
    @Id
    @GeneratedValue(generator = MyGenerator.generatorName)
    @GenericGenerator(name = MyGenerator.generatorName, strategy = "com.boardgameApp.Service.MyGenerator")
    private String gameId;

    private int minPlayers;
    private int maxPlayers;
    private int startingPoints;
    private boolean pointsGoDown;
    @Column(nullable = true, length = 64)
    private String image;
    public Game() {

    }

    public Game(String title, String gameId, int minPlayers, int maxPlayers, int startingPoints, boolean pointsGoDown, String image) {
        this.title = title;
        this.gameId = gameId;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.startingPoints = startingPoints;
        this.pointsGoDown = pointsGoDown;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getGameId() {
        return gameId;
    }
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getStartingPoints() {
        return startingPoints;
    }

    public void setStartingPoints(int startingPoints) {
        this.startingPoints = startingPoints;
    }

    public boolean getPointsGoDown() {
        return pointsGoDown;
    }

    public void setPointsGoDown(boolean pointsGoDown) {
        this.pointsGoDown = pointsGoDown;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Transient
    public String getImagePath() {
        if (image == null || gameId == null) return null;

        return "/Game-image/" + gameId + "/" + image;
    }
}

