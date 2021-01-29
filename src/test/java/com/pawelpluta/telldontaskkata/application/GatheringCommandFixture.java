package com.pawelpluta.telldontaskkata.application;

import com.pawelpluta.telldontaskkata.domain.PlantType;
import com.pawelpluta.telldontaskkata.domain.RaisedBedId;

class GatheringCommandFixture {

    static GatheringCommand gatherPlantsCommandWithBed(RaisedBedId raisedBedId) {
        return new GatheringCommand(raisedBedId, PlantType.FLOWER);
    }

}
