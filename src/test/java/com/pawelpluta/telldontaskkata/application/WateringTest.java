package com.pawelpluta.telldontaskkata.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pawelpluta.telldontaskkata.domain.Plant;
import com.pawelpluta.telldontaskkata.domain.RaisedBed;
import com.pawelpluta.telldontaskkata.domain.WaterValve;
import com.pawelpluta.telldontaskkata.repository.InMemoryRaisedBedRepository;
import com.pawelpluta.telldontaskkata.repository.InMemoryWaterValveRepository;
import com.pawelpluta.telldontaskkata.repository.RaisedBedRepository;
import com.pawelpluta.telldontaskkata.repository.WaterValveRepository;

import static com.pawelpluta.telldontaskkata.application.WateringCommandFixture.waterAnyRaisedBedCommandWith;
import static com.pawelpluta.telldontaskkata.application.WateringCommandFixture.waterAnyRaisedBedCommandWithBed;
import static com.pawelpluta.telldontaskkata.application.WateringCommandFixture.waterAnyRaisedBedCommandWithValve;
import static com.pawelpluta.telldontaskkata.domain.PlantFixture.plantWithMoistureOf;
import static com.pawelpluta.telldontaskkata.domain.RaisedBedFixture.someRaisedBed;
import static com.pawelpluta.telldontaskkata.domain.RaisedBedFixture.someRaisedBedWith;
import static com.pawelpluta.telldontaskkata.domain.WaterValveFixture.someWaterValve;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WateringTest {

    private RaisedBedRepository raisedBedRepository;
    private WaterValveRepository waterValveRepository;
    private Watering watering;

    @BeforeEach
    void setUp() {
        raisedBedRepository = new InMemoryRaisedBedRepository();
        waterValveRepository = new InMemoryWaterValveRepository();
        watering = new Watering(raisedBedRepository, waterValveRepository);
    }

    @Test
    void shouldNotWaterAnythingIfThereIsNoRaisedBed() {
        // given
        raisedBedRepositoryIsEmpty();
        Integer waterValveId = waterBedRepositoryContains(someWaterValve());
        WateringCommand command = waterAnyRaisedBedCommandWithValve(waterValveId);

        // when
        boolean wateringWasSuccessful = watering.perform(command);

        // then
        assertFalse(wateringWasSuccessful);
    }

    @Test
    void shouldNotWaterAnythingIfThereIsWaterValve() {
        // given
        Integer raisedBedId = raisedBedRepositoryContains(someRaisedBed());
        waterBedRepositoryIsEmpty();
        WateringCommand command = waterAnyRaisedBedCommandWithBed(raisedBedId);

        // when
        boolean wateringWasSuccessful = watering.perform(command);

        // then
        assertFalse(wateringWasSuccessful);
    }

    @Test
    void shouldSuccessfullyWaterPlants() {
        // given
        Integer raisedBedId = raisedBedRepositoryContains(someRaisedBed());
        Integer waterValveId = waterBedRepositoryContains(someWaterValve());
        WateringCommand command = waterAnyRaisedBedCommandWith(raisedBedId, waterValveId);

        // when
        boolean wateringWasSuccessful = watering.perform(command);

        // then
        assertTrue(wateringWasSuccessful);
    }

    @Test
    void wateredPlantShouldHaveHigherSoilMoisture() {
        // given
        Integer initialMoisture = 0;
        Integer raisedBedId = raisedBedRepositoryContains(someRaisedBedWith(plantWithMoistureOf(initialMoisture)));
        Integer waterValveId = waterBedRepositoryContains(someWaterValve());
        WateringCommand command = waterAnyRaisedBedCommandWith(raisedBedId, waterValveId);

        // when
        watering.perform(command);

        // then
        Plant plantInRaisedBed = raisedBedRepository.findById(raisedBedId).get().getPlants().get(0);
        assertTrue(plantInRaisedBed.getSoilMoisturePercentage() > initialMoisture);
    }

    @Test
    void shouldNotWaterPlantThatHaveEnoughWater() {
        // given
        Integer initialMoisture = 90;
        Integer raisedBedId = raisedBedRepositoryContains(someRaisedBedWith(plantWithMoistureOf(initialMoisture)));
        Integer waterValveId = waterBedRepositoryContains(someWaterValve());
        WateringCommand command = waterAnyRaisedBedCommandWith(raisedBedId, waterValveId);

        // when
        watering.perform(command);

        // then
        Plant plantInRaisedBed = raisedBedRepository.findById(raisedBedId).get().getPlants().get(0);
        assertEquals(initialMoisture, plantInRaisedBed.getSoilMoisturePercentage());
    }

    private void raisedBedRepositoryIsEmpty() {
        raisedBedRepository.save(null);
    }

    private Integer raisedBedRepositoryContains(RaisedBed raisedBed) {
        raisedBedRepository.save(raisedBed);
        return raisedBed.getId();
    }

    private void waterBedRepositoryIsEmpty() {
        waterValveRepository.save(null);
    }

    private Integer waterBedRepositoryContains(WaterValve waterValve) {
        waterValveRepository.save(waterValve);
        return waterValve.getId();
    }

}
