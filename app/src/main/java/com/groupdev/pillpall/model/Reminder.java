package com.groupdev.pillpall.model;

import java.sql.Time;
import java.util.Date;

public class Reminder {

    private long id;
    private String name;
    private String description;
    private Medication medication;
    private int quantity;
    private int frequency;
    private UnitOfTime unitOfTime;
    private String startTime;
    private Date startDate;
    private boolean repeat;
    private boolean active;
    private boolean isTaken;

    private enum UnitOfTime {
        HOURS, DAYS, WEEKS, MONTHS, YEARS
    }

    public Reminder() {
    }

    public Reminder(String name, String startTime ){
        this.name = name;
        this.startTime = startTime;

    }

    public Reminder(long id, String name, String description, Medication medication, int quantity, int frequency, UnitOfTime unitOfTime, String startTime, Date startDate, boolean repeat, boolean active,boolean isTaken) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.medication = medication;
        this.quantity = quantity;
        this.frequency = frequency;
        this.unitOfTime = unitOfTime;
        this.startTime = startTime;
        this.startDate = startDate;
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

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
                ", medication=" + medication +
                ", quantity=" + quantity +
                ", frequency=" + frequency +
                ", unitOfTime=" + unitOfTime +
                ", startTime=" + startTime +
                ", startDate=" + startDate +
                ", repeat=" + repeat +
                ", active=" + active +
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
