package com.groupdev.pillpall.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.groupdev.pillpall.model.Vitals;

import java.util.List;

@Dao
public interface VitalsDao {
    @Insert
    void insert(Vitals vitals);

    @Update
    void update(Vitals vitals);

    @Delete
    void delete(Vitals vitals);

    @Query("SELECT * FROM vitals_table")
    LiveData<List<Vitals>> getAllVitals();

    @Query("DELETE FROM vitals_table")
    void deleteAllVitals();
}
