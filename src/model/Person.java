package model;

import enums.Gender;
import enums.Type;

public class Person {
    protected String FullName;
    protected int age;
    protected String nationalCode;
    protected Gender gender;

    public Person(String fullName, int age, String nationalCode, Gender gender) {
        FullName = fullName;
        this.age = age;
        this.nationalCode = nationalCode;
        this.gender = gender;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public Type getType () {
        return null;
    }

    public String getFullName() {
        return FullName;
    }
}
