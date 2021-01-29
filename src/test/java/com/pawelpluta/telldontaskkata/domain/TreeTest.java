package com.pawelpluta.telldontaskkata.domain;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.pawelpluta.telldontaskkata.domain.WateringResult.SuccessfullyWatered;

import static com.pawelpluta.telldontaskkata.domain.PlantFixture.treeWithSoilMoistureOf;
import static com.pawelpluta.telldontaskkata.domain.PlantFixture.treeWithWateringCountOf;
import static com.pawelpluta.telldontaskkata.domain.WaterValveFixture.someWaterValve;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TreeTest {

    @Test
    void moistSoilShouldNotBeModifiedOnWatering() {
        // given
        Tree moistTree = treeWithSoilMoistureOf(45);

        // when
        WateringResult wateringResult = moistTree.waterWith(someWaterValve());

        // then
        assertTrue(wateringResult instanceof SuccessfullyWatered);
        assertEquals(45, moistTree.getSoilMoisturePercentage());
        assertEquals(0, moistTree.getWateringCount());
    }

    @Test
    void drySoilShouldBeMoisterAfterWatering() {
        // given
        Tree dryTree = treeWithSoilMoistureOf(44);

        // when
        WateringResult wateringResult = dryTree.waterWith(someWaterValve());

        // then
        assertTrue(wateringResult instanceof SuccessfullyWatered);
        assertEquals(64, dryTree.getSoilMoisturePercentage());
        assertEquals(1, dryTree.getWateringCount());
    }

    @Test
    void plantThatWasNotWateredEnoughTimesCannotBeGathered() {
        // given
        Tree treeNotReadyToBeGathered = treeWithWateringCountOf(10);

        // when
        Optional<Product> gatheredProduct = treeNotReadyToBeGathered.gather();

        // then
        assertTrue(gatheredProduct.isEmpty());
        assertEquals(10, treeNotReadyToBeGathered.getWateringCount());
    }

    @Test
    void plantThatWasWateredEnoughTimesCanBeGathered() {
        // given
        Tree treeNotReadyToBeGathered = treeWithWateringCountOf(11);

        // when
        Product gatheredProduct = treeNotReadyToBeGathered.gather().get();

        // then
        assertEquals(ProductType.FRUIT, gatheredProduct.getType());
        assertEquals(5, gatheredProduct.weight());
        assertEquals(0, treeNotReadyToBeGathered.getWateringCount());
    }
}
