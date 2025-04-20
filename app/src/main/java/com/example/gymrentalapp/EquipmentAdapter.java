package com.example.gymrentalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder> {

    private final Context context;
    private final List<Equipment> eqList;

    public EquipmentAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.eqList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, typeText, weightText, priceText, availabilityText, quantityText;
        Button addToCartButton, increaseQtyButton, decreaseQtyButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.equipmentName);
            typeText = itemView.findViewById(R.id.equipmentType);
            weightText = itemView.findViewById(R.id.equipmentWeight);
            priceText = itemView.findViewById(R.id.equipmentPrice);
            availabilityText = itemView.findViewById(R.id.equipmentAvailability);
            quantityText = itemView.findViewById(R.id.quantityText);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
            increaseQtyButton = itemView.findViewById(R.id.increaseQtyButton);
            decreaseQtyButton = itemView.findViewById(R.id.decreaseQtyButton);
        }
    }

    @Override
    public EquipmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.equipment_item, parent, false);
        return new EquipmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EquipmentAdapter.ViewHolder holder, int position) {
        Equipment equipment = eqList.get(position);

        holder.nameText.setText(equipment.getName());
        holder.typeText.setText("Type: " + equipment.getType());
        holder.weightText.setText("Weight: " + equipment.getWeight() + " kg");
        holder.priceText.setText("Price: $" + equipment.getPrice());
        holder.availabilityText.setText(equipment.isAvailable() ? "Available" : "Not Available");
        holder.availabilityText.setTextColor(equipment.isAvailable() ? 0xFF4CAF50 : 0xFFF44336);

        final int[] quantity = {0};
        holder.quantityText.setText(String.valueOf(quantity[0]));

        holder.increaseQtyButton.setOnClickListener(v -> {
            quantity[0]++;
            holder.quantityText.setText(String.valueOf(quantity[0]));
        });

        holder.decreaseQtyButton.setOnClickListener(v -> {
            if (quantity[0] > 0) {
                quantity[0]--;
                holder.quantityText.setText(String.valueOf(quantity[0]));
            }
        });

        holder.addToCartButton.setOnClickListener(v -> {
            if (!equipment.isAvailable()) {
                Toast.makeText(context, "Item not available", Toast.LENGTH_SHORT).show();
                return;
            }

            if (quantity[0] == 0) {
                Toast.makeText(context, "Please select a quantity", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPrefManager sharedPrefManager = new SharedPrefManager(context);
            sharedPrefManager.addToCart(equipment, quantity[0]);

            Toast.makeText(context, quantity[0] + " x " + equipment.getName() + " added to cart", Toast.LENGTH_SHORT).show();

            quantity[0] = 0;
            holder.quantityText.setText("0");
        });
    }

    @Override
    public int getItemCount() {
        return eqList.size();
    }
}
