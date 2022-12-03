package com.groupdev.pillpall.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.groupdev.pillpall.model.Reminder;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReminderRepository {
    private static ReminderRepository instance;
    private final ReminderDAO reminderDao;
    private final LiveData<List<Reminder>> allReminders;
    private final ExecutorService executorService;

    private ReminderRepository(Application application) {
        PillPallDataBase database = PillPallDataBase.getInstance(application);
        reminderDao = database.reminderDAO();
        allReminders = reminderDao.getAllReminders();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized ReminderRepository getInstance(Application application) {
        if (instance == null) {
            instance = new ReminderRepository(application);
        }
        return instance;
    }

    public LiveData<List<Reminder>> getAllReminders() {
        return allReminders;
    }

    public LiveData<List<Reminder>> getRemindersByDate(int date) {
        return reminderDao.getRemindersByDate(date);
    }

    public void insertReminder(Reminder reminder) {
        executorService.execute(() -> reminderDao.insert(reminder));
    }

    public void updateReminder(Reminder reminder) {
        executorService.execute(() -> reminderDao.update(reminder));
    }

    public void deleteReminder(Reminder reminder) {
        executorService.execute(() -> reminderDao.delete(reminder));
    }

    public void deleteAllReminders() {
        executorService.execute(reminderDao::deleteAllReminders);
    }
}
