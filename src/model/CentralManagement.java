package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CentralManagement {
    public static List<Book> allBooksEverExist = new ArrayList<>();
    public static List<Employee> allActiveEmployees = new ArrayList<>();
    public static List<Professor> allActiveProfessors = new ArrayList<>();
    public static List<Student> allActiveStudents = new ArrayList<>();
    public static Map<Book, Integer> allBooksInLibraries = new HashMap<>();
    public static Map<Book, String> allBorrowedBooks = new HashMap<>();//just been used in controller
    public static Map<Book, String> allReturnedBooks = new HashMap<>();//just been used in controller

    public static Book searchBook(Book book) {
        for(Book test : CentralManagement.allBooksEverExist) {
            if(test.getISBN() == book.getISBN())  {
                if(test.getPublishedYear() == book.getPublishedYear()
                        && test.getBookName().equals(book.getBookName())) {
                    return test;
                }
            }
        }
        return null;
    }
}
