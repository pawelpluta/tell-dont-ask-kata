package com.pawelpluta.telldontaskkata.application;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.pawelpluta.telldontaskkata.domain.Crates;
import com.pawelpluta.telldontaskkata.domain.Product;
import com.pawelpluta.telldontaskkata.domain.RaisedBed;
import com.pawelpluta.telldontaskkata.repository.CrateRepository;
import com.pawelpluta.telldontaskkata.repository.RaisedBedRepository;

public class Gathering {

    private final RaisedBedRepository raisedBedRepository;
    private final CrateRepository crateRepository;

    public Gathering(RaisedBedRepository raisedBedRepository, CrateRepository crateRepository) {
        this.raisedBedRepository = raisedBedRepository;
        this.crateRepository = crateRepository;
    }

    public void gather(GatheringCommand command) {
        Optional<RaisedBed> raisedBed = raisedBedRepository.findById(command.getRaisedBedId().value());
        List<Product> gatheredProducts = raisedBed
                .map(bed -> bed.gatherPlants(command.getPlantType()))
                .orElseGet(Collections::emptyList);
        if (!gatheredProducts.isEmpty()) {
            crateRepository.saveAll(Crates.from(gatheredProducts).value());
        }
        raisedBed.ifPresent(raisedBedRepository::save);
    }

}
