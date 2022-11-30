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
public interface ReminderDao {
    @Insert
    void insert(Reminder reminder);

    @Update
    void update(Reminder reminder);

    @Delete
    void delete(Reminder reminder);

    @Query("SELECT * FROM reminders_table")
    LiveData<List<Reminder>> getAllReminders();

    @Query("DELETE FROM reminders_table")
    void deleteAllReminders();
}