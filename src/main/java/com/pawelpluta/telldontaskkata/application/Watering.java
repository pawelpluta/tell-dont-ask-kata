package com.pawelpluta.telldontaskkata.application;

import java.util.Optional;

import com.pawelpluta.telldontaskkata.domain.RaisedBed;
import com.pawelpluta.telldontaskkata.domain.WaterValve;
import com.pawelpluta.telldontaskkata.domain.WateringResult;
import com.pawelpluta.telldontaskkata.repository.RaisedBedRepository;
import com.pawelpluta.telldontaskkata.repository.WaterValveRepository;

public class Watering {

    private final RaisedBedRepository raisedBedRepository;
    private final WaterValveRepository waterValveRepository;

    public Watering(RaisedBedRepository raisedBedRepository, WaterValveRepository waterValveRepository) {
        this.raisedBedRepository = raisedBedRepository;
        this.waterValveRepository = waterValveRepository;
    }

    public WateringResult perform(WateringCommand command) {
        Optional<RaisedBed> raisedBed = raisedBedRepository.findById(command.getRaisedBedId().value());
        Optional<WaterValve> waterValve = waterValveRepository.findById(command.getValveId());
        if (waterValve.isEmpty()) {
            return WateringResult.noValve();
        }
        if (raisedBed.isEmpty()) {
            return WateringResult.noPlants();
        }
        return waterBed(raisedBed.get(), waterValve.get());
    }

    private WateringResult waterBed(RaisedBed raisedBed, WaterValve waterValve) {
        WateringResult successfullyWatered = raisedBed.waterWith(waterValve);
        raisedBedRepository.save(raisedBed);
        waterValveRepository.save(waterValve);
        return successfullyWatered;
    }

}
