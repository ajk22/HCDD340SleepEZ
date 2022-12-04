package com.example.sleepez;

import java.sql.Time;
import java.util.Date;

public class SleepData {

    private Date date;
    private Time bedTime;
    private Time wakeTime;
    private double sleepQuality;
    private String dreamNotes;

    public Date getDate() {
        return date;
    }

    public SleepData(Date date, Time bedTime, Time wakeTime, double sleepQuality, String dreamNotes) {
        this.date = date;
        this.bedTime = bedTime;
        this.wakeTime = wakeTime;
        this.sleepQuality = sleepQuality;
        this.dreamNotes = dreamNotes;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getBedTime() {
        return bedTime;
    }

    public void setBedTime(Time bedTime) {
        this.bedTime = bedTime;
    }

    public Time getWakeTime() {
        return wakeTime;
    }

    public void setWakeTime(Time wakeTime) {
        this.wakeTime = wakeTime;
    }

    public double getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(double sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public String getDreamNotes() {
        return dreamNotes;
    }

    public void setDreamNotes(String dreamNotes) {
        this.dreamNotes = dreamNotes;
    }



}
