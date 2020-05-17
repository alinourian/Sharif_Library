package model;

import controller.MyDate;
import enums.AddBook;
import enums.WeekDays;

import java.util.ArrayList;
import java.util.List;

public interface Library {
    static final int NUMBERS_OF_EMPLOYEES = 15;
    static final int AREA = 500;
    static final int NUMBERS_OF_ROOMS = 8;
    static final int  MAX_BOOKS = 30;
    static final int FINE = 1000;

    AddBook addBook(Book book);
    boolean borrowBook(Book book);
    void returnBook(Book book);
    void addEmployee(Employee employee);
    void changeEmployeeSchedule(long nationalCode, List<WeekDays> newSchedule);
    void setFineForDelay(MyDate currentDay, int addDay);
    Book search(Book book);
    Employee getWorkerByTime(WeekDays day, int hour);
    void refreshWorkersSchedule();

}
