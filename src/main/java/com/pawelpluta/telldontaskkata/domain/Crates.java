package com.pawelpluta.telldontaskkata.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

public class Crates {

    private final List<Crate> crates;

    private Crates(List<Crate> crates) {
        this.crates = Collections.unmodifiableList(crates);
    }

    public static Crates empty() {
        return new Crates(emptyList());
    }

    public static Crates from(List<Product> products) {
        Crates crates = empty();
        Crate currentPackedCrate = Crate.empty();
        for (Product product : products) {
            if (currentPackedCrate.canFit(product) == Crate.Result.OVERWEIGHTED) {
                crates = crates.stack(currentPackedCrate);
                currentPackedCrate = Crate.empty();
            }
            currentPackedCrate = currentPackedCrate.pack(product);
        }
        if (currentPackedCrate.hasProducts()) {
            crates = crates.stack(currentPackedCrate);
        }
        return crates;
    }

    public static Crates of(List<Crate> crates) {
        return new Crates(new ArrayList<>(crates));
    }

    public Crates stack(Crate crate) {
        ArrayList<Crate> updatedCrates = new ArrayList<>(crates);
        updatedCrates.add(crate);
        return new Crates(updatedCrates);
    }

    public List<Crate> value() {
        return Collections.unmodifiableList(crates);
    }

    public boolean hasAtLeastCountOf(Integer minimalCount) {
        return crates.size() >= minimalCount;
    }

    public Package packFor(Customer customer) {
        return new Package(this, customer);
    }
}
