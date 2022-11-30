package com.groupdev.pillpall.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.groupdev.pillpall.model.Vitals;

@Database(entities = {Vitals.class}, version = 1)
public abstract class VitalsDatabase extends RoomDatabase {

    private static VitalsDatabase instance;
    public abstract VitalsDao vitalsDao();

    public static synchronized VitalsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            VitalsDatabase.class, "vitals_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
