package com.pawelpluta.telldontaskkata.domain;

import java.util.ArrayList;
import java.util.List;

public class Crate {

    private List<Product> products;

    public Crate() {
        products = new ArrayList<>();
    }

    public Crate(List<Product> products) {
        this.products = products;
    }

    public Integer getWeight() {
        return products.stream().map(Product::getWeight).reduce(0, Integer::sum);
    }

    public List<Product> getProducts() {
        return products;
    }
}
