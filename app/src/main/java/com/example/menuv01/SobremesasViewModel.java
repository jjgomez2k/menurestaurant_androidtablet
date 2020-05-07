package com.example.menuv01;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SobremesasViewModel extends AndroidViewModel {
    private SobremesasRepository mSobremesasRepository;
    private LiveData<List<Sobremesas>> mAllSobremesas;

    public SobremesasViewModel(Application application) {
        super(application);
        mSobremesasRepository = new SobremesasRepository(application);
        mAllSobremesas = mSobremesasRepository.getAllSobremesas();
    }

    public LiveData<List<Sobremesas>> getAllSobremesas() {
        return mAllSobremesas;
    }

    public void insertSobremesas(Sobremesas sobremesas) {
        mSobremesasRepository.insertSobremesas(sobremesas);
    }

    public void updateSobremesas(Sobremesas sobremesas) {
        mSobremesasRepository.updateSobremesas(sobremesas);
    }

    public void deleteSobremesas(Sobremesas sobremesas) {
        mSobremesasRepository.deleteSobremesas(sobremesas);
    }

    public void deleteAllSobremesas(Sobremesas sobremesas) {
        mSobremesasRepository.deleteAllSobremesas(sobremesas);
    }
}
