package com.pawelpluta.telldontaskkata.domain;

import java.util.UUID;

public class RaisedBedId {
    private final UUID value;

    private RaisedBedId(UUID value) {
        this.value = value;
    }

    public static RaisedBedId random() {
        return new RaisedBedId(UUID.randomUUID());
    }

    public static RaisedBedId from(UUID value) {
        return new RaisedBedId(value);
    }

    public UUID value() {
        return value;
    }
}
