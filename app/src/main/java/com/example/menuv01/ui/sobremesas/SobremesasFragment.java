package com.example.menuv01.ui.sobremesas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menuv01.R;
import com.example.menuv01.SobremesasAdapter;
import com.example.menuv01.SobremesasViewModel;

public class SobremesasFragment extends Fragment {
    public static final int DETAIL_SOBREMESAS_REQUEST = 1;
    private SobremesasViewModel mSobremesasViewModel;

    //Calling the ViewModel
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sobremesas, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerviewSobremesas);      //Creating the RecyclerView1 of entradas
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));      //Setting the layout
        final SobremesasAdapter adapter2 = new SobremesasAdapter();
        recyclerView.setAdapter(adapter2);        //Setting the adapter containing the recyclerview1?

        mSobremesasViewModel = ViewModelProviders.of(this).get(SobremesasViewModel.class);
        //Observe the entradas
        mSobremesasViewModel.getAllSobremesas().observe(getViewLifecycleOwner(), adapter2::setSobremesas);    //Observing the adapter and the function setEntradas to show them

        //Button click to go back to HomeFragment, view1= a variable that calls the navController in navhostfragment
      /*  view.findViewById(R.id.button_principal_voltar).setOnClickListener(view1 -> NavHostFragment.findNavController(GalleryFragment.this)
                .navigate(R.id.action_galleryFragmentDetail_to_nav_gallery)); //navigates to homeFragment*/

        adapter2.setOnItemClickListener(sobremesas -> {
            Intent intent = new Intent(getActivity(), SobremesasFragmentDetail.class);
            int imageViewId = sobremesas.getSobremesasid() - 1;
            int resourceId = getContext().getResources().getIdentifier("sobremesasn" + imageViewId, "drawable", "com.example.menuv01");
            intent.putExtra(SobremesasFragmentDetail.EXTRA_ID, String.valueOf(sobremesas.getSobremesasid()));   //Sends the current item selected through the EXTRA with intent
            intent.putExtra(SobremesasFragmentDetail.EXTRA_NOME, sobremesas.getSobremesasnome());
            intent.putExtra(SobremesasFragmentDetail.EXTRA_DETALHE, sobremesas.getSobremesasdetalhe());
            intent.putExtra(SobremesasFragmentDetail.EXTRA_PRECO, String.valueOf(sobremesas.getSobremesaspreco()));
            intent.putExtra(SobremesasFragmentDetail.EXTRA_IMAGEVIEW, resourceId);
            startActivityForResult(intent, DETAIL_SOBREMESAS_REQUEST);
        });
    }
}