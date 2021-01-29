package com.pawelpluta.telldontaskkata.domain;

import static com.pawelpluta.telldontaskkata.domain.ProductFixture.someProductOfType;

public class CrateFixture {

    public static Crate someCrate() {
        return someCrateOfType(ProductType.FRUIT);
    }

    public static Crate someCrateOfType(ProductType type) {
        return Crate.empty().pack(someProductOfType(type));
    }
}
