package com.groupdev.pillpall.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.groupdev.pillpall.model.Reminder;
import com.groupdev.pillpall.repository.ReminderRepository;

public class AddReminderViewModel extends AndroidViewModel {

    private ReminderRepository repository;

    public AddReminderViewModel(Application app) {
        super(app);
        repository = ReminderRepository.getInstance(app);
    }


    public void AddReminder(String reminderName, String reminderTime) {
        try {
            repository.insertReminder(new Reminder(reminderName, "desciption",1,2,1000,1000, 000,true, true, true));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


