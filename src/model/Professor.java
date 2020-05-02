package model;

import enums.Gender;

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

    public long getBudget() {
        return budget;
    }


}
