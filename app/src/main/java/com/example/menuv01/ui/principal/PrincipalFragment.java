package com.example.menuv01.ui.principal;

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

import com.example.menuv01.PrincipalAdapter;
import com.example.menuv01.PrincipalViewModel;
import com.example.menuv01.R;

public class PrincipalFragment extends Fragment {
    public static final int DETAIL_PRINCIPAL_REQUEST = 1;
    private PrincipalViewModel mPrincipalViewModel;

    //Calling the EntradasViewModel
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_principal, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerviewPrincipal);      //Creating the RecyclerView1 of entradas
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));      //Setting the layout
        final PrincipalAdapter adapter2 = new PrincipalAdapter();
        recyclerView.setAdapter(adapter2);        //Setting the adapter containing the recyclerview1?

        mPrincipalViewModel = ViewModelProviders.of(this).get(PrincipalViewModel.class);
        //Observe the entradas
        mPrincipalViewModel.getAllPrincipal().observe(getViewLifecycleOwner(), adapter2::setPrincipal);    //Observing the adapter and the function setEntradas to show them

        //Button click to go back to HomeFragment, view1= a variable that calls the navController in navhostfragment
      /*  view.findViewById(R.id.button_principal_voltar).setOnClickListener(view1 -> NavHostFragment.findNavController(GalleryFragment.this)
                .navigate(R.id.action_galleryFragmentDetail_to_nav_gallery)); //navigates to homeFragment*/

        adapter2.setOnItemClickListener(principal -> {
            Intent intent = new Intent(getActivity(), PrincipalFragmentDetail.class);
            int imageViewId = principal.getPrincipalid() - 1;
            int resourceId = getContext().getResources().getIdentifier("principaln" + imageViewId, "drawable", "com.example.menuv01");
            intent.putExtra(PrincipalFragmentDetail.EXTRA_ID, String.valueOf(principal.getPrincipalid()));   //Sends the current item selected through the EXTRA with intent
            intent.putExtra(PrincipalFragmentDetail.EXTRA_NOME, principal.getPrincipalnome());
            intent.putExtra(PrincipalFragmentDetail.EXTRA_DETALHE, principal.getPrincipaldetalhe());
            intent.putExtra(PrincipalFragmentDetail.EXTRA_PRECO, String.valueOf(principal.getPrincipalpreco()));
            intent.putExtra(PrincipalFragmentDetail.EXTRA_IMAGEVIEW, resourceId);
            startActivityForResult(intent, DETAIL_PRINCIPAL_REQUEST);
        });
    }
}