package com.example.menuv01;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PrincipalViewModel extends AndroidViewModel {
    private PrincipalRepository mPrincipalRepository;
    private LiveData<List<Principal>> mAllPrincipal;

    public PrincipalViewModel(Application application) {
        super(application);
        mPrincipalRepository = new PrincipalRepository(application);
        mAllPrincipal = mPrincipalRepository.getAllPrincipal();    //Selecting allentradas from Repository
    }

    public LiveData<List<Principal>> getAllPrincipal() {
        return mAllPrincipal;
    }   //Get/set allentradas

    public void insertPrincipal(Principal principal) {
        mPrincipalRepository.insertPrincipal(principal);
    }      //Using the methods in the repository

    public void updatePrincipal(Principal principal) {
        mPrincipalRepository.updatePrincipal(principal);
    }

    public void deletePrincipal(Principal principal) {
        mPrincipalRepository.deletePrincipal(principal);
    }

    public void deleteAllPrincipal(Principal principal) {
        mPrincipalRepository.deleteAllPrincipal(principal);
    }

}
