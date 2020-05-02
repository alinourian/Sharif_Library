package view;

import enums.*;
import model.*;

public abstract class ConsoleViewOut {
    public static void setDate(int year, int month, int day) {
        String s = "Date has been set at " + year + "/" + month + "/" + day + ".";
        System.out.println(s);
    }

    public static void createBook(CreateBook status, Book book) {
        if (status == CreateBook.NEW_BOOK_CREATED) {
            System.out.println("You have successfully created new book.");
        } else if (status == CreateBook.NUMBER_OF_BOOK_INCREASED) {
            System.out.println("You have successfully added one of this book.");
        }
    }

    public static void addBook(AddBook status, Book book) {
        if (status == AddBook.ADDED_SUCCESSFULLY) {
            System.out.println("You just successfully added one book to this library.");
            Book test = CentralManagement.searchBook(book);
            System.out.println("available: " + test.getNumbersAvailable());
        } else if (status == AddBook.NEW_ADDED_SUCCESSFULLY) {
            System.out.println("You just successfully added a new book to this library.");
        } else if (status == AddBook.LIBRARY_IS_FULL) {
            System.out.println("Sorry! The library is full! You can't add your book.");
        } else if (status == AddBook.NO_OTHER_BOOK_TO_ADD) {
            System.out.println("No book to add! Please create one another first.");
        } else if (status == AddBook.BELONG_TO_ANOTHER_LIBRARY) {
            System.out.println("This book was added to another library, it belongs to that!" +
                    " You can't add it to this library.");
        } else if (status == AddBook.BOOK_NEVER_EXIST) {
            System.out.println("This book does not exist! Please create your new book first.");
        } else if (status == AddBook.LIBRARY_NEVER_EXIST) {
            System.err.println("The library you're looking for does not exist! please choose one of these: ");
            System.out.println("1. CentralLibrary Or MainLibrary\n" +
                    "2. LibraryA Or A\n" +
                    "3. LibraryB Or B");
        }
    }

    public static void createStudent(Student student) {
        System.out.println("Student successfully created.");
    }

    public static void createProfessor(Professor professor) {
        System.out.println("Professor successfully created.");
    }

    public static void createEmployee(Employee employee) {
        System.out.println("Worker successfully created.");
    }

    public static void depositStudent(Student student) {
        System.out.println("Budget successfully increased. new budget becomes: "
                + student.getBudget());
    }

    public static void depositProfessor(Professor professor) {
        System.out.println("Budget successfully increased. new budget becomes: "
                + professor.getBudget());
    }



    public static void invalidCommands() {
        System.err.println("INVALID COMMAND! PLEASE TRY AGAIN!");
    }
}
