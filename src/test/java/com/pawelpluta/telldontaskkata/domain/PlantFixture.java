package com.pawelpluta.telldontaskkata.domain;

import static com.pawelpluta.telldontaskkata.domain.PlantType.FLOWER;

public class PlantFixture {

    public static Plant somePlant() {
        return new Plant(FLOWER, 30, 0);
    }

    public static Plant plantWithMoistureOf(Integer soilMoisture) {
        return new Plant(FLOWER, soilMoisture, 0);
    }

    public static Plant plantWithWateringCountOf(Integer wateringCount) {
        return new Plant(FLOWER, 50, wateringCount);
    }
}
