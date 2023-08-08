package com.boardgameApp.Domain;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

public class StartedGame {
    @ManyToMany
    private List<User> players;
    @ManyToOne
    private Game game;
    private WinningType winningType;
    private int winningOrLosingAmount;
    private boolean finished;
    private User starterPlayer;
    private User winner;
    @Id
    private static int gameCounter = 0;

    public StartedGame() {
    }

    public StartedGame(List<User> players, Game game, WinningType winningType, int winningOrLosingAmount, boolean finished, User winner) {
        Random random = new Random();
        this.players = players;
        this.game = game;
        this.winningType = winningType;
        this.winningOrLosingAmount = winningOrLosingAmount;
        this.finished = finished;
        this.starterPlayer = players.get(random.nextInt(players.size()));
        this.winner = winner;
        gameCounter++;
    }

    public StartedGame(Game game, List<User> players) {
        this.game = game;
        this.players = players;
    }

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game games) {
        this.game = game;
    }

    public WinningType getWinningType() {
        return winningType;
    }

    public void setWinningType(WinningType winningType) {
        this.winningType = winningType;
    }

    public int getWinningOrLosingAmount() {
        return winningOrLosingAmount;
    }

    public void setWinningOrLosingAmount(int winningOrLosingAmount) {
        this.winningOrLosingAmount = winningOrLosingAmount;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public User getStarterPlayer() {
        return starterPlayer;
    }

    public void setStarterPlayer(User starterPlayer) {
        this.starterPlayer = starterPlayer;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

}
