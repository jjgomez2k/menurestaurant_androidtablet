package com.example.menuv01.ui.entradas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menuv01.EntradasAdapter;
import com.example.menuv01.EntradasViewModel;
import com.example.menuv01.R;

public class EntradasSecondFragment extends Fragment {
    public static final int DETAIL_ENTRADA_REQUEST = 1;
    private EntradasViewModel mEntradasViewModel;

    //Calling the EntradasViewModel
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entradas, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerviewEntradas);      //Creating the RecyclerView1 of entradas
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));      //Setting the layout
        final EntradasAdapter adapter1 = new EntradasAdapter();
        recyclerView.setAdapter(adapter1);        //Setting the adapter containing the recyclerview1?

        mEntradasViewModel = ViewModelProviders.of(this).get(EntradasViewModel.class);
        //Observe the entradas
        mEntradasViewModel.getAllEntradas().observe(getViewLifecycleOwner(), adapter1::setEntradas);    //Observing the adapter and the function setEntradas to show them

        //Button click to go back to HomeFragment, view1= a variable that calls the navController in navhostfragment
        view.findViewById(R.id.button_home_second).setOnClickListener(view1 -> NavHostFragment.findNavController(EntradasSecondFragment.this)
                .navigate(R.id.action_HomeSecondFragment_to_HomeFragment)); //navigates to homeFragment
        adapter1.setOnItemClickListener(entradas -> {
            Intent intent = new Intent(getActivity(), EntradasSecondFragmentDetail.class);
            int imageViewId = entradas.getEntradasid() - 1;
            int resourceId = getContext().getResources().getIdentifier("entradan" + imageViewId, "drawable", "com.example.menuv01");
            intent.putExtra(EntradasSecondFragmentDetail.EXTRA_ID, String.valueOf(entradas.getEntradasid()));   //Sends the current item selected through the EXTRA zZZZ
            intent.putExtra(EntradasSecondFragmentDetail.EXTRA_NOME, entradas.getEntradasnome());
            intent.putExtra(EntradasSecondFragmentDetail.EXTRA_DETALHE, entradas.getEntradasdetalhe());
            intent.putExtra(EntradasSecondFragmentDetail.EXTRA_PRECO, String.valueOf(entradas.getEntradaspreco()));
            intent.putExtra(EntradasSecondFragmentDetail.EXTRA_IMAGEVIEW, resourceId);
            startActivityForResult(intent, DETAIL_ENTRADA_REQUEST);
        });
    }
}