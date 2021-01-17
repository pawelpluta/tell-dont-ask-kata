package com.pawelpluta.telldontaskkata.domain;

import java.util.List;

public class Package {

    private final List<Crate> crates;
    private final Customer customer;

    public Package(List<Crate> crates, Customer customer) {
        this.crates = crates;
        this.customer = customer;
    }
}
