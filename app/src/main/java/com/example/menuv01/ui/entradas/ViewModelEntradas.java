package com.example.menuv01.ui.entradas;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.menuv01.Entradas;
import com.example.menuv01.EntradasRepository;


public class ViewModelEntradas extends AndroidViewModel {
    private EntradasRepository mEntradasRepository;
    private MutableLiveData<String> mEntradas;

    public ViewModelEntradas(Application application) {
        super(application);
        mEntradas = new MutableLiveData<>();
    }

    public void insertEntradas(Entradas entradas) {
        mEntradasRepository.insertEntrada(entradas);
    }

    public LiveData<String> getText() {
        return mEntradas;
    }
}