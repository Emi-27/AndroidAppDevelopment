package com.groupdev.pillpall.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.groupdev.pillpall.model.Medication;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MedicationRepository {

    private final MedicationDAO medicationDAO;
    private static MedicationRepository instance;
    private final LiveData<List<Medication>> allMedications;
    private final ExecutorService executorService;

    public MedicationRepository(Application application) {
        PillPallDataBase database = PillPallDataBase.getInstance(application);
        medicationDAO = database.medicationDAO();
        allMedications = medicationDAO.getAllMedications();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized MedicationRepository getInstance(Application application){
        if(instance == null){
            instance = new MedicationRepository(application);
        }
        return instance;
    }

    public LiveData<List<Medication>> getAllMedications(){
        return allMedications;
    }

    public void addMedication(Medication medication){
        executorService.execute(() -> medicationDAO.insert(medication));
    }

    public void updateMedication(Medication medication){
        executorService.execute(() -> medicationDAO.update(medication));
    }

    public void deleteMedication(Medication medication){
        executorService.execute(() -> medicationDAO.delete(medication));
    }

    public void deleteAllMedications(){
        executorService.execute(medicationDAO::deleteAllMedications);
    }
}
