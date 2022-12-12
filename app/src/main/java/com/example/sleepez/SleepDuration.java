package com.example.sleepez;

public class SleepDuration {

    private int hourDuration;
    private int minDuration;
    private String durationString;

    public SleepDuration(SleepTime bedTime, SleepTime wakeTime) {
        this.hourDuration = calculateHourDuration(bedTime, wakeTime);
        this.minDuration = calculateMinDuration(bedTime, wakeTime);
        setDurationString();
        this.durationString = getDurationString();
    }

    private int calculateMinDuration(SleepTime bedTime, SleepTime wakeTime) {

        int result = 0;

        if (bedTime.getMin() < wakeTime.getMin()) {
            result = wakeTime.getMin() - bedTime.getMin();

        } else if (bedTime.getMin() > wakeTime.getMin()) {
            hourDuration--;
            int temp = 60 - bedTime.getMin();
            result = wakeTime.getMin() + temp;

        } else if (bedTime.getMin() == wakeTime.getMin()) {
            result = 0;
        }

        return result;
    }

    private int calculateHourDuration(SleepTime bedTime, SleepTime wakeTime) {

        int result;

        if (bedTime.getFormat().equals("AM")) {
            result = wakeTime.getHour() - bedTime.getHour();
        } else {
            System.out.println("Testing! " + bedTime.getHour());
            int temp = 12 - bedTime.getHour();
            result = temp + wakeTime.getHour();
            System.out.println("Testing result! " + result);
        }

        return result;
    }

    public int getHourDuration() {
        return hourDuration;
    }

    public void setHourDuration(int hourDuration) {
        this.hourDuration = hourDuration;
    }

    public int getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(int minDuration) {
        this.minDuration = minDuration;
    }

    public String getDurationString() {
        return durationString;
    }

    public void setDurationString() {
        String tempString = "";

        if (getHourDuration() < 10) {
            tempString = tempString + "0" + getHourDuration() + ":";
        } else {
            tempString = tempString + getHourDuration() + ":";
        }

        if (getMinDuration() < 10) {
            tempString = tempString + "0" + getMinDuration();
        } else {
            tempString = tempString + getMinDuration();
        }

        this.durationString = tempString;
    }

    @Override
    public String toString() {
        return getDurationString();
    }
}
