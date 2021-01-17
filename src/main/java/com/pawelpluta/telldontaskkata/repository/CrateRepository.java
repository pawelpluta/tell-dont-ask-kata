package com.pawelpluta.telldontaskkata.repository;

import java.util.List;

import com.pawelpluta.telldontaskkata.domain.Crate;
import com.pawelpluta.telldontaskkata.domain.ProductType;

public interface CrateRepository {

    void saveAll(List<Crate> crates);

    void delete(List<Crate> crates);

    List<Crate> findByTypeLimit(ProductType type, Integer cratesAmount);
}
