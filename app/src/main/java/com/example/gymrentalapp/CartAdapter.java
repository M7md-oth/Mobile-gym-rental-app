package com.example.gymrentalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class CartAdapter extends ArrayAdapter<CartItem> {

    private final List<CartItem> cartList;
    private final SharedPrefManager sharedPrefManager;

    public CartAdapter(Context context, List<CartItem> list) {
        super(context, 0, list);
        this.cartList = list;
        this.sharedPrefManager = new SharedPrefManager(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CartItem cartItem = cartList.get(position);
        Equipment equipment = cartItem.getEquipment();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_item, parent, false);
        }

        TextView itemText = convertView.findViewById(R.id.cartItemText);
        ImageButton removeBtn = convertView.findViewById(R.id.removeButton);

        if (equipment != null) {
            itemText.setText(equipment.getName() + " - " + equipment.getType() +
                    " (" + equipment.getWeight() + "kg) x" + cartItem.getQuantity());
        } else {
            itemText.setText("Unknown Item x" + cartItem.getQuantity());
        }

        removeBtn.setOnClickListener(v -> {
            cartList.remove(position);
            sharedPrefManager.saveCart(cartList);
            notifyDataSetChanged();
        });

        return convertView;
    }
}

