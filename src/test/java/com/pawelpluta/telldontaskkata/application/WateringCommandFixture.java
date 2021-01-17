package com.pawelpluta.telldontaskkata.application;

import static com.pawelpluta.telldontaskkata.IdentifierFixture.randomId;

class WateringCommandFixture {

    static WateringCommand waterAnyRaisedBedCommandWithValve(Integer waterValveId) {
        return new WateringCommand(randomId(), waterValveId);
    }

    static WateringCommand waterAnyRaisedBedCommandWithBed(Integer raisedBedId) {
        return new WateringCommand(raisedBedId, randomId());
    }

    static WateringCommand waterAnyRaisedBedCommandWith(Integer raisedBedId, Integer waterValveId) {
        return new WateringCommand(raisedBedId, waterValveId);
    }
}
