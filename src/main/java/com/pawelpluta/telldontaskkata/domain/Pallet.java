package com.pawelpluta.telldontaskkata.domain;

import java.util.List;

public class Pallet {

    private final List<Crate> crates;

    public Pallet(List<Crate> crates) {
        this.crates = crates;
    }
}
