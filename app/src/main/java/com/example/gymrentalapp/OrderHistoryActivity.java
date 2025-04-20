package com.example.gymrentalapp;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    ListView orderList;
    OrderHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        orderList = findViewById(R.id.orderListView);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(this);
        List<List<CartItem>> history = sharedPrefManager.getOrderHistory();

        List<CartItem> flattenedItems = new ArrayList<>();
        for (List<CartItem> order : history) {
            flattenedItems.addAll(order);
        }

        if (flattenedItems.isEmpty()) {
            Toast.makeText(this, "No previous orders !", Toast.LENGTH_SHORT).show();
        } else {
            adapter = new OrderHistoryAdapter(this, flattenedItems);
            orderList.setAdapter(adapter);
        }
    }
}
