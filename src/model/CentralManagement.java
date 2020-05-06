package model;

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
    public static Map<Book, String> allBorrowedBooks = new HashMap<>();//just been used in controller
    public static Map<Book, String> allReturnedBooks = new HashMap<>();//just been used in controller

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

    public static Professor getProfessorByNCInAllProfessors(long nationalCode) {
        for (Professor professor : allProfessors) {
            if (professor.getNationalCode() == nationalCode) {
                return professor;
            }
        }
        return null;
    }

    public static Professor getProfessorByNCInAllActiveProfessors(long nationalCode) {
        for (Professor professor : allActiveProfessors) {
            if (professor.getNationalCode() == nationalCode) {
                return professor;
            }
        }
        return null;
    }

    public static Employee getEmployeeByNCInAllEmployees(long nationalCode) {
        for (Employee employee : allEmployees) {
            if (employee.getNationalCode() == nationalCode) {
                return employee;
            }
        }
        return null;
    }

    public static void refreshWorkersSchedule() {

    }

    public static Employee getWorkerByTime() {
        return null;
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
