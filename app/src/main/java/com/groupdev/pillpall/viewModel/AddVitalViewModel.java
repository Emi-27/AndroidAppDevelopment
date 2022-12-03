package com.groupdev.pillpall.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.groupdev.pillpall.model.Vitals;
import com.groupdev.pillpall.repository.VitalsRepository;

public class AddVitalViewModel extends AndroidViewModel {
    private VitalsRepository repository;

    public AddVitalViewModel(@NonNull Application app) {
        super(app);
        repository = VitalsRepository.getInstance(app);
    }

    public void addVital(String dateTime, String bloodPressure, String heartRate, String temperature,
                         String respiratoryRate, String oxygenSaturation, String weight, String height,
                         String bmi, String bloodGlucose, String notes) {
        try {
            repository.insertVitals(new Vitals(dateTime, Integer.parseInt(bloodPressure), Integer.parseInt(heartRate), Double.parseDouble(temperature),
                    Integer.parseInt(respiratoryRate), Integer.parseInt(oxygenSaturation), Double.parseDouble(weight), Double.parseDouble(height),
                    Double.parseDouble(bmi), Double.parseDouble(bloodGlucose), notes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}