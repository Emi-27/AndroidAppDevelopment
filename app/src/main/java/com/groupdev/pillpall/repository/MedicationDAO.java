package com.groupdev.pillpall.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.groupdev.pillpall.model.Medication;

import java.util.List;

@Dao
public interface MedicationDAO {
    @Insert
    void insert(Medication medication);

    @Update
    void update(Medication medication);

    @Delete
    void delete(Medication medication);

    @Query("SELECT * FROM medications_table")
    LiveData<List<Medication>> getAllMedications();

    @Query("DELETE FROM medications_table")
    void deleteAllMedications();

}
