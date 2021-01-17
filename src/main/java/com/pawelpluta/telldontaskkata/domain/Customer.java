package com.pawelpluta.telldontaskkata.domain;

public class Customer {

    private Integer id;
    private Boolean warehouseAddressVerified;

    public Customer(Integer id, Boolean warehouseAddressVerified) {
        this.id = id;
        this.warehouseAddressVerified = warehouseAddressVerified;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getWarehouseAddressVerified() {
        return warehouseAddressVerified;
    }
}
