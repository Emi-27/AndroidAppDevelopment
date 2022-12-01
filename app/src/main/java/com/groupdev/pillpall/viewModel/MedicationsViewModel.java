package com.groupdev.pillpall.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.groupdev.pillpall.model.Medication;
import com.groupdev.pillpall.repository.MedicationRepository;

import java.util.List;

public class MedicationsViewModel extends AndroidViewModel {

    private MedicationRepository medicationRepository;

    public MedicationsViewModel(Application application) {
        super(application);
        medicationRepository = MedicationRepository.getInstance(application);
    }

    public LiveData<List<Medication>> getAllMedications(){
        return medicationRepository.getAllMedications();
    }

    public void addMedication(Medication medication){
        medicationRepository.addMedication(medication);
    }

}