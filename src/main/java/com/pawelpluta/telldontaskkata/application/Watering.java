package com.pawelpluta.telldontaskkata.application;

import java.util.List;
import java.util.Optional;

import com.pawelpluta.telldontaskkata.domain.Plant;
import com.pawelpluta.telldontaskkata.domain.PlantType;
import com.pawelpluta.telldontaskkata.domain.RaisedBed;
import com.pawelpluta.telldontaskkata.domain.WaterValve;
import com.pawelpluta.telldontaskkata.repository.RaisedBedRepository;
import com.pawelpluta.telldontaskkata.repository.WaterValveRepository;

public class Watering {

    private final RaisedBedRepository raisedBedRepository;
    private final WaterValveRepository waterValveRepository;

    public Watering(RaisedBedRepository raisedBedRepository, WaterValveRepository waterValveRepository) {
        this.raisedBedRepository = raisedBedRepository;
        this.waterValveRepository = waterValveRepository;
    }

    public boolean perform(WateringCommand command) {
        Optional<RaisedBed> raisedBed = raisedBedRepository.findById(command.getRaisedBedId());
        Optional<WaterValve> waterValve = waterValveRepository.findById(command.getValveId());
        if (waterValve.isEmpty()) {
            return false;
        }
        WaterValve valve = waterValve.get();
        return raisedBed.map(bed -> {
            boolean successfullyWatered = waterBed(bed, valve);
            raisedBedRepository.save(bed);
            waterValveRepository.save(valve);
            return successfullyWatered;
        }).orElse(false);
    }

    private boolean waterBed(RaisedBed raisedBed, WaterValve waterValve) {
        List<Plant> plants = raisedBed.getPlants();
        for (Plant plant : plants) {
            Integer moisture = plant.getSoilMoisturePercentage();
            PlantType type = plant.getType();
            boolean needsWatering = false;
            switch (type) {
                case VEGETABLE:
                    needsWatering = moisture < 60;
                    break;
                case FLOWER:
                    needsWatering = moisture < 50;
                    break;
                case TREE:
                    needsWatering = moisture < 45;
                    break;
            }
            if (needsWatering) {
                try {
                    waterValve.setOpen(true);
                    Thread.sleep(100); // wait for some water to flow
                } catch (InterruptedException e) {
                    return false;
                } finally {
                    waterValve.setOpen(false);
                }
                plant.setSoilMoisturePercentage(moisture + 20);
                plant.setWateringCount(plant.getWateringCount() + 1);
            }
        }
        return true;
    }
}
