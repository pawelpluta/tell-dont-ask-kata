package com.pawelpluta.telldontaskkata.domain;

public interface SaleResult {

    static SaleResult sold() {
        return new SoldSuccessfully();
    }

    static SaleResult receiverAddressUnknown() {
        return new CannotBeDelivered();
    }

    static SaleResult productOutOfStock() {
        return new CannotBeDelivered();
    }

    class SoldSuccessfully implements SaleResult {
    }

    class CannotBeDelivered implements SaleResult {
    }

}
