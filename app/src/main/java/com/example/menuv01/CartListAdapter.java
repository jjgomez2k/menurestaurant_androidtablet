package com.example.menuv01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartListViewHolder> {
    public static ArrayList<CartItems> selecteditems = new ArrayList<>();

    //a List that will contain all entradas // Cached copy of entradas

    public CartListAdapter(ArrayList<CartItems> mySelectedItems) {
        selecteditems = mySelectedItems;
    }

    @NonNull
    @Override
    public CartListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListViewHolder holder, int position) {
        CartItems currentCartItems = selecteditems.get(position);
        Double currentItemTotal2 = Double.parseDouble(currentCartItems.getPreco()) * Double.parseDouble(currentCartItems.getQuantidade());
        Double currentItemPreco = Double.parseDouble(currentCartItems.getPreco());
        holder.itemnome.setText(currentCartItems.getNome());
        holder.itempreco.setText(String.format("%.2f", currentItemPreco));
        holder.itemtipo.setText(currentCartItems.getTipo());
        holder.itemquantidade.setText(currentCartItems.getQuantidade());
        holder.tv_total.setText(String.format("%.2f", currentItemTotal2));
        holder.button_eliminar_item.setOnClickListener(view -> {
            // Get the clicked item label
            // Remove the item on remove/button click
            selecteditems.remove(position);
            notifyDataSetChanged();
            // Show the removed item label
        });
    }

    @Override
    public int getItemCount() {
        if (selecteditems != null) {
            return selecteditems.size();
        }
        return 0;
    }

    public class CartListViewHolder extends RecyclerView.ViewHolder {
        Button button_eliminar_item;
        private TextView itemnome, itemtipo, itempreco, itemquantidade, tv_total;

        public CartListViewHolder(View itemView) {
            super(itemView);
            itemnome = itemView.findViewById(R.id.itemnome);
            itempreco = itemView.findViewById(R.id.itempreco);
            itemtipo = itemView.findViewById(R.id.itemtipo);
            itemquantidade = itemView.findViewById(R.id.itemquantidade);
            tv_total = itemView.findViewById(R.id.tv_total);
            button_eliminar_item = itemView.findViewById(R.id.chk_selectitem);
        }
    }
}
