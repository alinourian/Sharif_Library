package model;

import enums.AddBook;
import enums.WeekDays;

import java.util.List;

public interface Library {
    static final int NUMBERS_OF_EMPLOYEES = 15;
    static final int AREA = 500;
    static final int NUMBERS_OF_ROOMS = 8;
    static final int  MAX_BOOKS = 5;

    AddBook addBook(Book book);
    boolean borrowBook(Book book);
    void returnBook(Book book);
    void addEmployee(Employee employee);
    void changeEmployeeSchedule(long nationalCode, List<WeekDays> newSchedule);
    double setFineForDelay();
    Book search(Book book);

}
