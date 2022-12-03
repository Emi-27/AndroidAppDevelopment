package com.groupdev.pillpall.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.groupdev.pillpall.model.Medication;
import com.groupdev.pillpall.model.Reminder;
import com.groupdev.pillpall.repository.MedicationRepository;
import com.groupdev.pillpall.repository.ReminderRepository;

import java.util.List;


public class AddReminderViewModel extends AndroidViewModel {

    private ReminderRepository reminderRepository;
    private MedicationRepository medicationRepository;

    public AddReminderViewModel(Application app) {
        super(app);
        reminderRepository = ReminderRepository.getInstance(app);
        medicationRepository = MedicationRepository.getInstance(app);
    }

    public void AddReminder(String reminderNameMed, String notes, int quantity,
                            int time, int frequency, int date) {
        int newTime = time;
        switch (frequency) {
            case 0:
                try {
                reminderRepository.insertReminder(new Reminder(reminderNameMed,notes,quantity,
                        newTime,date,21000000,true,true,false));
                } catch (Exception e) {
                    e.printStackTrace();
                } break;
            case 1:
                for (int i = -1; i < frequency; i++) {

                try {
                    reminderRepository.insertReminder(new Reminder(reminderNameMed,notes,quantity,
                            newTime ,date,21000000,true,true,false));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    newTime += 1200;
                    if(newTime > 2400) {
                        newTime -= 2400;
                    }
                }break;
            case 2:
                for (int i = -1; i < frequency; i++) {
                    try {
                        reminderRepository.insertReminder(new Reminder(reminderNameMed,notes,quantity,
                                newTime ,date,21000000,true,true,false));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }newTime += 800;
                    if(newTime > 2400) {
                        newTime -= 2400;
                    }
                }break;
            case 3:
                for (int i = -1; i < frequency; i++) {
                    try {
                        reminderRepository.insertReminder(new Reminder(reminderNameMed,notes,quantity,
                                newTime ,date,21000000,true,true,false));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }newTime += 600;
                    if(newTime > 2400) {
                        newTime -= 2400;
                    }
                }break;
                default:
                    throw new IllegalStateException("Unexpected value: " + frequency);
        }
    }

    public LiveData<List<Medication>> getAllMedications(){
        return medicationRepository.getAllMedications();
    }
}


