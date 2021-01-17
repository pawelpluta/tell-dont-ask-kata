package com.pawelpluta.telldontaskkata.application;

import com.pawelpluta.telldontaskkata.domain.PlantType;

public class GatheringCommand {

    private final Integer raisedBedId;

    public GatheringCommand(Integer raisedBedId, PlantType plantType) {
        this.raisedBedId = raisedBedId;
        this.plantType = plantType;
    }

    private final PlantType plantType;

    public Integer getRaisedBedId() {
        return raisedBedId;
    }

    public PlantType getPlantType() {
        return plantType;
    }
}
