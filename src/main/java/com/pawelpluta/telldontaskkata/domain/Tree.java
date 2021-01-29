package com.pawelpluta.telldontaskkata.domain;

import static com.pawelpluta.telldontaskkata.domain.PlantType.TREE;

public class Tree extends Plant {

    public Tree(Integer soilMoisturePercentage, Integer wateringCount) {
        super(TREE, soilMoisturePercentage, wateringCount);
    }

    @Override
    protected boolean needsWatering() {
        return getSoilMoisturePercentage() < 45;
    }

    @Override
    protected boolean readyForGathering() {
        return getWateringCount() > 10;
    }

    @Override
    protected Product plantProduct() {
        return new Product(5, ProductType.FRUIT);
    }
}
