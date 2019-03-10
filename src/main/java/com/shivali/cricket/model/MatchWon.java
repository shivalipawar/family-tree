package com.shivali.cricket.model;


import java.util.ArrayList;

public class MatchWon implements IGameResult {

    private String teamName;
    private int wicketsRemaining;
    private int overs;
    private int balls;
    private ArrayList<Player> playedPlayer;
    private ArrayList<String> resultScore;
    private ArrayList<Player> notOutPlayers;

    public MatchWon(String teamName, int wicketsRemaining, int overs, int balls, ArrayList<Player> playedPlayers,ArrayList<Player> notOutPlayers){
        this.teamName = teamName;
        this.wicketsRemaining = wicketsRemaining;
        this.overs = overs;
        this.balls = balls;
        this.playedPlayer=playedPlayers;
        this.resultScore = new ArrayList();
        this.notOutPlayers = notOutPlayers;
    }

    public String getGameResult() {
        return teamName+" won by "+wicketsRemaining+" wickets and "+ getOversString();
    }

    public ArrayList<String> getScoreCard() {
        for (Player player : playedPlayer) {
            String ball = (player.getNumberOfBalls() < 1) ? " ball " : " balls ";
            resultScore.add(player.getPlayerName() + " - " + player.getCurrentScore() + " (" + player.getNumberOfBalls() + " " + ball + " )");
        }
        for (Player player : notOutPlayers){
            String ball = (player.getNumberOfBalls() < 1) ? " ball " : " balls ";
            resultScore.add(player.getPlayerName() + " - " + player.getCurrentScore() +" *"+ " (" + player.getNumberOfBalls() + " " + ball + " )");
        }
        return resultScore;
    }

    private String getOversString() {
        return overs<1?" "+balls+" balls":overs+" overs and "+balls+" balls";
    }

}
