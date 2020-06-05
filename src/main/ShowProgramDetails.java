package main;

import enums.Libraries;
import model.*;
import view.ConsoleViewIn;

import java.util.ArrayList;
import java.util.Scanner;

public class ShowProgramDetails {
    public static void run() {
        while (true) {
            System.out.println("Choose which one would you like to show :");
            System.out.println("1.Objects details");
            System.out.println("2.Lists details");
            System.out.println("3.Workers schedule");
            System.out.println("4.back to program");
            Scanner scanner = ConsoleViewIn.getScanner();
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("4") ||
                    command.equalsIgnoreCase("back to program")) {
                System.out.println("Well, go on...");
                break;
            } else if (command.equalsIgnoreCase("1") ||
                    command.equalsIgnoreCase("objects details")) {
                objectsDetails();
            } else if (command.equalsIgnoreCase("2") ||
                    command.equalsIgnoreCase("lists details")) {
                listsDetails();
            } else if (command.equalsIgnoreCase("3") ||
                    command.equalsIgnoreCase("workers schedule")) {
                workersSchedule();
            } else {
                System.err.println("Invalid command!");
            }
        }
    }

    private static void objectsDetails() {
        // BOOKS
        showAllExistingBook();
        showAllAvailableBooksInLibraries();
        divide();
        showBooksInCentralLibrary();
        showBooksInLibraryA();
        showBooksInLibraryB();
        divide();
        divide();

        // PERSONS
        showAllProfessors();
        showAllActiveProfessors();
        divide();
        showAllStudents();
        showAllActiveStudents();
        divide();
        showAllWorkers();
        showAllActiveWorkers();
        divide();
        divide();
    }

    private static void listsDetails() {
        showAllBorrowedBooks();
        showAllReturnedBooks();
        divide();
        showCentralLibraryBorrowedBooks();
        showLibraryA_BorrowedBooks();
        showLibraryB_BorrowedBooks();
        divide();
        divide();

        showBooksForSale();
        divide();
        showBooksSoled();
        showBooksGiveBackToStore();
        divide();
        divide();
    }

    private static void workersSchedule() {
        workerScheduleHelp1(Libraries.CENTRAL_LIBRARY);
        divide();
        workerScheduleHelp1(Libraries.LIBRARY_A);
        divide();
        workerScheduleHelp1(Libraries.LIBRARY_B);
    }

    private static void workerScheduleHelp1(Libraries library) {
        System.out.println(library + " :");
        if (library == Libraries.CENTRAL_LIBRARY) {
            workerScheduleHelp2("saturday", CentralLibrary.getInstance().getSaturday());
            workerScheduleHelp2("sunday", CentralLibrary.getInstance().getSunday());
            workerScheduleHelp2("monday", CentralLibrary.getInstance().getMonday());
            workerScheduleHelp2("tuesday", CentralLibrary.getInstance().getTuesday());
            workerScheduleHelp2("wednesday", CentralLibrary.getInstance().getWednesday());
            workerScheduleHelp2("thursday", CentralLibrary.getInstance().getThursday());
        } else if (library == Libraries.LIBRARY_A) {
            workerScheduleHelp2("saturday", LibraryA.getInstance().getSaturday());
            workerScheduleHelp2("sunday", LibraryA.getInstance().getSunday());
            workerScheduleHelp2("monday", LibraryA.getInstance().getMonday());
            workerScheduleHelp2("tuesday", LibraryA.getInstance().getTuesday());
            workerScheduleHelp2("wednesday", LibraryA.getInstance().getWednesday());
            workerScheduleHelp2("thursday", LibraryA.getInstance().getThursday());
        } else {
            workerScheduleHelp2("saturday", LibraryB.getInstance().getSaturday());
            workerScheduleHelp2("sunday", LibraryB.getInstance().getSunday());
            workerScheduleHelp2("monday", LibraryB.getInstance().getMonday());
            workerScheduleHelp2("tuesday", LibraryB.getInstance().getTuesday());
            workerScheduleHelp2("wednesday", LibraryB.getInstance().getWednesday());
            workerScheduleHelp2("thursday", LibraryB.getInstance().getThursday());
        }

    }

    private static void workerScheduleHelp2(String string, ArrayList<Employee> day) {
        System.out.println("* " + string + " :");
        for (Employee employee : day) {
            System.out.println(employee);
        }
        System.out.println();
    }

    private static void divide() {
        for (int i = 0; i <= 40; i++)
            System.out.print("-");
        System.out.println();
    }

    private static void showAllBorrowedBooks() {
        System.out.println("All borrowed books :");
        for (String string : CentralManagement.allBorrowedBooks.keySet()) {
            System.out.println(CentralManagement.allBorrowedBooks.get(string));
        }
    }

    private static void showAllReturnedBooks() {
        System.out.println("All returned books :");
        for (String string : CentralManagement.allReturnedBooks.keySet()) {
            System.out.println(CentralManagement.allReturnedBooks.get(string));
        }
    }

    private static void showCentralLibraryBorrowedBooks() {
        System.out.println("Main-Library borrowed books :");
        for (Book book : CentralLibrary.getInstance().getBorrowedBooks().keySet()) {
            System.out.println(book);
            System.out.println(CentralLibrary.getInstance().getBorrowedBooks().get(book));
            for (Person person : book.getBorrowers().keySet()) {
                System.out.println(person);
                System.out.println(book.getBorrowers().get(person));
            }
            System.out.println();
            System.out.println();
        }
    }

    private static void showLibraryA_BorrowedBooks() {
        System.out.println("LibraryA borrowed books :");
        for (Book book : LibraryA.getInstance().getBorrowedBooks().keySet()) {
            System.out.println(book);
            System.out.println(LibraryA.getInstance().getBorrowedBooks().get(book));
            for (Person person : book.getBorrowers().keySet()) {
                System.out.println(person);
                System.out.println(book.getBorrowers().get(person));
            }
            System.out.println();
            System.out.println();
        }
    }

    private static void showLibraryB_BorrowedBooks() {
        System.out.println("LibraryB borrowed books :");
        for (Book book : LibraryB.getInstance().getBorrowedBooks().keySet()) {
            System.out.println(book);
            System.out.println(LibraryB.getInstance().getBorrowedBooks().get(book));
            for (Person person : book.getBorrowers().keySet()) {
                System.out.println(person);
                System.out.println(book.getBorrowers().get(person));
            }
            System.out.println();
            System.out.println();
        }
    }

    private static void showBooksForSale() {
        System.out.println("Books for sale :");
        for (Book book : CentralLibrary.getInstance().getBooksForSale()) {
            System.out.println(book);
        }
    }

    private static void showBooksSoled() {
        System.out.println("Books soled: ");
        for (String string : CentralLibrary.getInstance().getBooksSold().keySet()) {
            System.out.println(CentralLibrary.getInstance().getBooksSold().get(string));
        }
    }

    private static void showBooksGiveBackToStore() {
        System.out.println("Books returned to store :");
        for (String string : CentralLibrary.getInstance().getBooksGiveBack().keySet()) {
            System.out.println(CentralLibrary.getInstance().getBooksGiveBack().get(string));
        }
    }

    private static void showAllExistingBook() {
        System.out.println("\nAll existing books are: ");
        for (Book book : CentralManagement.allBooksEverExist) {
            System.out.println(book);
        }
    }

    private static void showAllAvailableBooksInLibraries() {
        System.out.println("Books available in libraries: ");
        for (Book book : CentralManagement.allBooksInLibraries.keySet()) {
            System.out.println(book);
        }
    }

    private static void showBooksInCentralLibrary() {
        System.out.println("Books are in Central-Library: ");
        for (Book book : CentralLibrary.getInstance().getBooks().keySet()) {
            System.out.println("Name: " + book.getBookName() + " - ISBN: " + book.getISBN() +
                    " - Existing: " + book.getNumbersAvailable() +
                    " - In libraries: " + CentralLibrary.getInstance().getBooks().get(book) +
                    " - Book Library: " + book.getBookPlace());
        }
    }

    private static void showBooksInLibraryA() {
        System.out.println("Books are in Library-A: ");
        for (Book book : LibraryA.getInstance().getBooks().keySet()) {
            System.out.println("Name: " + book.getBookName() + " - ISBN: " + book.getISBN() +
                    " - Existing: " + book.getNumbersAvailable() +
                    " - In libraries: " + LibraryA.getInstance().getBooks().get(book) +
                    " - Book Library: " + book.getBookPlace());
        }
    }

    private static void showBooksInLibraryB() {
        System.out.println("Books are in Library-B: ");
        for (Book book : LibraryB.getInstance().getBooks().keySet()) {
            System.out.println("Name: " + book.getBookName() + " - ISBN: " + book.getISBN() +
                    " - Existing: " + book.getNumbersAvailable() +
                    " - In libraries: " + LibraryB.getInstance().getBooks().get(book) +
                    " - Book Library: " + book.getBookPlace());
        }
    }

    private static void showAllStudents() {
        System.out.println("All existing students are: ");
        for (Student student : CentralManagement.getAllStudents()) {
            System.out.println(student);
        }
    }

    private static void showAllActiveStudents() {
        System.out.println("Active students are: ");
        for (Student student : CentralManagement.allActiveStudents) {
            System.out.println(student);
        }
    }

    private static void showAllProfessors() {
        System.out.println("All existing professors are: ");
        for (Professor professor : CentralManagement.getAllProfessors()) {
            System.out.println(professor);
        }
    }

    private static void showAllActiveProfessors() {
        System.out.println("Active professors are: ");
        for (Professor professor : CentralManagement.allActiveProfessors) {
            System.out.println(professor);
        }
    }

    private static void showAllWorkers() {
        System.out.println("All existing employees are: ");
        for (Employee employee : CentralManagement.getAllEmployees()) {
            System.out.println(employee);
        }
    }

    private static void showAllActiveWorkers() {
        System.out.println("Active employees are: ");
        for (Employee employee : CentralManagement.allActiveEmployees) {
            System.out.println(employee);
        }
    }
}
