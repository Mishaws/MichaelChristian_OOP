package com.michael.frontend.factories;

import java.util.*;
import com.michael.frontend.obstacles.BaseObstacle;

public class ObstacleFactory {

    /** Factory Method implementor */
    public interface ObstacleCreator {
        BaseObstacle create(float groundTopY, float spawnX, float playerHeight, Random rng);
        void release(BaseObstacle obstacle);
        void releaseAll();
        List<? extends BaseObstacle> getInUse();
        boolean supports(BaseObstacle obstacle);
        String getName();
    }

    private final Random random = new Random();
    private int totalWeight = 0;
    private final Map<String, ObstacleCreator> creators;
    private final List<ObstacleCreator> weightedSelection;

    public ObstacleFactory(Map<String, ObstacleCreator> creators, List<ObstacleCreator> weightedSelection) {
        this.creators = creators;
        this.weightedSelection = weightedSelection;
        // Register creators with weights for spawn probability
        // Vertical: 40%, Horizontal: 40%, Homing Missile: 20%
        register(new VerticalLaserCreator(), 2);
        register(new com.michael.frontend.factories.HorizontalLaserCreator(), 2);
        register(new HomingMissileCreator(), 1);
    }


    private void register(ObstacleCreator creator, int weight) {
        creators.put(creator.getName());
    }

    public void setWeights(Map<String, Integer> weights) {
        weightedSelection.clear();
    }
}



