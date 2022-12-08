package com.example.sleepez;

import java.sql.Time;
import java.util.Date;

public class SleepData {

    private String date;
    private SleepTime bedTime;
    private SleepTime wakeTime;
    private int sleepQuality;
    private String dreamNotes;

    public String getDate() {
        return date;
    }

    public SleepData(String date, SleepTime bedTime, SleepTime wakeTime, int sleepQuality, String dreamNotes) {
        this.date = date;
        this.bedTime = bedTime;
        this.wakeTime = wakeTime;
        this.sleepQuality = sleepQuality;
        this.dreamNotes = dreamNotes;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SleepTime getBedTime() {
        return bedTime;
    }

    public void setBedTime(SleepTime bedTime) {
        this.bedTime = bedTime;
    }

    public SleepTime getWakeTime() {
        return wakeTime;
    }

    public void setWakeTime(SleepTime wakeTime) {
        this.wakeTime = wakeTime;
    }

    public double getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(int sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public String getDreamNotes() {
        return dreamNotes;
    }

    public void setDreamNotes(String dreamNotes) {
        this.dreamNotes = dreamNotes;
    }



}
