package controller;

import enums.AddBook;
import enums.CreateBook;
import enums.Gender;
import enums.Libraries;
import model.*;
import view.ConsoleViewOut;

import java.util.ArrayList;

public class LibrariesController {
    private static LibrariesController instance;

    private Time time;
    private CentralLibrary centralLibrary;
    private LibraryA libraryA;
    private LibraryB libraryB;

    ArrayList<Employee> allEmployees;
    ArrayList<Professor> allProfessors;
    ArrayList<Student> allStudents;

    public static LibrariesController getInstance() {
        if(instance == null) {
            instance = new LibrariesController();
        }
        return instance;
    }

    private LibrariesController() {
        time = Time.getInstance();
        centralLibrary = CentralLibrary.getInstance();
        libraryA = LibraryA.getInstance();
        libraryB = LibraryB.getInstance();
        allEmployees = new ArrayList<>();
        allProfessors = new ArrayList<>();
        allStudents = new ArrayList<>();
    }

    public void setDate(int year, int month, int day) {
        time.setTime(year, month, day);
        ConsoleViewOut.setDate(year, month, day);
    }

    public void createBook(String bookName, int pages, int publishedYear,
                           String writer, String language, long ISBN, double price) {
        Book book;
        book = new Book(bookName, pages, publishedYear, writer, language, ISBN, price);
        Book test = CentralManagement.searchBook(book);
        if (test == null) {
            CentralManagement.allBooksEverExist.add(book);
            book.setNumbersAvailable(1);
            ConsoleViewOut.createBook(CreateBook.NEW_BOOK_CREATED, book);
        } else {
            test.setNumbersAvailable(test.getNumbersAvailable()+1);
            ConsoleViewOut.createBook(CreateBook.NUMBER_OF_BOOK_INCREASED, test);
        }
    }
    public void createBook(String bookName, int pages, int publishedYear,
                           String writer, String language, long ISBN, double price, String translator) {
        Book book;
        book = new Book(bookName, pages, publishedYear, writer, language, ISBN, price, translator);
        Book test = CentralManagement.searchBook(book);
        if (test == null) {
            CentralManagement.allBooksEverExist.add(book);
            book.setNumbersAvailable(1);
            ConsoleViewOut.createBook(CreateBook.NEW_BOOK_CREATED, book);
        } else {
            test.setNumbersAvailable(test.getNumbersAvailable()+1);
            ConsoleViewOut.createBook(CreateBook.NUMBER_OF_BOOK_INCREASED, test);
        }
    }

    public void addBook(String libraryName, String bookName, long ISBN, int publishedYear) {
        Book book = new Book(bookName, ISBN, publishedYear);
        AddBook addBookStatus;
        if (libraryName.equalsIgnoreCase("mainLibrary") ||
                libraryName.equalsIgnoreCase("centralLibrary")) {
            addBookStatus = CentralLibrary.getInstance().addBook(book);
        } else if (libraryName.equalsIgnoreCase("libraryA") ||
                libraryName.equalsIgnoreCase("A")) {
            addBookStatus = LibraryA.getInstance().addBook(book);
        } else if (libraryName.equalsIgnoreCase("libraryB") ||
                libraryName.equalsIgnoreCase("B")) {
            addBookStatus = LibraryB.getInstance().addBook(book);
        } else {
            addBookStatus = AddBook.LIBRARY_NEVER_EXIST;
        }
        ConsoleViewOut.addBook(addBookStatus, book);
    }

    public void createStudent(String fullName, int age, long nationalCode, Gender gender,
                                     int studentId, int yearOfEntry, String grade, long budget, String department) {
        Student student = new Student(fullName, age, nationalCode, gender,
        studentId, yearOfEntry, grade, budget, department);
        allStudents.add(student);
        ConsoleViewOut.createStudent(student);
    }

    public void createProfessor(String fullName, int age, long nationalCode, Gender gender,
                              int yearOfEntry, long budget, String department) {
        Professor professor = new Professor(fullName, age, nationalCode, gender,
                yearOfEntry, budget, department);
        allProfessors.add(professor);
        ConsoleViewOut.createProfessor(professor);
    }

    public void createWorker(String fullName, int age, long nationalCode, Gender gender, Libraries libraries) {
        Employee employee = new Employee(fullName, age, nationalCode, gender, libraries);
        allEmployees.add(employee);
        ConsoleViewOut.createEmployee(employee);
    }

    public void depositStudent(int studentId, long increase) {
        for (Student student : allStudents) {
            if (student.getStudentId() == studentId) {
                student.addBudget(increase);
                ConsoleViewOut.depositStudent(student);
                break;
            }
        }
    }

    public void depositProfessor(long nationalCode, long increase) {
        for (Professor professor : allProfessors) {
            if (professor.getNationalCode() == nationalCode) {
                professor.addBudget(increase);
                ConsoleViewOut.depositProfessor(professor);
                break;
            }
        }
    }


}
