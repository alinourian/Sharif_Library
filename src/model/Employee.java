package model;

import enums.Gender;
import enums.Libraries;
import enums.Type;
import enums.WeekDays;

import java.util.ArrayList;
import java.util.List;

public class Employee extends Person {
    private final Libraries workPlace;
    private List<WeekDays> workingDays;

    public Employee(String fullName, int age,
                    String nationalCode, Gender gender, Libraries workPlace) {
        super(fullName, age, nationalCode, gender);
        this.workPlace = workPlace;
        workingDays = new ArrayList<>();
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
