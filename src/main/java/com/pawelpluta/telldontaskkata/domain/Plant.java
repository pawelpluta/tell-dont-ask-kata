package com.pawelpluta.telldontaskkata.domain;

import java.util.Optional;

public abstract class Plant {

    private PlantType type;
    private Integer soilMoisturePercentage;
    private Integer wateringCount;

    protected Plant(PlantType type, Integer soilMoisturePercentage, Integer wateringCount) {
        this.type = type;
        this.soilMoisturePercentage = soilMoisturePercentage;
        this.wateringCount = wateringCount;
    }

    public PlantType type() {
        return type;
    }

    public Integer getSoilMoisturePercentage() {
        return soilMoisturePercentage;
    }

    public Integer getWateringCount() {
        return wateringCount;
    }

    public WateringResult waterWith(WaterValve waterValve) {
        if (needsWatering()) {
            try {
                waterValve.open();
                soilMoisturePercentage += 20;
                wateringCount++;
                Thread.sleep(100); // wait for some water to flow
            } catch (InterruptedException e) {
                return WateringResult.valveMalfunction();
            } finally {
                waterValve.close();
            }
        }
        return WateringResult.success();
    }

    public Optional<Product> gather() {
        if (readyForGathering()) {
            wateringCount = 0;
            return Optional.of(plantProduct());
        }
        return Optional.empty();
    }

    protected abstract boolean needsWatering();
    protected abstract boolean readyForGathering();
    protected abstract Product plantProduct();
}
