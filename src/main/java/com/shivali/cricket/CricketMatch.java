package com.shivali.cricket;

import com.shivali.cricket.model.*;
import com.shivali.cricket.util.WeightedRandomGenerator;

import java.util.ArrayList;

class CricketMatch {
    private String teamName;
    private WeightedRandomGenerator scorer;
    private CricketField cricketField;
    private ArrayList<Player> linedUpPlayers;
    private ArrayList<Player> playedPlayers;
    private ArrayList<Player> notOutPlayers;

    CricketMatch(String teamName, WeightedRandomGenerator scorer, CricketField cricketField, ArrayList<Player> linedUpPlayers) {
        this.teamName = teamName;
        this.scorer = scorer;
        this.cricketField = cricketField;
        this.linedUpPlayers = linedUpPlayers;
        this.playedPlayers = new ArrayList<>();
        this.notOutPlayers = new ArrayList<>();
    }

    IGameResult play() {
        for (int over = 1; over <= cricketField.getTotalOvers(); over++) {
            IGameResult updatedGameResult = playOver(over);
            if (isGameEnded(updatedGameResult)) return updatedGameResult;
            System.out.println(cricketField.perOverCommentary(over));
            cricketField.markEndOfOver();
            cricketField.changeStrike();
        }
        notOutPlayers.add(cricketField.getOffStrike());
        notOutPlayers.add(cricketField.getOnStrike());
        return new MatchLost(teamName, cricketField.getTargetScore() - cricketField.getCurrentScore(), this.playedPlayers, this.notOutPlayers);
    }

    private boolean isGameEnded(IGameResult updatedGameResult) {
        return updatedGameResult != null;
    }

    private IGameResult playOver(int overNumber) {
        for (int ball = 1; ball <= 6; ball++) {
            Player onStrike = cricketField.getOnStrike();
            int score = scorer.getWeightedRandomNumber(onStrike.getPlayerProbability());
            onStrike.setNumberOfBalls(onStrike.getNumberOfBalls() + 1);
            if (isLastPlayersWicket(score, overNumber, ball)) {
                this.playedPlayers.add(onStrike); //if its notOutPlayer or playedPlayer
                this.notOutPlayers.add(cricketField.getOffStrike());
                return new MatchLost(teamName, cricketField.getTargetScore() - cricketField.getCurrentScore(), this.playedPlayers, this.notOutPlayers);
            }
            updateScore(score);

            if (score != -1) {
                printPerBallCommentary(overNumber, ball, score);
            }
            if (shouldChangeStrike(score)) cricketField.changeStrike();

            if (gameWonResult()) {
                notOutPlayers.add(cricketField.getOffStrike());
                notOutPlayers.add(onStrike);
                return new MatchWon(teamName, cricketField.getTotalWickets() - cricketField.getWicketsDown(), cricketField.getTotalOvers() - cricketField.getOversCompleted(), 6 - ball, this.playedPlayers, this.notOutPlayers);
            }
        }
        return null;
    }

    private void printPerBallCommentary(int overNumber, int ball, int score) {
        System.out.println(cricketField.perBallCommentary(overNumber, ball, score));
    }

    private void handleAndPrintWicketCommentary(int overNumber, int ball, String playerName) {
        System.out.println(cricketField.perWicketCommentary(overNumber, ball, playerName));
    }

    private boolean shouldChangeStrike(int score) {
        return score == 1 || score == 3 || score == 5;
    }

    private boolean isLastPlayersWicket(int score, int overNumber, int ball) {
        if (score == -1) {
            handleAndPrintWicketCommentary(overNumber, ball, cricketField.getOnStrike().getPlayerName());
            if (linedUpPlayers.size() == 0) {
                return true;
            }
            changeOfOnStrikePlayerWithLinedUp(linedUpPlayers, playedPlayers);
        }
        return false;
    }

    private boolean gameWonResult() {
        return cricketField.getTargetScore() <= cricketField.getCurrentScore();
    }

    private void updateScore(int score) {
        if (score > 0) {
            cricketField.increaseScore(score);
            cricketField.getOnStrike().increaseScore(score);
        }
    }

    private void changeOfOnStrikePlayerWithLinedUp(ArrayList<Player> linedUpPlayers, ArrayList<Player> playedPlayers) {
        playedPlayers.add(cricketField.getOnStrike());
        cricketField.setOnStrike(linedUpPlayers.get(0));
        cricketField.setWicketsDown(cricketField.getWicketsDown() + 1);
        if (linedUpPlayers.size() != 0) {
            linedUpPlayers.remove(linedUpPlayers.get(0)); //Update the array list of lined up players.
        }
    }


}
