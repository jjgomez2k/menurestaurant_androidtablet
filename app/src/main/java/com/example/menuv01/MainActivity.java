package com.example.menuv01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EntradasViewModel mEntradasViewModel;
    private PrincipalViewModel mPrincipalViewModel;
    private SobremesasViewModel mSobremesasViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);


    }
}
