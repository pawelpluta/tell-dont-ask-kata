package com.pawelpluta.telldontaskkata.domain;

import java.util.ArrayList;
import java.util.List;

import static com.pawelpluta.telldontaskkata.domain.Crate.Result.FITTABLE;
import static com.pawelpluta.telldontaskkata.domain.Crate.Result.OVERWEIGHTED;
import static java.util.Collections.emptyList;

public class Crate {

    private static final int MAXIMUM_WEIGHT = 20;
    private final CrateId crateId;
    private final List<Product> products;

    private Crate(CrateId crateId, List<Product> products) {
        this.crateId = crateId;
        this.products = products;
    }

    public static Crate empty() {
        return new Crate(CrateId.random(), emptyList());
    }

    public Crate pack(Product product) {
        List<Product> updatedProducts = new ArrayList<>(this.products);
        updatedProducts.add(product);
        return new Crate(crateId, updatedProducts);
    }

    public boolean hasProducts() {
        return !products.isEmpty();
    }

    public Result canFit(Product product) {
        return (weight() + product.weight()) > MAXIMUM_WEIGHT ? OVERWEIGHTED : FITTABLE;
    }

    private Integer weight() {
        return products.stream().map(Product::weight).reduce(0, Integer::sum);
    }

    enum Result {
        OVERWEIGHTED, FITTABLE
    }
}
