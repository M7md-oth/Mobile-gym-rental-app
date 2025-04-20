package com.example.gymrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private List<CartItem> cartItems;
    private CartAdapter cartAdapter;
    private SharedPrefManager sharedPrefManager;
    private TextView totalPriceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ListView cartListView = findViewById(R.id.cartListView);
        totalPriceText = findViewById(R.id.totalPriceText);
        Button checkoutButton = findViewById(R.id.checkoutButton);
        ImageButton backButton = findViewById(R.id.backButton);

        sharedPrefManager = new SharedPrefManager(this);
        cartItems = sharedPrefManager.getCart();
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Your cart is empty 🛒", Toast.LENGTH_SHORT).show();
        }

        cartAdapter = new CartAdapter(this, cartItems);
        cartListView.setAdapter(cartAdapter);

        updateTotal();

        checkoutButton.setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                Toast.makeText(this, "Add something first!", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(this, CheckoutActivity.class));
            }
        });

        backButton.setOnClickListener(v -> finish());
    }

    private void updateTotal() {
        double totalPrice = 0;
        for (CartItem item : cartItems) {
            Equipment equipment = item.getEquipment();
            if (equipment != null) {
                totalPrice += equipment.getPrice() * item.getQuantity();
            } else {
                Log.e("CartActivity", "Null equipment in cart item: " + item);
            }
        }
        totalPriceText.setText("Total: $" + String.format("%.2f", totalPrice));
    }
}
