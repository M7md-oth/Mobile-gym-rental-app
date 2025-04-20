package com.example.gymrentalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class OrderHistoryAdapter extends ArrayAdapter<CartItem> {

    private final Context context;
    private final List<CartItem> orderItems;

    public OrderHistoryAdapter(@NonNull Context context, List<CartItem> orderItems) {
        super(context, 0, orderItems);
        this.context = context;
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CartItem item = orderItems.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        }

        TextView nameText = convertView.findViewById(R.id.orderItemName);
        TextView detailsText = convertView.findViewById(R.id.orderItemDetails);

        nameText.setText(item.getEquipment().getName());
        detailsText.setText("Quantity: x" + item.getQuantity() + "   Price: $" + item.getEquipment().getPrice());

        return convertView;
    }
}