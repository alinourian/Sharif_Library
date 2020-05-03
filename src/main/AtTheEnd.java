package main;

import controller.LibrariesController;
import model.*;

public class AtTheEnd {
    public static void run() {
        // BOOKS
        System.out.println("\nAll existing books are: ");
        for (Book book : CentralManagement.allBooksEverExist) {
            System.out.println(book);
        }
        System.out.println("\nBooks available in libraries: ");
        for (Book book : CentralManagement.allBooksInLibraries.keySet()) {
            System.out.println(book);
        }
        System.out.println("\nBooks are in Central-Library: ");
        for (Book book : CentralLibrary.getInstance().getBooks().keySet()) {
            System.out.println("Name: " + book.getBookName() + " - ISBN: " + book.getISBN() +
                    " - Existing: " + book.getNumbersAvailable() +
                    " - In libraries: " + CentralLibrary.getInstance().getBooks().get(book) +
                    " - Book Library: " + book.getBookPlace());
        }
        System.out.println("\nBooks are in Library-A: ");
        for (Book book : LibraryA.getInstance().getBooks().keySet()) {
            System.out.println("Name: " + book.getBookName() + " - ISBN: " + book.getISBN() +
                    " - Existing: " + book.getNumbersAvailable() +
                    " - In libraries: " + LibraryA.getInstance().getBooks().get(book) +
                    " - Book Library: " + book.getBookPlace());
        }
        System.out.println("\nBooks are in Library-B: ");
        for (Book book : LibraryB.getInstance().getBooks().keySet()) {
            System.out.println("Name: " + book.getBookName() + " - ISBN: " + book.getISBN() +
                    " - Existing: " + book.getNumbersAvailable() +
                    " - In libraries: " + LibraryB.getInstance().getBooks().get(book) +
                    " - Book Library: " + book.getBookPlace());
        }
        // END BOOKS
        for (int i = 0; i <= 20; i++)
            System.out.print("-");
        System.out.println();
        // STUDENTS
        System.out.println("\nAll existing students are: ");
        for (Student student : LibrariesController.getInstance().getAllStudents()) {
            System.out.println(student);
        }
        System.out.println("\nActive students are: ");
        for (Student student : CentralManagement.allActiveStudents) {
            System.out.println(student);
        }
        for (int i = 0; i <= 20; i++)
            System.out.print("-");
        System.out.println();
        // PROFESSOR
        System.out.println("\nAll existing professors are: ");
        for (Professor professor : LibrariesController.getInstance().getAllProfessors()) {
            System.out.println(professor);
        }
        System.out.println("\nActive professors are: ");
        for (Professor professor : CentralManagement.allActiveProfessors) {
            System.out.println(professor);
        }
        for (int i = 0; i <= 20; i++)
            System.out.print("-");
        System.out.println();
        // EMPLOYEE
        System.out.println("\nAll existing employees are: ");
        for (Employee employee : LibrariesController.getInstance().getAllEmployees()) {
            System.out.println(employee);
        }
        System.out.println("\nActive employees are: ");
        for (Employee employee : CentralManagement.allActiveEmployees) {
            System.out.println(employee);
        }
        for (int i = 0; i <= 20; i++)
            System.out.print("-");
        System.out.println();
    }
}
