package com.shivali.cricket;

import com.shivali.cricket.model.Player;

class CricketField {

    private Player onStrike;
    private Player offStrike;
    private int currentScore;
    private int targetScore;
    private int totalWickets;
    private int wicketsDown;
    private int oversCompleted;

    public CricketField(){}

    public CricketField(int wicketsDown,int oversComplete, Player onStrike, Player offStrike,int totalOvers,int targetScore,int totalWickets){
        this.setWicketsDown(wicketsDown);
        this.setOversCompleted(oversComplete);
        this.setOnStrike(onStrike);
        this.setOffStrike(offStrike);
        this.setTotalOvers(totalOvers);
        this.setTargetScore(targetScore);
        this.setTotalWickets(totalWickets);
    }

    int getTotalOvers() {
        return totalOvers;
    }

    void setTotalOvers(int totalOvers) {
        this.totalOvers = totalOvers;
    }

    private int totalOvers;

    Player getOnStrike() {
        return onStrike;
    }

    void setOnStrike(Player onStrike) {
        this.onStrike = onStrike;
    }

    Player getOffStrike() {
        return offStrike;
    }

    void setOffStrike(Player offStrike) {
        this.offStrike = offStrike;
    }

    int getCurrentScore() {
        return currentScore;
    }

    void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    int getTargetScore() {
        return targetScore;
    }

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
        return "" + (totalOvers - currentOver)  + " overs left. " + (targetScore -currentScore) + " runs to win";
    }

    int getOversCompleted() {
        return oversCompleted;
    }

    void setOversCompleted(int i) {
        this.oversCompleted = i;
    }

    private void setTotalWickets(int totalWickets) {
        this.totalWickets = totalWickets;
    }

}
