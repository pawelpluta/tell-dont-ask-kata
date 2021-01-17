package com.pawelpluta.telldontaskkata.domain;

import static com.pawelpluta.telldontaskkata.IdentifierFixture.randomId;

public class WaterValveFixture {

    public static WaterValve someWaterValve() {
        return new WaterValve(randomId());
    }
}
