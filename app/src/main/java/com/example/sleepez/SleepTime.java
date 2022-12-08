package com.example.sleepez;

public class SleepTime {

    private int hour;
    private int min;
    private String format;

    public SleepTime(int hour, int min, String format) {
        this.hour = hour;
        this.min = min;
        this.format = format;
    }

    @Override
    public String toString() {
        String minString = min + "";

        if (min <= 9) {
            minString = "0" + min;
        }

        return hour + ":" + minString + " " + format;
    }
}
