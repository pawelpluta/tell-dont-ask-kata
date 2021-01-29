package com.pawelpluta.telldontaskkata.domain;

public class Product {

    private final Integer weight;
    private final ProductType type;

    public Product(Integer weight, ProductType type) {
        this.weight = weight;
        this.type = type;
    }

    public Integer weight() {
        return weight;
    }

    public ProductType getType() {
        return type;
    }
}
