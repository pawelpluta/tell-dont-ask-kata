package com.pawelpluta.telldontaskkata.domain;

public class ProductFixture {

    public static Product someProductOfType(ProductType productType) {
        return new Product(10, productType);
    }

    public static Product productWithWeightOf(Integer weight) {
        return new Product(weight, ProductType.FRUIT);
    }

}
