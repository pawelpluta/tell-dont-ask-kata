package com.pawelpluta.telldontaskkata.domain;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.pawelpluta.telldontaskkata.domain.WateringResult.SuccessfullyWatered;

import static com.pawelpluta.telldontaskkata.domain.PlantFixture.vegetableWithSoilMoistureOf;
import static com.pawelpluta.telldontaskkata.domain.PlantFixture.vegetableWithWateringCountOf;
import static com.pawelpluta.telldontaskkata.domain.WaterValveFixture.someWaterValve;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VegetableTest {

    @Test
    void moistSoilShouldNotBeModifiedOnWatering() {
        // given
        Vegetable moistVegetable = vegetableWithSoilMoistureOf(60);

        // when
        WateringResult wateringResult = moistVegetable.waterWith(someWaterValve());

        // then
        assertTrue(wateringResult instanceof SuccessfullyWatered);
        assertEquals(60, moistVegetable.getSoilMoisturePercentage());
        assertEquals(0, moistVegetable.getWateringCount());
    }

    @Test
    void drySoilShouldBeMoisterAfterWatering() {
        // given
        Vegetable dryVegetable = vegetableWithSoilMoistureOf(59);

        // when
        WateringResult wateringResult = dryVegetable.waterWith(someWaterValve());

        // then
        assertTrue(wateringResult instanceof SuccessfullyWatered);
        assertEquals(79, dryVegetable.getSoilMoisturePercentage());
        assertEquals(1, dryVegetable.getWateringCount());
    }

    @Test
    void plantThatWasNotWateredEnoughTimesCannotBeGathered() {
        // given
        Vegetable vegetableNotReadyToBeGathered = vegetableWithWateringCountOf(5);

        // when
        Optional<Product> gatheredProduct = vegetableNotReadyToBeGathered.gather();

        // then
        assertTrue(gatheredProduct.isEmpty());
        assertEquals(5, vegetableNotReadyToBeGathered.getWateringCount());
    }

    @Test
    void plantThatWasWateredEnoughTimesCanBeGathered() {
        // given
        Vegetable vegetableNotReadyToBeGathered = vegetableWithWateringCountOf(6);

        // when
        Product gatheredProduct = vegetableNotReadyToBeGathered.gather().get();

        // then
        assertEquals(ProductType.VEGETABLE, gatheredProduct.getType());
        assertEquals(3, gatheredProduct.weight());
        assertEquals(0, vegetableNotReadyToBeGathered.getWateringCount());
    }
}
