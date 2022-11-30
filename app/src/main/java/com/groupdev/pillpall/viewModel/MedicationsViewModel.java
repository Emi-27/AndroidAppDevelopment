package com.groupdev.pillpall.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.groupdev.pillpall.model.Medication;
import com.groupdev.pillpall.storage.MedicationRepository;

import java.util.List;

public class MedicationsViewModel extends ViewModel {

    private MedicationRepository medicationRepository;

    public MedicationsViewModel() {
        medicationRepository = MedicationRepository.getInstance();
    }

    public LiveData<List<Medication>> getAllMedications(){
        return medicationRepository.getAllMedications();
    }

    public void addMedication(Medication medication){
        medicationRepository.addMedication(medication);
    }
}