package com.example.menuv01;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EntradasAdapter extends RecyclerView.Adapter<EntradasAdapter.EntradasViewHolder> {
    private List<Entradas> entradas = new ArrayList<>();
    private OnItemClickListener listener;

    //a List that will contain all entradas // Cached copy of entradas
    @NonNull
    @Override
    public EntradasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.entradas_item, parent, false);
        return new EntradasViewHolder(itemView);    //Creating the ViewHolder
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull EntradasViewHolder holder, int position) {    //Filling the ViewHolder
        Entradas currentEntradas = entradas.get(position);//Get entrada(position)
        int resourceId = holder.imageViewEntradas.getContext().getResources().getIdentifier("entradan" + position, "drawable", "com.example.menuv01");
        String currency = holder.textViewEntradasPreco.getContext().getResources().getString(R.string.currency);
        //Method getting the id of the drawable for each entrada
        holder.textViewEntradasId.setText(String.valueOf(currentEntradas.getEntradasid()));
        holder.textViewEntradasNome.setText(currentEntradas.getEntradasnome());
        holder.imageViewEntradas.setImageResource(resourceId);
        holder.textViewEntradasDetalhe.setText(currentEntradas.getEntradasdetalhe());
        holder.textViewEntradasPreco.setText(currency + String.format("%.2f", currentEntradas.getEntradaspreco()));
        //Setting Entrada(position) to textview in recyclerview for all entradas
    }

    @Override
    public int getItemCount() {             //Counting the entradas
        return entradas.size();
    }

    public void setEntradas(List<Entradas> entradas) {
        this.entradas = entradas;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void OnItemClick(Entradas entradas);
    }

    class EntradasViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewEntradas;
        private TextView textViewEntradasId;
        private TextView textViewEntradasNome;
        private TextView textViewEntradasDetalhe;
        private TextView textViewEntradasPreco;


        public EntradasViewHolder(View itemView) {                  //Assigning Entrada to textview (Recycler\View)
            super(itemView);
            textViewEntradasId = itemView.findViewById(R.id.entrada_es_textView2_id);
            textViewEntradasNome = itemView.findViewById(R.id.entrada_es_textView2_nome);
            textViewEntradasDetalhe = itemView.findViewById(R.id.entrada_es_textView2_detalhe);
            textViewEntradasPreco = itemView.findViewById(R.id.entrada_es_textView2_preco);
            imageViewEntradas = itemView.findViewById(R.id.entrada_es_imageView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.OnItemClick(entradas.get(position));
                    }
                }
            });
        }
    }
}
