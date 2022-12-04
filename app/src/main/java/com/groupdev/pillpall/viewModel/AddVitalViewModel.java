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
        if (bloodPressure.isEmpty()) {
            bloodPressure = "0";
        }
        if (heartRate.isEmpty()) {
            heartRate = "0";
        }
        if (temperature.isEmpty()) {
            temperature = "0";
        }
        if (respiratoryRate.isEmpty()) {
            respiratoryRate = "0";
        }
        if (oxygenSaturation.isEmpty()) {
            oxygenSaturation = "0";
        }
        if (weight.isEmpty()) {
            weight = "0";
        }
        if (height.isEmpty()) {
            height = "0";
        }
        if (bmi.isEmpty()) {
            bmi = "0";
        }
        if (bloodGlucose.isEmpty()) {
            bloodGlucose = "0";
        }

        try {
            repository.insertVitals(new Vitals(dateTime, Integer.parseInt(bloodPressure), Integer.parseInt(heartRate), Double.parseDouble(temperature),
                    Integer.parseInt(respiratoryRate), Integer.parseInt(oxygenSaturation), Double.parseDouble(weight), Double.parseDouble(height),
                    Double.parseDouble(bmi), Double.parseDouble(bloodGlucose), notes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}