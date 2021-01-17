package com.pawelpluta.telldontaskkata.domain;

import java.util.List;

import static com.pawelpluta.telldontaskkata.domain.ProductFixture.someProductOfType;

public class CrateFixture {

    public static Crate someCrateOfType(ProductType type) {
        return new Crate(List.of(someProductOfType(type)));
    }
}
