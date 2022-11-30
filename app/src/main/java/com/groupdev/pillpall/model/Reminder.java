package com.groupdev.pillpall.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reminders_table")
public class Reminder {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String description;
    private String medication;
    private int quantity;
    private int frequency;
    private UnitOfTime unitOfTime;
    private String startDateTime;
    private String endDateTime;
    private boolean repeat;
    private boolean active;
    private boolean isTaken;

    public enum UnitOfTime {
        HOURS, DAYS, WEEKS
    }

    public Reminder() {
    }

    public Reminder(String name, String description, String medication, int quantity, int frequency, UnitOfTime unitOfTime, String startDateTime, String endDateTime, boolean repeat, boolean active, boolean isTaken) {
        this.name = name;
        this.description = description;
        this.medication = medication;
        this.quantity = quantity;
        this.frequency = frequency;
        this.unitOfTime = unitOfTime;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.repeat = repeat;
        this.active = active;
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

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public UnitOfTime getUnitOfTime() {
        return unitOfTime;
    }

    public void setUnitOfTime(UnitOfTime unitOfTime) {
        this.unitOfTime = unitOfTime;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
                ", medication='" + medication + '\'' +
                ", quantity=" + quantity +
                ", frequency=" + frequency +
                ", unitOfTime=" + unitOfTime +
                ", startDateTime='" + startDateTime + '\'' +
                ", endDateTime='" + endDateTime + '\'' +
                ", repeat=" + repeat +
                ", active=" + active +
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
}
