package com.groupdev.pillpall.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.groupdev.pillpall.model.Reminder;
import com.groupdev.pillpall.repository.ReminderRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class HomeViewModel extends AndroidViewModel {

    private final ReminderRepository repository;

    public HomeViewModel(Application app) {
        super(app);
        repository = ReminderRepository.getInstance(app);
    }

    public LiveData<List<Reminder>> getAllReminders() {
        return repository.getAllReminders();
    }

    public LiveData<List<Reminder>> getRemindersByDate(int date) {
        LiveData<List<Reminder>> reminders = repository.getRemindersByDate(date);
        if(reminders != null){
            Collections.sort(Objects.requireNonNull(reminders.getValue()));
        }
        return reminders;
    }

    public void delete(final Reminder reminder) {
        repository.deleteReminder(reminder);
    }

}