package com.groupdev.pillpall.model;

import java.util.ArrayList;
import java.util.List;

    //Later Caretakers, Patients, and Elderly will be added
public class User {
    private long id;
    private String username;
    private String email;
    private String password;
    private List<Reminder> reminders;
    private List<Medication> medications;
    private List<Vitals> vitalsList;
    private List<User> following;
    private List<User> followers;

    public User() {
        reminders = new ArrayList<>();
        medications = new ArrayList<>();
        vitalsList = new ArrayList<>();
        followers = new ArrayList<>();
        following = new ArrayList<>();
    }

    public User(String username, String email, String password, List<Reminder> reminders, List<Medication> medications, List<Vitals> vitalsList, List<User> following, List<User> followers) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.reminders = reminders;
        this.medications = medications;
        this.vitalsList = vitalsList;
        this.following = following;
        this.followers = followers;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.reminders = new ArrayList<>();
        this.medications = new ArrayList<>();
        this.vitalsList = new ArrayList<>();
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<Vitals> getVitalsList() {
        return vitalsList;
    }

    public void setVitalsList(List<Vitals> vitalsList) {
        this.vitalsList = vitalsList;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", reminders=" + reminders +
                ", medications=" + medications +
                ", vitalsList=" + vitalsList +
                ", following=" + following +
                ", followers=" + followers +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user.id==(this.id);
    }

}
