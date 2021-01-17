package com.pawelpluta.telldontaskkata.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.pawelpluta.telldontaskkata.domain.Crate;
import com.pawelpluta.telldontaskkata.domain.PlantType;
import com.pawelpluta.telldontaskkata.domain.Product;
import com.pawelpluta.telldontaskkata.domain.ProductType;
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
        raisedBedRepository.findById(command.getRaisedBedId())
                           .map(bed -> {
                               List<Product> products = gatherPlants(bed, command.getPlantType());
                               raisedBedRepository.save(bed);
                               return products;
                           })
                           .map(this::packIntoCrates)
                           .ifPresent(crateRepository::saveAll);
    }

    private List<Crate> packIntoCrates(List<Product> gatheredProducts) {
        List<Crate> crates = new ArrayList<>();
        Crate currentPackedCrate = new Crate();
        for (Product product : gatheredProducts) {
            if (product.getWeight() + currentPackedCrate.getWeight() > 20) {
                crates.add(currentPackedCrate);
                currentPackedCrate = new Crate();
            }
            currentPackedCrate.getProducts().add(product);
        }
        if (!currentPackedCrate.getProducts().isEmpty()) {
            crates.add(currentPackedCrate);
        }
        return crates;
    }

    private List<Product> gatherPlants(RaisedBed bed, PlantType plantType) {
        switch (plantType) {
            case VEGETABLE:
                return gatherVegetables(bed);
            case FLOWER:
                return gatherFlowers(bed);
            case TREE:
                return gatherFruits(bed);
            default:
                return Collections.emptyList();
        }
    }

    private List<Product> gatherVegetables(RaisedBed bed) {
        return bed.getPlants().stream()
                  .filter(plant -> plant.getType().equals(PlantType.VEGETABLE))
                  .filter(plant -> plant.getWateringCount() > 5)
                  .map(plant -> {
                      plant.setWateringCount(0);
                      return new Product(3, ProductType.VEGETABLE);
                  }).collect(Collectors.toList());
    }

    private List<Product> gatherFlowers(RaisedBed bed) {
        return bed.getPlants().stream()
                  .filter(plant -> plant.getType().equals(PlantType.FLOWER))
                  .filter(plant -> plant.getWateringCount() > 3)
                  .map(plant -> {
                      plant.setWateringCount(0);
                      return new Product(2, ProductType.FLOWER);
                  }).collect(Collectors.toList());
    }

    private List<Product> gatherFruits(RaisedBed bed) {
        return bed.getPlants().stream()
                  .filter(plant -> plant.getType().equals(PlantType.TREE))
                  .filter(plant -> plant.getWateringCount() > 10)
                  .map(plant -> {
                      plant.setWateringCount(0);
                      return new Product(5, ProductType.FRUIT);
                  }).collect(Collectors.toList());
    }
}
