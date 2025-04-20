package com.example.gymrentalapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView equipmentRecyclerView;
    ArrayList<Equipment> equipmentList = new ArrayList<>();
    EquipmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        equipmentRecyclerView = findViewById(R.id.equipmentRecyclerView);
        equipmentRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        loadEquipmentData();

        adapter = new EquipmentAdapter(this, equipmentList);
        equipmentRecyclerView.setAdapter(adapter);

        EditText searchInput = findViewById(R.id.searchInput);
        Switch onlyAvailableSwitch = findViewById(R.id.onlyAvailableSwitch);
        Button searchButton = findViewById(R.id.searchButton);
        ImageButton historyButton = findViewById(R.id.historyButton);

        searchButton.setOnClickListener(v -> {
            String keyword = searchInput.getText().toString().trim().toLowerCase();
            boolean onlyAvailable = onlyAvailableSwitch.isChecked();

            List<Equipment> filteredList = new ArrayList<>();
            for (Equipment eq : equipmentList) {
                boolean matches = true;

                if (!keyword.isEmpty() && !eq.getName().toLowerCase().contains(keyword)) {
                    matches = false;
                }

                if (onlyAvailable && !eq.isAvailable()) {
                    matches = false;
                }

                if (matches) {
                    filteredList.add(eq);
                }
            }

            adapter = new EquipmentAdapter(this, filteredList);
            equipmentRecyclerView.setAdapter(adapter);
        });

        findViewById(R.id.openCartButton).setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, CartActivity.class));
        });

        historyButton.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, OrderHistoryActivity.class));
        });
    }

    private void loadEquipmentData() {
        equipmentList.add(new Equipment("Dumbbell Set", "Weight", 20, true, 60.0));
        equipmentList.add(new Equipment("Yoga Mat", "Mat", 1, true, 15.0));
        equipmentList.add(new Equipment("Kettlebell", "Weight", 12, false, 35.0));
        equipmentList.add(new Equipment("Spin Bike", "Cardio", 40, true, 120.0));
        equipmentList.add(new Equipment("Barbell", "Weight", 25, true, 75.0));
        equipmentList.add(new Equipment("Treadmill", "Cardio", 90, false, 200.0));
        equipmentList.add(new Equipment("Pull-up Bar", "Strength", 5, true, 30.0));
        equipmentList.add(new Equipment("Adjustable Bench", "Strength", 22, true, 80.0));
        equipmentList.add(new Equipment("Rowing Machine", "Cardio", 60, true, 150.0));
        equipmentList.add(new Equipment("Medicine Ball", "Weight", 8, true, 20.0));
        equipmentList.add(new Equipment("Foam Roller", "Recovery", 2, true, 10.0));
        equipmentList.add(new Equipment("Elliptical Trainer", "Cardio", 70, false, 180.0));
        equipmentList.add(new Equipment("Punching Bag", "Boxing", 35, true, 95.0));
        equipmentList.add(new Equipment("Resistance Bands", "Strength", 1, true, 12.0));
        equipmentList.add(new Equipment("Power Rack", "Strength", 100, false, 250.0));
        equipmentList.add(new Equipment("Battle Rope", "Cardio", 18, true, 50.0));
        equipmentList.add(new Equipment("Gym Ball", "Balance", 3, true, 18.0));
        equipmentList.add(new Equipment("Stepper", "Cardio", 35, true, 60.0));
        equipmentList.add(new Equipment("Dip Bar", "Strength", 15, true, 40.0));
        equipmentList.add(new Equipment("Ankle Weights", "Weight", 4, true, 14.0));
    }
}