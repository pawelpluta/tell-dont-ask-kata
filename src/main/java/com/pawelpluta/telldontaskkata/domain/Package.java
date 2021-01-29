package com.pawelpluta.telldontaskkata.domain;

import java.util.List;

import com.pawelpluta.telldontaskkata.service.DeliveryService;

public class Package {

    private final Crates crates;
    private final Customer customer;

    public Package(Crates crates, Customer customer) {
        this.crates = crates;
        this.customer = customer;
    }

    public Result send(DeliveryService deliveryService) {
        if (customer.isWarehouseAddressVerified()) {
            deliveryService.send(this);
            return Result.ACCEPTED;
        }
        return Result.DESTINATION_UNKNOWN;
    }

    public List<Crate> deliveredCrates() {
        return crates.value();
    }

    public enum Result {
        ACCEPTED, DESTINATION_UNKNOWN
    }
}
