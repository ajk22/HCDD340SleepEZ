package com.example.sleepez;

import java.sql.Time;
import java.util.Date;

public class SleepData {

    private String date;
    private SleepTime bedTime;
    private SleepTime wakeTime;
    private int sleepQuality;
    private String dreamNotes;
    private SleepDuration sleepDuration;

    public String getDate() {
        return date;
    }

    public SleepData(String date, SleepTime bedTime, SleepTime wakeTime, int sleepQuality, String dreamNotes) {
        this.date = date;
        this.bedTime = bedTime;
        this.wakeTime = wakeTime;
        this.sleepQuality = sleepQuality;
        this.dreamNotes = dreamNotes;
        this.sleepDuration = new SleepDuration(bedTime, wakeTime);
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

    @Override
    public String toString() {
        String returnString = getDate() + "," + getBedTime() + "," + getWakeTime() + "," + getSleepQuality() + "," + getDreamNotes() + "," + getSleepDuration().toString() + "-";
        return returnString;
    }

    public void setWakeTime(SleepTime wakeTime) {
        this.wakeTime = wakeTime;
    }

    public double getSleepQuality() {
        return sleepQuality;
    }

    public SleepDuration getSleepDuration() {
        return sleepDuration;
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
