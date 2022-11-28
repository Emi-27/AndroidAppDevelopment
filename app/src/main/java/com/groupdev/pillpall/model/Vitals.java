package com.groupdev.pillpall.model;

import java.sql.Date;
import java.sql.Time;

public class Vitals {
    private long id;
    private Date date;
    private Time time;
    private int bloodPressure;
    private int heartRate;
    private double temperature;
    private int respiratoryRate;
    private int oxygenSaturation;
    private double weight;
    private double height;
    private double bmi;
    private double bloodGlucose;
    private String notes;

    public Vitals() {
    }
    
    public Vitals(long id, Date date, Time time, int bloodPressure, int heartRate, double temperature, int respiratoryRate, int oxygenSaturation, double weight, double height, double bmi, double bloodGlucose, String notes) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.temperature = temperature;
        this.respiratoryRate = respiratoryRate;
        this.oxygenSaturation = oxygenSaturation;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.bloodGlucose = bloodGlucose;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(int respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public int getOxygenSaturation() {
        return oxygenSaturation;
    }

    public void setOxygenSaturation(int oxygenSaturation) {
        this.oxygenSaturation = oxygenSaturation;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getBloodGlucose() {
        return bloodGlucose;
    }

    public void setBloodGlucose(double bloodGlucose) {
        this.bloodGlucose = bloodGlucose;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Vitals{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", bloodPressure='" + bloodPressure + '\'' +
                ", heartRate='" + heartRate + '\'' +
                ", temperature='" + temperature + '\'' +
                ", respiratoryRate='" + respiratoryRate + '\'' +
                ", oxygenSaturation='" + oxygenSaturation + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", bmi='" + bmi + '\'' +
                ", bloodGlucose='" + bloodGlucose + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vitals vitals = (Vitals) o;
        return vitals.id==(this.id);
    }
}
