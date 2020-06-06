package model;

import controller.MyDate;
import enums.AddBook;
import enums.WeekDays;
import java.util.List;

public interface Library {
    int NUMBERS_OF_EMPLOYEES = 15;
    int AREA = 500;
    int NUMBERS_OF_ROOMS = 8;
    int  MAX_BOOKS = 30;
    int FINE = 1000;

    AddBook addBook(Book book);
    boolean borrowBook(Book book);
    void returnBook(Book book);
    void addEmployee(Employee employee);
    void changeEmployeeSchedule(String nationalCode, List<WeekDays> newSchedule);
    void setFineForDelay(MyDate currentDay, int addDay);
    Book search(Book book);
    Employee getWorkerByTime(WeekDays day, int hour);
    void refreshWorkersSchedule();

}
