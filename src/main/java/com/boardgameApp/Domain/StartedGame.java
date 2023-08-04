package com.boardgameApp.Domain;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

public class StartedGame {
    @ManyToOne
    private List<User> players;
    @ManyToOne
    private List<Game> games;
    private WinningType winningType;
    private int winningOrLosingAmount;
    private boolean finished;
    private User starterPlayer;
    private User winner;
    @Id
    private static int gameNumber = 0;

    public StartedGame() {
    }

    public StartedGame(List<User> players, List<Game> games, WinningType winningType, int winningOrLosingAmount, boolean finished, User starterPlayer, User winner) {
        Random random = new Random();
        this.players = players;
        this.games = games;
        this.winningType = winningType;
        this.winningOrLosingAmount = winningOrLosingAmount;
        this.finished = finished;
        this.starterPlayer = players.get(random.nextInt(players.size()));
        this.winner = winner;
        gameNumber++;
    }

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public List<Game> getGame() {
        return games;
    }

    public void setGame(List<Game> games) {
        this.games = games;
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
