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

public class SobremesasAdapter extends RecyclerView.Adapter<SobremesasAdapter.SobremesasViewHolder> {

    private List<Sobremesas> sobremesas = new ArrayList<>();
    private SobremesasAdapter.OnItemClickListener listener;

    //a List that will contain all principal // Cached copy of principal
    @NonNull
    @Override
    public SobremesasAdapter.SobremesasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sobremesas_item, parent, false);
        return new SobremesasAdapter.SobremesasViewHolder(itemView);    //Creating the ViewHolder
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull SobremesasAdapter.SobremesasViewHolder holder, int position) {    //Filling the ViewHolder
        Sobremesas currentSobremesas = sobremesas.get(position);//Get entrada(position)
        int resourceId = holder.imageViewSobremesas.getContext().getResources().getIdentifier("sobremesasn" + position, "drawable", "com.example.menuv01");
        String currency = holder.textViewSobremesasPreco.getContext().getResources().getString(R.string.currency);

        //Method getting the id of the drawable for each entrada
        holder.textViewSobremesasId.setText(String.valueOf(currentSobremesas.getSobremesasid()));
        holder.textViewSobremesasNome.setText(currentSobremesas.getSobremesasnome());
        holder.imageViewSobremesas.setImageResource(resourceId);
        holder.textViewSobremesasDetalhe.setText(currentSobremesas.getSobremesasdetalhe());
        holder.textViewSobremesasPreco.setText(currency + String.format("%.2f", currentSobremesas.getSobremesaspreco()));
        //Setting Principal(position) to textview in recyclerview for all principal
    }

    @Override
    public int getItemCount() {             //Counting the entradas
        return sobremesas.size();
    }

    public void setSobremesas(List<Sobremesas> sobremesas) {
        this.sobremesas = sobremesas;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(SobremesasAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void OnItemClick(Sobremesas sobremesas);
    }

    class SobremesasViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewSobremesas;
        private TextView textViewSobremesasId;
        private TextView textViewSobremesasNome;
        private TextView textViewSobremesasDetalhe;
        private TextView textViewSobremesasPreco;


        public SobremesasViewHolder(View itemView) {                  //Assigning Entrada to textview (Recycler\View)
            super(itemView);
            textViewSobremesasId = itemView.findViewById(R.id.sobremesas_es_textView2_id);
            textViewSobremesasNome = itemView.findViewById(R.id.sobremesas_es_textView2_nome);
            textViewSobremesasDetalhe = itemView.findViewById(R.id.sobremesas_es_textView2_detalhe);
            textViewSobremesasPreco = itemView.findViewById(R.id.sobremesas_es_textView2_preco);
            imageViewSobremesas = itemView.findViewById(R.id.sobremesas_es_imageView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.OnItemClick(sobremesas.get(position));
                    }
                }
            });
        }
    }
}
