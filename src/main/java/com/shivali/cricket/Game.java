package com.shivali.cricket;

import com.shivali.cricket.model.IGameResult;
import com.shivali.cricket.model.Player;
import com.shivali.cricket.util.RandomNumberGenerator;
import com.shivali.cricket.util.WeightedRandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;

import static com.shivali.cricket.util.Constants.*;

public class Game {

    public static void main(String[] args) {
        ArrayList<Player> players = createPlayers();

        ArrayList<Player> listOfLinedUpPlayers = getLinedUpPlayers(players);

        CricketField cricketField = new CricketField(players, TOTAL_OVERS, TARGET_SCORE);

        WeightedRandomGenerator scorer = new WeightedRandomGenerator(new RandomNumberGenerator());

        CricketMatch cricketMatch = new CricketMatch(TEAM_NAME, scorer, cricketField, listOfLinedUpPlayers);

        final IGameResult playResult = cricketMatch.play();
        String gameResult = playResult.getGameResult();
        ArrayList<String> scoreCard = playResult.getScoreCard();

        System.out.println(gameResult);
        displayScoreCard(scoreCard);
    }

    private static ArrayList<Player> createPlayers() {
        HashMap<String, int[]> playersDetails = new HashMap<>();
        playersDetails.put(PLAYER1_NAME, new int[]{5, 5, 30, 25, 10, 15, 1, 9});
        playersDetails.put(PLAYER2_NAME, new int[]{10, 10, 40, 20, 5, 10, 1, 4});
        playersDetails.put(PLAYER3_NAME, new int[]{20, 20, 30, 15, 5, 5, 1, 4});
        playersDetails.put(PLAYER4_NAME, new int[]{30, 30, 25, 5, 0, 5, 1, 4});

        ArrayList<Player> playerList = new ArrayList<>();
        playersDetails.forEach((name, probability) -> {
            HashMap<Integer, Integer> weightMap = initializePlayerProbabilities(probability);
            playerList.add(new Player(name, weightMap));
        });
        return playerList;
    }

    private static ArrayList<Player> getLinedUpPlayers(ArrayList<Player> players) {
        return new ArrayList(players.subList(2,players.size()));
    }

    private static void displayScoreCard(ArrayList<String> scoreCard) {
        for (String scoreCardValue : scoreCard) {
            System.out.println(scoreCardValue);
        }
    }

    private static HashMap<Integer, Integer> initializePlayerProbabilities(int[] val) {
        HashMap<Integer, Integer> weightMap = new HashMap();
        for (int i = -1; i <= 6; i++) {
            weightMap.put(i, val[i + 1]);
        }
        return weightMap;
    }
}
