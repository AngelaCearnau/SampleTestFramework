package com.one.ui.domains;

public class Product {
    private final String name;
    private final String description;
    private final String price;
    private final String button;

    public Product(String name, String description, String price, String button) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.button = button;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getButton() {
        return button;
    }
}
