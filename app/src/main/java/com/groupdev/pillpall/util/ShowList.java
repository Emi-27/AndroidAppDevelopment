package com.groupdev.pillpall.util;

public class ShowList {
    private String dateTime;
    private String type;
    private double measurement;

    public ShowList(String dateTime, double measurement, String type) {
        this.dateTime = dateTime;
        this.measurement = measurement;
        this.type = type;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getMeasurement() {
        return measurement;
    }

    public void setMeasurement(double measurement) {
        this.measurement = measurement;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
