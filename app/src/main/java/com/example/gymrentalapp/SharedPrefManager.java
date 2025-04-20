package com.example.gymrentalapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefManager {

    private static final String PREF_NAME = "gym_cart_pref";
    private static final String CART_KEY = "cart_items";
    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public List<CartItem> getCart() {
        String json = sharedPreferences.getString(CART_KEY, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<CartItem>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void saveCart(List<CartItem> cart) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(cart);
        editor.putString(CART_KEY, json);
        editor.apply();
    }

    public void clearCart() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CART_KEY);
        editor.apply();
    }

    public void addToCart(Equipment equipment, int quantityToAdd) {
        if (equipment == null) {
            Log.e("SharedPrefManager", "Tried to add null equipment to cart!");
            return;
        }

        List<CartItem> cart = getCart();
        boolean found = false;

        for (CartItem item : cart) {
            Equipment existing = item.getEquipment();
            if (existing != null && existing.getName().equals(equipment.getName())) {
                item.increaseQuantity(quantityToAdd);
                found = true;
                break;
            }
        }

        if (!found) {
            cart.add(new CartItem(equipment, quantityToAdd));
        }

        saveCart(cart);
    }

    private static final String ORDER_HISTORY_KEY = "order_history";

    public void saveOrderToHistory(List<CartItem> order) {
        List<List<CartItem>> history = getOrderHistory();
        history.add(order);
        String json = gson.toJson(history);
        sharedPreferences.edit().putString(ORDER_HISTORY_KEY, json).apply();
    }

    public List<List<CartItem>> getOrderHistory() {
        String json = sharedPreferences.getString(ORDER_HISTORY_KEY, null);
        if (json == null) return new ArrayList<>();
        Type type = new TypeToken<List<List<CartItem>>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void removeItem(CartItem target) {
        List<CartItem> cart = getCart();
        cart.removeIf(item ->
                item.getEquipment().getName().equals(target.getEquipment().getName()) &&
                        item.getEquipment().getWeight() == target.getEquipment().getWeight());
        saveCart(cart);
    }
}
