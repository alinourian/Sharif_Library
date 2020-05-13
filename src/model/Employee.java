package model;

import enums.Gender;
import enums.Libraries;
import enums.Type;
import enums.WeekDays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Employee extends Person {
    private final Libraries workPlace;
    private List<WeekDays> workingDays;
    private Map<Integer, Integer> workTime;

    public Employee(String fullName, int age,
                    long nationalCode, Gender gender, Libraries workPlace) {
        super(fullName, age, nationalCode, gender);
        this.workPlace = workPlace;
        workingDays = new ArrayList<>();
        workTime = new HashMap<>();
    }

    public Libraries getWorkPlace() {
        return workPlace;
    }

    public List<WeekDays> getWorkingDays() {
        return workingDays;
    }

    public void updateWorkingDays(List<WeekDays> newSchedule) {
        workingDays = newSchedule;
    }

    @Override
    public Type getType() {
        return Type.WORKER;
    }

    public Map<Integer, Integer> getWorkTime() {
        return workTime;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "FullName='" + FullName + '\'' +
                ", age=" + age +
                ", nationalCode=" + nationalCode +
                ", gender=" + gender +
                ", workPlace=" + workPlace +
                ", workingDays=" + workingDays +
                '}';
    }
}
