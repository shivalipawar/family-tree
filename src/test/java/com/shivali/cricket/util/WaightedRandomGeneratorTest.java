package com.shivali.cricket.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;

public class WaightedRandomGeneratorTest {

    HashMap<Integer, Integer> weightMap;

    @Before
    public void intialize() {
        weightMap = new HashMap();
        weightMap.put(-1, 5);
        weightMap.put(0, 5);
        weightMap.put(1, 30);
        weightMap.put(2, 25);
        weightMap.put(3, 10);
        weightMap.put(4, 15);
        weightMap.put(5, 1);
        weightMap.put(6, 9);
    }

    @Test
    public void weightedRandomNumber() {
        HashMap<Integer, Integer> testCases = new HashMap<Integer, Integer>();
        testCases.put(50, 2);
        testCases.put(70, 3);
        testCases.put(100, 6);
        testCases.put(30, 1);
        testCases.keySet();
        for (int key : testCases.keySet()) {
            RandomNumberGenerator r = Mockito.mock(RandomNumberGenerator.class);
            Mockito.when(r.getRandomNumber(100)).thenReturn(key);

            WeightedRandomGenerator waightedRandomGenerator = new WeightedRandomGenerator(r);
            int weightedRandomNumber = waightedRandomGenerator.getWeightedRandomNumber(weightMap);
            Assert.assertEquals(testCases.get(key).intValue(), weightedRandomNumber);
        }
    }
}