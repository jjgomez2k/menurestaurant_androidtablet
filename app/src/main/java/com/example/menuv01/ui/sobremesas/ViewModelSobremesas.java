package com.example.menuv01.ui.sobremesas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelSobremesas extends ViewModel {

    private MutableLiveData<String> mText;

    public ViewModelSobremesas() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}