package com.pawelpluta.telldontaskkata.domain;

import static com.pawelpluta.telldontaskkata.domain.PlantType.FLOWER;

public class Flower extends Plant {

    public Flower(Integer soilMoisturePercentage, Integer wateringCount) {
        super(FLOWER, soilMoisturePercentage, wateringCount);
    }

    @Override
    protected boolean needsWatering() {
        return getSoilMoisturePercentage() < 50;
    }

    @Override
    protected boolean readyForGathering() {
        return getWateringCount() > 3;
    }

    @Override
    protected Product plantProduct() {
        return new Product(2, ProductType.FLOWER);
    }
}
