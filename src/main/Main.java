package main;

import model.Book;
import model.CentralLibrary;
import model.CentralManagement;
import view.*;

public class Main {
    public static void main(String[] args) {
        ConsoleViewIn.start();
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
            System.out.println(book);
        }
        /*
        String command = "  1932  /5/   4";
        String[] help = command.split("/");
        int year = Integer.parseInt(help[0].trim());
        int month = Integer.parseInt(help[1].trim());
        int day = Integer.parseInt(help[2].trim());

        System.out.println(year + ".");
        System.out.println(month + ".");
        System.out.println(day + ".");

        create book book1 177 1397 ali persian 123456789 10000
        You have successfully added one of this book.
        7-Successfully the book has been created.
        create book book1 177 1397 ali persian 123456789 10000
        You have successfully added one of this book.
        7-Successfully the book has been created.
        add book book1,123456789,1397
        INVALID COMMAND! PLEASE TRY AGAIN!
        add book a book1,123456789,1397

         */
    }
}
