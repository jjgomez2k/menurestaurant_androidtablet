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

public class PrincipalAdapter extends RecyclerView.Adapter<PrincipalAdapter.PrincipalViewHolder> {
    private List<Principal> principal = new ArrayList<>();
    private OnItemClickListener listener;

    //a List that will contain all principal // Cached copy of principal
    @NonNull
    @Override
    public PrincipalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.principal_item, parent, false);
        return new PrincipalViewHolder(itemView);    //Creating the ViewHolder
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull PrincipalViewHolder holder, int position) {    //Filling the ViewHolder
        Principal currentPrincipal = principal.get(position);//Get entrada(position)
        int resourceId = holder.imageViewPrincipal.getContext().getResources().getIdentifier("principaln" + position, "drawable", "com.example.menuv01");
        String currency = holder.textViewPrincipalPreco.getContext().getResources().getString(R.string.currency);
        //Method getting the id of the drawable for each entrada
        holder.textViewPrincipalId.setText(String.valueOf(currentPrincipal.getPrincipalid()));
        holder.textViewPrincipalNome.setText(currentPrincipal.getPrincipalnome());
        holder.imageViewPrincipal.setImageResource(resourceId);
        holder.textViewPrincipalDetalhe.setText(currentPrincipal.getPrincipaldetalhe());
        holder.textViewPrincipalPreco.setText(currency + String.format("%.2f", currentPrincipal.getPrincipalpreco()));
        //Setting Principal(position) to textview in recyclerview for all principal
    }

    @Override
    public int getItemCount() {             //Counting the entradas
        return principal.size();
    }

    public void setPrincipal(List<Principal> principal) {
        this.principal = principal;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void OnItemClick(Principal principal);
    }

    class PrincipalViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewPrincipal;
        private TextView textViewPrincipalId;
        private TextView textViewPrincipalNome;
        private TextView textViewPrincipalDetalhe;
        private TextView textViewPrincipalPreco;


        public PrincipalViewHolder(View itemView) {                  //Assigning Entrada to textview (Recycler\View)
            super(itemView);
            textViewPrincipalId = itemView.findViewById(R.id.principal_es_textView2_id);
            textViewPrincipalNome = itemView.findViewById(R.id.principal_es_textView2_nome);
            textViewPrincipalDetalhe = itemView.findViewById(R.id.principal_es_textView2_detalhe);
            textViewPrincipalPreco = itemView.findViewById(R.id.principal_es_textView2_preco);
            imageViewPrincipal = itemView.findViewById(R.id.principal_es_imageView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.OnItemClick(principal.get(position));
                    }
                }
            });
        }
    }
}
