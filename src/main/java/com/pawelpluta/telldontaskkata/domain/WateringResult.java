package com.pawelpluta.telldontaskkata.domain;

public interface WateringResult {

    static WateringResult success() {
        return new SuccessfullyWatered();
    }

    static WateringResult valveMalfunction() {
        return new WateringFailedDueToValveMalfunction();
    }

    static WateringResult noValve() {
        return new WateringFailedDueToValveMalfunction();
    }

    static WateringResult noPlants() {
        return new NoPlantsToBeWatered();
    }

    class SuccessfullyWatered implements WateringResult {
    }

    class WateringFailedDueToValveMalfunction implements WateringResult {
    }

    class NoPlantsToBeWatered implements WateringResult {
    }
}
