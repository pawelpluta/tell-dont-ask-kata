package com.pawelpluta.telldontaskkata.domain;

public class Customer {

    private CustomerId id;
    private Boolean warehouseAddressVerified;

    public Customer(CustomerId id, Boolean warehouseAddressVerified) {
        this.id = id;
        this.warehouseAddressVerified = warehouseAddressVerified;
    }

    public CustomerId getId() {
        return id;
    }

    public Boolean isWarehouseAddressVerified() {
        return warehouseAddressVerified;
    }
}
