package com.example.sleepez;

import android.util.Log;

import java.util.ArrayList;

public class SleepDataList {

    private static final String TAG = "SleepDataList";
    private ArrayList<SleepData> sleepDataArrayList;

    public SleepDataList() {
        this.sleepDataArrayList = new ArrayList<>();
    }

    public ArrayList<SleepData> getSleepDataArrayList() {
        return sleepDataArrayList;
    }

    public void setSleepDataArrayList(ArrayList<SleepData> sleepDataArrayList) {
        this.sleepDataArrayList = sleepDataArrayList;
    }

    public void addToList(SleepData sleepData) {
        sleepDataArrayList.add(sleepData);
    }

    public String getAverageDuration(int range, SleepDataList list) {

        int totalMinutes = 0;

        for (int i = list.getSleepDataArrayList().size() - 1; i > list.getSleepDataArrayList().size() - (range + 1); i--) {
            int tempMinutes = list.getSleepDataArrayList().get(i).getSleepDuration().getHourDuration() * 60;
            tempMinutes = tempMinutes + list.getSleepDataArrayList().get(i).getSleepDuration().getMinDuration();
            totalMinutes = totalMinutes + tempMinutes;
        }

        int averageTimeInMinutes = Math.round(totalMinutes / range);
        int tempHourDuration = averageTimeInMinutes / 60;
        int tempMinuteDuration = averageTimeInMinutes % 60;
        String tempString = "";

        if (tempHourDuration < 10) {
            tempString = tempString + "0" + tempHourDuration + ":";
        } else {
            tempString = tempString + tempHourDuration + ":";
        }

        if (tempMinuteDuration < 10) {
            tempString = tempString + "0" + tempMinuteDuration;
        } else {
            tempString = tempString + tempMinuteDuration;
        }

        return tempString;
    }

    public String getAverageSleepQuality(int range, SleepDataList list) {

        int totalQuality = 0;

        for (int i = list.getSleepDataArrayList().size() - 1; i > list.getSleepDataArrayList().size() - (range + 1); i--) {
            int tempQuality = list.getSleepDataArrayList().get(i).getSleepQuality();
            totalQuality = totalQuality + tempQuality;
        }

        int returnQuality = Math.round(totalQuality / range);

        return String.valueOf(returnQuality);
    }


    public String getAverageWakeTime(int range, SleepDataList list) {
        ArrayList<Integer> tempList = new ArrayList<>();

        for (int i = 0; i < getSleepDataArrayList().size(); i++) {
            String temp = list.getSleepDataArrayList().get(i).getWakeTime().toString();

            int tempHour = Integer.valueOf(temp.substring(0,2));
            int tempMin = Integer.valueOf(temp.substring(3,5));
            int timeInMinutes = (tempHour * 60) + tempMin;

            tempList.add(timeInMinutes);
        }

        int totalTimeInMinutes = 0;

        for (int i = tempList.size() - 1; i > tempList.size() - (range + 1); i--) {
            totalTimeInMinutes = totalTimeInMinutes + tempList.get(i);
        }

        int averageTimeInMinutes = Math.round(totalTimeInMinutes / range);
        int hourQuotient = averageTimeInMinutes / 60;
        int minuteRemainder = averageTimeInMinutes % 60;
        String format = "AM";

        SleepTime tempTime = new SleepTime(hourQuotient, minuteRemainder, format);

        return tempTime.toString();
    }

    public String getAverageBedTime(int range, SleepDataList list) {
        ArrayList<Integer> tempList = new ArrayList<>();

        for (int i = 0; i < getSleepDataArrayList().size(); i++) {
            String temp = list.getSleepDataArrayList().get(i).getBedTime().toString();

            int tempHour = Integer.valueOf(temp.substring(0,2));
            if (list.getSleepDataArrayList().get(i).getBedTime().getFormat().equals("AM") && list.getSleepDataArrayList().get(i).getBedTime().getHour() != 12) {
                tempHour = tempHour + 12;
            }
            int tempMin = Integer.valueOf(temp.substring(3,5));
            int timeInMinutes = (tempHour * 60) + tempMin;

            tempList.add(timeInMinutes);
        }

        int totalTimeInMinutes = 0;
        for (int i = tempList.size() - 1; i > tempList.size() - (range + 1); i--) {
            totalTimeInMinutes = totalTimeInMinutes + tempList.get(i);

        }

        int averageTimeInMinutes = Math.round(totalTimeInMinutes / range);
        int hourQuotient = averageTimeInMinutes / 60;
        int minuteRemainder = averageTimeInMinutes % 60;
        String format = "PM";

        if (hourQuotient > 12) {
            hourQuotient = hourQuotient - 12;
            format = "AM";
        }

        SleepTime tempTime = new SleepTime(hourQuotient, minuteRemainder, format);

        return tempTime.toString();
    }

}
