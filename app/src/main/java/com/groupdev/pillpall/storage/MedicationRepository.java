package com.groupdev.pillpall.storage;

import androidx.lifecycle.LiveData;

import com.groupdev.pillpall.model.Medication;

import java.util.List;

public class MedicationRepository {

    private final MedicationDAO medicationDAO;
    private static MedicationRepository instance;

    public MedicationRepository() {
        medicationDAO = MedicationDAO.getInstance();
    }

    public static MedicationRepository getInstance(){
        if(instance == null)
            instance = new MedicationRepository();
        return instance;
    }

    public LiveData<List<Medication>> getAllMedications(){
        return medicationDAO.getAllMedications();
    }

    public void addMedication(Medication medication){
        medicationDAO.addMedication(medication);
    }
}
