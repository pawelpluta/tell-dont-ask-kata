package com.pawelpluta.telldontaskkata.application;

import com.pawelpluta.telldontaskkata.domain.Crates;
import com.pawelpluta.telldontaskkata.domain.Customer;
import com.pawelpluta.telldontaskkata.domain.SaleResult;
import com.pawelpluta.telldontaskkata.domain.Package;
import com.pawelpluta.telldontaskkata.domain.ProductType;
import com.pawelpluta.telldontaskkata.repository.CrateRepository;
import com.pawelpluta.telldontaskkata.service.DeliveryService;

import static com.pawelpluta.telldontaskkata.domain.Package.Result.ACCEPTED;

public class Sales {

    private final CrateRepository crateRepository;
    private final DeliveryService deliveryService;

    public Sales(CrateRepository crateRepository, DeliveryService deliveryService) {
        this.crateRepository = crateRepository;
        this.deliveryService = deliveryService;
    }

    public SaleResult sell(Integer soldCrates, ProductType type, Customer customer) {
        Crates crates = Crates.of(crateRepository.findByTypeLimit(type, soldCrates));
        if (!crates.hasAtLeastCountOf(soldCrates)) {
            return SaleResult.productOutOfStock();
        }
        Package packageToDeliver = crates.packFor(customer);
        if (packageToDeliver.send(deliveryService) == ACCEPTED) {
            crateRepository.delete(packageToDeliver.deliveredCrates());
            return SaleResult.sold();
        }
        return SaleResult.receiverAddressUnknown();
    }

}
