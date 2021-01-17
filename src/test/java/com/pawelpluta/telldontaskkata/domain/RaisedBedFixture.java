package com.pawelpluta.telldontaskkata.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.pawelpluta.telldontaskkata.IdentifierFixture.randomId;
import static com.pawelpluta.telldontaskkata.domain.PlantFixture.plantWithWateringCountOf;
import static com.pawelpluta.telldontaskkata.domain.PlantFixture.somePlant;

public class RaisedBedFixture {

    public static RaisedBed someRaisedBed() {
        return new RaisedBed(randomId(), List.of(somePlant()));
    }

    public static RaisedBed someRaisedBedWith(Plant plant) {
        return new RaisedBed(randomId(), List.of(plant));
    }
    public static RaisedBed someRaisedBedWithMultiplePlantsReadyForGathering() {
        List<Plant> plantsReadyForGathering = IntStream.range(0, 100).mapToObj(it -> plantWithWateringCountOf(10)).collect(Collectors.toList());
        return new RaisedBed(randomId(), plantsReadyForGathering);
    }
}
