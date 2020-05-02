package model;

import enums.Gender;

public class Person {
    protected String FullName;
    protected int age;
    protected long nationalCode;
    protected Gender gender;

    public Person(String fullName, int age, long nationalCode, Gender gender) {
        FullName = fullName;
        this.age = age;
        this.nationalCode = nationalCode;
        this.gender = gender;
    }

    public long getNationalCode() {
        return nationalCode;
    }
}
