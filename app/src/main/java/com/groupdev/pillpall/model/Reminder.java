package com.groupdev.pillpall.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "reminders_table")
public class Reminder implements Comparable<Reminder> , Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String medicationName;
    private String description;
    private int quantity;
    private int time;
    private int startDate;
    private int endDate;
    private boolean isRepeat;
    private boolean isActive;
    private boolean isTaken;

    public Reminder() {
    }

    @Ignore
    public Reminder(String name, String description, int quantity, int time, int startDate, int endDate, boolean isRepeat, boolean isActive, boolean isTaken) {
        this.medicationName = name;
        this.description = description;
        this.quantity = quantity;
        this.time = time;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isRepeat = isRepeat;
        this.isActive = isActive;
        this.isTaken = isTaken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return medicationName;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public void setRepeat(boolean repeat) {
        isRepeat = repeat;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + id +
                ", name='" + medicationName + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", time=" + time +
                ", startDateTime='" + startDate + '\'' +
                ", endDateTime='" + endDate + '\'' +
                ", isRepeat=" + isRepeat +
                ", isActive=" + isActive +
                ", isTaken=" + isTaken +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reminder reminder = (Reminder) o;
        return reminder.id==(this.id);
    }

    @Override
    public int compareTo(Reminder u) {
        if (getTime() == 0 || u.getTime() == 0) {
            return 0;
        }
        return this.getTime() - (u.getTime());
    }
}
