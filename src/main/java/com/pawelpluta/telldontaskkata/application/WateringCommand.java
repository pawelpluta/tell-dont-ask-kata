package com.pawelpluta.telldontaskkata.application;

public class WateringCommand {

    private Integer raisedBedId;
    private Integer valveId;

    public WateringCommand(Integer raisedBedId, Integer valveId) {
        this.raisedBedId = raisedBedId;
        this.valveId = valveId;
    }

    public Integer getRaisedBedId() {
        return raisedBedId;
    }

    public Integer getValveId() {
        return valveId;
    }
}
