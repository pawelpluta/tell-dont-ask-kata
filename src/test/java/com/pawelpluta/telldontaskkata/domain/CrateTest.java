package com.pawelpluta.telldontaskkata.domain;

import org.junit.jupiter.api.Test;

import static com.pawelpluta.telldontaskkata.domain.ProductFixture.productWithWeightOf;
import static org.junit.jupiter.api.Assertions.*;

class CrateTest {

    @Test
    void newCrateShouldBeEmpty() {
        // when
        Crate freshlyCreatedCrate = Crate.empty();

        // then
        assertFalse(freshlyCreatedCrate.hasProducts());
    }

    @Test
    void crateCanFitProductIfTotalWeightDoesNotExceedLimit() {
        // given
        Product firstLightProduct = productWithWeightOf(2);
        Product secondLightProduct = productWithWeightOf(3);
        Crate crate = Crate.empty().pack(firstLightProduct);

        // when
        Crate.Result result = crate.canFit(secondLightProduct);

        // then
        assertEquals(Crate.Result.FITTABLE, result);
    }

    @Test
    void crateCannotFitProductIfTotalWeightDoesExceedLimit() {
        // given
        Product heavyProduct = productWithWeightOf(19);
        Product productThatShouldNotFit = productWithWeightOf(2);
        Crate crate = Crate.empty().pack(heavyProduct);

        // when
        Crate.Result result = crate.canFit(productThatShouldNotFit);

        // then
        assertEquals(Crate.Result.OVERWEIGHTED, result);
    }

    @Test
    void crateShouldFitProductsThatTotalWeightIsEqualToMaximumCapacity() {
        // given
        Product productWeighting15 = productWithWeightOf(15);
        Product productWeighting5 = productWithWeightOf(5);
        Crate crate = Crate.empty().pack(productWeighting15);

        // when
        Crate.Result result = crate.canFit(productWeighting5);

        // then
        assertEquals(Crate.Result.FITTABLE, result);
    }

}
