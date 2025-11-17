package com.michael.frontend.factories;

import java.util.*;
import com.michael.frontend.obstacles.BaseObstacle;

public class ObstacleFactory {

    public interface ObstacleCreator {
        BaseObstacle create(float groundTopY, float spawnX, float playerHeight, Random rng);
        void release(BaseObstacle obstacle);
        void releaseAll();
        List<? extends BaseObstacle> getInUse();
        boolean supports(BaseObstacle obstacle);
        String getName();
    }

    private final Map<String, ObstacleCreator> creators;
    private final List<ObstacleCreator> weightedSelection;

    private final Random random = new Random();

    public ObstacleFactory() {
        creators = new HashMap<>();
        weightedSelection = new ArrayList<>();

        register(new VerticalLaserCreator());
        register(new HorizontalLaserCreator());
        register(new HomingMissileCreator());
    }

    private void register(ObstacleCreator creator) {
        creators.put(creator.getName(), creator);
    }

    public void setWeights(Map<String, Integer> weights) {
        weightedSelection.clear();

        for (Map.Entry<String, Integer> entry : weights.entrySet()) {
            String name = entry.getKey();
            int weight = entry.getValue();
            ObstacleCreator creator = creators.get(name);

            if (creator != null) {
                for (int i = 0; i < weight; i++) {
                    weightedSelection.add(creator);
                }
            }
        }
    }

    public BaseObstacle createRandomObstacle(float groundTopY, float spawnX, float playerHeight) {
        if (weightedSelection.isEmpty()) {
            throw new IllegalStateException("No obstacle weights set or creators registered");
        }

        ObstacleCreator creator = weightedSelection.get(random.nextInt(weightedSelection.size()));
        return creator.create(groundTopY, spawnX, playerHeight, random);
    }

    public void releaseObstacle(BaseObstacle obstacle) {
        for (ObstacleCreator creator : creators.values()) {
            if (creator.supports(obstacle)) {
                creator.release(obstacle);
                return;
            }
        }
    }

    public void releaseAllObstacles() {
        for (ObstacleCreator creator : creators.values()) {
            creator.releaseAll();
        }
    }

    public List<BaseObstacle> getAllInUseObstacles() {
        List<BaseObstacle> list = new ArrayList<>();
        for (ObstacleCreator creator : creators.values()) {
            list.addAll(creator.getInUse());
        }
        return list;
    }

    public List<String> getRegisteredCreatorNames() {
        return new ArrayList<>(creators.keySet());
    }
}
