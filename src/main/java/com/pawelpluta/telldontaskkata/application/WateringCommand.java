package com.pawelpluta.telldontaskkata.application;

import com.pawelpluta.telldontaskkata.domain.RaisedBedId;

public class WateringCommand {

    private final RaisedBedId raisedBedId;
    private final Integer valveId;

    public WateringCommand(RaisedBedId raisedBedId, Integer valveId) {
        this.raisedBedId = raisedBedId;
        this.valveId = valveId;
    }

    public RaisedBedId getRaisedBedId() {
        return raisedBedId;
    }

    public Integer getValveId() {
        return valveId;
    }
}
