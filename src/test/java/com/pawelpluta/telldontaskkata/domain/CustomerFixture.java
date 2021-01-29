package com.pawelpluta.telldontaskkata.domain;

import static com.pawelpluta.telldontaskkata.IdentifierFixture.randomId;

public class CustomerFixture {

    public static Customer someCustomerWithWarehouse() {
        return new Customer(CustomerId.random(), true);
    }

    public static Customer someCustomerWithoutWarehouse() {
        return new Customer(CustomerId.random(), false);
    }
}
