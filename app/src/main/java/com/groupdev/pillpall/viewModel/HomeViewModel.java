package com.groupdev.pillpall.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.groupdev.pillpall.model.Reminder;
import com.groupdev.pillpall.repository.ReminderRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private final ReminderRepository repository;

    public HomeViewModel(Application app) {
        super(app);
        repository = ReminderRepository.getInstance(app);
    }

    public LiveData<List<Reminder>> getAllReminders() {
        return repository.getAllReminders();
    }

    public void delete(final Reminder reminder) {
        repository.deleteReminder(reminder);
    }

}