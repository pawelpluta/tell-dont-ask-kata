package com.pawelpluta.telldontaskkata.application;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pawelpluta.telldontaskkata.domain.Crate;
import com.pawelpluta.telldontaskkata.domain.ProductType;
import com.pawelpluta.telldontaskkata.repository.CrateRepository;
import com.pawelpluta.telldontaskkata.repository.InMemoryCrateRepository;
import com.pawelpluta.telldontaskkata.service.DeliveryService;

import static com.pawelpluta.telldontaskkata.domain.CrateFixture.someCrateOfType;
import static com.pawelpluta.telldontaskkata.domain.CustomerFixture.someCustomerWithWarehouse;
import static com.pawelpluta.telldontaskkata.domain.CustomerFixture.someCustomerWithoutWarehouse;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class SalesTest {

    private CrateRepository crateRepository;
    private DeliveryService deliveryService;
    private Sales sales;

    @BeforeEach
    void setUp() {
        crateRepository = new InMemoryCrateRepository();
        deliveryService = mock(DeliveryService.class);
        sales = new Sales(crateRepository, deliveryService);
    }

    @Test
    void shouldNotSaleIfAmountOfCratesIsInsufficient() {
        // given
        cratesRepositoryContains(someCrateOfType(ProductType.FRUIT));

        // when
        boolean soldSuccessfully = sales.sell(2, ProductType.FRUIT, someCustomerWithWarehouse());

        // then
        assertFalse(soldSuccessfully);
    }

    @Test
    void shouldNotSaleIfCustomersWarehouseAddressHasNotBeenVerified() {
        // given
        cratesRepositoryContains(someCrateOfType(ProductType.FRUIT));

        // when
        boolean soldSuccessfully = sales.sell(1, ProductType.FRUIT, someCustomerWithoutWarehouse());

        // then
        assertFalse(soldSuccessfully);
    }

    @Test
    void canSellCratesToKnownCustomer() {
        // given
        cratesRepositoryContains(someCrateOfType(ProductType.FRUIT));

        // when
        boolean soldSuccessfully = sales.sell(1, ProductType.FRUIT, someCustomerWithWarehouse());

        // then
        assertTrue(soldSuccessfully);
    }

    @Test
    void soldCratesAreUnavailable() {
        // given
        cratesRepositoryContains(someCrateOfType(ProductType.FRUIT));

        // when
        sales.sell(1, ProductType.FRUIT, someCustomerWithWarehouse());

        // then
        assertTrue(crateRepository.findByTypeLimit(ProductType.FRUIT, 1).isEmpty());
    }

    @Test
    void packageIsSentToCustomerWhenCratesAreSold() {
        cratesRepositoryContains(someCrateOfType(ProductType.FRUIT));

        // when
        sales.sell(1, ProductType.FRUIT, someCustomerWithWarehouse());

        // then
        verify(deliveryService, times(1)).send(any());
    }

    private void cratesRepositoryContains(Crate crate) {
        crateRepository.saveAll(List.of(crate));
    }

}
