package com.pawelpluta.telldontaskkata.domain;

public class Plant {

    private PlantType type;
    private Integer soilMoisturePercentage;
    private Integer wateringCount;

    public Plant(PlantType type, Integer soilMoisturePercentage, Integer wateringCount) {
        this.type = type;
        this.soilMoisturePercentage = soilMoisturePercentage;
        this.wateringCount = wateringCount;
    }

    public PlantType getType() {
        return type;
    }

    public Integer getSoilMoisturePercentage() {
        return soilMoisturePercentage;
    }

    public void setSoilMoisturePercentage(Integer soilMoisturePercentage) {
        this.soilMoisturePercentage = soilMoisturePercentage;
    }

    public Integer getWateringCount() {
        return wateringCount;
    }

    public void setWateringCount(Integer wateringCount) {
        this.wateringCount = wateringCount;
    }
}
