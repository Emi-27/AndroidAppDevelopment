package com.groupdev.pillpall.repository;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.groupdev.pillpall.model.Medication;

@Database(entities = {Medication.class}, version = 1)
public abstract class MedicationDatabase extends RoomDatabase {

    private static MedicationDatabase instance;
    public abstract MedicationDAO medicationDAO();

    public static synchronized MedicationDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MedicationDatabase.class, "medication_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
