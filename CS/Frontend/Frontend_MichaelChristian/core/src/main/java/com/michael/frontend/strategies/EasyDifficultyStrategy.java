package com.michael.frontend.strategies;

import java.util.HashMap;
import java.util.Map;

public class EasyDifficultyStrategy implements DifficultyStrategy {

    @Override
    public Map<String, Integer> getObstacleWeights() {
        Map<String, Integer> weights = new HashMap<>();

        weights.put("VerticalLaser", 1);
        weights.put("HorizontalLaser", 1);

        return weights;
    }

    @Override
    public float getSpawnInterval() {
        return 2.5f;
    }

    @Override
    public int getDensity() {
        return 1;
    }

    @Override
    public int getMinGap() {
        return 200;
    }
}
