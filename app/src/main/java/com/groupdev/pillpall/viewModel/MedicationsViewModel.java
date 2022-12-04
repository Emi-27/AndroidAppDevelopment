package com.groupdev.pillpall.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.groupdev.pillpall.model.Medication;
import com.groupdev.pillpall.repository.MedicationRepository;
import com.groupdev.pillpall.repository.ReminderRepository;

import java.util.List;

public class MedicationsViewModel extends AndroidViewModel {

    private MedicationRepository medicationRepository;
    private ReminderRepository reminderRepository;


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