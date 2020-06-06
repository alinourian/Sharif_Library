package model;

import enums.Libraries;
import enums.WeekDays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CentralManagement {

    private static final ArrayList<Employee> allEmployees = new ArrayList<>();
    private static final ArrayList<Professor> allProfessors = new ArrayList<>();
    private static final ArrayList<Student> allStudents = new ArrayList<>();

    public static List<Book> allBooksEverExist = new ArrayList<>();
    public static List<Employee> allActiveEmployees = new ArrayList<>();
    public static List<Professor> allActiveProfessors = new ArrayList<>();
    public static List<Student> allActiveStudents = new ArrayList<>();
    public static Map<Book, Integer> allBooksInLibraries = new HashMap<>();
    public static Map<String, String> allBorrowedBooks = new HashMap<>();//just been used in controller - ID + details
    public static Map<String, String> allReturnedBooks = new HashMap<>();//just been used in controller - ID + details

    public static Book searchBookInAllBooks(Book book) {
        for(Book test : allBooksEverExist) {
            if(test.getISBN() == book.getISBN())  {
                if(test.getPublishedYear() == book.getPublishedYear()
                        && test.getBookName().equals(book.getBookName())) {
                    return test;
                }
            }
        }
        return null;
    }

    public static Book searchBookInLibraries(Book book) {
        for(Book test : allBooksInLibraries.keySet()) {
            if(test.getISBN() == book.getISBN())  {
                if(test.getPublishedYear() == book.getPublishedYear()
                        && test.getBookName().equals(book.getBookName())) {
                    return test;
                }
            }
        }
        return null;
    }

    public static Student getStudentByStudentIdInAllStudents(int studentId) {
        for (Student student : allStudents) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public static Student getStudentByStudentIdInAllActiveStudents(int studentId) {
        for (Student student : allActiveStudents) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public static Professor getProfessorByNCInAllProfessors(String nationalCode) {
        for (Professor professor : allProfessors) {
            if (professor.getNationalCode().equals(nationalCode)) {
                return professor;
            }
        }
        return null;
    }

    public static Professor getProfessorByNCInAllActiveProfessors(String nationalCode) {
        for (Professor professor : allActiveProfessors) {
            if (professor.getNationalCode().equals(nationalCode)) {
                return professor;
            }
        }
        return null;
    }

    public static Employee getEmployeeByNCInAllEmployees(String nationalCode) {
        for (Employee employee : allEmployees) {
            if (employee.getNationalCode().equals(nationalCode)) {
                return employee;
            }
        }
        return null;
    }

    public static Employee getWorkerByTime(Libraries library, WeekDays day, int hour) {
        Employee employee;
        if (library == Libraries.CENTRAL_LIBRARY) {
            employee = CentralLibrary.getInstance().getWorkerByTime(day, hour);
        } else if (library == Libraries.LIBRARY_A) {
            employee = LibraryA.getInstance().getWorkerByTime(day, hour);
        } else { // LibraryB
            employee = LibraryB.getInstance().getWorkerByTime(day, hour);
        }
        return employee;
    }

    public static Employee getWorker(ArrayList<Employee> day, int hour) {
        int shift;
        int worker;
        try {
            shift = 12 / day.size();
            worker = (hour - 8) / shift;
        } catch (ArithmeticException e) {
           return null;
        }
        return  day.get(worker);
    }

    public static void refreshWorkersSchedule() {
        CentralLibrary.getInstance().refreshWorkersSchedule();
        LibraryA.getInstance().refreshWorkersSchedule();
        LibraryB.getInstance().refreshWorkersSchedule();
    }

    public static ArrayList<Employee> getAllEmployees() {
        return allEmployees;
    }

    public static ArrayList<Professor> getAllProfessors() {
        return allProfessors;
    }

    public static ArrayList<Student> getAllStudents() {
        return allStudents;
    }
}
