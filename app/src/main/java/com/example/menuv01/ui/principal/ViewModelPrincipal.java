package com.example.menuv01.ui.principal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelPrincipal extends ViewModel {

    private MutableLiveData<String> mText;

    public ViewModelPrincipal() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}