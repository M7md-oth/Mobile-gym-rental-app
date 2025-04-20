package com.example.gymrentalapp;

public class CartItem {
    private Equipment equipment;
    private int quantity;

    public CartItem(Equipment equipment, int quantity) {
        this.equipment = equipment;
        this.quantity = quantity;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }
}
