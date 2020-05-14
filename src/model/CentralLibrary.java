package model;

import controller.LibrariesController;
import controller.MyDate;
import enums.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CentralLibrary implements Library {

    private static CentralLibrary instance;

    private int numbersOfBooks = 0;
    private int numbersOfEmployee = 0;
    private List<Employee> libraryEmployees;
    private List<Employee> storeEmployees;
    private Map<Book, Integer> books;
    private Map<Book, Integer> borrowedBooks;

    //store properties...
    private String discountCode;
    private int discountPercent;
    private ArrayList<Book> booksForSell;
    private HashMap<String, String> booksSold;//used in controller
    private HashMap<String, String> booksGiveBack;// ...
    //....

    public static CentralLibrary getInstance() {
        if(instance == null) {
            instance = new CentralLibrary();
        }
        return instance;
    }

    private CentralLibrary() {
        storeEmployees = new ArrayList<>();
        libraryEmployees = new ArrayList<>();
        books = new HashMap<>();
        borrowedBooks = new HashMap<>();
        discountCode = "NoDiscount";
        discountPercent = 0;
        booksForSell = new ArrayList<>();
        booksSold = new HashMap<>();
        booksGiveBack = new HashMap<>();
    }

    @Override
    public Book search(Book book) {
        for(Book test : CentralManagement.allBooksInLibraries.keySet()) {
            if(test.getISBN() == book.getISBN())  {
                if(test.getPublishedYear() == book.getPublishedYear()) {
                    return test;
                }
            }
        }
        return null;
    }

    @Override
    public AddBook addBook(Book book) {
        if (numbersOfBooks == MAX_BOOKS) {
            return AddBook.LIBRARY_IS_FULL;
        }
        Book test = CentralManagement.searchBookInAllBooks(book);
        if (test == null) {
            return AddBook.BOOK_NEVER_EXIST;//This book dose not ever exist!
        } else if (test.getBookPlace() == Libraries.NO_WHERE_YET) {
            books.put(test, 1);
            CentralManagement.allBooksInLibraries.put(test, 1);
            numbersOfBooks++;
            test.setBookPlace(Libraries.CENTRAL_LIBRARY);
            return AddBook.NEW_ADDED_SUCCESSFULLY;//New book has successfully added to this library.
        } else if (test.getBookPlace() == Libraries.CENTRAL_LIBRARY) {
            if (test.getNumbersAvailable() == books.get(test)) {
                return AddBook.NO_OTHER_BOOK_TO_ADD;
            }
            books.replace(test, books.get(test)+1);
            CentralManagement.allBooksInLibraries.replace(test, books.get(test)+1);
            numbersOfBooks++;
            return AddBook.ADDED_SUCCESSFULLY;//The book has successfully added to this library.
        } else {
            return AddBook.BELONG_TO_ANOTHER_LIBRARY;//This book belongs to the another library!
        }
    }

    @Override
    public boolean borrowBook(Book book) {
        Book test = search(book);
        if (test == null || books.get(test) == 0) {
            return false;//the book does not exist!
        }else {
            books.replace(test, books.get(test)-1);
            CentralManagement.allBooksInLibraries.replace(test, books.get(test)-1);
            numbersOfBooks--;
            if (borrowedBooks.get(test) == null)
                borrowedBooks.put(test, 1);
            else
                borrowedBooks.replace(test, borrowedBooks.get(test)+1);
            return true;
        }
    }

    @Override
    public void returnBook(Book book) {
        books.replace(book, books.get(book)+1);
        CentralManagement.allBooksInLibraries.replace(book, books.get(book)+1);
        borrowedBooks.replace(book, borrowedBooks.get(book)-1);
        if (borrowedBooks.get(book) == 0) {
            borrowedBooks.remove(book);
        }
        numbersOfBooks++;
    }

    @Override
    public void addEmployee(Employee employee) {
        addEmployee(employee, "library");
    }

    public void addEmployee(Employee employee, String workplace) {
        if (workplace.equalsIgnoreCase("library")) {
            libraryEmployees.add(employee);
        }else if (workplace.equalsIgnoreCase("store")) {
            storeEmployees.add(employee);
        }
        numbersOfEmployee++;
        CentralManagement.allActiveEmployees.add(employee);
    }

    @Override
    public void changeEmployeeSchedule(long nationalCode, List<WeekDays> newSchedule) {
        for (Employee libraryEmployee : libraryEmployees) {
            if (libraryEmployee.nationalCode == nationalCode) {
                libraryEmployee.updateWorkingDays(newSchedule);
                return;
            }
        }
        for (Employee storeEmployee : storeEmployees) {
            if (storeEmployee.nationalCode == nationalCode) {
                storeEmployee.updateWorkingDays(newSchedule);
                return;
            }
        }
    }

    @Override
    public void setFineForDelay(MyDate currentDay, int addDay) {
        for (Book book : borrowedBooks.keySet()) {
            for (Person person : book.getBorrowers().keySet()) {
                if (LibrariesController.getInstance().datePass(currentDay, book.getBorrowers().get(person))) {
                    int dayPassed;
                    dayPassed = LibrariesController.getInstance().daysPassed(currentDay, book.getBorrowers().get(person));
                    long fine = addDay > dayPassed ? dayPassed * FINE : addDay * FINE;
                    if (person.getType() == Type.STUDENT) {
                        Student student = (Student)person;
                        student.fine(fine);
                    } else if (person.getType() == Type.PROFESSOR) {
                        Professor professor = (Professor)person;
                        professor.fine(fine);
                    }
                }
            }
        }
    }

    public void addMember(Person person) {
        if (person.getType() == Type.PROFESSOR) {
            Professor professor = (Professor)person;
            CentralManagement.allActiveProfessors.add(professor);
        } else if (person.getType() == Type.STUDENT) {
            Student student = (Student)person;
            CentralManagement.allActiveStudents.add(student);
        }
    }

    public void addToStore(Book book) {
        booksForSell.add(book);
    }

    public void setDiscountCode(String code, int percent) {
        this.discountCode = code;
        this.discountPercent = percent;
    }

    public int sellBook(Book book, boolean bool) {
        int price;
        if (bool) {// disount
            price = book.getPrice() * (100 - discountPercent) / 100;
        } else {
            price = book.getPrice();
        }
        Store.sellBook(book);
        return price;
    }

    public void giveBackBook() {
        return;
    }

    public List<Employee> getLibraryEmployees() {
        return libraryEmployees;
    }

    public Map<Book, Integer> getBooks() {
        return books;
    }

    public int getNumbersOfEmployee() {
        return numbersOfEmployee;
    }

    public Map<Book, Integer> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public ArrayList<Book> getBooksForSell() {
        return booksForSell;
    }

    public HashMap<String, String> getBooksSold() {
        return booksSold;
    }

    public HashMap<String, String> getBooksGiveBack() {
        return booksGiveBack;
    }

    public int getNumbersOfBooks() {
        return numbersOfBooks;
    }

    public void setNumbersOfBooks(int numbersOfBooks) {
        this.numbersOfBooks = numbersOfBooks;
    }
}
