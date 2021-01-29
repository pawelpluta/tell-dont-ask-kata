package com.pawelpluta.telldontaskkata.domain;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.pawelpluta.telldontaskkata.domain.WateringResult.SuccessfullyWatered;

import static com.pawelpluta.telldontaskkata.domain.PlantFixture.flowerWithSoilMoistureOf;
import static com.pawelpluta.telldontaskkata.domain.PlantFixture.flowerWithWateringCountOf;
import static com.pawelpluta.telldontaskkata.domain.WaterValveFixture.someWaterValve;
import static org.junit.jupiter.api.Assertions.*;

class FlowerTest {

    @Test
    void moistSoilShouldNotBeModifiedOnWatering() {
        // given
        Flower moistFlower = flowerWithSoilMoistureOf(50);

        // when
        WateringResult wateringResult = moistFlower.waterWith(someWaterValve());

        // then
        assertTrue(wateringResult instanceof SuccessfullyWatered);
        assertEquals(50, moistFlower.getSoilMoisturePercentage());
        assertEquals(0, moistFlower.getWateringCount());
    }

    @Test
    void drySoilShouldBeMoisterAfterWatering() {
        // given
        Flower dryFlower = flowerWithSoilMoistureOf(49);

        // when
        WateringResult wateringResult = dryFlower.waterWith(someWaterValve());

        // then
        assertTrue(wateringResult instanceof SuccessfullyWatered);
        assertEquals(69, dryFlower.getSoilMoisturePercentage());
        assertEquals(1, dryFlower.getWateringCount());
    }

    @Test
    void plantThatWasNotWateredEnoughTimesCannotBeGathered() {
        // given
        Flower flowerNotReadyToBeGathered = flowerWithWateringCountOf(3);

        // when
        Optional<Product> gatheredProduct = flowerNotReadyToBeGathered.gather();

        // then
        assertTrue(gatheredProduct.isEmpty());
        assertEquals(3, flowerNotReadyToBeGathered.getWateringCount());
    }

    @Test
    void plantThatWasWateredEnoughTimesCanBeGathered() {
        // given
        Flower flowerNotReadyToBeGathered = flowerWithWateringCountOf(4);

        // when
        Product gatheredProduct = flowerNotReadyToBeGathered.gather().get();

        // then
        assertEquals(ProductType.FLOWER, gatheredProduct.getType());
        assertEquals(2, gatheredProduct.weight());
        assertEquals(0, flowerNotReadyToBeGathered.getWateringCount());
    }
}
