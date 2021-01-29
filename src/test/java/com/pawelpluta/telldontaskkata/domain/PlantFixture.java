package com.pawelpluta.telldontaskkata.domain;

public class PlantFixture {

    public static Plant somePlant() {
        return new Flower(30, 0);
    }

    public static Plant plantWithMoistureOf(Integer soilMoisture) {
        return new Flower(soilMoisture, 0);
    }

    public static Plant plantWithWateringCountOf(Integer wateringCount) {
        return new Flower(50, wateringCount);
    }

    public static Flower flowerWithSoilMoistureOf(Integer soilMoisture) {
        return new Flower(soilMoisture, 0);
    }

    public static Flower flowerWithWateringCountOf(Integer wateringCount) {
        return new Flower(50, wateringCount);
    }

    public static Tree treeWithSoilMoistureOf(Integer soilMoisture) {
        return new Tree(soilMoisture, 0);
    }

    public static Tree treeWithWateringCountOf(Integer wateringCount) {
        return new Tree(50, wateringCount);
    }

    public static Vegetable vegetableWithSoilMoistureOf(Integer soilMoisture) {
        return new Vegetable(soilMoisture, 0);
    }

    public static Vegetable vegetableWithWateringCountOf(Integer wateringCount) {
        return new Vegetable(50, wateringCount);
    }
}
