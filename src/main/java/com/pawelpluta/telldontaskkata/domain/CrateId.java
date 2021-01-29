package com.pawelpluta.telldontaskkata.domain;

import java.util.UUID;

public class CrateId {
    private final UUID value;

    private CrateId(UUID value) {
        this.value = value;
    }

    public static CrateId random() {
        return new CrateId(UUID.randomUUID());
    }

    public static CrateId from(UUID value) {
        return new CrateId(value);
    }

    public UUID value() {
        return value;
    }
}
