package com.pawelpluta.telldontaskkata.domain;

import java.util.List;

import static com.pawelpluta.telldontaskkata.domain.CustomerFixture.someCustomerWithWarehouse;
import static com.pawelpluta.telldontaskkata.domain.CustomerFixture.someCustomerWithoutWarehouse;

class PackageFixture {

    public static Package someDeliverablePackage() {
        return new Package(randomCrates(), someCustomerWithWarehouse());
    }

    public static Package somePackageForUnknownLocation() {
        return new Package(randomCrates(), someCustomerWithoutWarehouse());
    }

    private static Crates randomCrates() {
        return Crates.of(List.of(CrateFixture.someCrate()));
    }

}
