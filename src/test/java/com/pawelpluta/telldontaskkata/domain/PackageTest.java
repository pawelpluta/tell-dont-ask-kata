package com.pawelpluta.telldontaskkata.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pawelpluta.telldontaskkata.service.DeliveryService;

import static com.pawelpluta.telldontaskkata.domain.PackageFixture.someDeliverablePackage;
import static com.pawelpluta.telldontaskkata.domain.PackageFixture.somePackageForUnknownLocation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PackageTest {

    @Mock
    private DeliveryService deliveryService;

    @Test
    void packageThatRecipientHasWarehouseCanBeDelivered() {
        // given
        Package packageToBeDelivered = someDeliverablePackage();

        // when
        Package.Result result = packageToBeDelivered.send(deliveryService);

        // then
        assertEquals(Package.Result.ACCEPTED, result);
        verify(deliveryService, times(1)).send(eq(packageToBeDelivered));
    }

    @Test
    void packageForUnknownDestinationCannotBeSend() {
        // given
        Package packageToBeDelivered = somePackageForUnknownLocation();

        // when
        Package.Result result = packageToBeDelivered.send(deliveryService);

        // then
        assertEquals(Package.Result.DESTINATION_UNKNOWN, result);
        verify(deliveryService, never()).send(eq(packageToBeDelivered));
    }


}
