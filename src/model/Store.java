package model;

import enums.Libraries;
import enums.Type;

import java.util.HashMap;

public abstract class Store {
    private static HashMap<Book, Integer> booksForSale = new HashMap<>();

    public static void sellBook(Book book) {
        booksForSale.replace(book, booksForSale.get(book) - 1);
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
        return code.equals(CentralLibrary.getInstance().getDiscountCode());
    }

    public static void giveBackBook(Book book) {
        booksForSale.replace(book, booksForSale.get(book) - 1);
    }

    public static HashMap<Book, Integer> getBooksForSale() {
        return booksForSale;
    }
}
