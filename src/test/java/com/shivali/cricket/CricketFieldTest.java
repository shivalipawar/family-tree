package com.shivali.cricket;

import com.shivali.cricket.model.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CricketFieldTest {

    CricketField cricketField;
    Player player;
    ArrayList<Player> playedPlayers;

    @Before
    public void init(){
     cricketField = new CricketField();
     player = new Player();
     playedPlayers = new ArrayList();
    }

    @Test
    public void perBallCommentaryShouldReturnCorrectCommentary(){
        player.setPlayerName("Kirat Boli");
        cricketField.setOnStrike(player);
        playedPlayers.add(player);
        String result =cricketField.perBallCommentary(1,2,3);
        Assert.assertEquals("1.2 Kirat Boli scores 3 runs",result);
    }

    @Test
    public void perOverCommentaryShouldReturnCorrectCommentary(){
        player.setPlayerName("Kirat Boli");
        cricketField.setOnStrike(player);
        cricketField.setTotalOvers(4);
        cricketField.setTargetScore(10);
        cricketField.increaseScore(5);
        String result =cricketField.perOverCommentary(1);
        Assert.assertEquals("3 overs left. 5 runs to win",result);
    }

}