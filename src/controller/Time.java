package controller;


public class Time {
    private static Time instance;

    private Time() {

    }

    public static Time getInstance() {
        if(instance == null) {
            instance = new Time();
        }
        return instance;
    }

    private int year;
    private int month;
    private int day;

    public void setTime(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
