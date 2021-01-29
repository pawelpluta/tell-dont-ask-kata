package com.pawelpluta.telldontaskkata.domain;

import java.util.UUID;

public class CustomerId {
    private final UUID value;

    private CustomerId(UUID value) {
        this.value = value;
    }

    public static CustomerId random() {
        return new CustomerId(UUID.randomUUID());
    }

    public static CustomerId from(UUID value) {
        return new CustomerId(value);
    }

    public UUID value() {
        return value;
    }
}
