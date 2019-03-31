package com.shivali.cricket;

import com.shivali.cricket.model.Player;

import java.util.ArrayList;
import java.util.List;

class CricketField {

    private Player onStrike;
    private Player offStrike;
    private int currentScore;
    private int targetScore;
    private int totalWickets;
    private int wicketsDown;
    private int oversCompleted;
    private int totalOvers;

    CricketField() {
    }

    CricketField(List<Player> players, int totalOvers, int targetScore) {
        this.wicketsDown = 0;
        this.oversCompleted = 0;
        this.onStrike = players.get(0);
        this.offStrike = players.get(1);
        this.totalOvers = totalOvers;
        this.targetScore = targetScore;
        this.totalWickets = players.size() - 2;
    }

    int getTotalOvers() {
        return totalOvers;
    }

    //TODo Delete this only used in test
    void setTotalOvers(int totalOvers) {
        this.totalOvers = totalOvers;
    }

    Player getOnStrike() {
        return onStrike;
    }

    //TODo Delete this only used in test
    void setOnStrike(Player onStrike) {
        this.onStrike = onStrike;
    }

    Player getOffStrike() {
        return offStrike;
    }

    int getCurrentScore() {
        return currentScore;
    }

    //TODo Unit test
    void increaseScore(int score) {
        this.currentScore += score;
    }

    int getTargetScore() {
        return targetScore;
    }

    //TODo Delete this only used in test
    void setTargetScore(int targetScore) {
        this.targetScore = targetScore;
    }

    int getTotalWickets() {
        return totalWickets;
    }

    int getWicketsDown() {
        return wicketsDown;
    }

    void setWicketsDown(int wicketsDown) {
        this.wicketsDown = wicketsDown;
    }

    String perBallCommentary(int currentOver, int currBall, int runsScored) {
        return "" + currentOver + "." + currBall + " " + onStrike.getPlayerName() + " scores " + runsScored + (runsScored > 1 ? " runs" : " run");
    }

    String perWicketCommentary(int currentOver, int currBall, String playerName) {
        return "" + currentOver + "." + currBall + " " + playerName + " is out";
    }

    String perOverCommentary(int currentOver) {
        return "" + (totalOvers - currentOver) + " overs left. " + (targetScore - currentScore) + " runs to win";
    }

    int getOversCompleted() {
        return oversCompleted;
    }

    //TODO Unit test
    void markEndOfOver() {
        this.oversCompleted--;
    }

    //TODo Unit test.
    void changeStrike() {
        Player currentPlayer = this.getOnStrike();
        this.onStrike = this.offStrike;
        this.offStrike = currentPlayer;
    }
}
