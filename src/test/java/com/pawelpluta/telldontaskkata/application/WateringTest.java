package com.pawelpluta.telldontaskkata.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pawelpluta.telldontaskkata.domain.Plant;
import com.pawelpluta.telldontaskkata.domain.RaisedBed;
import com.pawelpluta.telldontaskkata.domain.RaisedBedId;
import com.pawelpluta.telldontaskkata.domain.WaterValve;
import com.pawelpluta.telldontaskkata.domain.WateringResult;
import com.pawelpluta.telldontaskkata.domain.WateringResult.SuccessfullyWatered;
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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
        WateringResult wateringResult = watering.perform(command);

        // then
        assertNotEquals(SuccessfullyWatered.class, wateringResult.getClass());
    }

    @Test
    void shouldNotWaterAnythingIfThereIsWaterValve() {
        // given
        RaisedBedId raisedBedId = raisedBedRepositoryContains(someRaisedBed());
        waterBedRepositoryIsEmpty();
        WateringCommand command = waterAnyRaisedBedCommandWithBed(raisedBedId);

        // when
        WateringResult wateringResult = watering.perform(command);

        // then
        assertNotEquals(SuccessfullyWatered.class, wateringResult.getClass());
    }

    @Test
    void shouldSuccessfullyWaterPlants() {
        // given
        RaisedBedId raisedBedId = raisedBedRepositoryContains(someRaisedBed());
        Integer waterValveId = waterBedRepositoryContains(someWaterValve());
        WateringCommand command = waterAnyRaisedBedCommandWith(raisedBedId, waterValveId);

        // when
        WateringResult wateringResult = watering.perform(command);

        // then
        assertEquals(SuccessfullyWatered.class, wateringResult.getClass());
    }

    @Test
    void wateredPlantShouldHaveHigherSoilMoisture() {
        // given
        Integer initialMoisture = 0;
        RaisedBedId raisedBedId = raisedBedRepositoryContains(someRaisedBedWith(plantWithMoistureOf(initialMoisture)));
        Integer waterValveId = waterBedRepositoryContains(someWaterValve());
        WateringCommand command = waterAnyRaisedBedCommandWith(raisedBedId, waterValveId);

        // when
        watering.perform(command);

        // then
        Plant plantInRaisedBed = raisedBedRepository.findById(raisedBedId.value()).get().getPlants().get(0);
        assertTrue(plantInRaisedBed.getSoilMoisturePercentage() > initialMoisture);
    }

    @Test
    void shouldNotWaterPlantThatHaveEnoughWater() {
        // given
        Integer initialMoisture = 90;
        RaisedBedId raisedBedId = raisedBedRepositoryContains(someRaisedBedWith(plantWithMoistureOf(initialMoisture)));
        Integer waterValveId = waterBedRepositoryContains(someWaterValve());
        WateringCommand command = waterAnyRaisedBedCommandWith(raisedBedId, waterValveId);

        // when
        watering.perform(command);

        // then
        Plant plantInRaisedBed = raisedBedRepository.findById(raisedBedId.value()).get().getPlants().get(0);
        assertEquals(initialMoisture, plantInRaisedBed.getSoilMoisturePercentage());
    }

    private void raisedBedRepositoryIsEmpty() {
        raisedBedRepository.save(null);
    }

    private RaisedBedId raisedBedRepositoryContains(RaisedBed raisedBed) {
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
