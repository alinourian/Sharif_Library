package controller;

public class MyTime {
    private int hour;
    private int min;
    private int sec;

    public MyTime(int hour, int min, int sec) {
        this.hour = hour;
        this.min = min;
        this.sec = sec;
    }

    public MyTime(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    public int getHour() {
        return hour;
    }

    public int getSec() {
        return sec;
    }
}
