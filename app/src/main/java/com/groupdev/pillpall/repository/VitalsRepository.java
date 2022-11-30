package com.groupdev.pillpall.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.groupdev.pillpall.model.Vitals;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VitalsRepository {
    private static VitalsRepository instance;
    private final VitalsDao vitalsDao;
    private final LiveData<List<Vitals>> allVitals;
    private final ExecutorService executorService;

    private VitalsRepository(Application application) {
        VitalsDatabase database = VitalsDatabase.getInstance(application);
        vitalsDao = database.vitalsDao();
        allVitals = vitalsDao.getAllVitals();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized VitalsRepository getInstance(Application application) {
        if (instance == null) {
            instance = new VitalsRepository(application);
        }
        return instance;
    }

    public LiveData<List<Vitals>> getAllVitals() {
        return allVitals;
    }

    public void insertVitals(Vitals vitals) {
        executorService.execute(() -> vitalsDao.insert(vitals));
    }

    public void updateReminder(Vitals vitals) {
        executorService.execute(() -> vitalsDao.update(vitals));
    }

    public void deleteReminder(Vitals vitals) {
        executorService.execute(() -> vitalsDao.delete(vitals));
    }

    public void deleteAllReminders() {
        executorService.execute(vitalsDao::deleteAllVitals);
    }
}
