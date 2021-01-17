package com.pawelpluta.telldontaskkata.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pawelpluta.telldontaskkata.domain.Plant;
import com.pawelpluta.telldontaskkata.domain.RaisedBed;
import com.pawelpluta.telldontaskkata.repository.InMemoryCrateRepository;
import com.pawelpluta.telldontaskkata.repository.InMemoryRaisedBedRepository;
import com.pawelpluta.telldontaskkata.repository.RaisedBedRepository;

import static com.pawelpluta.telldontaskkata.application.GatheringCommandFixture.gatherPlantsCommandWithBed;
import static com.pawelpluta.telldontaskkata.domain.PlantFixture.plantWithWateringCountOf;
import static com.pawelpluta.telldontaskkata.domain.RaisedBedFixture.someRaisedBedWith;
import static com.pawelpluta.telldontaskkata.domain.RaisedBedFixture.someRaisedBedWithMultiplePlantsReadyForGathering;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GatheringTest {

    private RaisedBedRepository raisedBedRepository;
    private InMemoryCrateRepository crateRepository;
    private Gathering gathering;

    @BeforeEach
    void setUp() {
        raisedBedRepository = new InMemoryRaisedBedRepository();
        crateRepository = new InMemoryCrateRepository();
        gathering = new Gathering(raisedBedRepository, crateRepository);
    }

    @Test
    void gatheredPlantsShouldNotBeAvailableForGatheringAgain() {
        // given
        Integer initialWateringCount = 10;
        Integer raisedBedId = raisedBedRepositoryContains(someRaisedBedWith(plantWithWateringCountOf(initialWateringCount)));
        GatheringCommand command = gatherPlantsCommandWithBed(raisedBedId);

        // when
        gathering.gather(command);

        // then
        Plant gatheredPlant = raisedBedRepository.findById(raisedBedId).get().getPlants().get(0);
        assertEquals(0, gatheredPlant.getWateringCount());
    }

    @Test
    void gatheringShouldProduceCrateWithProducts() {
        // given
        Integer initialWateringCount = 10;
        Integer raisedBedId = raisedBedRepositoryContains(someRaisedBedWith(plantWithWateringCountOf(initialWateringCount)));
        GatheringCommand command = gatherPlantsCommandWithBed(raisedBedId);

        // when
        gathering.gather(command);

        // then
        assertFalse(crateRepository.getCrates().isEmpty());
        assertNotEquals(0, crateRepository.getCrates().get(0).getWeight());
        assertNotNull(crateRepository.getCrates().get(0).getProducts());
    }

    @Test
    void gatheringBigAmountOfPlantsShouldProduceMultipleCrates() {
        // given
        Integer raisedBedId = raisedBedRepositoryContains(someRaisedBedWithMultiplePlantsReadyForGathering());
        GatheringCommand command = gatherPlantsCommandWithBed(raisedBedId);

        // when
        gathering.gather(command);

        // then
        assertTrue(crateRepository.getCrates().size() > 1);
    }

    private Integer raisedBedRepositoryContains(RaisedBed raisedBed) {
        raisedBedRepository.save(raisedBed);
        return raisedBed.getId();
    }
}
