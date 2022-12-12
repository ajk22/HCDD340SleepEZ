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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        String minString = min + "";
        String hourString = hour + "";
        if (min <= 9) {
            minString = "0" + min;
        }

        if (hour <= 9) {
            hourString = "0" + hour;
        }

        return hourString + ":" + minString + " " + format;
    }
}
