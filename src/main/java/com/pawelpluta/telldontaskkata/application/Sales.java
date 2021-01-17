package com.pawelpluta.telldontaskkata.application;

import java.util.List;

import com.pawelpluta.telldontaskkata.domain.Crate;
import com.pawelpluta.telldontaskkata.domain.Customer;
import com.pawelpluta.telldontaskkata.domain.Package;
import com.pawelpluta.telldontaskkata.domain.ProductType;
import com.pawelpluta.telldontaskkata.repository.CrateRepository;
import com.pawelpluta.telldontaskkata.service.DeliveryService;

public class Sales {

    private final CrateRepository crateRepository;
    private final DeliveryService deliveryService;

    public Sales(CrateRepository crateRepository, DeliveryService deliveryService) {
        this.crateRepository = crateRepository;
        this.deliveryService = deliveryService;
    }

    public boolean sell(Integer soldCrates, ProductType type, Customer customer) {
        List<Crate> crates = crateRepository.findByTypeLimit(type, soldCrates);
        if (crates.size() != soldCrates) {
            return false;
        }
        if (customer.getWarehouseAddressVerified()) {
            Package packageToDeliver = new Package(crates, customer);
            deliveryService.send(packageToDeliver);
            crateRepository.delete(crates);
            return true;
        }
        return false;
    }

}
