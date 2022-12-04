package com.groupdev.pillpall.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.groupdev.pillpall.model.Reminder;

import java.util.List;

@Dao
public interface ReminderDAO {
    @Insert
    void insert(Reminder reminder);

    @Update
    void update(Reminder reminder);

    @Query("DELETE FROM reminders_table WHERE id = :id")
    void delete(long id);

    @Query("SELECT * FROM reminders_table")
    LiveData<List<Reminder>> getAllReminders();

    @Query("DELETE FROM reminders_table")
    void deleteAllReminders();

    @Query("SELECT * FROM reminders_table WHERE startDate <= :date AND endDate >= :date")
    LiveData<List<Reminder>> getRemindersByDate(int date);

    @Query("SELECT * FROM reminders_table WHERE id = :id")
    LiveData<Reminder> getReminderById(long id);
}
