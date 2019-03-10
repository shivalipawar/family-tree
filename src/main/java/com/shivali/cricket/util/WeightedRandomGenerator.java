package com.shivali.cricket.util;

import java.util.Map;
import java.util.Set;


public class WeightedRandomGenerator {
    private static final int MAX_SCORE = 100;
    private RandomNumberGenerator randomNumberGenerator;

    public WeightedRandomGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public int getWeightedRandomNumber(Map<Integer, Integer> weightGraph) {
        int randomValue = randomNumberGenerator.getRandomNumber(100);
        final Set<Integer> keys = weightGraph.keySet();
        for (Integer outputKey : keys) {
            randomValue -= weightGraph.get(outputKey);
            if (randomValue <= 0) return outputKey;
        }
        final Object[] objects = keys.toArray();
        return (Integer) objects[objects.length-1];
    }
}
