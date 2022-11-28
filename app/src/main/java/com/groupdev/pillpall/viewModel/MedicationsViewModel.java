package com.groupdev.pillpall.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MedicationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MedicationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Medications");
    }

    public LiveData<String> getText() {
        return mText;
    }
}