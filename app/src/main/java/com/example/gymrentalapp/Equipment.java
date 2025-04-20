package com.example.gymrentalapp;

public class Equipment {
    private String name;
    private String type;
    private int weight;
    private boolean available;
    private double price;


    public Equipment(String name, String type, int weight, boolean available, double price) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.available = available;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isAvailable() {
        return available;
    }
}
