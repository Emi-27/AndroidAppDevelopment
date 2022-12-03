package com.groupdev.pillpall.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.groupdev.pillpall.model.Medication;
import com.groupdev.pillpall.model.Reminder;
import com.groupdev.pillpall.model.Vitals;

@Database(entities = {Medication.class, Reminder.class, Vitals.class}, version = 3)

public abstract class PillPallDataBase extends RoomDatabase {

    private static PillPallDataBase instance;

    public abstract ReminderDAO reminderDAO();
    public abstract MedicationDAO medicationDAO();
    public abstract VitalsDAO vitalsDAO();

    public static synchronized PillPallDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            PillPallDataBase.class, "PillPall_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
