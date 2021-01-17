package com.pawelpluta.telldontaskkata.repository;

import java.util.Collections;
import java.util.List;

import com.pawelpluta.telldontaskkata.domain.Crate;
import com.pawelpluta.telldontaskkata.domain.ProductType;

public class InMemoryCrateRepository implements CrateRepository {

    private List<Crate> crates;

    @Override
    public void saveAll(List<Crate> crates) {
        this.crates = crates;
    }

    @Override
    public void delete(List<Crate> crates) {
        this.crates = Collections.emptyList();
    }

    @Override
    public List<Crate> findByTypeLimit(ProductType type, Integer cratesAmount) {
        return crates;
    }

    public List<Crate> getCrates() {
        return crates;
    }
}
