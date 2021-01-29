package com.pawelpluta.telldontaskkata.domain;

import static com.pawelpluta.telldontaskkata.domain.PlantType.VEGETABLE;

public class Vegetable extends Plant {

    public Vegetable(Integer soilMoisturePercentage, Integer wateringCount) {
        super(VEGETABLE, soilMoisturePercentage, wateringCount);
    }

    @Override
    protected boolean needsWatering() {
        return getSoilMoisturePercentage() < 60;
    }

    @Override
    protected boolean readyForGathering() {
        return getWateringCount() > 5;
    }

    @Override
    protected Product plantProduct() {
        return new Product(3, ProductType.VEGETABLE);
    }
}
