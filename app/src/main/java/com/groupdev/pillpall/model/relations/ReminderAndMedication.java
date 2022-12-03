package com.groupdev.pillpall.model.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.groupdev.pillpall.model.Medication;
import com.groupdev.pillpall.model.Reminder;

public class ReminderAndMedication {
    @Embedded public Medication medication;
    @Relation(
            parentColumn = "id",
            entityColumn = "medicationId"
    ) public Reminder reminder;

}
