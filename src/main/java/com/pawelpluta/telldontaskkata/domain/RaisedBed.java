package com.pawelpluta.telldontaskkata.domain;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RaisedBed {

    private final RaisedBedId id;
    private final List<Plant> plants;

    public RaisedBed(RaisedBedId id, List<Plant> plants) {
        this.id = id;
        this.plants = plants;
    }

    public WateringResult waterWith(WaterValve waterValve) {
        for (Plant plant : plants) {
            WateringResult result = plant.waterWith(waterValve);
            if (!(result instanceof WateringResult.SuccessfullyWatered)) {
                return result;
            }
        }
        return WateringResult.success();
    }

    public List<Product> gatherPlants(PlantType plantType) {
        switch (plantType) {
            case VEGETABLE:
                return gather(Vegetable.class);
            case FLOWER:
                return gather(Flower.class);
            case TREE:
                return gather(Tree.class);
            default:
                return Collections.emptyList();
        }
    }

    public List<Product> gather(Class<? extends Plant> type) {
        return plants.stream()
                     .filter(type::isInstance)
                     .map(Plant::gather)
                     .filter(Optional::isPresent)
                     .map(Optional::get)
                     .collect(Collectors.toList());
    }

    public RaisedBedId getId() {
        return id;
    }

    public List<Plant> getPlants() {
        return Collections.unmodifiableList(plants);
    }
}
