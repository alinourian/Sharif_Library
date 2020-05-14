package model;

import enums.Gender;
import enums.Type;

public class Professor extends Person{
    private int yearOfEntry;
    private long budget;
    private String department;

    public Professor(String fullName, int age, long nationalCode,
                     Gender gender, int yearOfEntry, long budget, String department) {
        super(fullName, age, nationalCode, gender);
        this.yearOfEntry = yearOfEntry;
        this.budget = budget;
        this.department = department;
    }

    public void addBudget(long budget){
        this.budget += budget;
    }

    public void fine(long fine) {
        this.budget -= fine;
    }

    @Override
    public Type getType() {
        return Type.PROFESSOR;
    }

    public long getBudget() {
        return budget;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "FullName='" + FullName + '\'' +
                ", age=" + age +
                ", nationalCode=" + nationalCode +
                ", gender=" + gender +
                ", yearOfEntry=" + yearOfEntry +
                ", budget=" + budget +
                ", department='" + department + '\'' +
                '}';
    }
}
