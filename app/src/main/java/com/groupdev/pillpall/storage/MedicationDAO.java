package com.groupdev.pillpall.storage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.groupdev.pillpall.model.Medication;

import java.util.ArrayList;
import java.util.List;

public class MedicationDAO {

    private final MutableLiveData<List<Medication>> allMedications;
    private static MedicationDAO instance;

    private MedicationDAO() {
        allMedications = new MutableLiveData<>();
        List<Medication> medications = new ArrayList<>();
        medications.add(new Medication(1,"Panodil", 2, 100, 20,
                Medication.Form.TABLET, Medication.UnitOfDosage.MG, "","", ""));
        medications.add(new Medication(1,"Panodil", 2, 100, 20,
                Medication.Form.TABLET, Medication.UnitOfDosage.MG, "","", ""));
        medications.add(new Medication(1,"Panodil", 2, 100, 20,
                Medication.Form.TABLET, Medication.UnitOfDosage.MG, "","", ""));
        medications.add(new Medication(1,"Panodil", 2, 100, 20,
                Medication.Form.TABLET, Medication.UnitOfDosage.MG, "","", ""));
        allMedications.setValue(medications);
    }

    public static MedicationDAO getInstance(){
        if(instance == null){
            instance = new MedicationDAO();
        }
        return instance;
    }

    public LiveData<List<Medication>> getAllMedications(){
        return allMedications;
    }

    public void addMedication(Medication medication){
        List<Medication> currentMedications = allMedications.getValue();
        currentMedications.add(medication);
        allMedications.setValue(currentMedications);
    }
}
