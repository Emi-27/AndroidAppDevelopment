package com.groupdev.pillpall.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.groupdev.pillpall.model.Vitals;
import com.groupdev.pillpall.repository.VitalsRepository;
import com.groupdev.pillpall.util.ShowList;

import java.util.List;

public class VitalsViewModel extends AndroidViewModel {

    private final VitalsRepository repository;


    public VitalsViewModel(Application app) {
        super(app);
        repository = VitalsRepository.getInstance(app);
    }

    public LiveData<List<Vitals>> getVitals() {
        return repository.getAllVitals();
    }

    public List<ShowList> getSpecificVitals(String type) {
        List<Vitals> allVitals = repository.getAllVitals().getValue();
        List<ShowList> specificVitals = null;
        List<ShowList> specificTypeVitals = null;
        for (int i = 0; i < allVitals.size() ; i++) {
            if (allVitals.get(i).getBloodGlucose()!= 0) {
                specificVitals.add(new ShowList(allVitals.get(i).getDateTime(), allVitals.get(i).getBloodPressure(), "BloodPressure"));
            }
            if (allVitals.get(i).getBloodPressure()!= 0) {
                specificVitals.add(new ShowList(allVitals.get(i).getDateTime(), allVitals.get(i).getBloodGlucose(), "BloodGlucose"));
            }
            if (allVitals.get(i).getHeartRate()!= 0) {
                specificVitals.add(new ShowList(allVitals.get(i).getDateTime(), allVitals.get(i).getHeartRate(), "HeartRate"));
            }
            if (allVitals.get(i).getOxygenSaturation()!= 0) {
                specificVitals.add(new ShowList(allVitals.get(i).getDateTime(), allVitals.get(i).getOxygenSaturation(), "OxygenSaturation"));
            }
            if (allVitals.get(i).getTemperature()!= 0) {
                specificVitals.add(new ShowList(allVitals.get(i).getDateTime(), allVitals.get(i).getTemperature(), "Temperature"));
            }
            if (allVitals.get(i).getWeight()!= 0) {
                specificVitals.add(new ShowList(allVitals.get(i).getDateTime(), allVitals.get(i).getWeight(), "Weight"));
            }
            if (allVitals.get(i).getHeight()!= 0) {
                specificVitals.add(new ShowList(allVitals.get(i).getDateTime(), allVitals.get(i).getHeight(), "Height"));
            }
            if (allVitals.get(i).getBmi()!= 0) {
                specificVitals.add(new ShowList(allVitals.get(i).getDateTime(), allVitals.get(i).getBmi(), "BMI"));
            }
            if (allVitals.get(i).getRespiratoryRate()!= 0) {
                specificVitals.add(new ShowList(allVitals.get(i).getDateTime(), allVitals.get(i).getRespiratoryRate(), "RespiratoryRate"));
            }
        }

        for (int i = 0; i < specificVitals.size() ; i++) {
            if (specificVitals.get(i).getType().equals(type)) {
                specificTypeVitals.add(specificVitals.get(i));
            }
        }
        return specificTypeVitals;
    }
}