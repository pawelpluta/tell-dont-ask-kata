package com.pawelpluta.telldontaskkata.application;

import com.pawelpluta.telldontaskkata.domain.PlantType;
import com.pawelpluta.telldontaskkata.domain.RaisedBedId;

public class GatheringCommand {

    private final RaisedBedId raisedBedId;
    private final PlantType plantType;

    public GatheringCommand(RaisedBedId raisedBedId, PlantType plantType) {
        this.raisedBedId = raisedBedId;
        this.plantType = plantType;
    }

    public RaisedBedId getRaisedBedId() {
        return raisedBedId;
    }

    public PlantType getPlantType() {
        return plantType;
    }
}
