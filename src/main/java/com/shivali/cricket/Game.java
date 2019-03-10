package com.shivali.cricket;

import com.shivali.cricket.model.IGameResult;
import com.shivali.cricket.model.Player;
import com.shivali.cricket.util.RandomNumberGenerator;
import com.shivali.cricket.util.WeightedRandomGenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    public static void main(String[] args) {
        String teamName = "Lengaburu";
        int target = 40;
        int totalWickets = 3;
        int totalOvers = 4;

        Player player1 = getKiratBoli();
        Player player2 = getNSNodhi();
        Player player3 = getRRumrah();
        Player player4 = getShashiHenra();

        ArrayList<Player> listOfLinedUpPlayers = getLinedUpPlayers(player3, player4);

        CricketField cricketField = new CricketField(0,0,player1,player2,totalOvers,target,totalWickets);

        WeightedRandomGenerator scorer = new WeightedRandomGenerator(new RandomNumberGenerator());

        CricketMatch cricketMatch = new CricketMatch(teamName,scorer, cricketField,listOfLinedUpPlayers);

        final IGameResult playResult = cricketMatch.play();
        String gameResult = playResult.getGameResult();
        ArrayList<String> scoreCard = playResult.getScoreCard();

        System.out.println(gameResult);
        displayScoreCard(scoreCard);
    }

    private static ArrayList<Player> getLinedUpPlayers(Player player3, Player player4) {
        ArrayList<Player> listOfLinedUpPlayers = new ArrayList();
        listOfLinedUpPlayers.add(player3);
        listOfLinedUpPlayers.add(player4);
        return listOfLinedUpPlayers;
    }

    private static void displayScoreCard(ArrayList<String> scoreCard) {
        for(String scoreCardValue : scoreCard) {
            System.out.println(scoreCardValue);
        }
    }

    private static Player getShashiHenra() {
        HashMap<Integer, Integer> weightMap3;
        int[] val3 = {30,30,25,5,0,5,1,4};
        weightMap3 = initializePlayerProbabilities(val3);
        return new Player("Shashi Henra",weightMap3);
    }

    private static Player getRRumrah() {
        HashMap<Integer, Integer> weightMap2;
        int[] val2 = {20,20,30,15,5,5,1,4};
        weightMap2 = initializePlayerProbabilities(val2);
        return new Player("R Rumrah",weightMap2);
    }

    private static Player getNSNodhi() {
        HashMap<Integer, Integer> weightMap1;
        int[] val1 = {10,10,40,20,5,10,1,4};
        weightMap1 = initializePlayerProbabilities(val1);
        return new Player("NS Nodhi",weightMap1);
    }

    private static Player getKiratBoli() {
        HashMap<Integer, Integer> weightMap;
        int[] val = {5,5,30,25,10,15,1,9};
        weightMap = initializePlayerProbabilities(val);
        return new Player("Kirat Bohli",weightMap);
    }

    private static HashMap<Integer,Integer> initializePlayerProbabilities(int[] val) {
        HashMap<Integer, Integer> weightMap = new HashMap();
        for (int i = -1; i <= 6; i++) {
            weightMap.put(i, val[i+1]);
        }
        return weightMap;
    }
}
