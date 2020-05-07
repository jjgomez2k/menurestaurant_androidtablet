package com.example.menuv01;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EntradasViewModel extends AndroidViewModel {
    private EntradasRepository mEntradasRepository;
    private LiveData<List<Entradas>> mAllEntradas;

    public EntradasViewModel(Application application) {
        super(application);
        mEntradasRepository = new EntradasRepository(application);
        mAllEntradas = mEntradasRepository.getAllEntradas();    //Selecting allentradas from Repository
    }

    public LiveData<List<Entradas>> getAllEntradas() {
        return mAllEntradas;
    }   //Get/set allentradas

    public void insertEntradas(Entradas entradas) {
        mEntradasRepository.insertEntrada(entradas);
    }      //Using the methods in the repository

    public void updateEntradas(Entradas entradas) {
        mEntradasRepository.updateEntrada(entradas);
    }

    public void deleteEntradas(Entradas entradas) {
        mEntradasRepository.deleteEntrada(entradas);
    }

    public void deleteAllEntradas(Entradas entradas) {
        mEntradasRepository.deleteAllEntradas(entradas);
    }

}
