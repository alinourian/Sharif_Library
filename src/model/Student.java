package model;

import enums.Gender;
import enums.Type;

public class Student extends Person {
    private int studentId;
    private int yearOfEntry;
    private String grade;
    private long budget;
    private String department;

    public Student(String fullName, int age, long nationalCode, Gender gender,
                   int studentId, int yearOfEntry, String grade, long budget, String department) {
        super(fullName, age, nationalCode, gender);
        this.studentId = studentId;
        this.yearOfEntry = yearOfEntry;
        this.grade = grade;
        this.budget = budget;
        this.department = department;
    }

    public void addBudget(long budget) {
        this.budget += budget;
    }

    public void fine(long fine) {
        this.budget -= fine;
    }

    @Override
    public Type getType() {
        return Type.STUDENT;
    }

    public long getBudget() {
        return budget;
    }

    public int getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "FullName='" + FullName + '\'' +
                ", age=" + age +
                ", nationalCode=" + nationalCode +
                ", gender=" + gender +
                ", studentId=" + studentId +
                ", yearOfEntry=" + yearOfEntry +
                ", grade='" + grade + '\'' +
                ", budget=" + budget +
                ", department='" + department + '\'' +
                '}';
    }
}
