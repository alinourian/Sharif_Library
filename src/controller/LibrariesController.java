package controller;

import enums.*;
import model.*;
import view.ConsoleViewOut;

import java.sql.Struct;
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
    public void createBook(String bookName, int pages, int publishedYear, String writer,
                           String language, long ISBN, double price, String translator) {
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

    public void createStudent(String fullName, int age, long nationalCode, Gender gender, int studentId,
                              int yearOfEntry, String grade, long budget, String department) {
        for (Student stu : allStudents) {
            if (stu.getStudentId() == studentId || stu.getNationalCode() == nationalCode) {
                ConsoleViewOut.createPerson(stu, false);
                return;
            }
        }
        Student student = new Student(fullName, age, nationalCode, gender,
        studentId, yearOfEntry, grade, budget, department);
        allStudents.add(student);
        ConsoleViewOut.createPerson(student, true);
    }

    public void createProfessor(String fullName, int age, long nationalCode, Gender gender,
                              int yearOfEntry, long budget, String department) {
        for (Professor pro : allProfessors) {
            if (pro.getNationalCode() == nationalCode) {
                ConsoleViewOut.createPerson(pro, false);
                return;
            }
        }
        Professor professor = new Professor(fullName, age, nationalCode, gender,
                yearOfEntry, budget, department);
        allProfessors.add(professor);
        ConsoleViewOut.createPerson(professor, true);
    }

    public void createWorker(String fullName, int age, long nationalCode,
                             Gender gender, Libraries libraries) {
        for (Employee emp : allEmployees) {
            if (emp.getNationalCode() == nationalCode) {
                ConsoleViewOut.createPerson(emp, false);
                return;
            }
        }
        Employee employee = new Employee(fullName, age, nationalCode, gender, libraries);
        allEmployees.add(employee);
        ConsoleViewOut.createPerson(employee, true);
    }

    public void addStudent(int studentId) {
        for (Student student : allStudents) {
            if(student.getStudentId() == studentId) {
                CentralManagement.allActiveStudents.add(student);
                CentralLibrary.getInstance().addMember(student);
                ConsoleViewOut.addPerson(student);
                return;
            }
        }
        ConsoleViewOut.addStudentFailed(studentId);
    }

    public void addProfessor(Long nationalCode) {
        for (Professor professor : allProfessors) {
            if (professor.getNationalCode() == nationalCode) {
                CentralManagement.allActiveProfessors.add(professor);
                CentralLibrary.getInstance().addMember(professor);
                ConsoleViewOut.addPerson(professor);
                return;
            }
        }
        ConsoleViewOut.addProfessorFailed(nationalCode);
    }

    public void addEmployee(Long nationalCode) {
        for (Employee employee : allEmployees) {
            if (employee.getNationalCode() == nationalCode) {
                CentralManagement.allActiveEmployees.add(employee);
                if (employee.getWorkPlace() == Libraries.CENTRAL_LIBRARY) {
                    if (CentralLibrary.getInstance().getNumbersOfEmployee() < Library.NUMBERS_OF_EMPLOYEES) {
                        CentralLibrary.getInstance().addMember(employee);
                        CentralLibrary.getInstance().addEmployee(employee);
                        ConsoleViewOut.addPerson(employee);
                    } else {
                        ConsoleViewOut.addEmployeeFailed(nationalCode, false);
                        // false is because of the capacity of the library
                    }
                } else if (employee.getWorkPlace() == Libraries.LIBRARY_A) {
                    if (LibraryA.getInstance().getNumbersOfEmployee() < Library.NUMBERS_OF_EMPLOYEES) {
                        LibraryA.getInstance().addEmployee(employee);
                        ConsoleViewOut.addPerson(employee);
                    } else {
                        ConsoleViewOut.addEmployeeFailed(nationalCode, false);
                        // false is because of the capacity of the library
                    }
                } else if (employee.getWorkPlace() == Libraries.LIBRARY_B) {
                    if (LibraryB.getInstance().getNumbersOfEmployee() < Library.NUMBERS_OF_EMPLOYEES) {
                        LibraryB.getInstance().addEmployee(employee);
                        ConsoleViewOut.addPerson(employee);
                    } else {
                        ConsoleViewOut.addEmployeeFailed(nationalCode, false);
                        // false is because of the capacity of the library
                    }
                }
                return;
            }
        }
        ConsoleViewOut.addEmployeeFailed(nationalCode, true);
        // true is because of the capacity of the library
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
