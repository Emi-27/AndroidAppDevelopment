package com.groupdev.pillpall.model;

public class Medication {
    private long id;
    private String name;
    private double dosage;
    private Form form;
    private UnitOfDosage unitOfDosage;
    private String notes;

    private enum UnitOfDosage {
        MG, ML, G, MCG, IU
    }

    private enum Form {
        TABLET, CAPSULE, LIQUID, INHALER, INJECTION, PATCH, POWDER, SPRAY, GEL, CREAM, OINTMENT, DROPS, OTHER
    }

    public Medication() {
    }

    public Medication(long id, String name, int frequency, double dosage, int quantity, Form form, UnitOfDosage unitOfDosage, String startDate, String endDate, String notes) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.form = form;
        this.unitOfDosage = unitOfDosage;
        this.notes = notes;
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

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public UnitOfDosage getUnitOfDosage() {
        return unitOfDosage;
    }

    public void setUnitOfDosage(UnitOfDosage unitOfDosage) {
        this.unitOfDosage = unitOfDosage;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dosage=" + dosage +
                ", form=" + form +
                ", unitOfDosage=" + unitOfDosage +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medication medication = (Medication) o;
        return medication.id==(this.id);
    }

}
