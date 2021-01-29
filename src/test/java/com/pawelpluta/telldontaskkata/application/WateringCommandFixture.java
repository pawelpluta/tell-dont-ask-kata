package com.pawelpluta.telldontaskkata.application;

import com.pawelpluta.telldontaskkata.domain.RaisedBedId;

import static com.pawelpluta.telldontaskkata.IdentifierFixture.randomId;

class WateringCommandFixture {

    static WateringCommand waterAnyRaisedBedCommandWithValve(Integer waterValveId) {
        return new WateringCommand(RaisedBedId.random(), waterValveId);
    }

    static WateringCommand waterAnyRaisedBedCommandWithBed(RaisedBedId raisedBedId) {
        return new WateringCommand(raisedBedId, randomId());
    }

    static WateringCommand waterAnyRaisedBedCommandWith(RaisedBedId raisedBedId, Integer waterValveId) {
        return new WateringCommand(raisedBedId, waterValveId);
    }
}
