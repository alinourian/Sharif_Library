package model;

import enums.Libraries;
import enums.Type;

public abstract class Store {
    public static void sellBook(Book book) {
        if (book.getBookPlace() == Libraries.CENTRAL_LIBRARY) {
            CentralLibrary.getInstance().setNumbersOfBooks(CentralLibrary.getInstance().getNumbersOfBooks() - 1);
            CentralLibrary.getInstance().getBooks().replace(book, CentralLibrary.getInstance().getBooks().get(book) - 1);
        } else if (book.getBookPlace() == Libraries.LIBRARY_A) {
            LibraryA.getInstance().setNumbersOfBooks(LibraryA.getInstance().getNumbersOfBooks() - 1);
            LibraryA.getInstance().getBooks().replace(book, LibraryA.getInstance().getBooks().get(book) - 1);
        } else { //LibraryB
            LibraryB.getInstance().setNumbersOfBooks(LibraryB.getInstance().getNumbersOfBooks() - 1);
            LibraryB.getInstance().getBooks().replace(book, LibraryB.getInstance().getBooks().get(book) - 1);
        }
        CentralManagement.allBooksInLibraries.replace(book, CentralManagement.allBooksInLibraries.get(book) - 1);
    }

    public static boolean pay(Person person, int price) {
        if (person.getType() == Type.PROFESSOR) {
            Professor professor = (Professor)person;
            if (professor.getBudget() - price <= -100000) return false;
            professor.fine(price);
        } else { //student
            Student student = (Student)person;
            if (student.getBudget() - price <= -100000) return false;
            student.fine(price);
        }
        return true;
    }

    public static boolean checkDiscountCode(String code) {
        if (code.equals(CentralLibrary.getInstance().getDiscountCode()))
            return true;
        else
            return false;
    }


    public static void giveBackBook() {
        return;
    }
}
