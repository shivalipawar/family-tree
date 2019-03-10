package com.shivali.cricket.model;

import java.util.HashMap;

public class Player {
    private String playerName;
    private int currentScore = 0;
    private int numberOfBalls = 0;
    private HashMap<Integer, Integer> playerProbability;

    public Player(String playerName, HashMap<Integer, Integer> playerProbability) {

        this.playerName = playerName;
        this.playerProbability = playerProbability;
    }
    public Player(){

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getNumberOfBalls() {
        return numberOfBalls;
    }

    public void setNumberOfBalls(int numberOfBalls) {
        this.numberOfBalls = numberOfBalls;
    }

    public HashMap<Integer, Integer> getPlayerProbability() {
        return playerProbability;
    }

    public void setPlayerProbability(HashMap<Integer, Integer> playerProbability) {
        this.playerProbability = playerProbability;
    }
}
