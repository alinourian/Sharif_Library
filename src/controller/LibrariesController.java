package controller;

import enums.*;
import model.*;
import view.ConsoleViewOut;

import java.util.ArrayList;

public class LibrariesController {
    private static LibrariesController instance;

    private final Time time;
    private CentralLibrary centralLibrary = CentralLibrary.getInstance();
    private LibraryA libraryA = LibraryA.getInstance();
    private LibraryB libraryB = LibraryB.getInstance();

    public static LibrariesController getInstance() {
        if(instance == null) {
            instance = new LibrariesController();
        }
        return instance;
    }

    private LibrariesController() {
        time = Time.getInstance();
        //allEmployees = new ArrayList<>();
        //allProfessors = new ArrayList<>();
        //allStudents = new ArrayList<>();
    }

    public void setDate(int year, int month, int day) {
        time.setTime(year, month, day);
        ConsoleViewOut.setDate(year, month, day);
    }

    public void createBook(String bookName, int pages, int publishedYear,
                           String writer, String language, long ISBN, double price) {
        Book book;
        book = new Book(bookName, pages, publishedYear, writer, language, ISBN, price);
        Book test = CentralManagement.searchBookInAllBooks(book);
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
        Book test = CentralManagement.searchBookInAllBooks(book);
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
        for (Student stu : CentralManagement.getAllStudents()) {
            if (stu.getStudentId() == studentId || stu.getNationalCode() == nationalCode) {
                ConsoleViewOut.createPerson(stu, false);
                return;
            }
        }
        Student student = new Student(fullName, age, nationalCode, gender,
                studentId, yearOfEntry, grade, budget, department);
        CentralManagement.getAllStudents().add(student);
        ConsoleViewOut.createPerson(student, true);
    }

    public void createProfessor(String fullName, int age, long nationalCode, Gender gender,
                              int yearOfEntry, long budget, String department) {
        for (Professor pro : CentralManagement.getAllProfessors()) {
            if (pro.getNationalCode() == nationalCode) {
                ConsoleViewOut.createPerson(pro, false);
                return;
            }
        }
        Professor professor = new Professor(fullName, age, nationalCode, gender,
                yearOfEntry, budget, department);
        CentralManagement.getAllProfessors().add(professor);
        ConsoleViewOut.createPerson(professor, true);
    }

    public void createWorker(String fullName, int age, long nationalCode,
                             Gender gender, Libraries libraries) {
        for (Employee emp : CentralManagement.getAllEmployees()) {
            if (emp.getNationalCode() == nationalCode) {
                ConsoleViewOut.createPerson(emp, false);
                return;
            }
        }
        Employee employee = new Employee(fullName, age, nationalCode, gender, libraries);
        CentralManagement.getAllEmployees().add(employee);
        ConsoleViewOut.createPerson(employee, true);
    }

    public void addStudent(int studentId) {
        Student student = CentralManagement.getStudentByStudentIdInAllStudents(studentId);
        if (student == null) {
            ConsoleViewOut.addStudentFailed(studentId);
        } else {
            if (!CentralManagement.allActiveStudents.contains(student)) {
                CentralLibrary.getInstance().addMember(student);
                ConsoleViewOut.addPerson(student, true);
            } else {
                ConsoleViewOut.addPerson(student, false);
            }
        }
    }

    public void addProfessor(long nationalCode) {
        Professor professor = CentralManagement.getProfessorByNCInAllProfessors(nationalCode);
        if (professor == null) {
            ConsoleViewOut.addProfessorFailed(nationalCode);
        } else {
            if (!CentralManagement.allActiveProfessors.contains(professor)) {
                CentralLibrary.getInstance().addMember(professor);
                ConsoleViewOut.addPerson(professor, true);
            } else {
                ConsoleViewOut.addPerson(professor, false);
            }
        }
    }

    public void addEmployee(long nationalCode, Libraries libraries) {
        for (Employee employee : CentralManagement.getAllEmployees()) {
            if (employee.getNationalCode() == nationalCode) {
                if (CentralManagement.allActiveEmployees.contains(employee)) {
                    ConsoleViewOut.addPerson(employee, false);
                    return;
                }
                if (libraries == employee.getWorkPlace()) {
                    if (employee.getWorkPlace() == Libraries.CENTRAL_LIBRARY) {
                        if (CentralLibrary.getInstance().getNumbersOfEmployee() < Library.NUMBERS_OF_EMPLOYEES) {
                            CentralLibrary.getInstance().addEmployee(employee);
                            ConsoleViewOut.addPerson(employee, true);
                        } else {
                            ConsoleViewOut.addEmployeeFailed(nationalCode, AddWorker.LIBRARY_IS_FULL);
                        }
                    } else if (employee.getWorkPlace() == Libraries.LIBRARY_A) {
                        if (LibraryA.getInstance().getNumbersOfEmployee() < Library.NUMBERS_OF_EMPLOYEES) {
                            LibraryA.getInstance().addEmployee(employee);
                            ConsoleViewOut.addPerson(employee, true);
                        } else {
                            ConsoleViewOut.addEmployeeFailed(nationalCode, AddWorker.LIBRARY_IS_FULL);
                            // false is because of the capacity of the library
                        }
                    } else if (employee.getWorkPlace() == Libraries.LIBRARY_B) {
                        if (LibraryB.getInstance().getNumbersOfEmployee() < Library.NUMBERS_OF_EMPLOYEES) {
                            LibraryB.getInstance().addEmployee(employee);
                            ConsoleViewOut.addPerson(employee, true);
                        } else {
                            ConsoleViewOut.addEmployeeFailed(nationalCode, AddWorker.LIBRARY_IS_FULL);
                            // false is because of the capacity of the library
                        }
                    }
                } else if (libraries != employee.getWorkPlace()) {
                    ConsoleViewOut.addEmployeeFailed(nationalCode, AddWorker.WRONG_LIBRARY_TO_ADD);
                }
                return;
            }
        }
        ConsoleViewOut.addEmployeeFailed(nationalCode, AddWorker.INVALID_NC);
    }

    public void depositStudent(int studentId, long increase) {
        Student student = CentralManagement.getStudentByStudentIdInAllActiveStudents(studentId);
        if (student == null) {
            ConsoleViewOut.depositFailed();
        } else {
            student.addBudget(increase);
            ConsoleViewOut.depositStudent(student);
        }
    }

    public void depositProfessor(long nationalCode, long increase) {
        Professor professor = CentralManagement.getProfessorByNCInAllActiveProfessors(nationalCode);
        if (professor == null) {
            ConsoleViewOut.depositFailed();
        } else {
            professor.addBudget(increase);
            ConsoleViewOut.depositProfessor(professor);
        }
    }

    public void setSchedule(long nationalCode, Libraries libraries, ArrayList<WeekDays> schedule) {
        Employee employee = CentralManagement.getEmployeeByNCInAllEmployees(nationalCode);
        if (employee == null) {
            ConsoleViewOut.setSchedule(nationalCode, SetSchedule.WORKER_NOT_EXIST);
        } else {
            if (employee.getWorkPlace() == libraries ||
                    (libraries == Libraries.STORE && employee.getWorkPlace() == Libraries.CENTRAL_LIBRARY)) {
                employee.updateWorkingDays(schedule);
                ConsoleViewOut.setSchedule(nationalCode, SetSchedule.SUCCESSFUL);
            } else {
                ConsoleViewOut.setSchedule(nationalCode, SetSchedule.WRONG_LIBRARY);
            }
        }
    }

    public void findBook(int studentId, String bookName, long ISBN, int publishedYear) {
        if (CentralManagement.getStudentByStudentIdInAllActiveStudents(studentId) == null) {
            ConsoleViewOut.findBookFailed(false);
        } else {
            Book test = new Book(bookName, ISBN, publishedYear);
            Book book = CentralManagement.searchBookInLibraries(test);
            if (book == null) {
                ConsoleViewOut.findBookFailed(true);
            } else {
                ConsoleViewOut.findBookSuccessful(book);
            }
        }
    }

    public void findBook(long nationalCode, String bookName, long ISBN, int publishedYear) {
        if (CentralManagement.getProfessorByNCInAllActiveProfessors(nationalCode) == null) {
            ConsoleViewOut.findBookFailed(false);
        } else {
            Book test = new Book(bookName, ISBN, publishedYear);
            Book book = CentralManagement.searchBookInLibraries(test);
            if (book == null) {
                ConsoleViewOut.findBookFailed(true);
            } else {
                ConsoleViewOut.findBookSuccessful(book);
            }
        }
    }


}
