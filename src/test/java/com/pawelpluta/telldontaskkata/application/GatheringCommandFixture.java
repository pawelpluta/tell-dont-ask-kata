package com.pawelpluta.telldontaskkata.application;

import com.pawelpluta.telldontaskkata.domain.PlantType;

class GatheringCommandFixture {

    static GatheringCommand gatherPlantsCommandWithBed(Integer raisedBedId) {
        return new GatheringCommand(raisedBedId, PlantType.FLOWER);
    }

}
