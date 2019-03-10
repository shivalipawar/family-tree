package com.shivali.cricket;

import com.shivali.cricket.model.Player;
import com.shivali.cricket.util.WeightedRandomGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class CricketMatchTest {

    private CricketField cricketField;
    private WeightedRandomGenerator waightedRandomGenerator;
    private HashMap<Integer,Integer> weightMap;
    private Player player, player1,player2,player3;
    private CricketMatch cricketMatch;

    @Before
    public void init() {
        player = new Player();
        player1 = new Player();
        player2 = new Player();
        player3 = new Player();
        cricketField = new CricketField(0,0,player,player1,4,100,3);
        player.setPlayerName("Birat Kohli");
        player1.setPlayerName("NS Nodhi");
        player2.setPlayerName("R Rumrah");
        player3.setPlayerName("Shashi Henra");

        weightMap = new HashMap();
        HashMap weightMap1 = new HashMap();
        HashMap weightMap2 = new HashMap();
        HashMap weightMap3 = new HashMap();
        player.setPlayerProbability(weightMap);
        weightMap.put(-1, 5);
        weightMap.put(0, 5);
        weightMap.put(1, 30);
        weightMap.put(2, 25);
        weightMap.put(3, 10);
        weightMap.put(4, 15);
        weightMap.put(5, 1);
        weightMap.put(6, 9);

        player1.setPlayerProbability(weightMap1);
        weightMap1.put(-1,10);
        weightMap1.put(0,10);
        weightMap1.put(1,40);
        weightMap1.put(2,20);
        weightMap1.put(3,5);
        weightMap1.put(4,10);
        weightMap1.put(5,1);
        weightMap1.put(6,4);

        player2.setPlayerProbability(weightMap2);
        weightMap2.put(-1,20);
        weightMap2.put(0,20);
        weightMap2.put(1,30);
        weightMap2.put(2,15);
        weightMap2.put(3,5);
        weightMap2.put(4,5);
        weightMap2.put(5,1);
        weightMap2.put(6,4);

        player3.setPlayerProbability(weightMap3);
        weightMap3.put(-1,30);
        weightMap3.put(0,30);
        weightMap3.put(1,25);
        weightMap3.put(2,5);
        weightMap3.put(3,0);
        weightMap3.put(4,5);
        weightMap3.put(5,1);
        weightMap3.put(6,4);
    }

    @Test
    public void playShouldReturnCorrectGameResultforOneOver() {
        cricketField.setTotalOvers(1);
        waightedRandomGenerator = mock(WeightedRandomGenerator.class);
        Mockito.when(waightedRandomGenerator.getWeightedRandomNumber(weightMap)).thenReturn(2).thenReturn(2).thenReturn(0);
        ArrayList<Player> linedUpBatsman = new ArrayList();
        cricketMatch = new CricketMatch("Lengaburu",waightedRandomGenerator,cricketField, linedUpBatsman);
        String result = "Lengaburu lost by 96 runs";
        String play = null;
        play = cricketMatch.play().getGameResult();
        Assert.assertEquals( result, play);
    }

    @Test
    public void playShouldChangePlayerOnStrikeForOddRuns(){
        cricketField.setTotalOvers(1);
        waightedRandomGenerator = mock(WeightedRandomGenerator.class);
        Mockito.when(waightedRandomGenerator.getWeightedRandomNumber(weightMap)).thenReturn(3).thenReturn(0);
        ArrayList<Player> linedUpBatsman = new ArrayList();
        cricketMatch = new CricketMatch("Lengaburu",waightedRandomGenerator,cricketField, linedUpBatsman);
        cricketMatch.play();

        Assert.assertEquals(player,cricketField.getOnStrike());
        Assert.assertEquals(player1,cricketField.getOffStrike());
        Assert.assertEquals(0,cricketField.getOffStrike().getCurrentScore());
        Assert.assertEquals(3,cricketField.getOnStrike().getCurrentScore());
    }

    @Test
    public void playShouldReturnGameLostWhenAllWicketsDownButTargetNoAchieved(){
        cricketField.setTotalOvers(1);
        waightedRandomGenerator = mock(WeightedRandomGenerator.class);
        Mockito.when(waightedRandomGenerator.getWeightedRandomNumber(Mockito.any(Map.class))).thenReturn(-1);
        ArrayList<Player> linedUpBatsman = new ArrayList();
        cricketMatch = new CricketMatch("Lengaburu",waightedRandomGenerator,cricketField, linedUpBatsman);
        String result = cricketMatch.play().getGameResult();
        Assert.assertEquals("Lengaburu lost by 100 runs",result);
    }

    @Test
    public void playShouldReturnGameLostWhenOversFinished(){
        cricketField.setTotalOvers(4);
        waightedRandomGenerator = mock(WeightedRandomGenerator.class);
        Mockito.when(waightedRandomGenerator.getWeightedRandomNumber(Mockito.any(Map.class))).thenReturn(0);
        ArrayList<Player> linedUpBatsman = new ArrayList();
        cricketMatch = new CricketMatch("Lengaburu",waightedRandomGenerator,cricketField, linedUpBatsman);
        String result = cricketMatch.play().getGameResult();
        Assert.assertEquals("Lengaburu lost by 100 runs",result);
    }

    @Test
    public void playShouldChangePlayerOnStrikeForOverChange(){
        cricketField.setTotalOvers(2);
        waightedRandomGenerator = mock(WeightedRandomGenerator.class);
        Mockito.when(waightedRandomGenerator.getWeightedRandomNumber(player.getPlayerProbability())).thenReturn(2);
        Mockito.when(waightedRandomGenerator.getWeightedRandomNumber(player1.getPlayerProbability())).thenReturn(4);
        ArrayList<Player> linedUpBatsman = new ArrayList();
//        cricketField.setOversCompleted(3);
        cricketMatch = new CricketMatch("Lengaburu",waightedRandomGenerator,cricketField, linedUpBatsman);
        cricketMatch.play();
        int currentScoreOnStrike = cricketField.getOnStrike().getCurrentScore();
        int currentScoreOffStrike = cricketField.getOffStrike().getCurrentScore();
        Assert.assertEquals(currentScoreOffStrike,24);
        Assert.assertEquals(currentScoreOnStrike,12);
    }

    @Test
    public void playShouldChangeOnStrikeToLineUpBatsmanWhenOnStrikePlayerIsOut(){
        cricketField.setTotalOvers(1);
        waightedRandomGenerator = mock(WeightedRandomGenerator.class);
        Mockito.when(waightedRandomGenerator.getWeightedRandomNumber(weightMap)).thenReturn(-1);
        Mockito.when(waightedRandomGenerator.getWeightedRandomNumber(player2.getPlayerProbability())).thenReturn(0);
        ArrayList<Player> linedUpBatsman = new ArrayList();
        linedUpBatsman.add(player2);
        linedUpBatsman.add(player3);
        cricketMatch = new CricketMatch("Lengaburu",waightedRandomGenerator,cricketField, linedUpBatsman);
        String playerName =linedUpBatsman.get(0).getPlayerName();
        cricketMatch.play();
        Assert.assertEquals(player2,cricketField.getOffStrike());
        Assert.assertEquals(0,cricketField.getOffStrike().getCurrentScore());
    }

    @Test
    public void playShouldReturnScoreCardForAllFourOversAndPlayers(){
        cricketField.setTotalOvers(4);
        waightedRandomGenerator = mock(WeightedRandomGenerator.class);
        Mockito.when(waightedRandomGenerator.getWeightedRandomNumber(weightMap)).thenReturn(2).thenReturn(-1);
        Mockito.when(waightedRandomGenerator.getWeightedRandomNumber(player2.getPlayerProbability())).thenReturn(4).thenReturn(-1);
        Mockito.when(waightedRandomGenerator.getWeightedRandomNumber(player3.getPlayerProbability())).thenReturn(0).thenReturn(-1);
        Mockito.when(waightedRandomGenerator.getWeightedRandomNumber(cricketField.getOffStrike().getPlayerProbability())).thenReturn(2).thenReturn(0);
        ArrayList<Player> linedUpBatsman = new ArrayList();
        linedUpBatsman.add(player2);
        linedUpBatsman.add(player3);
        cricketMatch = new CricketMatch("Lengaburu",waightedRandomGenerator,cricketField, linedUpBatsman);
        ArrayList<String> result= cricketMatch.play().getScoreCard();
        ArrayList<String> expected = new ArrayList();
        expected.add("Birat Kohli - 2 (2  balls  )");
        expected.add("R Rumrah - 4 (2  balls  )");
        expected.add("Shashi Henra - 0 (2  balls  )");
        expected.add("NS Nodhi - 0 * (0  ball  )");
        Assert.assertEquals(expected,result);
    }
}