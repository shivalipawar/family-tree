package com.shivali.cricket.model;

import java.util.ArrayList;

public class MatchLost implements IGameResult {

    private String teamName;
    private int runs;
    private ArrayList<Player> playedPlayer;
    private ArrayList<String> resultScore;
    private ArrayList<Player> notOutPlayers;

    public MatchLost(String teamName, int runs, ArrayList<Player> playedPlayers, ArrayList<Player> notOutPlayers){
        this.teamName = teamName;
        this.runs = runs;
        this.playedPlayer = playedPlayers;
        this.resultScore = new ArrayList();
        this.notOutPlayers = notOutPlayers;
    }
    public String getGameResult() {
        return teamName+" lost by "+runs+" runs";
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

}
