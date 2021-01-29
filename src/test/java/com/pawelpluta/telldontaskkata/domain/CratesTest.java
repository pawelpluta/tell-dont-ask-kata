package com.pawelpluta.telldontaskkata.domain;

import java.util.List;

import org.junit.jupiter.api.Test;

import static com.pawelpluta.telldontaskkata.domain.CrateFixture.someCrate;
import static com.pawelpluta.telldontaskkata.domain.CustomerFixture.someCustomerWithWarehouse;
import static com.pawelpluta.telldontaskkata.domain.ProductFixture.productWithWeightOf;
import static org.junit.jupiter.api.Assertions.*;

class CratesTest {

    @Test
    void creatingCratesShouldGroupProductsIntoIndividualCrates() {
        // given
        List<Product> productsToBeGroupedIntoCrates = List.of(
                productWithWeightOf(10), productWithWeightOf(10), productWithWeightOf(10));

        // when
        Crates crates = Crates.from(productsToBeGroupedIntoCrates);

        // then
        assertTrue(crates.hasAtLeastCountOf(2));
        assertFalse(crates.hasAtLeastCountOf(3));
    }

    @Test
    void cratesCanBePackedForCustomerForSendout() {
        // given
        Crates crates = Crates.of(List.of(someCrate(), someCrate()));
        Customer customer = someCustomerWithWarehouse();

        // when
        Package packedCrates = crates.packFor(customer);

        // then
        assertEquals(crates.value(), packedCrates.deliveredCrates());
    }
}
