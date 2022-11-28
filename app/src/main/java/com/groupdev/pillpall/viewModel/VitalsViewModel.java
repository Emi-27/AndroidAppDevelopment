package com.groupdev.pillpall.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VitalsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public VitalsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Vitals");
    }

    public LiveData<String> getText() {
        return mText;
    }
}