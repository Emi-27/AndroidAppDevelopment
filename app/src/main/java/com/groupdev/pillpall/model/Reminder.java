package com.groupdev.pillpall.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Comparator;

@Entity(tableName = "reminders_table")
public class Reminder implements Comparable<Reminder> {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String description;
    private long medicationID;
    private int quantity;
    private int time;
    private int startDateTime;
    private int endDateTime;
    private boolean isRepeat;
    private boolean isActive;
    private boolean isTaken;

    public Reminder() {
    }

    public Reminder(String name, String description, long medicationID, int quantity, int time, int startDateTime, int endDateTime, boolean isRepeat, boolean isActive, boolean isTaken) {
        this.name = name;
        this.description = description;
        this.medicationID = medicationID;
        this.quantity = quantity;
        this.time = time;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getMedicationID() {
        return medicationID;
    }

    public void setMedicationID(long medicationID) {
        this.medicationID = medicationID;
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

    public int getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(int startDateTime) {
        this.startDateTime = startDateTime;
    }

    public int getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(int endDateTime) {
        this.endDateTime = endDateTime;
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
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", medicationID=" + medicationID +
                ", quantity=" + quantity +
                ", time=" + time +
                ", startDateTime='" + startDateTime + '\'' +
                ", endDateTime='" + endDateTime + '\'' +
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
